package es.acamargo.entities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "COMPANY")
public class Company {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String symbol;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String marketName;

}
