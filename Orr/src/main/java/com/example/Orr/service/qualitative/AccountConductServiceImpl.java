package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.AccountConductDto;
import com.example.Orr.entity.qualitative.AccountConduct;
import com.example.Orr.service.qualitative.AccountConductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountConductServiceImpl implements AccountConductService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<AccountConductDto> findAll() {
        List<AccountConduct> list = mongoTemplate.findAll(AccountConduct.class);
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public AccountConductDto findById(Integer id) {
        AccountConduct accountConduct = mongoTemplate.findById(id, AccountConduct.class);
        return accountConduct != null ? toDto(accountConduct) : null;
    }

    @Override
    public AccountConductDto create(AccountConductDto dto) {
        AccountConduct entity = toEntity(dto);
        AccountConduct saved = mongoTemplate.save(entity);
        return toDto(saved);
    }

    @Override
    public AccountConductDto updateById(Integer id, AccountConductDto dto) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update()
                .set("bounceCheques", dto.getBounceCheques())
                .set("ongoingCreditRelationship", dto.getOngoingCreditRelationship())
                .set("delayInInstallments", dto.getDelayInInstallments())
                .set("delinquencyHistory", dto.getDelinquencyHistory())
                .set("writeOff", dto.getWriteOff())
                .set("fraudLitigation", dto.getFraudLitigation());

        AccountConduct updated = mongoTemplate.findAndModify(query, update, AccountConduct.class);
        return updated != null ? toDto(updated) : null;
    }

    @Override
    public void deleteById(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, AccountConduct.class);
    }

    private AccountConductDto toDto(AccountConduct entity) {
        AccountConductDto accountConductDto= new AccountConductDto();
                accountConductDto.setId(entity.getId());
                accountConductDto.setBounceCheques(entity.getBounceCheques());
                accountConductDto.setWriteOff(entity.getWriteOff());
                accountConductDto.setFraudLitigation(entity.getFraudLitigation());
                accountConductDto.setDelinquencyHistory(entity.getDelinquencyHistory());
                accountConductDto.setDelayInInstallments(entity.getDelayInInstallments());
                accountConductDto.setOngoingCreditRelationship(entity.getOngoingCreditRelationship());

        return accountConductDto;
    }

    private AccountConduct toEntity(AccountConductDto dto) {
        return new AccountConduct(
                dto.getId(),
                dto.getBounceCheques(),
                dto.getOngoingCreditRelationship(),
                dto.getDelayInInstallments(),
                dto.getDelinquencyHistory(),
                dto.getWriteOff(),
                dto.getFraudLitigation()
        );
    }
}
