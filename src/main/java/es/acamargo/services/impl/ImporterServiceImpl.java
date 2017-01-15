package es.acamargo.services.impl;

import es.acamargo.services.ImporterService;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImporterServiceImpl implements ImporterService {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String getDataFromYahoo(String ticker, String dateStart, String dateEnd) {

        String uri = "http://query.yahooapis.com/v1/public/yql?q=select%20%2a%20from%20yahoo.finance.historicaldata%20where%20symbol%20in%20%28%27" + ticker + "%27%29%20and%20startDate%20=%20%27" + dateStart + "%27%20and%20endDate%20=%20%27"+ dateEnd + "%27&diagnostics=true&env=store://datatables.org/alltableswithkeys";

        return callWebService(uri);

    }

    /**
     *
     * @param index FRA --> Frankfurt
     * @param ticker
     * @param dateStart dd-MMM-yyyy 01-JUN-2016
     * @param dateEnd dd-MMM-yyyy 30-JUN-2016
     * @return
     */
    public String getDataFromGoogle(String index, String ticker, String dateStart, String dateEnd) {

        String[] start = dateStart.split("-");
        String[] end = dateEnd.split("-");

        String uri = "http://www.google.com/finance/historical?q=" + index + ":" + ticker + "&startdate=" + start[1] + "+" + start[0] + "%2C+" + start[2] + "&enddate=" + end[1] + "+" + end[0] + "%2C+" + end[2] + "&output=csv";

        return callWebService(uri);

    }

    private String callWebService(String uri) {

        try {

            URL obj = new URL(uri);

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            Integer responseCode = con.getResponseCode();

            logger.info("Sending 'GET' request to URL : " + uri);

            logger.info("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;

            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {

                response.append(inputLine);
                response.append("\n");
            }

            in.close();

            return response.toString();

        } catch (Exception e) {

            logger.error(e.getMessage());

        }

        return "";

    }


}
