package me.capthua.advancedjava.week5;

import me.capthua.advancedjava.week5.entity.MyTestBean;
import me.capthua.advancedjava.week5.entity.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Homework2 {

    public static void main(String[] args) {
        ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");
        //xml配置
        MyTestBean myTestBean=(MyTestBean) context.getBean("myTestBean");
        //注解扫描
        Person person=context.getBean(Person.class);
        //configuration配置
        Person person2=(Person) context.getBean("personInConfiguration");
    }

}
