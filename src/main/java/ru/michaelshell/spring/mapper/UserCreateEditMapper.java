package ru.michaelshell.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.michaelshell.spring.database.entity.Company;
import ru.michaelshell.spring.database.entity.User;
import ru.michaelshell.spring.dto.UserCreateEditDto;
import ru.michaelshell.spring.repository.CompanyRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User> {

    private final CompanyRepository companyRepository;

    @Override
    public User map(UserCreateEditDto object, User toObject) {
        copy(object, toObject);
        return toObject;
    }

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private void copy(UserCreateEditDto object, User user) {
        user.setUsername(object.getUsername());
        user.setBirthDate(object.getBirthDate());
        user.setFirstname(object.getFirstname());
        user.setLastname(object.getLastname());
        user.setRole(object.getRole());
        user.setCompany(getCompany(object.getCompanyId()));
    }

    private Company getCompany(Integer companyId) {
        return Optional.ofNullable(companyId)
                .flatMap(companyRepository::findById)
                .orElse(null);
    }
}
