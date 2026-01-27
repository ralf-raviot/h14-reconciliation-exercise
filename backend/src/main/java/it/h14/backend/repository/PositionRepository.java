package it.h14.backend.repository;

import it.h14.backend.domain.PositionOrigin;
import it.h14.backend.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, String> {

    Set<PositionEntity> findByPortfolioIdAndOrigin(String portfolioId, PositionOrigin origin);

    Set<PositionEntity> findByClientIdAndOrigin(String clientId, PositionOrigin origin);

}
