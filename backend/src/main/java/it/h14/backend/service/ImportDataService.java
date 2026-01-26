package it.h14.backend.service;

import it.h14.backend.repository.BankRepository;
import it.h14.backend.repository.SwarmRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportDataService {

    private final SwarmRepository swarmRepository;

    private final List<BankRepository> bankRepositories;

    private final ConsolidationService consolidationService;

    public void importAllData() {
        log.info("Importing data from Swarm and Banks...");
        importSwarmData();
        importBanksData();
        consolidationService.computeConsolidatedPositions();
    }

    private void importSwarmData() {
        log.info("Importing Swarm data...");
        swarmRepository.importData();
    }

    private void importBanksData() {
        log.info("Importing Banks data...");
        for (BankRepository bankRepository : bankRepositories) {
            log.info("Importing data from {}", bankRepository.getBank());
            bankRepository.importData();
        }
    }
}
