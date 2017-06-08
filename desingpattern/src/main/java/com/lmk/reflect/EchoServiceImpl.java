package com.lmk.reflect;

/**
 * Created by lmk on 2017/6/8.
 * 服务实现类
 */
public class EchoServiceImpl implements EchoService {
    public String echo(String ping) {
        return ping != null ? ping :"I'm OK!";
    }
}
