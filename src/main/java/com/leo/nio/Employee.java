package com.leo.nio;

import java.io.Serializable;

/**
 * 序列化对象
 */
public class Employee implements Serializable {

    //版本号UID ，缺省反序列化时如果删除或添加原字段，会throw exception
    private static final long serialVersionUID = 3045711851067528600L;

    private static String title = "employee";

    private String name;

    transient private String pass;   //不需要序列化的字段 +transient
    private Address address;    //关联对象必须可序列化，throw java.io.NotSerializableException

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
