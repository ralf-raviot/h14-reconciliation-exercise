package it.h14.backend.service;

import it.h14.backend.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImportDataService {

    private final List<BankRepository> bankRepositories;

    public void importAllData() {
        log.info("Importing data from Swarm and Banks...");
        // Logic to fetch and save data
        importSwarmData();
        importBanksData();
    }

    private void importSwarmData() {
        log.info("Importing Swarm data...");
    }

    private void importBanksData() {
        log.info("Importing Banks data...");
        for (BankRepository bankRepository : bankRepositories) {
            log.info("Importing data from {}", bankRepository.getBank());
        }
    }
}
