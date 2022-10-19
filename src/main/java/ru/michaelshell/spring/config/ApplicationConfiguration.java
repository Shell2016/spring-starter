package ru.michaelshell.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;
import ru.michaelshell.spring.database.pool.ConnectionPool;
import ru.michaelshell.web.config.WebConfiguration;

@Import(WebConfiguration.class)
@Configuration
//@PropertySource("classpath:application.properties")
//@ComponentScan(basePackages = "ru.michaelshell.spring",
//        useDefaultFilters = false,
//        includeFilters = {
//                @Filter(type = FilterType.ANNOTATION, value = Component.class), //default
//                @Filter(type = FilterType.ASSIGNABLE_TYPE, value = CrudRepository.class),
//                @Filter(type = FilterType.REGEX, pattern = "com\\..+Repository")
//        })
public class ApplicationConfiguration {

    @Bean("pool2")
    @Scope(BeanDefinition.SCOPE_SINGLETON)
    public ConnectionPool pool2(@Value("${db.username}") String userName) {
        return new ConnectionPool(userName, 20);
    }

    @Bean
    public ConnectionPool pool3() {
        return new ConnectionPool("test-pool", 25);
    }

}
