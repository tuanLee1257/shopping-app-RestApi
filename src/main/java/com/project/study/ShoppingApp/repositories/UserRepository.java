package com.project.study.ShoppingApp.repositories;

import com.project.study.ShoppingApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
     Optional<User> findByUsername(String username);
}
