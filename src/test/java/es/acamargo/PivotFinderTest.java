package es.acamargo;

import es.acamargo.entities.AbstractMarket;
import es.acamargo.entities.Company;
import es.acamargo.entities.Ibex35;
import es.acamargo.repository.CompanyRepository;
import es.acamargo.services.PivotFinderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PivotFinderTest {

    @Autowired
    PivotFinderService service;

    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void findPivots() {

        Company c = companyRepository.findBySymbol("BBVA.MC");

        List<AbstractMarket> ls = service.findPivots(c, Ibex35.class, 5);

        ls.forEach(p -> {

            System.out.println("PIVOT found:  " + p.getDate() + " ====> " + p.getClose());

        });

    }


    @Test
    public void findResistance() {

        Company c = companyRepository.findBySymbol("CABK.MC");

        List<Double> ls = service.findResistance(c, Ibex35.class, 5, 0.96d);

        ls.forEach(r -> {

            System.out.println("Resistance found ====> " + r);

        });

    }


}
