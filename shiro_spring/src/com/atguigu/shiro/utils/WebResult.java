package com.atguigu.shiro.utils;

import com.atguigu.shiro.constant.Config;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hskBeginner Email：2752962035@qq.com
 * @version 1.0
 * @description
 * @create 2020年03月28日 11时52分30秒
 */
public class WebResult {

    private String code;//状态码
    private String msg;//提示信息
    private String redirectUrl;//重定向的url
    private boolean back;
    private boolean refresh;//刷新页面
    private Map<String, Object> data;//返回的数据

    public WebResult(){
        back = false;
        refresh = false;
        code = Config.SUCCESS;

        data = new HashMap<>();
    }

    public static WebResult buildResult(){
        return new WebResult();
    }

    public WebResult code(String code){
        setCode(code);
        return this;
    }

    public WebResult msg(String msg){
        setMsg(msg);
        return this;
    }

    public WebResult redirectUrl(String redirectUrl){
        setRedirectUrl(redirectUrl);
        return this;
    }

    public WebResult back(){
        setBack(true);
        return this;
    }

    public WebResult refresh(){
        setRefresh(true);
        return this;
    }

    public WebResult putData(String name,Object val){
        data.put(name,val);
        return this;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public boolean isBack() {
        return back;
    }

    public void setBack(boolean back) {
        this.back = back;
    }

    public boolean isRefresh() {
        return refresh;
    }

    public void setRefresh(boolean refresh) {
        this.refresh = refresh;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

}
