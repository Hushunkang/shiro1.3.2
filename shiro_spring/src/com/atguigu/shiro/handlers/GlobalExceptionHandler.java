package com.atguigu.shiro.handlers;

import com.atguigu.shiro.constant.Config;
import com.atguigu.shiro.exceptions.SystemException;
import com.atguigu.shiro.utils.WebResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


/**
 *
 * 全局异常处理，捕获所有Handler中抛出的异常
 *
 * @author hskBeginner Email：2752962035@qq.com
 * @version 1.0
 * @description
 * @create 2020年03月28日
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_VIEW = "error";

    //处理自定义的异常
    @ExceptionHandler(SystemException.class)
//    @ResponseBody
    public Object customHandler(HttpServletRequest request,SystemException e){
//        e.printStackTrace();
//        return WebResult.buildResult().code(e.getCode()).msg(e.getMessage());
        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURL());
        mv.addObject("exception", e);
        mv.setViewName(ERROR_VIEW);
        return mv;
    }

    //其它未处理的异常
    @ExceptionHandler(Exception.class)
//    @ResponseBody
    public Object exceptionHandler(Exception e){
//        e.printStackTrace();
//        return WebResult.buildResult().code(Config.FAIL).msg("系统异常！！！");
        return WebResult.buildResult().code(Config.FAIL).msg(e.getMessage());
    }

}

