package it.h14.backend.repository;

import it.h14.backend.domain.Bank;
import it.h14.backend.domain.Portfolio;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankRepository {

    public Bank getBank();

    public List<Portfolio> getPortfolios();
}
