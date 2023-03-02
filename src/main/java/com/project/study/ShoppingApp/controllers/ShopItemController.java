package com.project.study.ShoppingApp.controllers;

import com.project.study.ShoppingApp.models.auth.ResponseObject;
import com.project.study.ShoppingApp.models.Item;
import com.project.study.ShoppingApp.repositories.ShopItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shop")
public class ShopItemController {
    @Autowired
    private ShopItemRepo shopItemRepo;

    @GetMapping("")
    ResponseEntity<ResponseObject> findAllShopItems() {
        List<Item> itemList = shopItemRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Query successfully", itemList));
    }

    @GetMapping("/search")
    ResponseEntity<ResponseObject> searchItems(@RequestParam(name = "name")Optional< String> name){
        List<Item> itemList = shopItemRepo.searchItem(name.orElse(""));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Query successfully", itemList));
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable("id") Long id) {
        Optional<Item> shopItem = shopItemRepo.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Query successfully", shopItem));
    }

    @PostMapping("")
    ResponseEntity<ResponseObject> insertItem(@RequestBody Item item) {
        List<Item> foundItems = shopItemRepo.findByItemName(item.getItemName().trim());
        if (foundItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Insert successfully", shopItemRepo.save(item)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("false", "Insert failed", ""));
        }
    }
}
