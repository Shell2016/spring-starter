package ru.michaelshell.spring.repository;

import org.springframework.stereotype.Repository;
import ru.michaelshell.spring.database.pool.ConnectionPool;

@Repository
public class UserRepository {

    private final ConnectionPool connectionPool;

    public UserRepository(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
