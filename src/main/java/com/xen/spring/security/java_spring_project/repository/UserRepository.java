package com.xen.spring.security.java_spring_project.repository;

import com.xen.spring.security.java_spring_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAllByUsernameStartingWithAndUsernameIsNot(String start, String currentUser);
}
