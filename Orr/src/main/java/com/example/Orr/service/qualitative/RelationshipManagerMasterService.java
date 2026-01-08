package com.example.Orr.service.qualitative;



import com.example.Orr.dto.qualitative.RelationshipManagerMasterDto;

import java.util.List;

public interface RelationshipManagerMasterService {

    RelationshipManagerMasterDto create(RelationshipManagerMasterDto dto);

    List<RelationshipManagerMasterDto> getAll();

    RelationshipManagerMasterDto getById(String id);

    RelationshipManagerMasterDto update(String id, RelationshipManagerMasterDto dto);

    void delete(String id);
}
