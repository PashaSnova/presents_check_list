package com.xen.spring.security.java_spring_project.controller;

import com.xen.spring.security.java_spring_project.entity.Gift;
import com.xen.spring.security.java_spring_project.entity.User;
import com.xen.spring.security.java_spring_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StartPageController {

    private final UserService userService;

    @Autowired
    public StartPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/welcome")
    public String start(Authentication auth, Model model)
    {
        User user = userService.getCurrentUser(auth);
        model.addAttribute("gifts", user.getGifts());
        model.addAttribute("newGift", new Gift());
        model.addAttribute("friends", user.getFriends());
        return "index";
    }


}
