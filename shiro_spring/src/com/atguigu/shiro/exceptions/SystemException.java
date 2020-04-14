package com.atguigu.shiro.exceptions;

/**
 * @author hskBeginner Email：2752962035@qq.com
 * @version 1.0
 * @description
 * @create 2020年03月28日
 */
public class SystemException extends Exception {

    private String code;//状态码

    public SystemException(String code,String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
