package es.acamargo.services.impl;

import es.acamargo.entities.AbstractMarket;
import es.acamargo.entities.Company;
import es.acamargo.entities.Ibex35;
import es.acamargo.repository.Ibex35Repository;
import es.acamargo.services.PivotFinderService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PivotFinderServiceImpl implements PivotFinderService {


    private Ibex35Repository ibex35Repository;

    public PivotFinderServiceImpl(Ibex35Repository ibex35Repository) {
        this.ibex35Repository = ibex35Repository;
    }

    public List<AbstractMarket> findPivots(Company company, Class<? extends AbstractMarket> clazz, Integer pivotSize) {


        if (clazz.equals(Ibex35.class)) {

            List<Ibex35> values = ibex35Repository.findBySymbolOrderByDateDesc(company.getSymbol());

            return calculatePivots(values, pivotSize);

        }

        return null;

    }

    private List<AbstractMarket> calculatePivots(List<? extends AbstractMarket> values, Integer pivotSize) {

        List<AbstractMarket> pivots = new ArrayList<>();

        for (int i = 0; i < values.size(); i++) {

            if (i > pivotSize && (i+pivotSize < values.size())) {

                AbstractMarket x = values.get(i);

                if (isSorted(values.subList(i, i + pivotSize))) {

                    List<? extends AbstractMarket> negatives = values.subList(i - pivotSize, i);

                    Collections.reverse(negatives);

                    if (isSorted(negatives)) {

                        //x is a pivot!
                        pivots.add(x);

                    }


                }


            }

        }

        return pivots;

    }

    /**
     * A resistance is found when there are two pivots with similar value (setting the threshold)
     * @param company
     * @param clazz
     * @param pivotSize number of values consecutive values (bigger-before-descending and bigger-after-increasing) to make the pivot
     * @param threshold from 0 to 1 (range-height of the resistance) (0.95 => 95% similar)
     * @return
     */
    public List<Double> findResistance(Company company, Class<? extends AbstractMarket> clazz, Integer pivotSize, Double threshold) {

        List<Double> resistanceList = new ArrayList<>();

        List<AbstractMarket> pivots = findPivots(company, clazz, pivotSize);

        for (int i = 0; i < pivots.size(); i++) {

            Double value = pivots.get(i).getClose();

            for (int j = 0; j < pivots.size(); j++) {

                if (j != i) {

                    Double value2 = pivots.get(j).getClose();

                    if (value <= value2) {

                        if (value / value2 > threshold) {

                            //we have a resistance!
                            resistanceList.add(value);

                        }

                    } else {

                        if (value2 / value > threshold) {

                            //we have a resistance!
                            resistanceList.add(value);


                        }
                    }

                }

            }

        }

        return resistanceList;

    }

    public boolean isSorted(List<? extends AbstractMarket> list) {

        boolean sorted = true;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1).compareTo(list.get(i)) > 0) sorted = false;
        }

        return sorted;
    }

}
