package ru.michaelshell.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.michaelshell.spring.config.ApplicationConfiguration;
import ru.michaelshell.spring.listener.AccessType;
import ru.michaelshell.spring.listener.EntityEvent;
import ru.michaelshell.spring.repository.CompanyRepository;
import ru.michaelshell.spring.database.pool.ConnectionPool;
import ru.michaelshell.spring.repository.CrudRepository;
import ru.michaelshell.spring.service.CompanyService;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
            var pool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(pool);
            var companyService = context.getBean(CompanyService.class);
            System.out.println(companyService.findById(1));
        }
    }
}
