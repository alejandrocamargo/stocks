package es.acamargo;

import es.acamargo.services.ImporterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImporterServiceTest {

	@Autowired
	public ImporterService importerService;

	@Test
	public void contextLoads() {

//		importerService.getDataFromYahoo("ITX.MC", "2015-01-01", "2015-12-31");
//

		System.out.println(importerService.getDataFromGoogle("FRA", "DWNI", "01-JAN-2016", "31-DEC-2016"));
	}

}
