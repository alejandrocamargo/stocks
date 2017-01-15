package es.acamargo.entities;

import lombok.Getter;
import lombok.Setter;


public class CandleStick {

    @Getter
    @Setter
    private Double high;

    @Getter
    @Setter
    private Double low;

    @Getter
    @Setter
    private Double open;

    @Getter
    @Setter
    private Double close;

    public CandleStick() {
    }

    public CandleStick(Double high, Double low, Double open, Double close) {
        this.high = high;
        this.low = low;
        this.open = open;
        this.close = close;
    }

    public Boolean isGreen() {

        if (close >= open) return true;
        else return false;

    }

    public Boolean isRed() {

        if (close < open) return true;
        else return false;

    }

    public Double getHeight() {

        return high - low;

    }

    public Double getBody() {

        if (isGreen()) return close - open;
        else return open - close;

    }

}
