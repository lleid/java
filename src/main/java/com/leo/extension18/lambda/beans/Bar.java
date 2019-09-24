package com.leo.extension18.lambda.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class Bar {
    public String name;

    public Bar(String name) {
        this.name = name;
    }
}
