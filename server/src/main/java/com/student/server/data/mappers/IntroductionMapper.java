package com.student.server.data.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.model.tables.records.IntroductionRecord;
import com.student.server.data.dto.IntroductionDTO;
import com.student.server.data.request.IntroductionRequest;
import com.student.server.data.response.IntroductionResponse;
import org.jooq.JSON;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface IntroductionMapper {

    IntroductionMapper INSTANCE = Mappers.getMapper(IntroductionMapper.class);

    ObjectMapper objectMapper = new ObjectMapper();

    IntroductionDTO toIntroductionDTO(IntroductionRecord introduction);

    @Mapping(source = "introduction", target = "introduction", qualifiedByName = "mapIntroduction")
    @Mapping(source = "partner", target = "partner", qualifiedByName = "mapPartner")
    @Mapping(source = "founderAdvisor", target = "founderAdvisor", qualifiedByName = "mapFounderAdvisor")
    @Mapping(source = "competitiveAdvantages", target = "competitiveAdvantages", qualifiedByName = "mapCompetitiveAdvantages")
    @Mapping(source = "content", target = "content", qualifiedByName = "mapContent")
    @Mapping(source = "coreValue", target = "coreValue", qualifiedByName = "mapCoreValue")
    IntroductionDTO toIntroductionDTO(IntroductionRequest introduction);

    @Mapping(source = "introduction", target = "introduction", qualifiedByName = "mapIntroductionRevert")
    @Mapping(source = "partner", target = "partner", qualifiedByName = "mapPartnerRevert")
    @Mapping(source = "founderAdvisor", target = "founderAdvisor", qualifiedByName = "mapFounderAdvisorRevert")
    @Mapping(source = "competitiveAdvantages", target = "competitiveAdvantages", qualifiedByName = "mapCompetitiveAdvantagesRevert")
    @Mapping(source = "content", target = "content", qualifiedByName = "mapContentRevert")
    @Mapping(source = "coreValue", target = "coreValue", qualifiedByName = "mapCoreValueRevert")
    IntroductionResponse toResponse(IntroductionDTO introduction);

    @AfterMapping
    default void setDefault(@MappingTarget IntroductionDTO dto, IntroductionRequest introduction) {
        dto.setCreatedAt(LocalDateTime.now());
        dto.setUpdatedAt(LocalDateTime.now());
        dto.setIsDeleted(false);
        dto.setIsSelected(false);
    }

    @Named("mapIntroduction")
    default JSON mapIntroduction(List<String> introduction) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(introduction);
        return JSON.valueOf(jsonString);
    }

    @Named("mapPartner")
    default JSON mapPartner(List<IntroductionRequest.Partner> partner) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(partner);
        return JSON.valueOf(jsonString);
    }

    @Named("mapFounderAdvisor")
    default JSON mapFounderAdvisor(List<IntroductionRequest.FounderAdvisor> founderAdvisor)
            throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(founderAdvisor);
        return JSON.valueOf(jsonString);
    }

    @Named("mapCompetitiveAdvantages")
    default JSON mapCompetitiveAdvantages(List<IntroductionRequest.CompetitiveAdvantages> competitiveAdvantages)
            throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(competitiveAdvantages);
        return JSON.valueOf(jsonString);
    }

    @Named("mapContent")
    default JSON mapContent(List<IntroductionRequest.Content> content) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(content);
        return JSON.valueOf(jsonString);
    }

    @Named("mapCoreValue")
    default JSON mapCoreValue(IntroductionRequest.CoreValue coreValue) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(coreValue);
        return JSON.valueOf(jsonString);
    }

    @Named("mapIntroductionRevert")
    default List<String> mapIntroductionRevert(JSON introduction) throws JsonProcessingException {
        if (introduction == null) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(introduction.toString(), new TypeReference<List<String>>() {
        });
    }

    @Named("mapPartnerRevert")
    default List<IntroductionResponse.Partner> mapPartnerRevert(JSON partner) throws JsonProcessingException {
        if (partner == null) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(partner.toString(), new TypeReference<List<IntroductionResponse.Partner>>() {
        });
    }

    @Named("mapFounderAdvisorRevert")
    default List<IntroductionResponse.FounderAdvisor> mapFounderAdvisorRevert(JSON founderAdvisor)
            throws JsonProcessingException {
        if (founderAdvisor == null) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(founderAdvisor.toString(), new TypeReference<List<IntroductionResponse.FounderAdvisor>>() {
        });
    }

    @Named("mapCompetitiveAdvantagesRevert")
    default List<IntroductionResponse.CompetitiveAdvantages> mapCompetitiveAdvantagesRevert(JSON competitiveAdvantages)
            throws JsonProcessingException {
        if (competitiveAdvantages == null) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(competitiveAdvantages.toString(), new TypeReference<List<IntroductionResponse.CompetitiveAdvantages>>() {
        });
    }

    @Named("mapContentRevert")
    default List<IntroductionResponse.Content> mapContentRevert(JSON content) throws JsonProcessingException {
        if (content == null) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(content.toString(), new TypeReference<List<IntroductionResponse.Content>>() {
        });
    }

    @Named("mapCoreValueRevert")
    default IntroductionResponse.CoreValue mapCoreValueRevert(JSON coreValue) throws JsonProcessingException {
        if (coreValue == null) {
            return null;
        }
        return objectMapper.readValue(coreValue.toString(), new TypeReference<IntroductionResponse.CoreValue>() {
        });
    }

}
