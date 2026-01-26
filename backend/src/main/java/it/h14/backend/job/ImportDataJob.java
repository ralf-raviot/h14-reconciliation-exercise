package it.h14.backend.job;

import it.h14.backend.service.ImportDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImportDataJob {

    private final ImportDataService importDataService;

    @EventListener(ApplicationReadyEvent.class)
    @Scheduled(cron = "${jobs.import-data.cron:0 0 0 * * *}")
    public void importData() {
        log.info("Starting data import job...");
        importDataService.importAllData();
        log.info("Data import job finished.");
    }
}
