package ru.michaelshell.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.michaelshell.spring.database.pool.ConnectionPool;

public class ApplicationRunner {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("application.xml");
        System.out.println(context.getBean("p1", ConnectionPool.class));
    }
}
