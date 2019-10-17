package com.leo.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

public class CharsetTest {
    public static void main(String[] args) throws IOException {
        String inputFile = "b.txt";
        String outFile = "c.txt";

        RandomAccessFile inf = new RandomAccessFile(inputFile, "rw");
        RandomAccessFile outf = new RandomAccessFile(outFile, "rw");
        long inputLength = new File(inputFile).length();

        FileChannel inc = inf.getChannel();
        FileChannel outc = outf.getChannel();

        MappedByteBuffer inputData = inc.map(FileChannel.MapMode.READ_WRITE, 0, inputLength);

        Charset latin1 = Charset.forName("ISO-8859-1");

        CharsetDecoder decode = latin1.newDecoder();
        CharsetEncoder encode = latin1.newEncoder();

        CharBuffer cb = decode.decode(inputData);

        ByteBuffer outputData = encode.encode(cb);
        outc.write(outputData);

        inf.close();
        outf.close();
    }
}
