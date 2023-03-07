package com.project.study.ShoppingApp.controllers;


import com.project.study.ShoppingApp.models.Comment;
import com.project.study.ShoppingApp.models.Item;
import com.project.study.ShoppingApp.models.auth.ResponseObject;
import com.project.study.ShoppingApp.models.user.User;
import com.project.study.ShoppingApp.repositories.CartRepository;
import com.project.study.ShoppingApp.repositories.CommentRepository;
import com.project.study.ShoppingApp.repositories.ItemRepository;
import com.project.study.ShoppingApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    CommentRepository commentRepository;

    @PostMapping("")
    public ResponseEntity<ResponseObject> comment(@Param("username") String username, @RequestBody Item item,@Param("comment")String comment){
        User user = userRepository.findByUsername(username);
        Optional<Item> findItem = itemRepository.findById(item.getId());
        Item foundItem = findItem.get();
        Comment addComment = new Comment();
        addComment.setUserComment(user);
        addComment.setComment(comment);
        addComment.setItemComment(foundItem);
        return ResponseEntity.ok().body(new ResponseObject("ok","add successfully",commentRepository.save(addComment)));
    }

    @GetMapping("/getByUser")
    public ResponseEntity<ResponseObject> getCommentByUser(@Param("username") String username){
        User user = userRepository.findByUsername(username);
        return ResponseEntity.ok().body(new ResponseObject("ok","add successfully",commentRepository.findByUserComment(user)));
    }

    @GetMapping("/getByItem")
    public ResponseEntity<ResponseObject> getCommentByItem(@RequestBody Item item){
        Optional<Item> findItem = itemRepository.findById(item.getId());
        Item foundItem = findItem.get();
        return ResponseEntity.ok().body(new ResponseObject("ok","add successfully",commentRepository.findByItemComment(foundItem)));
    }
}
