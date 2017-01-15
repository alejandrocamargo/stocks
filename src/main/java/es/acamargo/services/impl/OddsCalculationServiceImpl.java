package es.acamargo.services.impl;

import es.acamargo.entities.AbstractMarket;
import es.acamargo.entities.Dax30;
import es.acamargo.entities.Ibex35;
import es.acamargo.entities.Ticker;
import es.acamargo.repository.Dax30Repository;
import es.acamargo.repository.Ibex35Repository;
import es.acamargo.services.ImporterService;
import es.acamargo.services.OddsCalculationService;
import es.acamargo.services.XmlMapper;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class OddsCalculationServiceImpl implements OddsCalculationService {

    private Dax30Repository dax30Repository;

    private Ibex35Repository ibex35Repository;

    private ImporterService importerService;

    private XmlMapper xmlMapper;

    //autowired
    public OddsCalculationServiceImpl(Dax30Repository dax30Repository, Ibex35Repository ibex35Repository, ImporterService importerService, XmlMapper xmlMapper) {
        this.dax30Repository = dax30Repository;
        this.ibex35Repository = ibex35Repository;
        this.importerService = importerService;
        this.xmlMapper = xmlMapper;
    }

    public List<AbstractMarket> getTheData(String ticker, String startDate, String endDate) {

        String xml = importerService.getDataFromYahoo(ticker, startDate, endDate);

        return xmlMapper.mapToMarketFromXml(xml, Ticker.class);

    }

    public List<? extends AbstractMarket> getDataFromDatabase(Class<? extends AbstractMarket> clazz ) {

        if (clazz.equals(Dax30.class)) {

            return dax30Repository.findAll();

        } else if (clazz.equals(Ibex35.class)) {

            return ibex35Repository.findAll();

        }

        return null;

    }

    public void calculateOdds(List<? extends AbstractMarket> values) {

        AtomicInteger positives = new AtomicInteger(0);
        AtomicInteger negatives = new AtomicInteger(0);
        AtomicInteger neutral = new AtomicInteger(0);

        values.stream().forEach(v -> {

            double delta = v.getClose() - v.getOpen();

            if (delta > 0) positives.getAndIncrement();
            else if (delta < 0) negatives.getAndIncrement();
            else neutral.getAndIncrement();

        });

        System.out.println("Positives: " + (positives.doubleValue()/values.size() * 100) + "%");
        System.out.println("Negatives: " + (negatives.doubleValue()/values.size() * 100) + "%");
        System.out.println("Neutral: " + (neutral.doubleValue()/values.size() * 100) + "%");

    }

}
