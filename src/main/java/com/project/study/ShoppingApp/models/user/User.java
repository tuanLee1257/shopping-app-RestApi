package com.project.study.ShoppingApp.models.user;

import com.project.study.ShoppingApp.models.Item;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    private String displayName;
    private String avatar;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_liked_item",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
    private List<Item> likedItems = new ArrayList<>();

//    @OneToMany(mappedBy = "userComment",cascade = CascadeType.PERSIST)
//    private List<Comments> commentsList;

}
