package it.h14.backend.repository;

import it.h14.backend.domain.Bank;
import it.h14.backend.mock.MockedData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BancaPostaleRepository implements BankRepository {

    MockedData mockedData;

    @Override
    public Bank getBank() {
        return Bank.BANCAPOSTALE;
    }

    @Override
    public void importData() {
    }
}
