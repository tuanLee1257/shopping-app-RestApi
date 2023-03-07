package com.project.study.ShoppingApp.models;

import com.project.study.ShoppingApp.models.user.User;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String itemName;
    private String description;
    private String imgUrl;
    private Double price;

    @OneToMany(mappedBy = "item",cascade = CascadeType.ALL)
    List<CartDetail> cartDetailSet;

    @ManyToMany(mappedBy = "likedItems",fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "itemComment",cascade = CascadeType.ALL)
    private List<Comments> commentsList;

    public Item() {
    }

    public Item(String itemName, String description, String imgUrl, Double price) {
        this.itemName = itemName;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
