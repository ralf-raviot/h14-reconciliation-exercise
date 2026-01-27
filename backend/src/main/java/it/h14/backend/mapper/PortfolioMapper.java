package it.h14.backend.mapper;

import it.h14.backend.domain.Portfolio;
import it.h14.backend.entity.PortfolioEntity;
import it.h14.backend.openapi.model.PortfolioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PortfolioMapper {

    PortfolioEntity toEntity(Portfolio portfolio);

    Portfolio toDomain(PortfolioEntity portfolioEntity);

    Portfolio toDomain(PortfolioResponse portfolioResponse);

    PortfolioResponse toResponse(Portfolio portfolio);
}