package com.leo;

import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc -Xms20M -Xmx20M -XX:+HeapDumpOnOutOfMemoryError
 *  代码限制堆的大小为20M，不可扩展
 */
public class HeapOOM {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        while (true) {
            list.add("a");
        }
    }
}
