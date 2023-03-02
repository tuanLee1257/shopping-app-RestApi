package com.project.study.ShoppingApp.repositories;

import com.project.study.ShoppingApp.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
