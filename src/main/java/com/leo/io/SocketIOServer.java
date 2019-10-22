package com.leo.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * java1.4 BIO=Block IO=同步阻塞IO
 * 阻塞：单线程环境下，如果IO操作没有完成的话，当前线程不能做其他事情
 * 非阻塞：单线程环境下，如果IO操作没有完成的话，当前线程可以进行其他操作
 */
public class SocketIOServer {
    public static void server1() {
        ServerSocket server = null;
        InputStream in = null;
        OutputStream out = null;
        try {
            server = new ServerSocket(8000);
            System.out.println("服务端启动成功，监听端口为8000，等待客户端连接...");
            Socket socket = server.accept();
            in = socket.getInputStream();
            out = socket.getOutputStream();

            StringBuffer stringBuffer = new StringBuffer();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes)) > 0) {
                stringBuffer.append(new String(bytes, 0, len));
            }
            out.write("hello world".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void server2() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(8000);
            System.out.println("服务端启动成功，监听端口为8000，等待客户端连接...");
            while (true) {
                Socket socket = server.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void server3() {
        ServerSocket server = null;
        ExecutorService executorService = Executors.newFixedThreadPool(60);//线程池
        try {
            server = new ServerSocket(8000);
            System.out.println("服务端启动成功，监听端口为8000，等待客户端连接...");
            while (true) {
                Socket socket = server.accept();
                executorService.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        server1();
    }
}
