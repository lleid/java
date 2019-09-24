package com.leo.io;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class CopyTest {

    public static void copyByFileStreams(File source, File dest) throws IOException {
        FileInputStream in = new FileInputStream(source);
        FileOutputStream out = new FileOutputStream(dest);

        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        in.close();
        out.close();
    }


    public static void copyByFileChannel(File source, File dest) throws IOException {
        FileChannel in = new FileInputStream(source).getChannel();
        FileChannel out = new FileOutputStream(dest).getChannel();

        out.transferFrom(in, 0, in.size());
        in.close();
        out.close();
    }


    public static void copyFiles(File source, File dest) throws IOException {
        Files.copy(source.toPath(), dest.toPath());
    }

    public static void copyFileUtils(File source, File dest)
            throws IOException {
        FileUtils.copyFile(source, dest);
    }

    public static void main(String[] args) throws IOException {
        File file1 = new File("c:\\test\\a.txt");
        File file2 = new File("c:\\test\\b.txt");

        if (file2.exists()) {
            file2.delete();
        }
//        copyByFileStreams(file1, file2);
//        copyByFileChannel(file1, file2);
//        copyFiles(file1, file2);
        copyFileUtils(file1, file2);


    }
}
