package ru.michaelshell.spring.mapper;

import org.springframework.stereotype.Component;
import ru.michaelshell.spring.database.entity.Company;
import ru.michaelshell.spring.dto.CompanyReadDto;

@Component
public class CompanyReadMapper implements Mapper<Company, CompanyReadDto> {

    @Override
    public CompanyReadDto map(Company object) {
        return new CompanyReadDto(
                object.getId(),
                object.getName()
        );
    }
}
