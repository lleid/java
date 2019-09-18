package com.leo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;

public class Test {

    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("a.txt");
        byte[] bytes = new byte[1024];
        in.read(bytes);
        User user = SerializationUtils.deserialize(bytes);
        System.out.println(user);
    }
}

@Getter
@Setter
@ToString
class User implements Serializable {

    private String name;

    private Integer age;

    public User(String name) {
        this.name = name;
    }
}


