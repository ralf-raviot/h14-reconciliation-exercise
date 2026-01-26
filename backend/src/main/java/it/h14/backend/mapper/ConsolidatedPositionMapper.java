package it.h14.backend.mapper;

import it.h14.backend.domain.ConsolidatedPosition;
import it.h14.backend.domain.Position;
import it.h14.backend.domain.Security;
import it.h14.backend.entity.ConsolidatedPositionEntity;
import it.h14.backend.entity.PositionEntity;
import it.h14.backend.entity.SecurityEntity;
import it.h14.backend.openapi.model.ConsolidatedPositionResponse;
import it.h14.backend.openapi.model.PositionResponse;
import it.h14.backend.openapi.model.SecurityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ConsolidatedPositionMapper {

    ConsolidatedPosition toDomain(ConsolidatedPositionEntity entity);

    ConsolidatedPositionEntity toEntity(ConsolidatedPosition domain);

    ConsolidatedPosition toDomain(ConsolidatedPositionResponse response);

    ConsolidatedPositionResponse toResponse(ConsolidatedPosition domain);

    Security toDomain(SecurityEntity entity);

    SecurityEntity toEntity(Security domain);

    Security toDomain(SecurityResponse response);

    SecurityResponse toResponse(Security domain);

    Position toDomain(PositionEntity entity);

    PositionEntity toEntity(Position domain);

    Position toDomain(PositionResponse response);

    PositionResponse toResponse(Position domain);

    List<Position> toDomainList(Set<PositionEntity> entities);

    Set<PositionEntity> toEntitySet(List<Position> domains);

    default Map<Security, ConsolidatedPosition> toDomainMap(Set<ConsolidatedPositionEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toMap(ConsolidatedPosition::security, p -> p));
    }

    default Set<ConsolidatedPositionEntity> toEntitySetFromMap(Map<Security, ConsolidatedPosition> map) {
        if (map == null) return null;
        return map.values().stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    default Map<String, ConsolidatedPositionResponse> toResponseMap(Map<Security, ConsolidatedPosition> domainMap) {
        if (domainMap == null) return null;
        return domainMap.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().isin(), e -> toResponse(e.getValue())));
    }

    default Map<String, ConsolidatedPositionResponse> toResponseMapFromEntities(Set<ConsolidatedPositionEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toMap(p -> p.security().isin(), this::toResponse));
    }

    default Map<Security, ConsolidatedPosition> toDomainMapFromResponse(Map<String, ConsolidatedPositionResponse> responseMap) {
        if (responseMap == null) return null;
        return responseMap.values().stream()
                .map(this::toDomain)
                .collect(Collectors.toMap(ConsolidatedPosition::security, p -> p));
    }
}
