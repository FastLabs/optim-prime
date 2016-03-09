package optim.prime.algo;


import java.util.BitSet;
import java.util.concurrent.atomic.AtomicReference;

public class CachedSievePrimeCalculator extends SievePrimeCalculator {


    final AtomicReference<BitSet> sieve;

    public CachedSievePrimeCalculator() {
        this.sieve = new AtomicReference<>(super.getPrimes(1));
    }

    private BitSet selectOne(BitSet cSieve, BitSet nSieve) {
        if (cSieve.size() < nSieve.size()) {
            System.out.println("Excended Sieve" + nSieve.size());
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
