package it.h14.backend.mapper;

import it.h14.backend.domain.Position;
import it.h14.backend.entity.PositionEntity;
import it.h14.backend.entity.SecurityEntity;
import it.h14.backend.openapi.model.PositionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PositionMapper {

    @Mapping(target = "isin", expression = "java(position.security().isin())")
    PositionEntity toEntity(Position position);

    Position toDomain(PositionEntity positionEntity, SecurityEntity security);

    PositionResponse toResponse(Position position);
}