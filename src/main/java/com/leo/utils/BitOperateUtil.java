package com.leo.utils;

/**
 * & 与运算，两个位都是1时，结果位1，否则为0
 * | 或运算，两个位都是0时，结果为0，否则为1
 * ^ 异或运算，两个位相同为0，不同为1
 * ~ 取反，0变成1，1变成0
 * << 左移运算，向左进行移位操作，高位丢弃，低位补0
 * >> 右移运算，向右进行移位操作，对无符号数，高位补0，对于有符号数，高位补符号位
 */
@SuppressWarnings("all")
public class BitOperateUtil {
    public static void main(String[] args) {
        int src = 8;
        int tar = bytes2Int(int2Bytes(src));
        System.out.println("src=" + src + ",tar=" + tar);

        System.out.println("左移一位相当于*2, 8 << 1 =" + (8 << 1));
        System.out.println("右移一位相当于/2, 8 >> 1 =" + (8 >> 1));

        int s1 = 5, s2 = 7;
        swap(s1, s2);

        System.out.println(Integer.toBinaryString(8));
    }


    /**
     * int转byte数组
     *
     * @param i
     * @return
     */
    public static byte[] int2Bytes(int i) {
        byte[] bytes = new byte[4];

        bytes[0] = (byte) i;
        bytes[1] = (byte) (i >> 8);
        bytes[2] = (byte) (i >> 16);
        bytes[3] = (byte) (i >> 24);
        return bytes;
    }


    /**
     * byte数组转int
     *
     * @param i
     * @return
     */
    public static int bytes2Int(byte[] bytes) {
        int i0 = bytes[0] & 0xFF;
        int i1 = (bytes[1] & 0xFF) << 8;
        int i2 = (bytes[2] & 0xFF) << 16;
        int i3 = (bytes[3] & 0xFF) << 24;

        return i0 | i1 | i2 | i3;
    }

    /**
     * 判断奇偶数，偶数返回true,奇数返回false
     *
     * @param a
     * @return
     */
    public static boolean oddEven(int a) {
        return (a & 1) == 0;
    }

    /**
     * 位操作交换符号，正数变负数，负数变正数
     *
     * @param a
     * @return
     */
    public static int reversal(int a) {
        return ~a + 1;
    }

    /**
     * 绝对值
     *
     * @param a
     * @return
     */
    public static int abs(int a) {
        int i = a >> 31;
        return a == 0 ? a : (~a + 1);
    }

    /**
     * 交换
     *
     * @param a
     * @param b
     */
    public static void swap(int a, int b) {
        System.out.println("swap 之前 ，a=" + a + ",b=" + b);
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println("swap 之后 ，a=" + a + ",b=" + b);
    }

    /**
     * 高低位切换 10000110 11011000
     * 切换成     11011000 10000110
     *
     * @param a
     */
    public static void highLowSwap(int a) {
        a = (a << 8) | (a >> 8);
    }

    /**
     * 查询二进制中1的个数
     *
     * @param a
     * @return
     */
    public static int count1ByBinary(int a) {
        int count = 0;
        while (a != 0) {
            a = a & (a - 1);
            count++;
        }
        return count;
    }
}
