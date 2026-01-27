package it.h14.backend.controller;

import it.h14.backend.mapper.PortfolioMapper;
import it.h14.backend.openapi.api.PortfoliosApi;
import it.h14.backend.openapi.model.PortfolioResponse;
import it.h14.backend.openapi.model.PortfolioSummaryResponse;
import it.h14.backend.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PortfolioController implements PortfoliosApi {

    private final PortfolioRepository portfolioRepository;
    private final PortfolioMapper portfolioMapper;

    @Override
    public ResponseEntity<List<PortfolioSummaryResponse>> portfoliosGet() {
        return null;
    }

    @Override
    public ResponseEntity<PortfolioResponse> portfoliosPortfolioIdGet(String portfolioId) {
        return null;
    }
}
