package optim.prime.algo;

import java.util.List;

//TODO: review this algorithm
public class SimplePrimeCalculator1 implements PrimeCalculable {

    boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }


        for (int i = 5; i * i <= n + 1; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;

    }

    @Override
    public List<Long> getPrimes(long from, long to) {
        return PrimeCalculable.primesFromTo(from, to, this::isPrime);
    }
}
