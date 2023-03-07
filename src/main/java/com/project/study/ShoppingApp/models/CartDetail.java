package com.project.study.ShoppingApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDetail {
//    @EmbeddedId
//    private CartDetailKey id = new CartDetailKey();
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private Double price;
    private Integer quantity;

}

//@Embeddable
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//class CartDetailKey implements Serializable {
//    @Column(name = "cart_id")
//    Long cart_id;
//    @Column(name = "item_id")
//    Long item_id;
//}