package it.h14.backend.job;

import it.h14.backend.service.ImportDataService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@SpringBootTest
class ImportDataJobStartupTest {

    @MockitoSpyBean
    private ImportDataService importDataService;

    @Test
    void shouldRunImportDataOnStartup() {
        // ApplicationReadyEvent is fired during SpringBootTest
        verify(importDataService, atLeastOnce()).importAllData();
    }
}
