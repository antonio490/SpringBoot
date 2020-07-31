package com.example.ascorbalan;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class StoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

	@Autowired
	MongoTemplate mongoTemplate;

	@Bean
	public Mongobee mongobee() {
		Mongobee runner = new Mongobee("mongodb://localhost:27017/store");
		runner.setMongoTemplate(mongoTemplate);
		runner.setChangeLogsScanPackage("asc.store.persistence");

		return runner;
	}
}
