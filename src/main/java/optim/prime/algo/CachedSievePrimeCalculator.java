package optim.prime.algo;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.BitSet;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Extends the original sieve prime calculator by adding sieve cache
 */

public class CachedSievePrimeCalculator extends SievePrimeCalculator {

    private static final Log logger = LogFactory.getLog(CachedSievePrimeCalculator.class);

    final AtomicReference<BitSet> sieve;

    public CachedSievePrimeCalculator() {
        this.sieve = new AtomicReference<>(super.getPrimes(1));
    }

    private BitSet selectOne(BitSet cSieve, BitSet nSieve) {
        if (cSieve.size() < nSieve.size()) {
            logger.warn("Exceeded Sieve" + nSieve.size());
        }

        return cSieve.size() > nSieve.size() ? cSieve : nSieve;
    }

    protected BitSet getPrimes(int limit) {
        BitSet currentSieve = sieve.get();
        if (currentSieve.size() < limit) {
            currentSieve = super.getPrimes(limit);
            sieve.accumulateAndGet(currentSieve, this::selectOne);
        }
        return currentSieve.get(0, limit);
    }


}
