package com.leo.io;

import com.sun.org.apache.xpath.internal.operations.String;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8000);

        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        byte[] bytes = new byte[1024];
        int len;
        StringBuffer stringBuffer = new StringBuffer();
        while ((len = in.read(bytes)) > 0) {
            stringBuffer.append(new java.lang.String(bytes, 0, len));
        }

        out.write("lalala".getBytes());
        out.close();
    }
}
