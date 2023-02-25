package com.project.study.ShoppingApp.controllers;

import com.project.study.ShoppingApp.models.ResponseObject;
import com.project.study.ShoppingApp.models.ShopItem;
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
        List<ShopItem> shopItemList = shopItemRepo.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Query successfully", shopItemList));
//        return shopItemList.isEmpty() ?
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("ok","Shop item empty" , shopItemList))
//                : ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Items found", shopItemList));
    }

    @GetMapping("/search")
    ResponseEntity<ResponseObject> searchItems(@RequestParam(name = "search")String search){
        List<ShopItem> shopItemList = shopItemRepo.searchItem(search);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Query successfully", shopItemList));
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable("id") Long id) {
        Optional<ShopItem> shopItem = shopItemRepo.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Query successfully", shopItem));
    }

    @PostMapping("")
    ResponseEntity<ResponseObject> insertItem(@RequestBody ShopItem item) {
        List<ShopItem> foundItems = shopItemRepo.findByItemName(item.getItemName().trim());
        if (foundItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Insert successfully", shopItemRepo.save(item)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("false", "Insert failed", ""));
        }
    }
}
