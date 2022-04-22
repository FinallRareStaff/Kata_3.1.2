package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/user_table";
    }

    @GetMapping("/users/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/user";
    }

    @GetMapping("/users/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/user_new";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/edit")
    public String createUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/user_edit";
    }

    @PatchMapping("/users/{id}")
    public String updateUser(@PathVariable("id") long id,
                             @ModelAttribute("user") User user) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
