package by.bsu.tsp.util;

import by.bsu.tsp.algs.domain.Resolve;

import java.util.Comparator;

/**
 * Created by Mikhail on 16.04.2017.
 */
public class ResolveComparator implements Comparator<Resolve> {
    @Override
    public int compare(Resolve o1, Resolve o2) {
        int length1 = o1.getLength();
        int length2 = o2.getLength();

        if (length1 > length2) {
            return 1;
        } else if (length1 < length2) {
            return -1;
        } else {
            return 0;
        }
    }
}
