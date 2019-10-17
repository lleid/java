package com.leo.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

@SuppressWarnings("all")
public class FileChannelTest {
    public static void main(String[] args) throws IOException {
//        write("b.txt");
//        read("b.txt");
//
//        copy("b.txt", "c.txt");
//        copy2("b.txt", "c.txt");
//
//        for (int i = 0; i < 5; i++) {
//            new Thread(() -> {
//                try {
//                    lock();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }

        map();
    }

    public static void read(String path) throws IOException {
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        FileChannel fileChannel = file.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int byteRead = fileChannel.read(buffer);
        while (byteRead != -1) {
            System.out.println("Read :" + byteRead);
            buffer.flip();

            while (buffer.hasRemaining()) {
                System.out.println((char) buffer.get());
            }

            buffer.clear();
            byteRead = fileChannel.read(buffer);
        }

        file.close();
    }

    public static void write(String path) throws IOException {
        RandomAccessFile file = new RandomAccessFile(path, "rw");
        FileChannel fileChannel = file.getChannel();

        String str = "我爱你中国";

        fileChannel.write(ByteBuffer.wrap(str.getBytes()));
        file.close();
    }

    public static void copy(String path1, String path2) throws IOException {
        FileChannel inChannel = new RandomAccessFile(path1, "rw").getChannel();
        FileChannel outChannel = new RandomAccessFile(path2, "rw").getChannel();

        outChannel.transferFrom(inChannel, 0, inChannel.size());
//        inChannel.transferTo(0, inChannel.size(), outChannel);

        inChannel.close();
        outChannel.close();
    }


    public static void copy2(String path1, String path2) throws IOException {
        FileChannel inChannel = new RandomAccessFile(path1, "rw").getChannel();
        FileChannel outChannel = new RandomAccessFile(path2, "rw").getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            buffer.clear();
            int len = inChannel.read(buffer);
            if (len == -1) {
                break;
            }

            buffer.flip();
            outChannel.write(buffer);
        }
    }

    /**
     * FileLock每次只能有一个channel获得锁，其他线程抛OverlappingFileLockException异常
     *
     * @throws IOException
     */
    public static void lock() throws IOException {
        FileChannel channel = new RandomAccessFile("b.txt", "rw").getChannel();
        System.out.println(Thread.currentThread().getName() + " trying to get lock");
        int start = 10, end = 20;
        FileLock lock = null;

        try {
            lock = channel.tryLock(start, end, false);

            System.out.println(Thread.currentThread().getName() + " got lock");

            System.out.println(Thread.currentThread().getName() + " pasuing");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " going to release lock");
            lock.release();
            System.out.println(Thread.currentThread().getName() + " release lock");
            channel.close();
        } catch (OverlappingFileLockException e) {

        }
    }

    /**
     * MappedByteBuffer 直接内存，速度更快
     *
     * @throws IOException
     */
    public static void map() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("b.txt", "rw");
        FileChannel channel = raf.getChannel();

        int stat = 0;
        int end = 10;
        MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, stat, end);

        System.out.println((char) buffer.get(0));
        System.out.println((char) buffer.get(9));

        raf.close();
    }
}
