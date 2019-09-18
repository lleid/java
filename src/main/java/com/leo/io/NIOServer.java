package com.leo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    private int port = 8000;
    private InetSocketAddress address = null;
    private Selector selector;

    public NIOServer(int port) {
        try {
            this.port = port;
            address = new InetSocketAddress(port);
            ServerSocketChannel server = ServerSocketChannel.open();
            server.socket().bind(address);
            server.configureBlocking(false);

            selector = Selector.open();
            server.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("服务器启动成功：" + this.port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            //轮询
            while (true) {
                int wait = this.selector.select();
                if (wait == 0) {
                    continue;
                }
                Set<SelectionKey> keys = this.selector.selectedKeys();
                Iterator<SelectionKey> i = keys.iterator();
                while (i.hasNext()) {
                    SelectionKey key = i.next();
                    process(key);
                    i.remove();
                }
            }
        } catch (Exception e) {
        }
    }

    public void process(SelectionKey key) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        if (key.isAcceptable()) {
            ServerSocketChannel server = (ServerSocketChannel) key.channel();
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel client = (SocketChannel) key.channel();
            int len = client.read(buffer);
            if (len > 0) {
                buffer.flip();
                String content = new String(buffer.array(), 0, len);
                client.register(selector, SelectionKey.OP_WRITE);
                System.out.println(content);
            }
            buffer.clear();
        } else if (key.isWritable()) {
            SocketChannel client = (SocketChannel) key.channel();
            client.write(buffer.wrap("hello world".getBytes()));
            client.close();
        }
    }

    public static void main(String[] args) {
        new NIOServer(8000).listen();
    }
}
