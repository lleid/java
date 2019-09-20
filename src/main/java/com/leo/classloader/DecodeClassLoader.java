package com.leo.classloader;

import java.io.*;

/**
 * 自定义class loader 解密
 */
public class DecodeClassLoader extends ClassLoader {

    //class 指定class的位置
    private String libPath;

    public DecodeClassLoader(String path) {
        this.libPath = path;
    }

    /**
     * @param name 文件名
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String filenName = getFileName(name);

        File file = new File(libPath, filenName);
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            int len = 0;
            while ((len = in.read()) != -1) {
                out.write(len ^ 2);
            }

            byte[] data = out.toByteArray();

            return defineClass(name, data, 0, data.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private String getFileName(String name) {
        int index = name.lastIndexOf(".");
        if (index == -1) {
            return name + ".classen";
        }
        return name.substring(index + 1) + ".classen";
    }
}
