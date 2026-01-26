package it.h14.backend.repository;

import it.h14.backend.entity.SecurityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SecurityRepository extends JpaRepository<SecurityEntity, Long> {
    Optional<SecurityEntity> findByIsin(String isin);
}
