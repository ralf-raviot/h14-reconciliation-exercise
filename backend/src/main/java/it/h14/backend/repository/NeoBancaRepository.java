package it.h14.backend.repository;

import it.h14.backend.domain.Bank;
import it.h14.backend.domain.Portfolio;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NeoBancaRepository implements BankRepository {
    @Override
    public Bank getBank() {
        return Bank.NEOBANCA;
    }

    @Override
    public List<Portfolio> getPortfolios() {
        return List.of();
    }
}
