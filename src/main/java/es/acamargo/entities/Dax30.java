package es.acamargo.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "DAX30")
public class Dax30 extends AbstractMarket {

    @Id
    @Getter
    @Setter
    private String id;
}

