package es.acamargo.runnable;

import es.acamargo.entities.AbstractMarket;
import es.acamargo.entities.Dax30;
import es.acamargo.entities.Ibex35;
import es.acamargo.repository.Dax30Repository;
import es.acamargo.repository.Ibex35Repository;
import es.acamargo.services.CsvMapper;
import es.acamargo.services.XmlMapper;
import es.acamargo.services.ImporterService;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class Extractor implements Runnable {


    private XmlMapper xmlMapper;

    private CsvMapper csvMapper;

    private ImporterService importerService;

    private Ibex35Repository repository;

    private Dax30Repository dax30Repository;

    @Getter
    @Setter
    private String symbol;

    @Getter
    @Setter
    private Class<? extends  AbstractMarket> clazz;

    @Getter
    @Setter
    private String dateStart;

    @Getter
    @Setter
    private String dateEnd;

    @Getter
    @Setter
    private FileFormat format;

    @Getter
    @Setter
    private String index;

    public enum FileFormat {
        CSV, XML
    }


    //Autowired constructor
    public Extractor(CsvMapper csvMapper, XmlMapper xmlMapper, ImporterService importerService, Ibex35Repository repository, Dax30Repository dax30Repository) {
        this.xmlMapper = xmlMapper;
        this.csvMapper = csvMapper;
        this.importerService = importerService;
        this.repository = repository;
        this.dax30Repository = dax30Repository;

    }

    public Extractor(String symbol, Class<? extends AbstractMarket> clazz, String dateStart, String dateEnd) {

        this.clazz = clazz;

        this.symbol = symbol;

        this.dateEnd = dateEnd;

        this.dateStart = dateStart;

    }

    @Override
    public void run() {

        List<AbstractMarket> marketList = new ArrayList<>();

        if (format.equals(FileFormat.XML)) {

            String xml = importerService.getDataFromYahoo(symbol, dateStart, dateEnd);

            marketList = xmlMapper.mapToMarketFromXml(xml, clazz);

        } else if (format.equals(FileFormat.CSV)) {

            String csv = importerService.getDataFromGoogle(index, symbol, dateStart, dateEnd);

            marketList = csvMapper.mapToMarketFromCsv(csv, clazz, symbol);

        }

        //Now add to the database...
//        marketList.forEach(m -> {
//            if (m != null) System.out.println(m.toString());
//        });

        marketList.forEach(m -> {

            if (m instanceof Ibex35) {

                repository.save((Ibex35) m);

            } else if (m instanceof Dax30) {

                dax30Repository.save((Dax30) m);
            }
        });

    }
}
