package me.capthua.advancedjava.week5.configuration;

import me.capthua.advancedjava.week5.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BeansConfiguration {

    @Bean(name = "personInConfiguration")
    public Person person(){
        return new Person();
    }

}
