package es.acamargo;

import es.acamargo.services.ImportCompaniesFromFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImportCompaniesFromFileServiceTest {

    @Autowired
    ImportCompaniesFromFileService service;

    @Test
    public void testService() {
//        service.importCompanies("/Users/alejandrocamargo/Desktop/Bolsa/companies_dax.csv");

    }

}
