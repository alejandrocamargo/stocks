package es.acamargo.services.impl;

import es.acamargo.entities.Company;
import es.acamargo.repository.CompanyRepository;
import es.acamargo.services.ImportCompaniesFromFileService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class ImportCompaniesFromFileServiceImpl implements ImportCompaniesFromFileService {


    private CompanyRepository companyRepository;

    //autowired
    public ImportCompaniesFromFileServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public void importCompanies(String file) {


        try {

            Stream<String> lines = Files.lines(Paths.get(file));

            lines.forEach(l -> {

                Company company = new Company();

                String array[] = l.split(";");

                company.setSymbol(array[0]);
                company.setName(array[1]);
                company.setMarketName(array[2]);

                companyRepository.save(company);


            });

            lines.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
