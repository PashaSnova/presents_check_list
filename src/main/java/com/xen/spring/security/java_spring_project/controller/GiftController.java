package com.xen.spring.security.java_spring_project.controller;

import com.xen.spring.security.java_spring_project.entity.Gift;
import com.xen.spring.security.java_spring_project.entity.User;
import com.xen.spring.security.java_spring_project.repository.GiftRepository;
import com.xen.spring.security.java_spring_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GiftController {

    private final UserService userService;
    private final GiftRepository giftRepository;

    @Autowired
    public GiftController(UserService userService, GiftRepository giftRepository) {
        this.userService = userService;
        this.giftRepository = giftRepository;
    }


    @PostMapping("/addGift")
    public String addGift(Authentication auth,
                          @ModelAttribute("newGift") Gift gift) {
        User user = userService.getCurrentUser(auth);
        userService.addGift(user, gift);
        return "redirect:/welcome";
    }

    @PostMapping("/removeGift")
    public String removeGift(Authentication auth, @RequestParam("id") int id) {
        User user = userService.getCurrentUser(auth);
        Gift gift = giftRepository.getById(id);
        userService.removeGift(user, gift);
        return "redirect:/welcome";
    }
}
