package com.xen.spring.security.java_spring_project.repository;

import com.xen.spring.security.java_spring_project.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface FriendRepository extends JpaRepository<Friend, Integer> {

    Friend findByUsernameAndFriend(String username, String friend);
}
