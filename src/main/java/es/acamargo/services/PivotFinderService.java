package es.acamargo.services;

import es.acamargo.entities.AbstractMarket;
import es.acamargo.entities.Company;

import java.util.List;


public interface PivotFinderService {

    List<AbstractMarket> findPivots(Company company, Class<? extends AbstractMarket> clazz, Integer pivotSize);

    List<Double> findResistance(Company company, Class<? extends AbstractMarket> clazz, Integer pivotSize, Double threshold);

}
