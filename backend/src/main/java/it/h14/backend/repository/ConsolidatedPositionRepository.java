package it.h14.backend.repository;

import it.h14.backend.entity.ConsolidatedPositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsolidatedPositionRepository extends JpaRepository<ConsolidatedPositionEntity, Long> {
}
