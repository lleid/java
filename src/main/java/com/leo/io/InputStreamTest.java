package com.leo.io;

import java.io.*;

public class InputStreamTest {
    public static void main(String[] args) throws IOException {
        File file = new File("c:\\test\\a.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

//        FileOutputStream out = new FileOutputStream(file);
//        FileOutputStream out = new FileOutputStream(file, true);//是否追加
//        out.write("\n你好".getBytes());
//        out.write("123".getBytes());InputStreamTest
//        out.close();

        FileInputStream in = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = in.read(bytes)) != -1) {
            System.out.println(new String(bytes));
        }
        in.close();
    }
}
