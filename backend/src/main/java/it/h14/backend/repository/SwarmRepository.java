package it.h14.backend.repository;

import it.h14.backend.domain.Portfolio;
import it.h14.backend.domain.Position;
import it.h14.backend.domain.Security;
import it.h14.backend.mock.MockedData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SwarmRepository {

    private final MockedData mockedData;

    public List<Portfolio> getPortfolios() {
        return mockedData.getAllPortfolios();
    }

    public List<Security> getSecurities() {
        return mockedData.getAllSecurities();
    }

    public List<Position> getPositions() {
        return mockedData.getAllPositionsFromSwarm();
    }
}
