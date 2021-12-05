package com.xen.spring.security.java_spring_project.controller;

import com.xen.spring.security.java_spring_project.entity.User;
import com.xen.spring.security.java_spring_project.exceptions.UserAlreadyExistsException;
import com.xen.spring.security.java_spring_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, Model model){
        try {
            userService.register(user);
        } catch (UserAlreadyExistsException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User("", ""));
        return "registration";
    }
}
