package com.leo.extension18;

import com.leo.extension18.lambda.beans.User;

/**
 * 接口中 default he static 可以有多个，defalut可以被继承，static 方法不能被继承和重写
 * default 只能写在接口中
 */
public class InterfaceTest {
    public static void main(String[] args) {
        IUserInterface iUserInterface = new UserServiceImpl();

        System.out.println(iUserInterface.getDefaultUser1());
        System.out.println(iUserInterface.getDefaultUser2());
        System.out.println(IUserInterface.getDefaultUser3());
    }

    interface IUserInterface {
        default User getDefaultUser1() {
            return new User("interface User1", 10);
        }

        default User getDefaultUser2() {
            return new User("interface User2", 10);
        }

        static User getDefaultUser3() {
            return new User("interface User3", 10);
        }
    }

    public static class UserServiceImpl implements IUserInterface {
        @Override
        public User getDefaultUser1() {
            return new User("implement User1", 10);
        }
    }
}
