package com.example.Orr.service.qualitative;


import com.example.Orr.dto.qualitative.IndustryMasterDto;
import com.example.Orr.entity.qualitative.IndustryMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndustryMasterServiceImpl implements IndustryMasterService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private IndustryMasterDto toDto(IndustryMaster entity) {
        IndustryMasterDto dto = new IndustryMasterDto();
        dto.setId(entity.getId());
        dto.setIndustryCode(entity.getIndustryCode());
        dto.setIndustryName(entity.getIndustryName());
        dto.setActive(entity.isActive());
        return dto;
    }

    private IndustryMaster toEntity(IndustryMasterDto dto) {
        IndustryMaster entity = new IndustryMaster();
        entity.setIndustryCode(dto.getIndustryCode());
        entity.setIndustryName(dto.getIndustryName());
        entity.setActive(dto.isActive());
        return entity;
    }

    @Override
    public IndustryMasterDto create(IndustryMasterDto dto) {

        Query query = new Query(Criteria.where("industryCode").is(dto.getIndustryCode()));
        if (mongoTemplate.exists(query, IndustryMaster.class)) {
            throw new RuntimeException("Industry already exists");
        }

        IndustryMaster entity = toEntity(dto);
        mongoTemplate.save(entity);
        return toDto(entity);
    }

    @Override
    public List<IndustryMasterDto> findAll() {
        return mongoTemplate.findAll(IndustryMaster.class)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public IndustryMasterDto findByCode(String industryCode) {
        Query query = new Query(Criteria.where("industryCode").is(industryCode));
        IndustryMaster entity = mongoTemplate.findOne(query, IndustryMaster.class);
        return entity != null ? toDto(entity) : null;
    }

    @Override
    public IndustryMasterDto update(String industryCode, IndustryMasterDto dto) {

        Query query = new Query(Criteria.where("industryCode").is(industryCode));
        Update update = new Update()
                .set("industryName", dto.getIndustryName())
                .set("active", dto.isActive());

        mongoTemplate.findAndModify(query, update, IndustryMaster.class);
        return findByCode(industryCode);
    }

    @Override
    public void deactivate(String industryCode) {
        Query query = new Query(Criteria.where("industryCode").is(industryCode));
        Update update = new Update().set("active", false);
        mongoTemplate.findAndModify(query, update, IndustryMaster.class);
    }
}

