package it.h14.backend.mapper;

import it.h14.backend.domain.Security;
import it.h14.backend.entity.SecurityEntity;
import it.h14.backend.openapi.model.SecurityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SecurityMapper {

    SecurityEntity toEntity(Security security);

    Security toDomain(SecurityEntity securityEntity);

    SecurityResponse toResponse(Security security);
}