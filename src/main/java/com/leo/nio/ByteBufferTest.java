package com.leo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@SuppressWarnings("all")
public class ByteBufferTest {
    public static void main(String[] args) throws IOException {
//        read1();
//        read2();
        read3();
//        create1();
//        create2();
    }

    public static void read1() throws IOException {
        RandomAccessFile file = new RandomAccessFile("b.txt", "rw");
        FileChannel fileChannel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int byteRead = fileChannel.read(buffer);
        while (byteRead != -1) {

            System.out.println("postion :" + buffer.position());
            System.out.println("limit :" + buffer.limit());//写模式下limit=capacity，表示最多可写
            System.out.println("capacity :" + buffer.capacity());

            buffer.flip();//写模式切换读模式

            System.out.println("flip postion :" + buffer.position());
            System.out.println("flip limit :" + buffer.limit());//读模式下limit=position,position=0，表示最多可读
            System.out.println("flip capacity :" + buffer.capacity());

            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }

            buffer.clear();
            byteRead = fileChannel.read(buffer);
        }

        file.close();
    }

    /**
     * 缓冲区和子缓冲区共享同一个底层数据数组
     * slice 获取position到limit之间的组成子缓冲区
     */
    public static void read2() {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        buffer.position(3);
        buffer.limit(7);

        ByteBuffer slice = buffer.slice();//获取position到limit之间的数据

        System.out.println(buffer.toString());
        System.out.println(slice.toString());

        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);

            b *= 11;
            slice.put(i, b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());
        System.out.println(buffer.toString());

        while (buffer.remaining() > 0) {
            System.out.println(buffer.get());
        }
    }


    public static void read3() {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(30);
        buffer.putLong(7000000000000L);
        buffer.putDouble(Math.PI);

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
    }

    /**
     * 通过allocate(int) 创建ByteBuffer
     */
    public static void create1() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put((byte) 'a');
        buffer.put((byte) 'b');
        buffer.put((byte) 'c');

        System.out.println("position =" + buffer.position());
        System.out.println("capacity =" + buffer.capacity());
        buffer.flip();
        System.out.println("position =" + buffer.position());
        System.out.println("capacity =" + buffer.capacity());

        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
    }

    /**
     * 通过wrap(byte[]) 创建ByteBuffer
     */
    public static void create2() {
        byte[] bytes = new byte[1024];
        ByteBuffer buffer = ByteBuffer.wrap(bytes);

        buffer.put((byte) 'a');
        buffer.put((byte) 'b');
        buffer.put((byte) 'c');

        System.out.println("position =" + buffer.position());
        System.out.println("capacity =" + buffer.capacity());
        buffer.flip();
        System.out.println("position =" + buffer.position());
        System.out.println("capacity =" + buffer.capacity());

        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
    }
}
