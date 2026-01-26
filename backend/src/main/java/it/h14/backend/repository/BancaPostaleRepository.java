package it.h14.backend.repository;

import it.h14.backend.domain.Bank;
import it.h14.backend.entity.ConsolidatedPositionEntity;
import it.h14.backend.mapper.ConsolidatedPositionMapper;
import it.h14.backend.mock.MockedData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BancaPostaleRepository implements BankRepository {

    private final MockedData mockedData;
    private final PortfolioRepository portfolioRepository;
    private final PositionRepository positionRepository;
    private final SecurityRepository securityRepository;
    private final ConsolidatedPositionRepository consolidatedPositionRepository;
    private final ConsolidatedPositionMapper consolidatedPositionMapper;

    @Override
    public Bank getBank() {
        return Bank.BANCAPOSTALE;
    }

    @Override
    public void importData() {
        mockedData.getAllPortfoliosMapFromBancaPostale().forEach(this::importPortfolioPositions);
    }

    private void importPortfolioPositions(String portfolioId, List<it.h14.backend.domain.Position> positions) {
        portfolioRepository.findById(portfolioId).ifPresent(portfolioEntity -> {
            positions.forEach(position -> {
                var portFolioPosition = portfolioEntity.getPositions().stream().filter(p -> p.getSecurity().getIsin().equals(position.security().isin())).findFirst();
                if (portFolioPosition.isPresent()) {
                    portFolioPosition.get().getBankPositions().add(consolidatedPositionMapper.toEntity(position));
                } else {
                    ConsolidatedPositionEntity cp = new ConsolidatedPositionEntity();
                    cp.setSecurity(consolidatedPositionMapper.toEntity(position.security()));
                    cp.getBankPositions().add(consolidatedPositionMapper.toEntity(position));
                    portfolioEntity.getPositions().add(cp);
                }
            });
            portfolioRepository.save(portfolioEntity);
        });
    }
}
