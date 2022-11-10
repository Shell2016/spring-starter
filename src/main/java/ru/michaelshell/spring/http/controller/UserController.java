package ru.michaelshell.spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.michaelshell.spring.dto.UserCreateEditDto;
import ru.michaelshell.spring.service.UserService;

@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
//        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
//        model.addAttribute("user", userService.findById(id));
        return "user/user";
    }

    @PostMapping
    public String create(UserCreateEditDto user, Model model) {
//        userService.create(user);
        return "redirect:/users/" + 25;
    }

//    @PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String update(@PathVariable Long id, @ModelAttribute UserCreateEditDto user) {
//        userService.update(id, user);
        return "redirect:/users/{id}";
    }

//    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
//        userService.delete(id);
        return "redirect:/users";
    }
}
