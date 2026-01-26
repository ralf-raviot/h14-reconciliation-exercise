package it.h14.backend.repository;

import it.h14.backend.domain.Bank;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository {

    public Bank getBank();

    public void importData();
}
