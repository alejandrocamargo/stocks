package es.acamargo.services;


import es.acamargo.entities.AbstractMarket;
import es.acamargo.entities.Company;

import java.util.List;

public interface ImportIntoDatabaseService {

    void importDataFromYahooFullYearIbex35(List<String> years);

    void importDataFromYahooFullYearDax30(List<String> years);

    void importDataFromGoogleFullYearDax30(List<String> years);

    void importDataFromYahooDay(List<Company> companies, String dayStart, String dayEnd, Class<? extends AbstractMarket> clazz);

    void importDataFromYahooFullYear(List<Company> companies, List<String> years, Class<? extends AbstractMarket> clazz);

    void importDataFromGoogleDay(List<Company> companies, String dayStart, String dayEnd, Class<? extends AbstractMarket> clazz, String index);

    void importDataFromGoogleFullYear(List<Company> companies, List<String> years, Class<? extends AbstractMarket> clazz, String index);

}
