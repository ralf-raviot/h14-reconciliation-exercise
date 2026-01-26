package it.h14.backend.repository;

import it.h14.backend.domain.Bank;
import it.h14.backend.mock.MockedData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NeoBancaRepository implements BankRepository {

    MockedData mockedData;

    @Override
    public Bank getBank() {
        return Bank.NEOBANCA;
    }

    @Override
    public void importData() {
    }
}
