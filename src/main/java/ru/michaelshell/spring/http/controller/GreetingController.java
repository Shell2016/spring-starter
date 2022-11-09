package ru.michaelshell.spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.michaelshell.spring.dto.UserReadDto;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {


    @GetMapping("/hello")
    public ModelAndView hello(ModelAndView mv, HttpServletRequest request) {
//        request.getSession().setAttribute();   session scope
//        request.setAttribute();  request scope
        mv.setViewName("greeting/hello");
        mv.addObject("user", new UserReadDto(1L, "Ivan"));
        return mv;
    }

    @GetMapping("/hello/{id}")
    public ModelAndView hello2(ModelAndView mv,
                              HttpServletRequest request,
                              @RequestParam Integer age,
                              @RequestHeader String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable Integer id
    ) {
        var ageParamValue = request.getParameter("age");
        var acceptHeader = request.getHeader("accept");
        var cookies = request.getCookies();

        mv.setViewName("greeting/hello");
        return mv;
    }

    @GetMapping("/bye")
    public ModelAndView bye(ModelAndView mv, @SessionAttribute("user") UserReadDto userReadDto) {
//        request.getSession().getAttribute("user");
        mv.setViewName("greeting/bye");
        return mv;
    }
}
