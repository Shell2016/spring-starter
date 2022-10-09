package ru.michaelshell.spring.service;

import org.springframework.stereotype.Service;
import ru.michaelshell.spring.database.pool.entity.Company;
import ru.michaelshell.spring.repository.CompanyRepository;
import ru.michaelshell.spring.repository.CrudRepository;
import ru.michaelshell.spring.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CrudRepository<String, Company> companyRepository;


    public UserService(UserRepository userRepository,
                       CrudRepository<String, Company> companyRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }
}
