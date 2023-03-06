package com.project.study.ShoppingApp.controllers;

import com.project.study.ShoppingApp.models.Cart;
import com.project.study.ShoppingApp.models.CartDetail;
import com.project.study.ShoppingApp.models.Item;
import com.project.study.ShoppingApp.models.auth.ResponseObject;
import com.project.study.ShoppingApp.models.user.User;
import com.project.study.ShoppingApp.repositories.CartRepository;
import com.project.study.ShoppingApp.repositories.ItemRepository;
import com.project.study.ShoppingApp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;

    @PostMapping("/{username}/cart/insert")
    public ResponseEntity<String> insertCart(@RequestBody List<CartDetail> cartDetails, @PathVariable("username")String username){
        Cart cart = new Cart();
        User user = userRepository.findByUsername(username);
        cart.setUser(user);
        cart.setOrderDate(LocalDateTime.now());
        cart.setCartDetails(
                cartDetails.stream().map(cartDetail -> {
                    cartDetail.setCart(cart);
                    cartDetail.setPrice(cartDetail.getQuantity()*cartDetail.getItem().getPrice());
                    return cartDetail;
                }).collect(Collectors.toList())
        );
        cartRepository.save(cart);
        return ResponseEntity.ok().body("okk");
    }

    @PutMapping("/{username}/item/like")
    public ResponseEntity<ResponseObject> likeItem(@PathVariable("username")String username, @RequestBody Item item){
        User user = userRepository.findByUsername(username);
        Optional<Item> findItem = itemRepository.findById(item.getId());
        Item itemToAdd = findItem.get();
        List<Item> items = user.getLikedItems();
        if (items.contains(itemToAdd)){
            items.remove(itemToAdd);
            user.setLikedItems(items);
            return ResponseEntity.ok().body(new ResponseObject("ok","item removed",userRepository.save(user)));
        }
        items.add(itemToAdd);
        user.setLikedItems(items);
        return ResponseEntity.ok().body(new ResponseObject("ok","add successfully",userRepository.save(user)));
    }

    @GetMapping("/{username}/item")
    public ResponseEntity<ResponseObject> getItemLikedByUser(@PathVariable String username){
        User user = userRepository.findByUsername(username);
        return ResponseEntity.ok().body(new ResponseObject("ok","all item like by "+username,itemRepository.findByUsers(user)));
    }
}
