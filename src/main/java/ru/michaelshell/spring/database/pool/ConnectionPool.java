package ru.michaelshell.spring.database.pool;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

@ToString
@Component("pool1")
public class ConnectionPool {

    private final String username;
    private final Integer poolSize;
//    private List<Object> args;
//    private Map<String, Object> properties;

    public ConnectionPool(@Value("${db.username}") String username,
                          @Value("${db.pool.size}") Integer poolSize) {
        this.username = username;
        this.poolSize = poolSize;
    }

    @PostConstruct
    private void init() {
        System.out.println("Initializing connection pool...");
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Destroy method...");
    }
}
