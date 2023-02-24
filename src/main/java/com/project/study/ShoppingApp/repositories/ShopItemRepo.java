package com.project.study.ShoppingApp.repositories;

import com.project.study.ShoppingApp.models.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopItemRepo extends JpaRepository<ShopItem,Long> {
    List<ShopItem> findByItemName(String name);

}
