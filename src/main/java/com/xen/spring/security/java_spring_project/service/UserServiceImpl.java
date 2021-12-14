package com.xen.spring.security.java_spring_project.service;

import com.xen.spring.security.java_spring_project.entity.Friend;
import com.xen.spring.security.java_spring_project.entity.Gift;
import com.xen.spring.security.java_spring_project.entity.Role;
import com.xen.spring.security.java_spring_project.entity.User;
import com.xen.spring.security.java_spring_project.exceptions.UserAlreadyExistsException;
import com.xen.spring.security.java_spring_project.repository.FriendRepository;
import com.xen.spring.security.java_spring_project.repository.GiftRepository;
import com.xen.spring.security.java_spring_project.repository.RoleRepository;
import com.xen.spring.security.java_spring_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeSet;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final GiftRepository giftRepository;
    private final FriendRepository friendRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           GiftRepository giftRepository,
                           FriendRepository friendRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.giftRepository = giftRepository;
        this.friendRepository = friendRepository;
    }

    @Override
    public List<User> findAllByUsernameStartingWithAndUsernameIsNot(String start, Authentication auth) {
        return userRepository.findAllByUsernameStartingWithAndUsernameIsNot(start,
                this.getCurrentUser(auth).getUsername());
    }

    @Override
    public void register(User user) throws Exception {
        if (userRepository.existsById(user.getUsername()))
            throw new UserAlreadyExistsException("User with such username already exists");
        if (user.getUsername() == null || user.getPassword() == null || user.getUsername().equals("") || user.getPassword().equals(""))
            throw new Exception("Fields cannot be empty");
        Role role = new Role();
        role.setAuthority("ROLE_ORDINARY");
        role.setUser(user);
        user.setRole(role);
        user.setEnabled(1);
        user.setPassword("{noop}" + user.getPassword());
        userRepository.save(user);
    }

    @Override
    public void addGift(User user, Gift gift) {
        if (user.getGifts() == null)
            user.setGifts(new TreeSet<>());
        if (gift.getGift() != null && !gift.getGift().equals("")) {
            user.getGifts().add(gift);
            gift.setUsername(user.getUsername());
            giftRepository.save(gift);
        }
    }

    @Override
    public void removeGift(User user, Gift gift) {
        user.getGifts().remove(gift);
        giftRepository.delete(gift);
    }

    @Override
    public User getCurrentUser(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userRepository.getById(userDetails.getUsername());
    }

    @Override
    public void addFriend(User user, User friend) {
        if (user.getFriends() == null)
            user.setFriends(new TreeSet<>());
        Friend f = new Friend();
        f.setUsername(user.getUsername());
        f.setFriend(friend.getUsername());
        user.getFriends().add(f);
        friendRepository.save(f);
    }

    @Override
    public void removeFriend(User user, int id) {
        Friend friend = friendRepository.getById(id);
        friendRepository.delete(friend);
        user.getFriends().remove(friend);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean hasFriend(User user, User friend) {
        Friend f = friendRepository.findByUsernameAndFriend(user.getUsername(), friend.getUsername());
        return f != null;
    }
}
