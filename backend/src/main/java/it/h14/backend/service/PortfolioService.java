package it.h14.backend.service;

import it.h14.backend.domain.ConsolidatedPosition;
import it.h14.backend.domain.PositionOrigin;
import it.h14.backend.entity.SecurityEntity;
import it.h14.backend.mapper.PortfolioMapper;
import it.h14.backend.mapper.PositionMapper;
import it.h14.backend.openapi.model.PortfolioResponse;
import it.h14.backend.openapi.model.PortfolioSummaryResponse;
import it.h14.backend.repository.PortfolioRepository;
import it.h14.backend.repository.PositionRepository;
import it.h14.backend.repository.SecurityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PortfolioService {


    private final PortfolioMapper portfolioMapper;
    private final PositionMapper positionMapper;

    private final PortfolioRepository portfolioRepository;
    private final PositionRepository positionRepository;
    private final SecurityRepository securityRepository;

    public List<PortfolioSummaryResponse> getAllPortfolios() {
        return portfolioRepository.findAll().stream()
                .map(portfolioMapper::toDomain)
                .map(portfolioMapper::toSummaryResponse)
                .toList();
    }

    public PortfolioResponse getPortfolioById(String id) {
        var portfolio = portfolioRepository.findById(id).stream().findFirst().map(portfolioMapper::toDomain).orElse(null);
        if (portfolio != null) {
            var consolidatedPositions = computeConsolidatedPositions(id, portfolio.clientId());
            return portfolioMapper.toResponse(portfolio, consolidatedPositions);
        }
        return null;
    }

    private List<ConsolidatedPosition> computeConsolidatedPositions(String portfolioId, String clientId) {
        List<ConsolidatedPosition> consolidatedPositions = new ArrayList<>();
        var internalPositions = positionRepository.findByPortfolioIdAndOrigin(portfolioId, PositionOrigin.INTERNAL)
                .stream().map(position -> positionMapper.toDomain(position, getSecurity(position.getIsin()))).collect(Collectors.toSet());
        var bankPositions = positionRepository.findByPortfolioIdAndOrigin(portfolioId, PositionOrigin.BANK)
                .stream().map(position -> positionMapper.toDomain(position, getSecurity(position.getIsin()))).collect(Collectors.toSet());
        bankPositions.addAll(positionRepository.findByClientIdAndOrigin(portfolioId, PositionOrigin.BANK)
                .stream().map(position -> positionMapper.toDomain(position, getSecurity(position.getIsin()))).collect(Collectors.toSet()));

        for (var internalPosition : internalPositions) {
            consolidatedPositions.add(
                    new ConsolidatedPosition(
                            internalPosition.security(),
                            internalPosition,
                            bankPositions.stream().filter(bankPosition ->
                                    bankPosition.security().equals(internalPosition.security())
                                            && (bankPosition.portfolioId() == null || bankPosition.portfolioId().equals(portfolioId))
                                            && (bankPosition.clientId() == null || bankPosition.clientId().equals(clientId))
                            ).toList()));
        }

        return consolidatedPositions;
    }

    private SecurityEntity getSecurity(String isin) {
        return securityRepository.findByIsin(isin).orElse(null);
    }

}
