package com.example.ascorbalan.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Product {
    private String id;
    private String name;
    private ProductDifficulty difficulty;
    private String theme;
    private Collection<ProductReview> reviews = new ArrayList<>();
    private DeliveryInfo deliveryInfo;

    public LegoSet(String name,
                   String theme,
                   ProductDifficulty difficulty,
                   DeliveryInfo deliveryInfo,
                   Collection<ProductReview> reviews){
        this.name = name;
        this.theme = theme;
        this.difficulty = difficulty;
        this.deliveryInfo = deliveryInfo;
        if(reviews != null){
            this.reviews = reviews;
        }
    }


    private int nbParts;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public ProductDifficulty getDifficulty() {
        return difficulty;
    }

    public String getTheme() {
        return theme;
    }

    public Collection<ProductReview> getReviews() {
        return Collections.unmodifiableCollection(this.reviews);
    }

    public int getNbParts() {
        return nbParts;
    }
}
