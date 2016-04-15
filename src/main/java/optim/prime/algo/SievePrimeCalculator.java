package optim.prime.algo;


import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sieve prime calculation algorithm
 * algorithm details: https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 */
public class SievePrimeCalculator implements PrimeCalculable {


    @Override
    public List<Long> getPrimes(long start, long limit) {
        if (limit - start > Integer.MAX_VALUE) {
            throw new IllegalArgumentException();
        }
        final long sqrtLimit = (long) Math.ceil(Math.sqrt(limit));
        final BitSet primes = getPrimes((int) sqrtLimit);
        final BitSet segment = new BitSet();
        if (0 - start >= 0) {
            segment.set((int) (0 - start), false);
        }
        if (1 - start >= 0) {
            segment.set((int) (1 - start), false);
        }
        segment.set((int) (Math.max(0, 2 - start)), (int) (limit - start), true);
        for (int d = 2; d < sqrtLimit; d++) {
            if (primes.get(d)) {
                final int remainder = (int) (start % d);
                final long mStart = start - remainder + (remainder == 0 ? 0 : d);
                for (long m = Math.max(mStart, d * d); m < limit; m += d) {
                    segment.clear((int) (m - start));
                }
            }
        }

        return segment.stream()
                .boxed()
                .map(Integer::longValue)
                .collect(Collectors.collectingAndThen(Collectors.<Long>toList(), Collections::unmodifiableList));
    }

    protected BitSet getPrimes(int limit) {
        final BitSet primes = new BitSet();
        primes.set(0, false);
        primes.set(1, false);
        if (limit == 0 || limit == 1) {
            return primes;
        }
        primes.set(2, limit, true);

        for (int i = 2; i < Math.ceil(Math.sqrt(limit)); i++) {
            if (primes.get(i)) {
                for (int j = i * i; j < limit; j += i) {
                    primes.clear(j);
                }
            }
        }
        return primes;
    }

}
