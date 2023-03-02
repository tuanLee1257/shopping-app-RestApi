package com.project.study.ShoppingApp.models;

import com.project.study.ShoppingApp.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    List<CartDetail> cartDetails;

    @ManyToOne
    @JoinColumn(name = "owned_user")
    private User user;

    private LocalDateTime orderDate;
    private LocalDateTime shipDate;

}
