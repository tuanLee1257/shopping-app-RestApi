package com.project.study.ShoppingApp.models.user;
import jakarta.persistence.*;
import lombok.Data;


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
}
