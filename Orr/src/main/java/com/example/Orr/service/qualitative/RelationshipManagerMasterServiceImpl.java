package com.example.Orr.service.qualitative;


import com.example.Orr.dto.qualitative.RelationshipManagerMasterDto;
import com.example.Orr.entity.qualitative.RelationshipManagerMaster;
import com.example.Orr.service.qualitative.RelationshipManagerMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelationshipManagerMasterServiceImpl
        implements RelationshipManagerMasterService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public RelationshipManagerMasterDto create(RelationshipManagerMasterDto dto) {

        // Unique RM Code check
        Query query = new Query(Criteria.where("rmCode").is(dto.getRmCode()));
        if (mongoTemplate.exists(query, RelationshipManagerMaster.class)) {
            throw new RuntimeException("RM Code already exists");
        }

        RelationshipManagerMaster entity = toEntity(dto);
        entity.setActive(true);

        RelationshipManagerMaster saved =
                mongoTemplate.save(entity);

        return toDto(saved);
    }

    @Override
    public List<RelationshipManagerMasterDto> getAll() {
        return mongoTemplate.findAll(RelationshipManagerMaster.class)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RelationshipManagerMasterDto getById(String id) {

        RelationshipManagerMaster entity =
                mongoTemplate.findById(id, RelationshipManagerMaster.class);

        if (entity == null) {
            throw new RuntimeException("Relationship Manager not found");
        }
        return toDto(entity);
    }

    @Override
    public RelationshipManagerMasterDto update(String id, RelationshipManagerMasterDto dto) {

        Query query = new Query(Criteria.where("_id").is(id));

        Update update = new Update()
                .set("rmName", dto.getRmName())
                .set("email", dto.getEmail())
                .set("mobile", dto.getMobile())
                .set("branch", dto.getBranch())
                .set("active", dto.getActive());

        RelationshipManagerMaster updated =
                mongoTemplate.findAndModify(
                        query,
                        update,
                        RelationshipManagerMaster.class
                );

        if (updated == null) {
            throw new RuntimeException("Relationship Manager not found");
        }

        return toDto(
                mongoTemplate.findById(id, RelationshipManagerMaster.class)
        );
    }

    @Override
    public void delete(String id) {

        Query query = new Query(Criteria.where("_id").is(id));

        mongoTemplate.remove(query, RelationshipManagerMaster.class);
    }


    private RelationshipManagerMaster toEntity(RelationshipManagerMasterDto dto) {
        return new RelationshipManagerMaster(
                dto.getId(),
                dto.getRmCode(),
                dto.getRmName(),
                dto.getEmail(),
                dto.getMobile(),
                dto.getBranch(),
                dto.getActive()
        );
    }

    private RelationshipManagerMasterDto toDto(RelationshipManagerMaster entity) {
RelationshipManagerMasterDto relationshipManagerMasterDto=new RelationshipManagerMasterDto();
relationshipManagerMasterDto.setId(entity.getId());
relationshipManagerMasterDto.setRmCode(entity.getRmCode());
relationshipManagerMasterDto.setRmName(entity.getRmName());
relationshipManagerMasterDto.setEmail(entity.getEmail());
relationshipManagerMasterDto.setMobile(entity.getMobile());
relationshipManagerMasterDto.setBranch(entity.getBranch());
relationshipManagerMasterDto.setActive(entity.getActive());
return relationshipManagerMasterDto;
    }
}

