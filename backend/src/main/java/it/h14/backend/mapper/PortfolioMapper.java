package it.h14.backend.mapper;

import it.h14.backend.domain.ConsolidatedPosition;
import it.h14.backend.domain.Portfolio;
import it.h14.backend.entity.PortfolioEntity;
import it.h14.backend.openapi.model.PortfolioResponse;
import it.h14.backend.openapi.model.PortfolioSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PortfolioMapper {

    PortfolioEntity toEntity(Portfolio portfolio);

    Portfolio toDomain(PortfolioEntity portfolioEntity);

    PortfolioSummaryResponse toSummaryResponse(Portfolio portfolio);

    default PortfolioResponse toResponse(Portfolio portfolio, List<ConsolidatedPosition> consolidatedPositions) {
        var portfolioResponse = new PortfolioResponse();
        portfolioResponse.setId(portfolio.id());
        portfolioResponse.setName(portfolio.name());
        portfolioResponse.setClientId(portfolio.clientId());
        portfolioResponse.setPositions(consolidatedPositions);
        return portfolioResponse;
    }
}