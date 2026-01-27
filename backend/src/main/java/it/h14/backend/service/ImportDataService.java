package it.h14.backend.service;

import it.h14.backend.mapper.PortfolioMapper;
import it.h14.backend.mapper.PositionMapper;
import it.h14.backend.mapper.SecurityMapper;
import it.h14.backend.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportDataService {

    private final SwarmRepository swarmRepository;
    private final List<BankRepository> bankRepositories;

    private final PortfolioRepository portfolioRepository;
    private final PositionRepository positionRepository;
    private final SecurityRepository securityRepository;

    private final PortfolioMapper portfolioMapper;
    private final SecurityMapper securityMapper;
    private final PositionMapper positionMapper;


    @Transactional
    public void importAllData() {
        cleanData();
        log.info("Importing data from Swarm and Banks...");
        importSwarmData();
        importBanksData();
    }

    private void cleanData() {
        log.info("Cleaning data...");
        positionRepository.deleteAll();
        securityRepository.deleteAll();
        portfolioRepository.deleteAll();
    }

    private void importSwarmData() {
        log.info("Importing Swarm data...");
        portfolioRepository.saveAll(swarmRepository.getPortfolios().stream().map(portfolioMapper::toEntity).toList());
        securityRepository.saveAll(swarmRepository.getSecurities().stream().map(securityMapper::toEntity).toList());
        positionRepository.saveAll(swarmRepository.getPositions().stream().map(positionMapper::toEntity).toList());
    }

    private void importBanksData() {
        log.info("Importing Banks data...");
        for (BankRepository bankRepository : bankRepositories) {
            log.info("Importing data from {}", bankRepository.getBank());
            positionRepository.saveAll(bankRepository.getPositions().stream().map(positionMapper::toEntity).toList());
        }
    }
}
