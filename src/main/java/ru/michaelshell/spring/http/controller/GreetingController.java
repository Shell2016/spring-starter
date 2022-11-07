package ru.michaelshell.spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1")
public class GreetingController {


    @GetMapping("/hello/{id}")
    public ModelAndView hello(ModelAndView mv,
                              @RequestParam Integer age,
                              @RequestHeader String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable Integer id
                              ) {
        mv.setViewName("greeting/hello");
        return mv;
    }

    @GetMapping("/bye")
    public ModelAndView bye(ModelAndView mv) {
        mv.setViewName("greeting/bye");
        return mv;
    }
}
