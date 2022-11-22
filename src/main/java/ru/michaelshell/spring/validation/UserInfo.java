package ru.michaelshell.spring.validation;

import ru.michaelshell.spring.validation.impl.UserInfoValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = UserInfoValidator.class)
public @interface UserInfo {

    String message() default "Firstname or lastname must be filled in";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
