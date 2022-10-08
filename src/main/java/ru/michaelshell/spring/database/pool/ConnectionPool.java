package ru.michaelshell.spring.database.pool;

import lombok.ToString;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

@ToString
public class ConnectionPool {
    private String username;
    private Integer poolSize;
    private List<Object> args;
    private Map<String, Object> properties;

    public ConnectionPool() {
    }

    public ConnectionPool(String username, Integer poolSize, List<Object> args, Map<String, Object> properties) {
        this.username = username;
        this.poolSize = poolSize;
        this.args = args;
        this.properties = properties;
    }

    @PostConstruct
    private void init() {
        System.out.println("Initializing connection pool...");
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    @PreDestroy
    private void destroy() {
        System.out.println("Destroy method...");
    }
}
