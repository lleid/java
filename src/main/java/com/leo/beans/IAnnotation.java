package com.leo.beans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解元素可有的类型有如下
 * 1.所有基本类型（int、float等）
 * 2.String
 * 3.enum
 * 4.Annotation
 * 5.以上类型的数组
 * <p>
 * 默认值不能用null,注解不支持继承
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IAnnotation {
    String name() default "";
}
