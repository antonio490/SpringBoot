package com.example.ascorbalan.persistence;

import com.example.ascorbalan.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

@Service
public class DbSeeder implements CommandLineRunner {
    private ProductSetRepository productSetRepository;
    private MongoTemplate mongoTemplate;

    public DbSeeder(ProductSetRepository productSetRepository, MongoTemplate mongoTemplate) {
        this.productSetRepository = productSetRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) {
        this.productSetRepository.deleteAll();
        this.mongoTemplate.dropCollection(PaymentOptions.class);

        /*
        Payment Options
         */

        PaymentOptions creditCardPayment = new PaymentOptions(PaymentType.CreditCard, 0);
        PaymentOptions payPalPayment = new PaymentOptions(PaymentType.PayPal, 1);
        PaymentOptions cashPayment = new PaymentOptions(PaymentType.Cash, 10);
        this.mongoTemplate.insert(creditCardPayment);
        this.mongoTemplate.insert(payPalPayment);
        this.mongoTemplate.insert(cashPayment);

        /*
        Lego Sets
         */

        Product milleniumFalcon = new Product(
                "Millennium Falcon",
                "Star Wars",
                ProductDifficulty.HARD,
                new DeliveryInfo(LocalDate.now().plusDays(1), 30, true),
                Arrays.asList(
                        new ProductReview("Dan", 7),
                        new ProductReview("Anna", 10),
                        new ProductReview("John", 8)
                ),
                creditCardPayment);

        Product skyPolice = new Product(
                "Sky Police Air Base",
                "City",
                ProductDifficulty.MEDIUM,
                new DeliveryInfo(LocalDate.now().plusDays(3), 50, true),
                Arrays.asList(
                        new ProductReview("Dan", 5),
                        new ProductReview("Andrew", 8)
                ),
                creditCardPayment);

        Product mcLarenSenna = new Product(
                "McLaren Senna",
                "Speed Champions",
                ProductDifficulty.EASY,
                new DeliveryInfo(LocalDate.now().plusDays(7), 70, false),
                Arrays.asList(
                        new ProductReview("Bogdan", 9),
                        new ProductReview("Christa", 9)
                ),
                payPalPayment);

        Product mindstormsEve = new Product(
                "MINDSTORMS EV3",
                "Mindstorms",
                ProductDifficulty.HARD,
                new DeliveryInfo(LocalDate.now().plusDays(10), 100, false),
                Arrays.asList(
                        new ProductReview("Cosmin", 10),
                        new ProductReview("Jane", 9),
                        new ProductReview("James", 10)
                ),
                cashPayment);

        Collection<Product> initialProducts = Arrays.asList(milleniumFalcon, mindstormsEve,mcLarenSenna,skyPolice);

        this.productSetRepository.insert(initialProducts);
    }
}
