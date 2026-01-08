package com.example.Orr.config;

import com.example.Orr.entity.qualitative.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        initRelationshipManagerMaster();
        initAssessmentMaster();
    }



    private void initAssessmentMaster() {

        Query query = new Query(Criteria.where("_id").is("MASTER_ASSESSMENT"));

        if (mongoTemplate.exists(query, AssessmentMaster.class)) {
            return;
        }

        AssessmentMaster master = new AssessmentMaster();
        master.setId("MASTER_ASSESSMENT");

        //OASA

        Map<String, AssessmentGroup> oasa = new HashMap<>();

        oasa.put("OWNER_NET_WORTH",
                new AssessmentGroup(
                        "OASA_01",
                        "Owner's personal net worth",
                        List.of(
                                new AssessmentOption(1, "Excellent - > 50% of total business bank debt"),
                                new AssessmentOption(2, "Moderate - < 50% of total business bank debt")
                        )
                )
        );

        master.setOASA(oasa);


        //SOAA

        Map<String, AssessmentGroup> soaa = new HashMap<>();

        soaa.put("YEAR_IN_BUSINESS",
                new AssessmentGroup(
                        "SOAA_01",
                        "Year In Business",
                        List.of(
                                new AssessmentOption(1, "> 10 Years"),
                                new AssessmentOption(2, "> 6 Years - 10 Years"),
                                new AssessmentOption(3, "> 2 Years - 6 Years"),
                                new AssessmentOption(4, "<= 2 Years")
                        )
                )
        );

        master.setSOAA(soaa);


        //COAA

        Map<String, AssessmentGroup> coaa = new HashMap<>();

        coaa.put("BOUNCE_CHEQUES",
                new AssessmentGroup(
                        "COAA_01",
                        "Bounce Cheques",
                        List.of(
                                new AssessmentOption(1, "None"),
                                new AssessmentOption(2, "Less than 3 times"),
                                new AssessmentOption(3, "3 to 8 times"),
                                new AssessmentOption(4, "Greater than 8 times")
                        )
                )
        );

        master.setCOAA(coaa);

        mongoTemplate.save(master);
    }




    private void initRelationshipManagerMaster() {
        insertRelationshipManagerIfNotExist("RM001","Rahul","rahul@gmail.com","9371209546","pune");
        insertRelationshipManagerIfNotExist("RM002","Rohan","rohan@gmail.com","6576567645","Mumbai");
    }

    private void insertRelationshipManagerIfNotExist(String rmCode,String rmName,String email,String mobile,String branch) {

        Query query = new Query(Criteria.where("rmName").is(rmName));
        if (!mongoTemplate.exists(query, RelationshipManagerMaster.class)) {
            RelationshipManagerMaster relationshipManagerMaster =
                    new RelationshipManagerMaster(rmCode,rmName,email,mobile,branch, true);
            mongoTemplate.save(relationshipManagerMaster);
        }



    }


    private void initCurrencyMaster() {
        insertCurrencyIfNotExists("INR", "Indian Rupee", "₹");
        insertCurrencyIfNotExists("USD", "US Dollar", "$");
        insertCurrencyIfNotExists("SAR","Saudi Rial","﷼");
    }

    private void insertCurrencyIfNotExists(
            String currencyCode,
            String currencyName,
            String currencySymbol
    ) {
        Query query = new Query(Criteria.where("currencyCode").is(currencyCode));
        if (!mongoTemplate.exists(query, CurrencyMaster.class)) {
            CurrencyMaster cm =
                    new CurrencyMaster(currencyCode, currencyName, currencySymbol, true);
            mongoTemplate.save(cm);
        }
    }




    private void initIndustryMaster() {
        insertIndustryIfNotExists("manufacturing", "Manufacturing");
        insertIndustryIfNotExists("IT services", "IT Services");
        insertIndustryIfNotExists("agriculture", "Agriculture");
        insertIndustryIfNotExists("healthcare","HealthCare");
    }

    private void insertIndustryIfNotExists(
            String industryCode,
            String industryName
    ) {
        Query query = new Query(Criteria.where("industryCode").is(industryCode));
        if (!mongoTemplate.exists(query, IndustryMaster.class)) {
            IndustryMaster industry = new IndustryMaster();
            industry.setIndustryCode(industryCode);
            industry.setIndustryName(industryName);
            industry.setActive(true);
            mongoTemplate.save(industry);
        }
    }




    private void initClassificationMaster() {
        insertClassificationIfNotExists("micro", "Micro", 25);
        insertClassificationIfNotExists("small", "Small", 50);
        insertClassificationIfNotExists("medium", "Medium", 75);
    }

    private void insertClassificationIfNotExists(
            String classificationCode,
            String classificationName,
            Integer riskWeight
    ) {
        Query query =
                new Query(Criteria.where("classificationCode").is(classificationCode));

        if (!mongoTemplate.exists(query, ClassificationMaster.class)) {
            ClassificationMaster cm = new ClassificationMaster();
            cm.setClassificationCode(classificationCode);
            cm.setClassificationName(classificationName);
            cm.setRiskWeight(riskWeight);
            cm.setActive(true);
            mongoTemplate.save(cm);
        }
    }
}
