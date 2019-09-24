package com.leo.extension18;

import java.lang.annotation.*;

/**
 * 可重复注解
 */
public class RepeatableTest {
    public static void main(String[] args) {
        Token[] tokens = UserToken.class.getAnnotationsByType(Token.class);
        for (Token token : tokens) {
            System.out.println(token.value());
        }
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Tokens {
        Token[] value();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Tokens.class)
    public @interface Token {
        String value();
    }

    @Token("123")
    @Token("456")
    interface UserToken {

    }
}
