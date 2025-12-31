package com.example.Orr.service.qualitative;

import com.example.Orr.dto.qualitative.IndustriRiskDto;
import com.example.Orr.entity.qualitative.IndustriRisk;
import com.example.Orr.service.qualitative.IndustriRiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndustriRiskServiceImpl implements IndustriRiskService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<IndustriRiskDto> findAll() {
        return mongoTemplate.findAll(IndustriRisk.class)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public IndustriRiskDto findById(Integer id) {
        IndustriRisk industriRisk = mongoTemplate.findById(id, IndustriRisk.class);
        return industriRisk != null ? toDto(industriRisk) : null;
    }

    @Override
    public IndustriRiskDto createIndustriRisk(IndustriRiskDto industriRiskDto) {
        IndustriRisk industriRisk = toEntity(industriRiskDto);
        IndustriRisk saved = mongoTemplate.save(industriRisk);
        return toDto(saved);
    }

    @Override
    public IndustriRiskDto updateById(Integer id, IndustriRisk industriRisk) {
        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update()
                .set("competitiveness", industriRisk.getCompetitiveness())
                .set("environmentalConcerns", industriRisk.getEnvironmentalConcerns())
                .set("fiscalPolicyDependence", industriRisk.getFiscalPolicyDependence())
                .set("businessCyclicality", industriRisk.getBusinessCyclicality())
                .set("inflationSensitivity", industriRisk.getInflationSensitivity())
                .set("fxSensitivity", industriRisk.getFxSensitivity())
                .set("interestRateSensitivity", industriRisk.getInterestRateSensitivity())
                .set("industrySalesTrend", industriRisk.getIndustrySalesTrend())
                .set("industryProfitability", industriRisk.getIndustryProfitability())
                .set("industryStage", industriRisk.getIndustryStage())
                .set("importPenetration", industriRisk.getImportPenetration())
                .set("industryFailureRate", industriRisk.getIndustryFailureRate())
                .set("skilledLaborGap", industriRisk.getSkilledLaborGap())
                .set("productPositioning", industriRisk.getProductPositioning())
                .set("capitalSensitivity", industriRisk.getCapitalSensitivity())
                .set("technologyDependence", industriRisk.getTechnologyDependence());

        mongoTemplate.updateFirst(query, update, IndustriRisk.class);
        return findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, IndustriRisk.class);
    }

    private IndustriRiskDto toDto(IndustriRisk entity) {
IndustriRiskDto industriRiskDto=new IndustriRiskDto();
industriRiskDto.setIndustryProfitability(entity.getIndustryProfitability());
industriRiskDto.setId(entity.getId());
industriRiskDto.setIndustryStage(entity.getIndustryStage());
industriRiskDto.setCompetitiveness(entity.getCompetitiveness());
industriRiskDto.setIndustryFailureRate(entity.getIndustryFailureRate());
industriRiskDto.setCapitalSensitivity(entity.getCapitalSensitivity());
industriRiskDto.setIndustrySalesTrend(entity.getIndustrySalesTrend());
industriRiskDto.setFxSensitivity(entity.getFxSensitivity());
industriRiskDto.setEnvironmentalConcerns(entity.getEnvironmentalConcerns());
industriRiskDto.setBusinessCyclicality(entity.getBusinessCyclicality());
industriRiskDto.setFiscalPolicyDependence(entity.getFiscalPolicyDependence());
industriRiskDto.setImportPenetration(entity.getImportPenetration());
industriRiskDto.setInterestRateSensitivity(entity.getInterestRateSensitivity());
industriRiskDto.setProductPositioning(entity.getProductPositioning());
industriRiskDto.setSkilledLaborGap(entity.getSkilledLaborGap());
industriRiskDto.setTechnologyDependence(entity.getTechnologyDependence());
return industriRiskDto;
    }

    private IndustriRisk toEntity(IndustriRiskDto dto) {
        return new IndustriRisk(
                dto.getId(),
                dto.getCompetitiveness(),
                dto.getEnvironmentalConcerns(),
                dto.getFiscalPolicyDependence(),
                dto.getBusinessCyclicality(),
                dto.getInflationSensitivity(),
                dto.getFxSensitivity(),
                dto.getInterestRateSensitivity(),
                dto.getIndustrySalesTrend(),
                dto.getIndustryProfitability(),
                dto.getIndustryStage(),
                dto.getImportPenetration(),
                dto.getIndustryFailureRate(),
                dto.getSkilledLaborGap(),
                dto.getProductPositioning(),
                dto.getCapitalSensitivity(),
                dto.getTechnologyDependence()
        );
    }
}
