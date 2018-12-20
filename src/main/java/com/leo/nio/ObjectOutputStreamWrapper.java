package com.leo.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * ObjectOutputStream 包装器，如果内容不为空的时候，不添加StreamHeader
 */
public class ObjectOutputStreamWrapper {

    public static ObjectOutputStream stream(File file) throws IOException {
        ObjectOutputStream out = null;
        FileOutputStream fout = new FileOutputStream(file, true);
        if (file.length() < 1) {
            return new ObjectOutputStream(fout);
        } else {
            return new ObjectOutputStreamNoHeader(fout);
        }
    }
}
