package ru.michaelshell.spring.http.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.michaelshell.spring.database.entity.Role;
import ru.michaelshell.spring.dto.UserReadDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @ModelAttribute("roles")
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }


    @GetMapping("/hello")
    public String hello(Model model, HttpServletRequest request, UserReadDto userReadDto) {
//        request.getSession().setAttribute();   session scope
//        request.setAttribute();  request scope

        model.addAttribute("user", userReadDto);
        return "greeting/hello";
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
    public String bye(@SessionAttribute("user") UserReadDto userReadDto) {
//        request.getSession().getAttribute("user");

        return "greeting/bye";
    }
}
