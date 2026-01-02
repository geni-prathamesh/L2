package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.ClassificationMasterDto;
import com.example.Orr.entity.qualitative.ClassificationMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassificationMasterServiceImpl implements ClassificationMasterService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private ClassificationMasterDto toDto(ClassificationMaster entity) {
        ClassificationMasterDto dto = new ClassificationMasterDto();
        dto.setId(entity.getId());
        dto.setClassificationCode(entity.getClassificationCode());
        dto.setClassificationName(entity.getClassificationName());
        dto.setRiskWeight(entity.getRiskWeight());
        dto.setActive(entity.isActive());
        return dto;
    }

    private ClassificationMaster toEntity(ClassificationMasterDto dto) {
        ClassificationMaster entity = new ClassificationMaster();
        entity.setClassificationCode(dto.getClassificationCode());
        entity.setClassificationName(dto.getClassificationName());
        entity.setRiskWeight(dto.getRiskWeight());
        entity.setActive(dto.isActive());
        return entity;
    }

    @Override
    public ClassificationMasterDto create(ClassificationMasterDto dto) {

        Query query = new Query(
                Criteria.where("classificationCode").is(dto.getClassificationCode())
        );

        if (mongoTemplate.exists(query, ClassificationMaster.class)) {
            throw new RuntimeException("Classification already exists");
        }

        ClassificationMaster entity = toEntity(dto);
        mongoTemplate.save(entity);
        return toDto(entity);
    }

    @Override
    public List<ClassificationMasterDto> findAll() {
        return mongoTemplate.findAll(ClassificationMaster.class)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClassificationMasterDto findByCode(String classificationCode) {
        Query query = new Query(
                Criteria.where("classificationCode").is(classificationCode)
        );
        ClassificationMaster entity = mongoTemplate.findOne(query, ClassificationMaster.class);
        return entity != null ? toDto(entity) : null;
    }

    @Override
    public ClassificationMasterDto update(String classificationCode,
                                          ClassificationMasterDto dto) {

        Query query = new Query(
                Criteria.where("classificationCode").is(classificationCode)
        );

        Update update = new Update()
                .set("classificationName", dto.getClassificationName())
                .set("riskWeight", dto.getRiskWeight())
                .set("active", dto.isActive());

        mongoTemplate.findAndModify(query, update, ClassificationMaster.class);
        return findByCode(classificationCode);
    }

    @Override
    public void deactivate(String classificationCode) {
        Query query = new Query(
                Criteria.where("classificationCode").is(classificationCode)
        );
        Update update = new Update().set("active", false);
        mongoTemplate.findAndModify(query, update, ClassificationMaster.class);
    }
}
