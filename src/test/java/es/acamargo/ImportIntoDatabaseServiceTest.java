package es.acamargo;

import es.acamargo.services.ImportIntoDatabaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportIntoDatabaseServiceTest {

    @Autowired
    private ImportIntoDatabaseService service;

    @Test
    public void testInsertion() {

        String a[] = {"2010", "2011", "2012", "2013", "2014", "2015", "2016"};

        //service.importDataFromGoogleFullYearDax30(Arrays.asList(a));

        service.importDataFromYahooFullYearIbex35(Arrays.asList(a));

        //service.importDataFromYahooFullYearDax30(Arrays.asList(a));

    }
}
