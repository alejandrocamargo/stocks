package es.acamargo.services;

import es.acamargo.entities.AbstractMarket;
import es.acamargo.entities.Company;
import es.acamargo.services.impl.CandleStickServiceImpl;


public interface CandleStickService {

    Boolean findPattern(Company company, Class<? extends AbstractMarket> clazz, CandleStickServiceImpl.Pattern pattern);

}
