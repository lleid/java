package com.leo.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * ObjectOutputStream 子类，如果内容不为空的时候，不添加StreamHeader
 */
public class ObjectOutputStreamNoHeader extends ObjectOutputStream {


    public ObjectOutputStreamNoHeader(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
    }
}