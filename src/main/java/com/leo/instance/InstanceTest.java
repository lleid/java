package com.leo.instance;

import com.leo.instance.beans.User;
import org.apache.commons.lang3.SerializationUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 生成对象的5钟方式
 * 1.new
 * 2.Class.newInstance()
 * 3.Construct.newInstance()
 * 4.obj.clone()
 * 5.Serialize
 */
public class InstanceTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, NoSuchMethodException, InvocationTargetException, IOException {
        User user = new User();
        user.setName("leo");
        generateSerializableUser(user);     //生成序列化数据

        System.out.println("第1种 new ");
        User user1 = new User();
        user1.setName("leo1");
        System.out.println(user1.getName());
        System.out.println("-------------------");
        System.out.println("第2种 newInstance ");
        Class clazz = Class.forName("com.leo.instance.beans.User");
        User user2 = (User) clazz.newInstance();
        user2.setName("leo2");
        System.out.println(user2.getName());
        System.out.println("-------------------");
        System.out.println("第3种 Constructor ");
        Constructor constructor = User.class.getConstructor();
        User user3 = (User) constructor.newInstance();
        user3.setName("leo3");
        System.out.println(user3.getName());
        System.out.println("-------------------");
        System.out.println("第4种 clone ");
        User user4 = (User) user.clone();
        user4.setName("leo4");
        System.out.println(user4.getName());
        System.out.println("-------------------");
        System.out.println("第5种 Serializable");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.obj"));
        User user5 = (User) in.readObject();
        user5.setName("leo5");
        System.out.println(user5.getName());
    }

    public static void generateSerializableUser(User user) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("data.obj");
            byte[] buser = SerializationUtils.serialize(user);
            out.write(buser);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
