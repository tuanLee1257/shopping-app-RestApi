package com.project.study.ShoppingApp.controllers;

import com.project.study.ShoppingApp.models.Cart;
import com.project.study.ShoppingApp.models.CartDetail;
import com.project.study.ShoppingApp.models.Comment;
import com.project.study.ShoppingApp.models.Item;
import com.project.study.ShoppingApp.models.auth.ResponseObject;
import com.project.study.ShoppingApp.models.user.User;
import com.project.study.ShoppingApp.repositories.CartRepository;
import com.project.study.ShoppingApp.repositories.CommentRepository;
import com.project.study.ShoppingApp.repositories.ItemRepository;
import com.project.study.ShoppingApp.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

}
