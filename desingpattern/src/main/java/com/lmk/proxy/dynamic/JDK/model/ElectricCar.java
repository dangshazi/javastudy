package com.lmk.proxy.dynamic.JDK.model;

/**
 * Created by lmk on 2017/6/8.
 */
/**
 * 电能车类，实现Rechargable，Vehicle接口
 * @author louluan
 */
public class ElectricCar implements Rechargable, Vehicle {

    public void drive() {
        System.out.println("Electric Car is Moving silently...");
    }

    public void recharge() {
        System.out.println("Electric Car is Recharging...");
    }

}