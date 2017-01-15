package es.acamargo.entities;


import lombok.Getter;
import lombok.Setter;

/**
 * Not persistent class
 */
public class Ticker extends AbstractMarket {

    @Getter
    @Setter
    private String id;


}
