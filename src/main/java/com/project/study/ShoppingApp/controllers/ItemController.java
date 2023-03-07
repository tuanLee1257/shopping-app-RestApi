package com.project.study.ShoppingApp.controllers;

import com.project.study.ShoppingApp.models.auth.ResponseObject;
import com.project.study.ShoppingApp.models.Item;
import com.project.study.ShoppingApp.models.user.User;
import com.project.study.ShoppingApp.repositories.ItemRepository;
import com.project.study.ShoppingApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
     ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("")
    ResponseEntity<ResponseObject> findAllShopItems() {
        List<Item> itemList = itemRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Query successfully", itemList));
    }


    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertItem(@RequestBody Item item) {
        List<Item> foundItems = itemRepository.findByItemName(item.getItemName().trim());
        if (foundItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Insert successfully", itemRepository.save(item)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("false", "Insert failed", ""));
        }
    }


    @GetMapping("/search")
    ResponseEntity<ResponseObject> searchItems(@RequestParam(name = "name")Optional< String> name){
        List<Item> itemList = itemRepository.searchItem(name.orElse(""));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Query successfully", itemList));
    }

    @GetMapping("/getById/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable("id") Long id) {
        Optional<Item> shopItem = itemRepository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Query successfully", shopItem));
    }

    @PutMapping("/like")
    public ResponseEntity<ResponseObject> likeItem(@Param("username") String username, @RequestBody Item item){
        User user = userRepository.findByUsername(username);
        Optional<Item> findItem = itemRepository.findById(item.getId());
        Item foundItem = findItem.get();
        List<Item> items = user.getLikedItems();
        if (items.contains(foundItem)){
            items.remove(foundItem);
            user.setLikedItems(items);
            return ResponseEntity.ok().body(new ResponseObject("ok","item removed",userRepository.save(user)));
        }
        items.add(foundItem);
        user.setLikedItems(items);
        return ResponseEntity.ok().body(new ResponseObject("ok","add successfully",userRepository.save(user)));
    }
}
