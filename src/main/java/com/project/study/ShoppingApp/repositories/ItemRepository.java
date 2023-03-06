package com.project.study.ShoppingApp.repositories;

import com.project.study.ShoppingApp.models.Item;
import com.project.study.ShoppingApp.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {
    List<Item> findByItemName(String name);
    @Query(value = "select * from shop_item where shop_item.item_name like %:name%"
            ,nativeQuery = true)
    List<Item> searchItem(@Param("name")String name);
    List<Item> findByUsers(User user);

//    @Query(value = "")
}
