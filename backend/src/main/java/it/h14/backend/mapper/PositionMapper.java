package it.h14.backend.mapper;

import it.h14.backend.domain.Position;
import it.h14.backend.entity.PositionEntity;
import it.h14.backend.openapi.model.PositionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PositionMapper {

    PositionEntity toEntity(Position position);

    Position toDto(PositionEntity positionEntity);

    Position toDomain(PositionEntity positionEntity);

    PositionResponse toResponse(Position position);
}