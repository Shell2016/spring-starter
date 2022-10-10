package ru.michaelshell.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import ru.michaelshell.spring.database.pool.ConnectionPool;
import ru.michaelshell.spring.integration.annotation.IT;
import ru.michaelshell.spring.service.UserService;

@IT
@RequiredArgsConstructor
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserServiceIT {

    private final UserService userService;
    private final ConnectionPool pool1;

    @Test
    void test1() {
        System.out.println();
    }
}
