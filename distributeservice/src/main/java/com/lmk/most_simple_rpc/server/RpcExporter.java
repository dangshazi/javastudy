package com.lmk.most_simple_rpc.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lmk on 2017/6/8.
 *
 * 发布服务的类
 */
public class RpcExporter {

    public static final Executor executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void export(String host,int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(host,port));
        try {
            while (true){
                executor.execute(new ServerTask(serverSocket.accept()));
            }
        }finally {
            serverSocket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        RpcExporter.export("localhost",12343);
    }
}
