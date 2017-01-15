package es.acamargo.configuration;


import com.mongodb.MongoClient;
import es.acamargo.repository.CompanyRepository;
import es.acamargo.repository.Dax30Repository;
import es.acamargo.repository.Ibex35Repository;
import es.acamargo.runnable.Extractor;
import es.acamargo.services.*;
import es.acamargo.services.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories(basePackages = "es.acamargo.repository")
public class Beans {

    @Autowired
    private Ibex35Repository ibex35Repository;

    @Autowired
    private Dax30Repository dax30Repository;

    @Autowired
    private CompanyRepository companyRepository;

    @Bean
    public XmlMapper xmlMapper() {
        return new XmlMapperImpl();
    }

    @Bean
    public CsvMapper csvMapper() {
        return new CsvMapperImpl();
    }

    @Bean
    public PivotFinderService pivotFinderService() {
        return new PivotFinderServiceImpl(ibex35Repository);
    }

    @Bean
    public CandleStickService candleStickService() {
        return new CandleStickServiceImpl(ibex35Repository, dax30Repository);
    }

    @Bean
    public ImportCompaniesFromFileService importCompaniesFromFileService() {
        return new ImportCompaniesFromFileServiceImpl(companyRepository);
    }

    @Bean
    public ImportIntoDatabaseService importIntoDatabaseService() {
        return new ImportIntoDatabaseServiceImpl(companyRepository);
    }

    @Bean
    public ImporterService yahooImporterService() {
        return new ImporterServiceImpl();
    }

    @Bean
    @Scope("prototype")
    public Extractor extractor(CsvMapper csvMapper, XmlMapper xmlMapper, ImporterService yahooImporterService) {
        return new Extractor(csvMapper, xmlMapper, yahooImporterService, ibex35Repository, dax30Repository);
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(), "stocks");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }
}
