package es.acamargo.repository;

import es.acamargo.entities.Ibex35;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface Ibex35Repository extends MongoRepository<Ibex35, String> {

    List<Ibex35> findBySymbolOrderByDateDesc(String symbol);

    List<Ibex35> findTop10BySymbolOrderByDateDesc(String symbol);

    List<Ibex35> findTop1BySymbolOrderByDateDesc(String symbol);

}

