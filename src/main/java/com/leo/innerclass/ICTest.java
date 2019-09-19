package com.leo.innerclass;

import com.leo.innerclass.beans.IMain;

public class ICTest {
    public static void main(String[] args) {
        IMain iMain = new IMain();
        System.out.println(iMain.getName());

//        IMain.InnerPublic innerPrivate = iMain.new InnerPrivate();//报错
        iMain.getInnerPrivate();
        IMain.InnerPublic innerPublic = iMain.new InnerPublic();
        innerPublic.go();

        iMain.go();
        iMain.run();

        IMain.InnerStatic innerStatic = new IMain.InnerStatic();
        innerStatic.go();


    }
}
