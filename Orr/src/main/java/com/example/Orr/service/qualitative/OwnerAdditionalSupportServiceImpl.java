package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.OwnerAdditionalSupportDto;
import com.example.Orr.entity.qualitative.OwnerAdditionalSupport;
import com.example.Orr.service.qualitative.OwnerAdditionalSupportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerAdditionalSupportServiceImpl implements OwnerAdditionalSupportService {

    @Autowired
    private MongoTemplate mongoTemplate;

    private OwnerAdditionalSupportDto toDto(OwnerAdditionalSupport entity) {
       OwnerAdditionalSupportDto ownerAdditionalSupportDto=new OwnerAdditionalSupportDto();
       ownerAdditionalSupportDto.setId(entity.getId());
       ownerAdditionalSupportDto.setPersonalNetWorthScore(entity.getPersonalNetWorthScore());
       return ownerAdditionalSupportDto;
    }

    private OwnerAdditionalSupport toEntity(OwnerAdditionalSupportDto dto) {
        return new OwnerAdditionalSupport(dto.getId(), dto.getPersonalNetWorthScore());
    }

    @Override
    public List<OwnerAdditionalSupportDto> findAll() {
        return mongoTemplate.findAll(OwnerAdditionalSupport.class)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OwnerAdditionalSupportDto findById(Integer id) {
        OwnerAdditionalSupport entity = mongoTemplate.findById(id, OwnerAdditionalSupport.class);
        return entity != null ? toDto(entity) : null;
    }

    @Override
    public OwnerAdditionalSupportDto createOwnerAdditionalSupport(OwnerAdditionalSupportDto ownerAdditionalSupportDto) {
        OwnerAdditionalSupport entity = toEntity(ownerAdditionalSupportDto);
        mongoTemplate.save(entity);
        return toDto(entity);
    }

    @Override
    public OwnerAdditionalSupportDto updateById(Integer id, OwnerAdditionalSupport ownerAdditionalSupport) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update()
                .set("personalNetWorthScore", ownerAdditionalSupport.getPersonalNetWorthScore());

        mongoTemplate.updateFirst(query, update, OwnerAdditionalSupport.class);
        OwnerAdditionalSupport updated = mongoTemplate.findById(id, OwnerAdditionalSupport.class);
        return updated != null ? toDto(updated) : null;
    }

    @Override
    public void deleteById(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, OwnerAdditionalSupport.class);
    }
}
