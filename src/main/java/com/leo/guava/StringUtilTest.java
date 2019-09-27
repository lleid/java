package com.leo.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

public class StringUtilTest {
    public static void main(String[] args) {
        // joiner
        String str = Joiner.on(",").skipNulls().join(1, 2, 3, 4, 5, null, 6, 7, null);
        System.out.println(str);
        String str2 = Joiner.on(",").useForNull("0").join(1, 2, 3, 4, 5, null, 6, 7, null);
        System.out.println(str2);

        //splitter
        Splitter.on(",").trimResults().omitEmptyStrings().split("the , brown ,  ,fox, jumpes ,over,the,lazy").forEach(System.out::println);

        //charmatcher
        System.out.println(CharMatcher.inRange('0', '9').retainFrom("123abc"));
        System.out.println(CharMatcher.inRange('A', 'Z').retainFrom("AsdfsDdsfs123abc"));
    }
}
