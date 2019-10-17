package com.leo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.Set;

/**
 * nio 实现SocketClient
 */
public class SocketNIOClient {

    //服务端地址
    private InetSocketAddress SERVER;
    //字符集用于编解码
    private Charset charset = Charset.forName("UTF-8");
    //用于接收数据缓冲区
    private ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    //用于发送数据缓冲区
    private ByteBuffer sBuffer = ByteBuffer.allocate(1024);
    //用户监听事件通道
    private static Selector selector;

    public SocketNIOClient(int port) {
        SERVER = new InetSocketAddress(port);
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化客户端
     *
     * @throws IOException
     */
    private void init() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(SERVER);

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(key -> handle(key));
            selectionKeys.clear();
        }
    }

    public void handle(SelectionKey selectionKey) {
        try {
            if (selectionKey.isConnectable()) {//连接准备就绪
                SocketChannel client = (SocketChannel) selectionKey.channel();
                if (client.isConnectionPending()) {
                    client.finishConnect();
                    System.out.println("连接成功");

                    new Thread(() -> {
                        while (true) {
                            try {
                                sBuffer.clear();
                                Scanner scanner = new Scanner(System.in);
                                String sendText = scanner.nextLine();
                                System.out.println(sendText);

                                sBuffer.put(charset.encode(sendText));
                                sBuffer.flip();
                                client.write(sBuffer);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();

                    client.register(selector, SelectionKey.OP_READ);
                }
            } else if (selectionKey.isReadable()) {//可读事件，有从服务端发送过来的消息，读取输入到屏幕上
                SocketChannel client = (SocketChannel) selectionKey.channel();
                rBuffer.clear();
                int count = client.read(rBuffer);
                if (count > 0) {
                    String receiveText = new String(rBuffer.array(), 0, count);
                    System.out.println(receiveText);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new SocketNIOClient(7777);
    }
}
