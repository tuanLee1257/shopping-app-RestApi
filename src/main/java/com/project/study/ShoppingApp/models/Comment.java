package com.project.study.ShoppingApp.models;

import com.project.study.ShoppingApp.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "owned_user")
    private User userComment;

    @ManyToOne
    @JoinColumn(name = "owned_item")
    private Item itemComment;
}
