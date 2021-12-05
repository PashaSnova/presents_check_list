package com.xen.spring.security.java_spring_project.controller;

import com.xen.spring.security.java_spring_project.entity.User;
import com.xen.spring.security.java_spring_project.repository.FriendRepository;
import com.xen.spring.security.java_spring_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FriendsController {

    private final UserService userService;
    private final FriendRepository friendRepository;

    @Autowired
    public FriendsController(UserService userService, FriendRepository friendRepository) {
        this.userService = userService;
        this.friendRepository = friendRepository;
    }


    @PostMapping("/addFriend")
    public String addFriend(@RequestParam("friendUsername") String friendUsername, Authentication auth) {
        User current = userService.getCurrentUser(auth);
        User friend = userService.getUserByUsername(friendUsername);
        userService.addFriend(current, friend);
        return "redirect:/welcome";
    }

    @PostMapping("/removeFriend")
    public String removeFriend(@RequestParam("friendId") int id, Authentication auth) {
        User current = userService.getCurrentUser(auth);
        userService.removeFriend(current, id);
        return "redirect:/welcome";
    }

    @GetMapping("/{friendLink}")
    public String friendsPage(@PathVariable("friendLink")String friendLink, Model model){
        User user = userService.getUserByUsername(friendLink);
        model.addAttribute("gifts", user.getGifts());
        return "friend";
    }

    @GetMapping("/search")
    public String findUsers(@RequestParam("search") String start, Model model, Authentication auth) {
        model.addAttribute("current", userService.getCurrentUser(auth));
        model.addAttribute("users", userService.findAllByUsernameStartingWithAndUsernameIsNot(start, auth));
        model.addAttribute("service", userService);
        return "search";
    }

}
