package com.lmk.most_simple_rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Created by lmk on 2017/6/8.
 * 服务任务的执行类
 */
public class ServerTask implements Runnable {
    Socket socket = null;
    public ServerTask(Socket accept) {
        socket = accept;
    }

    /**
     * 主要学习 反射的使用
     * 注意使用的service类
     *
     */
    public void run() {

        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try{
            inputStream = new ObjectInputStream(socket.getInputStream());
            String interfaceName = inputStream.readUTF()+"Impl";
            Class<?> service = Class.forName(interfaceName);
            String methodName = inputStream.readUTF();
            Class<?>[] parameterTypes = (Class<?>[]) inputStream.readObject();
            Object[] args = (Object[]) inputStream.readObject();
            Method method = service.getMethod(methodName,parameterTypes);
            Object result = method.invoke(service.newInstance(),args);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
