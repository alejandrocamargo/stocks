package es.acamargo.services.impl;

import es.acamargo.entities.*;
import es.acamargo.repository.Dax30Repository;
import es.acamargo.repository.Ibex35Repository;
import es.acamargo.services.CandleStickService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class CandleStickServiceImpl implements CandleStickService {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Ibex35Repository ibex35Repository;

    private Dax30Repository dax30Repository;

    public enum Pattern {

        DARK_CLOUD_COVER

    }

    //autowired
    public CandleStickServiceImpl(Ibex35Repository ibex35Repository, Dax30Repository dax30Repository) {
        this.ibex35Repository = ibex35Repository;
        this.dax30Repository = dax30Repository;
    }

    public Boolean findPattern(Company company, Class<? extends AbstractMarket> clazz, Pattern pattern) {

        List<AbstractMarket> abs = new ArrayList<>();

        if (clazz.equals(Ibex35.class)) {

            //Get last 10 values
            List<Ibex35> values = ibex35Repository.findTop10BySymbolOrderByDateDesc(company.getSymbol());

            abs.addAll(values);

        } else if (clazz.equals(Dax30.class)) {

            //Get last 10 values
            List<Ibex35> values = dax30Repository.findTop10BySymbolOrderByDateDesc(company.getSymbol());

            abs.addAll(values);

        }


        //Get CandleSticks
        List <CandleStick> csList = getCandleStickList(abs);

        if (csList.size() == 0) logger.warn("Candlestick list is ZERO ===> " + company.getSymbol());

        if (pattern.equals(Pattern.DARK_CLOUD_COVER)) {

            return isDarkCloudCover(csList);

        }

        return Boolean.FALSE;


    }

    private List<CandleStick> getCandleStickList(List<AbstractMarket> list) {

        List<CandleStick> candleStickList = new ArrayList<>();

        list.stream().forEach(e -> candleStickList.add(new CandleStick(e.getHigh(), e.getLow(), e.getOpen(), e.getClose())));

        return candleStickList;

    }

    private Boolean isDarkCloudCover(List<CandleStick> candleStickList) {

        if (candleStickList.size() > 0) {

            CandleStick last = candleStickList.get(0);
            CandleStick beforeLast = candleStickList.get(1);

            if (last.isGreen() && beforeLast.isRed()) {

                if (last.getHeight() / last.getBody() <= 2) {

//                    if (beforeLast.getHeight() / beforeLast.getBody() <= 1.3d) {

                        if (last.getBody() / beforeLast.getBody() > 0.9) {

                            //pattern found
                            return Boolean.TRUE;

                        }

                    //}

                }

            }

        }

        return Boolean.FALSE;

    }

}
