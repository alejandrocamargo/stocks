package es.acamargo.services.impl;


import es.acamargo.configuration.Beans;
import es.acamargo.entities.AbstractMarket;
import es.acamargo.entities.Company;
import es.acamargo.entities.Dax30;
import es.acamargo.entities.Ibex35;
import es.acamargo.repository.CompanyRepository;
import es.acamargo.runnable.Extractor;
import es.acamargo.services.ImportIntoDatabaseService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImportIntoDatabaseServiceImpl implements ImportIntoDatabaseService{

    private CompanyRepository companyRepository;

    //autowired
    public ImportIntoDatabaseServiceImpl(CompanyRepository companyRepository) {

        this.companyRepository = companyRepository;

    }

    public void importDataFromYahooFullYearIbex35(List<String> years) {

        importDataFromYahooFullYear(companyRepository.findByMarketName("IBEX35"), years, Ibex35.class);

    }

    public void importDataFromGoogleFullYearDax30(List<String> years) {

        importDataFromGoogleFullYear(companyRepository.findByMarketName("DAX30"), years, Dax30.class, "FRA");

    }


    public void importDataFromYahooFullYearDax30(List<String> years) {

        importDataFromYahooFullYear(companyRepository.findByMarketName("DAX30"), years, Dax30.class);

    }

    public void importDataFromYahooDay(List<Company> companies, String dayStart, String dayEnd, Class<? extends AbstractMarket> clazz) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        companies.forEach(c -> {


            Extractor ext = ctx.getBean(Extractor.class);

            ext.setDateStart(dayStart);
            ext.setDateEnd(dayEnd);
            ext.setSymbol(c.getSymbol());
            ext.setClazz(clazz);

            ext.setFormat(Extractor.FileFormat.XML);
            ext.setIndex(null); //not needed

            executor.execute(ext);

        });


        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println("Finished all threads");

    }



    public void importDataFromYahooFullYear(List<Company> companies, List<String> years, Class<? extends AbstractMarket> clazz) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        companies.forEach(c -> {

            years.forEach(y -> {

                Extractor ext = ctx.getBean(Extractor.class);

                ext.setDateStart(y + "-01-01");
                ext.setDateEnd(y + "-12-31");
                ext.setSymbol(c.getSymbol());
                ext.setClazz(clazz);

                ext.setFormat(Extractor.FileFormat.XML);
                ext.setIndex(null); //not needed

                executor.execute(ext);

            });


        });

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println("Finished all threads");

    }

    public void importDataFromGoogleDay(List<Company> companies, String dayStart, String dayEnd, Class<? extends AbstractMarket> clazz, String index) {


        //TODO


    }


    public void importDataFromGoogleFullYear(List<Company> companies, List<String> years, Class<? extends AbstractMarket> clazz, String index) {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Beans.class);

        ExecutorService executor = Executors.newFixedThreadPool(10);

        companies.forEach(c -> {

            years.forEach(y -> {

                Extractor ext = ctx.getBean(Extractor.class);

                ext.setDateStart("01-JAN-" + y);
                ext.setDateEnd("31-DEC-" + y);
                ext.setSymbol(c.getSymbol());
                ext.setClazz(clazz);

                ext.setFormat(Extractor.FileFormat.CSV);
                ext.setIndex(index); //FRA for DAX30

                executor.execute(ext);

            });


        });

        executor.shutdown();

        while (!executor.isTerminated()) {
        }

        System.out.println("Finished all threads");

    }

}
