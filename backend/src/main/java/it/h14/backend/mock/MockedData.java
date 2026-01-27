package it.h14.backend.mock;

import it.h14.backend.domain.*;
import org.springframework.stereotype.Component;

import java.util.Currency;
import java.util.List;

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
                new Portfolio(PORTFOLIO1, null, CLIENT1, null),
                new Portfolio(PORTFOLIO2, "Portfolio tech", CLIENT2, null)
        );
    }

    public List<Position> getAllPositionsFromSwarm() {
        return List.of(
                simpleFromSwarm(),
                twoBanksFromSwarm(),
                missingBankPositionFromSwarm(),
                mismatchingQuantityFromSwarm(),
                missingQuantityFromSwarm());
    }

    public List<Position> getAllPositionsFromBancaPostale() {
        return List.of(simpleFromBancaPostale(), twoBanksFromBancaPostale(), mismatchingQuantityFromBancaPostale());
    }

    public List<Position> getAllPositionsFromNeobanca() {
        return List.of(twoBanksFromNeobanca());
    }

    public List<Position> getAllPositionsFromSuperBanca() {
        return List.of(unknownPositionFromSuperBanca(), missingQuantityFromSuperBanca());
    }


    //USE CASES :

    //Case simple
    Position simpleFromSwarm() {
        return new Position(SEC1, 100D, PositionOrigin.INTERNAL, null, PORTFOLIO1, CLIENT1);
    }

    Position simpleFromBancaPostale() {
        return new Position(SEC1, 100D, PositionOrigin.BANK, Bank.BANCAPOSTALE, PORTFOLIO1, CLIENT1);
    }

    //Case two banks
    Position twoBanksFromSwarm() {
        return new Position(SEC2, 100D, PositionOrigin.INTERNAL, null, PORTFOLIO1, CLIENT1);
    }

    Position twoBanksFromBancaPostale() {
        return new Position(SEC2, 80D, PositionOrigin.BANK, Bank.BANCAPOSTALE, PORTFOLIO1, CLIENT1);
    }

    Position twoBanksFromNeobanca() {
        return new Position(SEC2, 20D, PositionOrigin.BANK, Bank.NEOBANCA, PORTFOLIO1, CLIENT1);
    }

    //Case missing bank position
    Position missingBankPositionFromSwarm() {
        return new Position(SEC3, 100D, PositionOrigin.INTERNAL, null, PORTFOLIO1, CLIENT1);
    }

    //Case unknown position
    Position unknownPositionFromSuperBanca() {
        return new Position(SEC1, 100D, PositionOrigin.BANK, Bank.SUPERBANCA, PORTFOLIO2, CLIENT2);
    }

    //Case mismatching quantity
    Position mismatchingQuantityFromSwarm() {
        return new Position(SEC2, 100D, PositionOrigin.INTERNAL, null, PORTFOLIO2, CLIENT2);
    }

    Position mismatchingQuantityFromBancaPostale() {
        return new Position(SEC2, 80D, PositionOrigin.BANK, Bank.BANCAPOSTALE, PORTFOLIO2, CLIENT2);
    }

    //Case missing quantity
    Position missingQuantityFromSwarm() {
        return new Position(SEC3, 100D, PositionOrigin.INTERNAL, null, PORTFOLIO2, CLIENT2);
    }

    Position missingQuantityFromSuperBanca() {
        return new Position(SEC3, null, PositionOrigin.BANK, Bank.SUPERBANCA, PORTFOLIO2, CLIENT2);
    }


}
