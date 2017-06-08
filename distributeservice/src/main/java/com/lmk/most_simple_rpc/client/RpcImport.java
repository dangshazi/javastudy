package com.lmk.most_simple_rpc.client;

import com.lmk.most_simple_rpc.server.EchoService;
import com.lmk.most_simple_rpc.server.EchoServiceImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by lmk on 2017/6/8.
 */
public class RpcImport<S> {

    /**
     *
     * @param serviceClass 注意这个类 传进来的是实现类，但是仅仅是为了得到服务的接口 EchoService
     * @param addr
     * @return
     */
    public S importer(final Class<?> serviceClass, final InetSocketAddress addr){
        return (S) Proxy.newProxyInstance(serviceClass.getClassLoader(), serviceClass.getInterfaces(), new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = null;
                ObjectOutputStream outputStream = null;
                ObjectInputStream inputStream = null;
                try {
                    socket = new Socket();
                    socket.connect(addr);
                    outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.writeUTF(serviceClass.getInterfaces()[0].getName());
                    System.out.println(serviceClass.getInterfaces()[0].getName());
                    outputStream.writeUTF(method.getName());
                    outputStream.writeObject(method.getParameterTypes());
                    outputStream.writeObject(args);
                    inputStream = new ObjectInputStream(socket.getInputStream());
                    return inputStream.readObject();
                }finally {
                    if (socket != null){
                        socket.close();
                    }
                }
            }
        });
    }

    public static void main(String[] args ){
        RpcImport<EchoService> importer = new RpcImport<EchoService>();
        InetSocketAddress addr = new InetSocketAddress("localhost",12343);
        EchoService service = importer.importer(EchoServiceImpl.class,addr);
        System.out.println(service.echo("lmk"));
        System.out.println(service.echo(null));
    }
}
