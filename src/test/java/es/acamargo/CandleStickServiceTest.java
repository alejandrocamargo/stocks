package es.acamargo;

import es.acamargo.entities.Company;
import es.acamargo.entities.Dax30;
import es.acamargo.entities.Ibex35;
import es.acamargo.repository.CompanyRepository;
import es.acamargo.services.CandleStickService;
import es.acamargo.services.impl.CandleStickServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandleStickServiceTest {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CandleStickService candleStickService;

    @Test
    public void testFindPattern() {

        companyRepository.findByMarketName("IBEX35")
                .stream()
                .forEach(c -> {

                    if (candleStickService.findPattern(c, Ibex35.class, CandleStickServiceImpl.Pattern.DARK_CLOUD_COVER)) {

                        System.out.println("Pattern: " + CandleStickServiceImpl.Pattern.DARK_CLOUD_COVER + " in ====> " +  c.getSymbol());

                    }
                });


//        Company c = companyRepository.findBySymbol("MRL.MC");
//
//        if (candleStickService.findPattern(c, Ibex35.class, CandleStickServiceImpl.Pattern.DARK_CLOUD_COVER)) {
//
//            System.out.println("Pattern: " + CandleStickServiceImpl.Pattern.DARK_CLOUD_COVER + " in ====> " +  c.getSymbol());
//
//        }


    }

}
