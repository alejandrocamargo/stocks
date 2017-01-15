package es.acamargo.services;


import es.acamargo.entities.AbstractMarket;

import java.util.List;

public interface CsvMapper {

    List<AbstractMarket> mapToMarketFromCsv(String csv, Class<? extends AbstractMarket> clazz, String symbol);

}
