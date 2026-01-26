package it.h14.backend.repository;

import it.h14.backend.mapper.PortfolioMapper;
import it.h14.backend.mock.MockedData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SwarmRepository {

    private final MockedData mockedData;
    private final PortfolioRepository portfolioRepository;
    private final PortfolioMapper portfolioMapper;

    public void importData() {
        mockedData.getAllPortfolios().forEach(portfolio -> {
            portfolioRepository.save(portfolioMapper.toEntity(portfolio));
        });
    }
}
