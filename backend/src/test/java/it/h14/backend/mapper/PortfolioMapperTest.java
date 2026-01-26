package it.h14.backend.mapper;

import it.h14.backend.domain.ConsolidatedPosition;
import it.h14.backend.domain.Portfolio;
import it.h14.backend.domain.Security;
import it.h14.backend.entity.ConsolidatedPositionEntity;
import it.h14.backend.entity.PortfolioEntity;
import it.h14.backend.entity.SecurityEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Currency;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PortfolioMapperTest {

    @Autowired
    private PortfolioMapper portfolioMapper;

    @Test
    void shouldMapPortfolioEntityWithPositionsToDomain() {
        // Given
        PortfolioEntity entity = new PortfolioEntity();
        entity.setId("p1");
        entity.setName("My Portfolio");
        entity.setClientId("c1");

        SecurityEntity securityEntity = new SecurityEntity();
        securityEntity.setIsin("ISIN1");
        securityEntity.setName("Security 1");
        securityEntity.setCurrency(Currency.getInstance("EUR"));
        securityEntity.setMarketPrice(100.0);

        ConsolidatedPositionEntity posEntity = new ConsolidatedPositionEntity();
        posEntity.setSecurity(securityEntity);
        entity.setPositions(Set.of(posEntity));

        // When
        Portfolio domain = portfolioMapper.toDomain(entity);

        // Then
        assertNotNull(domain);
        assertEquals("p1", domain.id());
        assertNotNull(domain.positions());
        assertEquals(1, domain.positions().size());

        Security securityDomain = domain.positions().keySet().iterator().next();
        assertEquals("ISIN1", securityDomain.isin());

        ConsolidatedPosition posDomain = domain.positions().get(securityDomain);
        assertNotNull(posDomain);
        assertEquals(securityDomain, posDomain.security());
    }

    @Test
    void shouldMapPortfolioToResponseWithPositions() {
        // Given
        PortfolioEntity entity = new PortfolioEntity();
        entity.setId("p1");

        SecurityEntity securityEntity = new SecurityEntity();
        securityEntity.setIsin("ISIN1");

        ConsolidatedPositionEntity posEntity = new ConsolidatedPositionEntity();
        posEntity.setSecurity(securityEntity);
        entity.setPositions(Set.of(posEntity));

        // When
        var response = portfolioMapper.toResponse(entity);

        // Then
        assertNotNull(response);
        assertNotNull(response.getPositions());
        assertTrue(response.getPositions().containsKey("ISIN1"));
        assertNotNull(response.getPositions().get("ISIN1").getSecurity());
        assertEquals("ISIN1", response.getPositions().get("ISIN1").getSecurity().getIsin());
    }
}
