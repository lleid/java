package com.leo.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 自定义 加密工具类
 */
public class FileUtils {

    public static void test(String path) {
        File file = new File(path);

        try {
            FileInputStream in = new FileInputStream(file);
            FileOutputStream out = new FileOutputStream(path + "en");

            int b = 0;
            while ((b = in.read()) != -1) {
                out.write(b ^ 2);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        test("c:\\lib\\Speaker.class");
    }
}
