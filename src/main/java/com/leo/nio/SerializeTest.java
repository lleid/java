package com.leo.nio;

import org.apache.commons.lang3.SerializationUtils;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializeTest {
    public static void main(String[] args) {
        Employee employee = new Employee();
        employee.setName("123");
        employee.setPass("123456");

//        Address address = new Address("江苏");   //关联对象必须可序列化，throw java.io.NotSerializableException
//        employee.setAddress(address);

        //通过工具类转换成字节流数组
        byte[] bytes = SerializationUtils.serialize(employee);
        Employee employee1 = SerializationUtils.deserialize(bytes);

        System.out.println(employee1.getName());
        System.out.println(employee1.getPass());


        generateSerializable(employee1);
        generateSerializable(employee1);
        generateSerializable(employee1);
        generateSerializable(employee1);

        List<Employee> list = getSerializableUser("data.obj");
        for (Employee e : list) {
            System.out.println(e.getName() + "," + e.getPass());
        }
    }

    public static void generateSerializable(Employee employee) {
        ObjectOutputStream out = null;
        File file = new File("data.obj");
        try {
            out = ObjectOutputStreamWrapper.stream(file);
            out.writeObject(employee);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //通过ObjectInputStream读取序列化对象时，如果有多条数据，则通过EOFException跳出循环
    //java.io.StreamCorruptedException: invalid type code: AC 
    public static List<Employee> getSerializableUser(String path) {
        ObjectInputStream in = null;
        List<Employee> list = new ArrayList<Employee>();
        try {
            in = new ObjectInputStream(new FileInputStream("data.obj"));

            while (true) {
                try {
                    Employee employee = (Employee) in.readObject();
                    list.add(employee);
                } catch (NullPointerException ee) {
                    continue;
                } catch (EOFException e) {                //end of file exception
                    break;
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
