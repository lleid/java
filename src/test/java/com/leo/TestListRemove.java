package com.leo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestListRemove {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");

        //可以删除
//       for (int i = 0; i < list.size(); i++) {
//            if (list.get(i).equals("d")) {
//                list.remove(i);
//            }
//            if (list.get(i).equals("e")) {
//                list.remove(i);
//            }
//        }

        //不可删除，ConcurrentModificationException
//       for (String str : list) {
//            if (str.equals("b")) {
//                list.remove(str);
//            }
//        }

        //不可删除 IndexOutOfBoundsException
//        int len = list.size();
//        for (int i = 0; i < len; i++) {
//            if (list.get(i).equals("d")) {
//                list.remove(i);
//            }
//            if (list.get(i).equals("e")) {
//                list.remove(i);
//            }
//        }


        //不可删除，ConcurrentModificationException
        for (Iterator<String> item = list.iterator(); item.hasNext(); ) {
            String str = item.next();
            if (str.equals("b")) {
                list.remove(str);
            }
        }


        //可删除
//        for (Iterator<String> item = list.iterator(); item.hasNext(); ) {
//            String str = item.next();
//            if (str.equals("b")) {
//                item.remove();
//            }
//        }


        System.out.println(list);
    }
}
