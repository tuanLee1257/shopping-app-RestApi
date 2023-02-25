package com.project.study.ShoppingApp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Table
public class ShopItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String itemName;
    private String description;
    private String imgUrl;
    private Double price;
    private ArrayList<Long> likedBy;

    public ShopItem() {
    }

    public ShopItem(String itemName, String description, String imgUrl, Double price) {
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

    public ArrayList<Long> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(ArrayList<Long> likedBy) {
        this.likedBy = likedBy;
    }
}
