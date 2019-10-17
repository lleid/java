package com.leo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * nio 实现SocketServer
 */
public class SocketNIOServer {

    private int port = 8888;
    //用于字符集编解码
    private Charset charset = Charset.forName("UTF-8");
    //用于接受数据的缓冲区
    private ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    //用于发送数据的缓冲区
    private ByteBuffer sBuffer = ByteBuffer.allocate(1024);
    //用户存放客户端Socket Channel的集合
    private Map<String, SocketChannel> clientMap = new HashMap();
    //用户监听通道事件，多路复用器
    private static Selector selector;

    public SocketNIOServer(int port) {
        this.port = port;
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化服务配置
     *
     * @throws IOException
     */
    private void init() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(port));

        selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器启动，端口为:" + port);
    }

    /**
     * 服务器端轮询监听，select 方法会一直阻塞直到有相关事件发生或超时
     */
    public void listen() {
        while (true) {
            try {
                selector.select();//返回值为本次触发的事件数
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    handle(selectionKey);
                });
                selectionKeys.clear();//清除处理过的事件
            } catch (Exception e) {

            }
        }
    }

    /**
     * 处理事件
     *
     * @param selectionKey
     */
    private void handle(SelectionKey selectionKey) {
        try {
            if (selectionKey.isAcceptable()) {//有客户端要连接
                ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                SocketChannel client = server.accept();
                client.configureBlocking(false);
                client.register(selector, SelectionKey.OP_READ);
                clientMap.put(getClientName(client), client);
            } else if (selectionKey.isReadable()) {//客户端发送了数据
                SocketChannel client = (SocketChannel) selectionKey.channel();
                rBuffer.clear();

                int bytes = client.read(rBuffer);
                if (bytes > 0) {
                    rBuffer.flip();
                    String receiveText = String.valueOf(charset.decode(rBuffer));
                    System.out.println(client.toString() + ":" + receiveText);
                    dipatch(client, receiveText);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 转发消息给各个客户端
     *
     * @param client
     * @param info
     * @throws IOException
     */
    public void dipatch(SocketChannel client, String info) {
        if (!clientMap.isEmpty()) {
            clientMap.forEach((k, v) -> {
                if (!client.equals(v)) {
                    sBuffer.clear();
                    sBuffer.put(charset.encode(getClientName(client) + ":" + info));
                    sBuffer.flip();
                    try {
                        v.write(sBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    /**
     * 生成客户端名字
     *
     * @param client
     * @return
     */
    private String getClientName(SocketChannel client) {
        Socket socket = client.socket();
        return "[" + socket.getInetAddress().toString().substring(1) + ":" + Integer.toHexString(client.hashCode()) + "]";
    }

    public static void main(String[] args) throws IOException {
        SocketNIOServer server = new SocketNIOServer(9999);
        server.listen();
    }
}
