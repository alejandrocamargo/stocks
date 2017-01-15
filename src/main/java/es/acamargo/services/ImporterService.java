package es.acamargo.services;


public interface ImporterService {

    String getDataFromYahoo(String ticker, String dateStart, String dateEnd);

    String getDataFromGoogle(String index, String ticker, String dateStart, String dateEnd);


}
