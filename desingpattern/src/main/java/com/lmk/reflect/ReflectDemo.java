package com.lmk.reflect;

import org.junit.Test;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by lmk on 2017/6/8.
 */
public class ReflectDemo {

    private String className = "com.lmk.reflect.EchoServiceImpl";
    private String methodName = "echo";
//    - 获得某个类的实例
//- 获得某个类的方法
//- 调用某个类的方法
//- 获得当前类的泛型类型

    //    通过newInstance获得某个类的实例
    @Test
    public Object getClassInstance() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> cl = Class.forName(className);
        return cl.newInstance();
    }

    //- 获得某个类的方法
    @Test
    public Method getMethod() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Class<?> cl = Class.forName(className);
        return cl.getMethod(methodName,new Class<?>[] {String.class});
    }


//    1. 得到某个对象的属性
    public Object getProperty(Object owner, String fieldName) throws Exception {
        Class ownerClass = owner.getClass();
        Field field = ownerClass.getField(fieldName);
        Object property = field.get(owner);
        return property;
    }
//2. 得到某个类的静态属性
    public Object getStaticProperty(String className, String fieldName)
            throws Exception {
        Class ownerClass = Class.forName(className);
        Field field = ownerClass.getField(fieldName);
        Object property = field.get(ownerClass);
        return property;
    }

//3. 执行某对象的方法
    public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
        Class ownerClass = owner.getClass();
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = ownerClass.getMethod(methodName,argsClass);
        return method.invoke(owner, args);
    }

//4. 执行某个类的静态方法
    public Object invokeStaticMethod(String className, String methodName,
                                     Object[] args) throws Exception {
        Class ownerClass = Class.forName(className);
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Method method = ownerClass.getMethod(methodName,argsClass);
        return method.invoke(null, args);
    }

//5. 使用constructor新建实例
 public Object newInstance(String className, Object[] args) throws Exception {
        Class newoneClass = Class.forName(className);
        Class[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = args[i].getClass();
        }
        Constructor cons = newoneClass.getConstructor(argsClass);
        return cons.newInstance(args);
}

//6. 判断是否为某个类的实例
    public boolean isInstance(Object obj, Class cls) {
        return cls.isInstance(obj);
  }

}
