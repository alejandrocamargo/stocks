package es.acamargo.services;

import es.acamargo.entities.AbstractMarket;

import java.util.List;


public interface OddsCalculationService {
    List<AbstractMarket> getTheData(String ticker, String startDate, String endDate);

    List<? extends AbstractMarket> getDataFromDatabase(Class<? extends AbstractMarket> clazz );

    void calculateOdds(List<? extends AbstractMarket> values);
}
