package it.h14.backend.mock;

import it.h14.backend.domain.*;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.List;
import java.util.Map;

@Component
public class MockedData {

    //Clients
    String CLIENT1 = "client1";
    String CLIENT2 = "client2";


    //Portfolios
    String PORTFOLIO1 = "portfolio1";
    String PORTFOLIO2 = "portfolio2";

    Security SEC1 = new Security("isin1", "Security 1", Currency.getInstance("USD"), 10D);
    Security SEC2 = new Security("isin2", "Security 2", Currency.getInstance("EUR"), 20D);
    Security SEC3 = new Security("isin3", "Security 3", Currency.getInstance("GBP"), 30D);

    //List of securities from "Swarm"
    public List<Security> getAllSecurities() {
        return List.of(SEC1, SEC2, SEC3);
    }

    //List of portfolios from "Swarm"
    public List<Portfolio> getAllPortfolios() {
        return List.of(
                new Portfolio(PORTFOLIO1, null, CLIENT1, Map.of(
                        SEC1, simpleFromSwarm(),
                        SEC2, twoBanksFromSwarm(),
                        SEC3, missingBankPositionFromSwarm()
                )),
                new Portfolio(PORTFOLIO2, "Portfolio tech", CLIENT2, Map.of(
                        SEC2, mismatchingQuantityFromSwarm(),
                        SEC3, missingQuantityFromSwarm()
                ))
        );
    }

    public Map<String, List<Position>> getAllPortfoliosMapFromBancaPostale() {
        return Map.of(
                PORTFOLIO1, List.of(simpleFromBancaPostale(), twoBanksFromBancaPostale()),
                PORTFOLIO2, List.of(mismatchingQuantityFromBancaPostale())
        );
    }

    public Map<String, List<Position>> getAllPortfoliosMapFromNeobanca() {
        return Map.of(
                PORTFOLIO1, List.of(twoBanksFromNeobanca())
        );
    }

    public Map<String, List<Position>> getAllPortfoliosMapFromSuperBanca() {
        return Map.of(
                PORTFOLIO2, List.of(unknownPositionFromSuperBanca(), missingQuantityFromSuperBanca())
        );
    }


    //USE CASES :

    //Case simple
    ConsolidatedPosition simpleFromSwarm() {
        return new ConsolidatedPosition(SEC1, new Position(SEC1, 100D, PositionOrigin.INTERNAL, null), null);
    }

    Position simpleFromBancaPostale() {
        return new Position(SEC1, 100D, PositionOrigin.BANK, Bank.BANCAPOSTALE);
    }

    //Case two banks
    ConsolidatedPosition twoBanksFromSwarm() {
        return new ConsolidatedPosition(SEC2, new Position(SEC2, 100D, PositionOrigin.INTERNAL, null), null);
    }

    Position twoBanksFromBancaPostale() {
        return new Position(SEC2, 80D, PositionOrigin.BANK, Bank.BANCAPOSTALE);
    }

    Position twoBanksFromNeobanca() {
        return new Position(SEC2, 20D, PositionOrigin.BANK, Bank.NEOBANCA);
    }

    //Case missing bank position
    ConsolidatedPosition missingBankPositionFromSwarm() {
        return new ConsolidatedPosition(SEC3, new Position(SEC3, 100D, PositionOrigin.INTERNAL, null), null);
    }

    //Case unknown position
    Position unknownPositionFromSuperBanca() {
        return new Position(SEC1, 100D, PositionOrigin.BANK, Bank.SUPERBANCA);
    }

    //Case mismatching quantity
    ConsolidatedPosition mismatchingQuantityFromSwarm() {
        return new ConsolidatedPosition(SEC2, new Position(SEC2, 100D, PositionOrigin.INTERNAL, null), null);
    }

    Position mismatchingQuantityFromBancaPostale() {
        return new Position(SEC2, 80D, PositionOrigin.BANK, Bank.BANCAPOSTALE);
    }

    //Case missing quantity
    ConsolidatedPosition missingQuantityFromSwarm() {
        return new ConsolidatedPosition(SEC3, new Position(SEC3, 100D, PositionOrigin.INTERNAL, null), null);
    }

    Position missingQuantityFromSuperBanca() {
        return new Position(SEC3, null, PositionOrigin.BANK, Bank.SUPERBANCA);
    }


}
