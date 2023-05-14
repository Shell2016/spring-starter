package ru.michaelshell.spring.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageRestController {

    private final MessageSource messageSource;

    @GetMapping
    public String getMessage(@RequestParam String key,
                             @RequestParam("lang") String language) {
//        ResourceBundle messages = ResourceBundle.getBundle("messages");
        return messageSource.getMessage(key, null, null, new Locale(language));
    }
}
