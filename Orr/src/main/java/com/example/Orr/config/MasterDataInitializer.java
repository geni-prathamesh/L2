package com.example.Orr.config;

import com.example.Orr.entity.qualitative.CurrencyMaster;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@Configuration
public class MasterDataInitializer implements CommandLineRunner {

    private final MongoTemplate mongoTemplate;

    public MasterDataInitializer(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void run(String... args) {

        initCurrencyMaster();
        initIndustryMaster();
        initClassificationMaster();
    }

    private void initCurrencyMaster() {
        insertIfNotExists("INR", "Indian Rupee", "₹");
        insertIfNotExists("USD", "US Dollar", "$");
    }

    private void initIndustryMaster() {


    }

    private void initClassificationMaster() {


    }

    private void insertIfNotExists(String code, String name, String symbol) {
        Query query = new Query(Criteria.where("code").is(code));
        if (!mongoTemplate.exists(query, CurrencyMaster.class)) {
            CurrencyMaster cm = new CurrencyMaster(code, name, symbol, true);
            mongoTemplate.save(cm);
        }
    }
}
