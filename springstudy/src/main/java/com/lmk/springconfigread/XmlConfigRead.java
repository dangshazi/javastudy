package com.lmk.springconfigread;

import com.lmk.beanmodel.BeanModel;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by lmk on 2017/6/7.
 */
public class XmlConfigRead {


    @Test
    /**
     * 注意文件的路径，是以项目为根目录的
     */
    public void ApplicationContextTest_FileSystemXml(){
        ApplicationContext ac = new FileSystemXmlApplicationContext("src/main/resources/spring.xml");
        BeanModel bm = (BeanModel) ac.getBean("beanModel");
        System.out.println(bm.getName());
    }
    @Test
    /**
     * 注意文件的路径，是以编译后的target/class为根目录的
     */
    public void ApplicationContextTest_ClassPathXml(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        BeanModel bm = (BeanModel) ac.getBean("beanModel");
        System.out.println(bm.getName());
    }

    @Test
    /**
     * 注意文件的路径，是以编译后的target/class为根目录的
     */
    public void ApplicationContextTest_MulitClassPathXml(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml","spring2.xml");
        BeanModel bm = (BeanModel) ac.getBean("beanModel");
        System.out.println(bm.getName());
        bm = (BeanModel) ac.getBean("beanModel_setterinject");
        System.out.println(bm.getName());
    }


}
