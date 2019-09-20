package com.leo.classloader.beans;

public class ISpeaker implements ISpeak {
    @Override
    public void say() {
        System.out.println("ISpeaker I am frank");
    }
}
