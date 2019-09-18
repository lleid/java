package com.leo.lambda;

import java.time.LocalDate;

public class Person {
    public enum Sex {
        MALE, FEMALE
    }

    public String name;
    public LocalDate birthday;
    public Sex gender;
    public String emailAddress;
    public int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void printPerson() {
        System.out.println("name=" + this.name + ",age=" + this.age + ",gender=" + this.gender +
                ",birthday=" + this.birthday + ",emailAddress=" + emailAddress);
    }
}
