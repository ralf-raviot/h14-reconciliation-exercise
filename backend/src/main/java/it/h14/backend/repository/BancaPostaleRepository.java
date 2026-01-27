package it.h14.backend.repository;

import it.h14.backend.domain.Bank;
import it.h14.backend.domain.Position;
import it.h14.backend.mock.MockedData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BancaPostaleRepository implements BankRepository {

    private final MockedData mockedData;

    @Override
    public Bank getBank() {
        return Bank.BANCAPOSTALE;
    }

    @Override
    public List<Position> getPositions() {
        return mockedData.getAllPositionsFromBancaPostale();
    }


}
