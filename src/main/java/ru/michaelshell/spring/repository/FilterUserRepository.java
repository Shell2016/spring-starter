package ru.michaelshell.spring.repository;

import ru.michaelshell.spring.database.entity.User;
import ru.michaelshell.spring.dto.UserFilter;

import java.util.List;

public interface FilterUserRepository {

    List<User> findAllByFilter(UserFilter userFilter);
}
