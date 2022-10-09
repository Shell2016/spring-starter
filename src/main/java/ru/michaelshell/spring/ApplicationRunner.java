package ru.michaelshell.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.michaelshell.spring.config.ApplicationConfiguration;
import ru.michaelshell.spring.repository.CompanyRepository;
import ru.michaelshell.spring.database.pool.ConnectionPool;
import ru.michaelshell.spring.repository.CrudRepository;

public class ApplicationRunner {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class)) {
            var pool = context.getBean("pool1", ConnectionPool.class);
            System.out.println(pool);
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));
        }
    }
}
