package com.project.study.ShoppingApp.repositories;

import com.project.study.ShoppingApp.models.ShopItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopItemRepo extends JpaRepository<ShopItem,Long> {
    List<ShopItem> findByItemName(String name);

    @Query(value = "select * from shop_item where shop_item.item_name like %:name%"
            ,nativeQuery = true)
    List<ShopItem> searchItem(@Param("name")String name);

//    @Query(value = "")
}
