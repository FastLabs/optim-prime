package optim.prime.algo;


import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CachedSievePrimeCalculator extends SievePrimeCalculator {

    private BitSet cache = null;

    protected synchronized BitSet getPrimes(int limit) {

        if (cache == null || cache.size() < limit) {
            cache = super.getPrimes(limit);
        }
        return cache.get(0, limit);
    }


}
