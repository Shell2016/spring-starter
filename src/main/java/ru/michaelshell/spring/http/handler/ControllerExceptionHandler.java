package ru.michaelshell.spring.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Slf4j
@ControllerAdvice(basePackages = "ru.michaelshell.spring.http.controller")
public class ControllerExceptionHandler /*extends ResponseEntityExceptionHandler*/ {

//    @ExceptionHandler(Exception.class)
//    public String handleExceptions(Exception e) {
//        log.error("Failed to return response", e);
//        return "error/error500";
//    }
}
