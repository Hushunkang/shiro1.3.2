package com.atguigu.shiro.test;

/**
 * @author hskBeginner Email：2752962035@qq.com
 * @version 1.0
 * @description
 * @create 2020年03月28日 13时27分09秒
 */
public class SuperClass {

    private transient  int age;
    private static final String NAME = "Hsk";
    private String schoolName = "Yangtze University";

    public int getAge() {
        return age;
    }

    public static String getNAME() {
        return NAME;
    }

    public String getSchoolName() {
        return schoolName;
    }

}
