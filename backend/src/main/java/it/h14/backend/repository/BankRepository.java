package it.h14.backend.repository;

import it.h14.backend.domain.Bank;
import it.h14.backend.domain.Position;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository {

    public Bank getBank();

    public List<Position> getPositions();
}
