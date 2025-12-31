package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.AccountStatusDto;
import com.example.Orr.entity.qualitative.AccountStatus;
import com.example.Orr.service.qualitative.AccountStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountStatusServiceImpl implements AccountStatusService {

    @Autowired
    private MongoTemplate mongoTemplate;

    AccountStatusServiceImpl(MongoTemplate mongoTemplate){
        this.mongoTemplate=mongoTemplate;
    }

    private AccountStatusDto toDto(AccountStatus accountStatus) {
        AccountStatusDto accountStatusDto= new AccountStatusDto();
        accountStatusDto.setId(accountStatus.getId());
        accountStatusDto.setAuditorOpinion(accountStatus.getAuditorOpinion());
        accountStatusDto.setAuditorQuality(accountStatus.getAuditorQuality());
        accountStatusDto.setRelationshipAge(accountStatus.getRelationshipAge());
        accountStatusDto.setNationalizationScheme(accountStatus.getNationalizationScheme());
        accountStatusDto.setLocationOfBusiness(accountStatus.getLocationOfBusiness());
        accountStatusDto.setYearInBusiness(accountStatus.getYearInBusiness());
        return accountStatusDto;
    }

    private AccountStatus toEntity(AccountStatusDto dto) {
        if (dto == null) return null;
        return new AccountStatus(
                dto.getId(),
                dto.getYearInBusiness(),
                dto.getLocationOfBusiness(),
                dto.getRelationshipAge(),
                dto.getAuditorQuality(),
                dto.getAuditorOpinion(),
                dto.getNationalizationScheme()
        );
    }

    @Override
    public List<AccountStatusDto> findAll() {
        return mongoTemplate.findAll(AccountStatus.class)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountStatusDto findById(Integer id) {
        AccountStatus accountStatus = mongoTemplate.findById(id, AccountStatus.class);
        return toDto(accountStatus);
    }

    @Override
    public AccountStatusDto create(AccountStatusDto dto) {
        AccountStatus saved = mongoTemplate.save(toEntity(dto));
        return toDto(saved);
    }

    @Override
    public AccountStatusDto updateById(Integer id, AccountStatusDto dto) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update()
                .set("yearInBusiness", dto.getYearInBusiness())
                .set("locationOfBusiness", dto.getLocationOfBusiness())
                .set("relationshipAge", dto.getRelationshipAge())
                .set("auditorQuality", dto.getAuditorQuality())
                .set("auditorOpinion", dto.getAuditorOpinion())
                .set("nationalizationScheme", dto.getNationalizationScheme());

        AccountStatus updated = mongoTemplate.findAndModify(query, update, AccountStatus.class);
        return toDto(updated);
    }

    @Override
    public void deleteById(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, AccountStatus.class);
    }
}
