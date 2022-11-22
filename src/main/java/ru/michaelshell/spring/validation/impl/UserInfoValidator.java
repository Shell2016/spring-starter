package ru.michaelshell.spring.validation.impl;

import org.springframework.util.StringUtils;
import ru.michaelshell.spring.dto.UserCreateEditDto;
import ru.michaelshell.spring.validation.UserInfo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserInfoValidator implements ConstraintValidator<UserInfo, UserCreateEditDto> {


    @Override
    public boolean isValid(UserCreateEditDto value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value.getFirstname()) || StringUtils.hasText(value.getLastname());
    }
}

