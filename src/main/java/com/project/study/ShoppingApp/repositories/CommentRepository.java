package com.project.study.ShoppingApp.repositories;

import com.project.study.ShoppingApp.models.Comment;
import com.project.study.ShoppingApp.models.Item;
import com.project.study.ShoppingApp.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByUserComment(User user);
    List<Comment> findByItemComment(Item item);
}
