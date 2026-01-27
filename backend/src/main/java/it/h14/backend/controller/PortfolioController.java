package it.h14.backend.controller;

import it.h14.backend.mapper.PortfolioMapper;
import it.h14.backend.openapi.api.PortfoliosApi;
import it.h14.backend.openapi.model.PortfolioResponse;
import it.h14.backend.openapi.model.PortfolioSummaryResponse;
import it.h14.backend.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PortfolioController implements PortfoliosApi {
    private final PortfolioMapper portfolioMapper;

    private final PortfolioService portfolioService;

    @Override
    public ResponseEntity<List<PortfolioSummaryResponse>> portfoliosGet() {
        log.info("Fetching all portfolios");
        return ResponseEntity.ok(portfolioService.getAllPortfolios());
    }

    @Override
    public ResponseEntity<PortfolioResponse> portfoliosPortfolioIdGet(String portfolioId) {
        log.info("Fetching portfolio with id {}", portfolioId);
        return ResponseEntity.ok(portfolioService.getPortfolioById(portfolioId));
    }
}
