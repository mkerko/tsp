package by.bsu.tsp.util;

import by.bsu.tsp.genetic.domain.ResolveGenetic;

import java.util.Comparator;

/**
 * Created by Mikhail on 16.04.2017.
 */
public class ResolveGeneticComparator implements Comparator<ResolveGenetic> {
    @Override
    public int compare(ResolveGenetic o1, ResolveGenetic o2) {
        return Util.calculateFitness(o1) - Util.calculateFitness(o2);
    }
}
