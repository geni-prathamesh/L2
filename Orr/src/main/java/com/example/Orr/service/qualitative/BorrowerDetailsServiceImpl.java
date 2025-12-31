package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.BorrowerDetailsDto;
import com.example.Orr.entity.qualitative.BorrowerDetails;
import com.example.Orr.service.qualitative.BorrowerDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BorrowerDetailsServiceImpl implements BorrowerDetailsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private BorrowerDetailsDto toDto(BorrowerDetails entity) {
       BorrowerDetailsDto borrowerDetailsDto=new BorrowerDetailsDto();
       borrowerDetailsDto.setUUId(entity.getUUId());
       borrowerDetailsDto.setBorrowerId(entity.getBorrowerId());
       borrowerDetailsDto.setId(entity.getId());
       borrowerDetailsDto.setName(entity.getName());
       borrowerDetailsDto.setCurrency(entity.getCurrency());
       borrowerDetailsDto.setClassification(entity.getClassification());
       borrowerDetailsDto.setIndustry(entity.getIndustry());
       borrowerDetailsDto.setAssessmentDate(entity.getAssessmentDate());
       borrowerDetailsDto.setRelationshipManager(entity.getRelationshipManager());
       borrowerDetailsDto.setCurrencyType(entity.getCurrencyType());
       return borrowerDetailsDto;
    }

    private BorrowerDetails toEntity(BorrowerDetailsDto dto) {
        return new BorrowerDetails(
                null,
                dto.getUUId(),
                dto.getBorrowerId(),
                dto.getName(),
                dto.getCurrencyType(),
                dto.getCurrency(),
                dto.getClassification(),
                dto.getAssessmentDate(),
                dto.getRelationshipManager(),
                dto.getIndustry()
        );
    }

    @Override
    public List<BorrowerDetailsDto> findAll() {
        List<BorrowerDetails> list = mongoTemplate.findAll(BorrowerDetails.class);
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public BorrowerDetailsDto findByBorrowerId(Integer borrowerId) {
        Query query = new Query(Criteria.where("borrowerId").is(borrowerId));
        BorrowerDetails entity = mongoTemplate.findOne(query, BorrowerDetails.class);
        return entity != null ? toDto(entity) : null;
    }

    @Override
    public BorrowerDetailsDto create(BorrowerDetailsDto borrowerDetailsDto) {
        BorrowerDetails entity = toEntity(borrowerDetailsDto);
        entity.setUUId(UUID.randomUUID().toString());
        mongoTemplate.save(entity);
        return toDto(entity);
    }

    @Override
    public BorrowerDetailsDto updateByBorrowerId(Integer borrowerId, BorrowerDetailsDto borrowerDetailsDto) {
        Query query = new Query(Criteria.where("borrowerId").is(borrowerId));
        Update update = new Update()
                .set("name", borrowerDetailsDto.getName())
                .set("currencyType", borrowerDetailsDto.getCurrencyType())
                .set("currency", borrowerDetailsDto.getCurrency())
                .set("classification", borrowerDetailsDto.getClassification())
                .set("assessmentDate", borrowerDetailsDto.getAssessmentDate())
                .set("relationshipManager", borrowerDetailsDto.getRelationshipManager())
                .set("industry", borrowerDetailsDto.getIndustry());

        mongoTemplate.findAndModify(query, update, BorrowerDetails.class);
        return findByBorrowerId(borrowerId);
    }

    @Override
    public void deleteByBorrowerId(Integer borrowerId) {
        Query query = new Query(Criteria.where("borrowerId").is(borrowerId));
        mongoTemplate.remove(query, BorrowerDetails.class);
    }
}
