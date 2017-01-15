package es.acamargo.repository;

import es.acamargo.entities.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CompanyRepository extends MongoRepository<Company, String> {

    List<Company> findByMarketName(String marketName);

    Company findBySymbol(String symbol);
}
