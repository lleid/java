package com.leo.io;

import java.io.*;

public class ReaderTest {
    public static void main(String[] args) throws IOException {
        File file = new File("c:\\test\\a.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        Writer out = new FileWriter(file, true);
        out.write("\n你好");
        out.write("123");
        out.close();

        Reader in = new FileReader(file);
        char[] chars = new char[1024];
        int len = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while ((len = in.read(chars)) != -1) {
            stringBuffer.append(chars);
        }
        System.out.println(stringBuffer.toString());
        in.close();
    }
}
