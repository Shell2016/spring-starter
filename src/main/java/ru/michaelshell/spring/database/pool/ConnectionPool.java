package ru.michaelshell.spring.database.pool;

import lombok.ToString;

import java.util.List;
import java.util.Map;

@ToString
public class ConnectionPool {
    private String username;
    private Integer poolSize;
    private List<Object> args;
    private Map<String, Object> properties;


    public ConnectionPool(String username, Integer poolSize, List<Object> args, Map<String, Object> properties) {
        this.username = username;
        this.poolSize = poolSize;
        this.args = args;
        this.properties = properties;
    }
}
