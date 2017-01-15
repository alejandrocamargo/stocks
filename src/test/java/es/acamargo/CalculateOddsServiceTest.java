package es.acamargo;

import es.acamargo.entities.AbstractMarket;
import es.acamargo.entities.Ibex35;
import es.acamargo.services.OddsCalculationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculateOddsServiceTest {

    @Autowired
    OddsCalculationService calculationService;

    @Test
    public void test() {

        //List<AbstractMarket> data = calculationService.getTheData("SPY", "2014-01-01", "2014-12-31");

        List<? extends AbstractMarket> data = calculationService.getDataFromDatabase(Ibex35.class);

        calculationService.calculateOdds(data);

    }

}
