package me.capthua.advancedjava.week5.entity;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class MyTestBean implements BeanFactoryAware {

//    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        this.beanFactory=beanFactory;
    }

    public MyTestBean(){
        System.out.println("myTestBean实例化");
    }

    private String testStr;

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test(){
        System.out.println("test");
    }
}
