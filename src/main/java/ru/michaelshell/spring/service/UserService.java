package ru.michaelshell.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.michaelshell.spring.database.pool.entity.Company;
import ru.michaelshell.spring.repository.CrudRepository;
import ru.michaelshell.spring.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<String, Company> companyRepository;

}
