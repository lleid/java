package com.leo.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal extends FExtend implements Serializable, FInterface {

    @IAnnotation(name = "张三")
    public String name;
    private int age;

    public void doPublic() {
        System.out.println("animal do public");
    }

    private void doPrivate() {
        System.out.println("animal do private");
    }

    public void doGeneric(HashMap<String, Object> map) {
    }
}
