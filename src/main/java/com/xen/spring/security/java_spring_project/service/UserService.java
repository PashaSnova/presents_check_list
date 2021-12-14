package com.xen.spring.security.java_spring_project.service;

import com.xen.spring.security.java_spring_project.entity.Gift;
import com.xen.spring.security.java_spring_project.entity.User;
import com.xen.spring.security.java_spring_project.exceptions.UserAlreadyExistsException;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService {

    void register(User user) throws Exception;

    void addGift(User user, Gift gift);

    User getCurrentUser(Authentication authentication);

    void removeGift(User user, Gift gift);

    void addFriend(User user, User friend);

    void removeFriend(User user, int id);

    User getUserByUsername(String username);

    List<User> findAllByUsernameStartingWithAndUsernameIsNot(String start, Authentication auth);

    boolean hasFriend(User user, User friend);
}
