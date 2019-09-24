package com.leo.extension18;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Test {
    public static void main(String[] args) {
        String text = "hello 中国";

        String encode = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
        System.out.println(encode);

        String decode = new String(Base64.getDecoder().decode(encode), StandardCharsets.UTF_8);
        System.out.println(decode);
    }
}
