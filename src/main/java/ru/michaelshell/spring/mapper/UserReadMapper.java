package ru.michaelshell.spring.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.michaelshell.spring.database.entity.User;
import ru.michaelshell.spring.dto.UserReadDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserReadMapper implements Mapper<User, UserReadDto> {

    private final CompanyReadMapper companyReadMapper;

    @Override
    public UserReadDto map(User object) {
        var company = Optional.ofNullable(object.getCompany())
                .map(companyReadMapper::map)
                .orElse(null);

        return new UserReadDto(
                object.getId(),
                object.getUsername(),
                object.getBirthDate(),
                object.getFirstname(),
                object.getLastname(),
                object.getRole(),
                company,
                object.getImage()
        );
    }
}
