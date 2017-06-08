package com.lmk.proxy.dynamic.JDK;

/**
 * Created by lmk on 2017/6/8.
 */
import com.lmk.proxy.dynamic.JDK.model.ElectricCar;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerImpl implements InvocationHandler {

    private ElectricCar car;

    public InvocationHandlerImpl(ElectricCar car)
    {
        this.car=car;
    }

    public Object invoke(Object paramObject, Method paramMethod,
                         Object[] paramArrayOfObject) throws Throwable {
        System.out.println("You are going to invoke "+paramMethod.getName()+" ...");
        paramMethod.invoke(car, null);
        System.out.println(paramMethod.getName()+" invocation Has Been finished...");
        return null;
    }

}
