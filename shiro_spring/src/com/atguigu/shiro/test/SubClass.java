package com.atguigu.shiro.test;

/**
 * @author hskBeginner Email：2752962035@qq.com
 * @version 1.0
 * @description
 * @create 2020年03月28日 13时28分48秒
 */
public class SubClass extends SuperClass {

    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        System.out.println(subClass);

        System.out.println(subClass.getAge());
        System.out.println(getNAME());
        System.out.println(subClass.getSchoolName());
    }

}
