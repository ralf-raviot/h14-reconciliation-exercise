package it.h14.backend.repository;

import it.h14.backend.mock.MockedData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SwarmRepository {

    MockedData mockedData;

    public void importData() {
        importSecurityData();
        importPortfolioData();
    }

    private void importSecurityData() {
    }

    private void importPortfolioData() {

    }
}
