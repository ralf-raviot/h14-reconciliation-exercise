package it.h14.backend.mapper;

import it.h14.backend.domain.ConsolidatedPosition;
import it.h14.backend.domain.Portfolio;
import it.h14.backend.entity.PortfolioEntity;
import it.h14.backend.openapi.model.ConsolidatedPositionResponse;
import it.h14.backend.openapi.model.ConsolidationStatusResponse;
import it.h14.backend.openapi.model.PortfolioResponse;
import it.h14.backend.openapi.model.PortfolioSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {PositionMapper.class, SecurityMapper.class})
public interface PortfolioMapper {

    PortfolioEntity toEntity(Portfolio portfolio);

    Portfolio toDomain(PortfolioEntity portfolioEntity);

    PortfolioSummaryResponse toSummaryResponse(Portfolio portfolio);

    @Mapping(target = "id", source = "portfolio.id")
    @Mapping(target = "name", source = "portfolio.name")
    @Mapping(target = "clientId", source = "portfolio.clientId")
    @Mapping(target = "positions", source = "consolidatedPositions")
    PortfolioResponse toResponse(Portfolio portfolio, List<ConsolidatedPosition> consolidatedPositions);

    @Mapping(target = "status", expression = "java(toConsolidationStatusResponse(consolidatedPosition.computeConsolidationStatus()))")
    ConsolidatedPositionResponse toConsolidatedResponse(ConsolidatedPosition consolidatedPosition);

    default ConsolidationStatusResponse toConsolidationStatusResponse(ConsolidatedPosition.ConsolidationStatus status) {
        if (status == null) {
            return null;
        }
        return ConsolidationStatusResponse.fromValue(status.name());
    }
}