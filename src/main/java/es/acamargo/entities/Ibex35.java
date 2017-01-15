package es.acamargo.entities;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*

<quote Symbol="YHOO"><Date>2010-03-10</Date><Open>16.51</Open><High>16.940001</High><Low>16.51</Low><Close>16.790001</Close><Volume>33088600</Volume><Adj_Close>16.790001</Adj_Close></quote>

 */


@Document(collection = "IBEX35")
public class Ibex35 extends AbstractMarket {

    @Id
    @Getter
    @Setter
    private String id;

}
