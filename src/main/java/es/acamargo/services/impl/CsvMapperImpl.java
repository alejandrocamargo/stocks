package es.acamargo.services.impl;

import es.acamargo.entities.AbstractMarket;
import es.acamargo.services.CsvMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class CsvMapperImpl implements CsvMapper {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<AbstractMarket> mapToMarketFromCsv(String csv, Class<? extends AbstractMarket> clazz, String symbol) {

        List<AbstractMarket> marketList = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new StringReader(csv));

        reader.lines().forEach(l -> marketList.add(lineToAbstractMarket(l, clazz, symbol)));

        return marketList;

    }

    private AbstractMarket lineToAbstractMarket(String line, Class<? extends AbstractMarket> clazz, String symbol) {


        try {

            String[] array = line.split(",");

            AbstractMarket market = clazz.newInstance();

            market.setSymbol(symbol);
            market.setDate(parseDate(array[0]));
            market.setOpen(Double.parseDouble(array[1]));
            market.setHigh(Double.parseDouble(array[2]));
            market.setLow(Double.parseDouble(array[3]));
            market.setClose(Double.parseDouble(array[4]));
            market.setVolume(Double.parseDouble(array[5]));
            market.setAdjClose(0.0d);

            return market;

        } catch (Exception ex) {

            logger.error(ex.getMessage());
        }


        return null;


    }

    private String parseDate(String date) {

        SimpleDateFormat destiny = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat origin = new SimpleDateFormat("d-MMM-yy", Locale.US);

        try {

            Date before = origin.parse(date);

            String ret =  destiny.format(before);

            return ret;

        } catch (ParseException e) {

            logger.warn("Could not parse date: " + date);

        }

        return date;

    }
}
