package com.leo.extension18.lambda.beans;

import lombok.*;

import java.util.function.Supplier;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String name;
    private Integer age;

    public static User create(Supplier<User> supplier) {
        return supplier.get();
    }

    public static void updateName(User user) {
        user.setName(user.getName() + " updated");
    }

    public void updateAge() {
        this.setAge(this.getAge() + 10);
    }

    public void changeAge(User user) {
        user.setAge(user.getAge() + 10);
    }

}
