package com.lmk.proxy.dynamic.cglib;

/**
 * Created by lmk on 2017/6/8.
 */
import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
/*
 * 实现了方法拦截器接口
 */
public class Hacker implements MethodInterceptor {

    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        System.out.println("**** I am a hacker,Let's see what the poor programmer is doing Now...");
        proxy.invokeSuper(obj, args);
        System.out.println("****  Oh,what a poor programmer.....");
        return null;
    }
//  这个方法没法被调用
//    public void hack(){
//        System.out.println("I hack a computer!");
//    }
}