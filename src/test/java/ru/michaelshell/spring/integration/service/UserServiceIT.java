package ru.michaelshell.spring.integration.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.michaelshell.spring.database.entity.Role;
import ru.michaelshell.spring.dto.UserCreateEditDto;
import ru.michaelshell.spring.dto.UserReadDto;
import ru.michaelshell.spring.integration.IntegrationTestBase;
import ru.michaelshell.spring.service.UserService;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@RequiredArgsConstructor
public class UserServiceIT extends IntegrationTestBase {

    private static final Long USER_1 = 1L;
    private static final Integer COMPANY_1 = 1;

    private final UserService userService;

    @Test
    void findAll() {
        var users = userService.findAll();
        assertThat(users).hasSize(5);
    }

    @Test
    void findById() {
        Optional<UserReadDto> maybeUser = userService.findById(USER_1);
        assertTrue(maybeUser.isPresent());
        maybeUser.ifPresent(user -> assertEquals("ivan@gmail.com", user.getUsername()));
    }


    @Test
    void create() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "test",
                "test",
                Role.ADMIN,
                COMPANY_1
        );
        UserReadDto result = userService.create(userDto);

        assertEquals(userDto.getUsername(), result.getUsername());
        assertEquals(userDto.getBirthDate(), result.getBirthDate());
        assertEquals(userDto.getFirstname(), result.getFirstname());
        assertEquals(userDto.getLastname(), result.getLastname());
        assertEquals(userDto.getCompanyId(), result.getCompany().id());
        assertSame(userDto.getRole(), result.getRole());
    }


    @Test
    void update() {
        UserCreateEditDto userDto = new UserCreateEditDto(
                "test@gmail.com",
                LocalDate.now(),
                "test",
                "test",
                Role.ADMIN,
                COMPANY_1
        );
        Optional<UserReadDto> result = userService.update(USER_1, userDto);
        assertTrue(result.isPresent());
        result.ifPresent(user -> {
            assertEquals(userDto.getUsername(), user.getUsername());
            assertEquals(userDto.getBirthDate(), user.getBirthDate());
            assertEquals(userDto.getFirstname(), user.getFirstname());
            assertEquals(userDto.getLastname(), user.getLastname());
            assertEquals(userDto.getCompanyId(), user.getCompany().id());
            assertSame(userDto.getRole(), user.getRole());
        });
    }

    @Test
    void delete() {
        assertTrue(userService.delete(USER_1));
        assertFalse(userService.delete(-123L));
    }
}
