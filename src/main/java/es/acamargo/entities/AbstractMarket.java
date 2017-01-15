package es.acamargo.entities;

import lombok.Getter;
import lombok.Setter;

public abstract class AbstractMarket implements Comparable <AbstractMarket>{


    @Getter
    @Setter
    private String symbol;

    @Getter
    @Setter
    private String date;

    @Getter
    @Setter
    private Double open;

    @Getter
    @Setter
    private Double high;

    @Getter
    @Setter
    private Double low;

    @Getter
    @Setter
    private Double close;

    @Getter
    @Setter
    private Double volume;

    @Getter
    @Setter
    private Double adjClose;


    @Override
    public int compareTo(AbstractMarket o) {

        if (this.getClose() > o.getClose()) return 1;
        else if (this.getClose() < o.getAdjClose()) return -1;
        else return 0;

    }

    @Override
    public String toString() {
        return "AbstractMarket{ symbol='" + symbol + '\'' +
                ", date='" + date + '\'' +
                ", open=" + open +
                ", high=" + high +
                ", low=" + low +
                ", close=" + close +
                ", volume=" + volume +
                ", adjClose=" + adjClose +
                '}';
    }
}
