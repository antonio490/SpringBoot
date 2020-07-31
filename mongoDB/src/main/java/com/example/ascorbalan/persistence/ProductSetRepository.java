package com.example.ascorbalan.persistence;

import com.example.ascorbalan.model.Product;
import com.example.ascorbalan.model.ProductDifficulty;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductSetRepository extends MongoRepository<Product, String>, QuerydslPredicateExecutor<Product> {
    Collection<Product> findAllByThemeContains(String theme, Sort sort);
    Collection<Product> findAllByDifficultyAndNameStartsWith(ProductDifficulty difficulty, String name);
    Collection<Product> findAllBy(TextCriteria textCriteria);

    @Query("{'delivery.deliveryFee' : {$lt : ?0}}")
    Collection<Product> findAllByDeliveryPriceLessThan(int price);

    @Query("{'reviews.rating' : {$eq : 10}}")
    Collection<Product> findAllByGreatReviews();

    @Query("{'paymentOptions.id' : ?0}")
    Collection<Product> findByPaymentOptionId(String id);
}
