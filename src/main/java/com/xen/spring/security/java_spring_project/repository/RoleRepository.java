package com.xen.spring.security.java_spring_project.repository;

import com.xen.spring.security.java_spring_project.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
