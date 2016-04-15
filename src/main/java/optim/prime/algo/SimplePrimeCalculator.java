package optim.prime.algo;


import java.util.List;

/**
 * The most naive prime algorithm without any optimisation steps
 */
public class SimplePrimeCalculator implements PrimeCalculable {

    public static boolean isPrime(long n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }

        for (int i = 2; i <= Math.sqrt(n) + 1; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Long> getPrimes(long from, long to) {
        return PrimeCalculable.primesFromTo(from, to, SimplePrimeCalculator::isPrime);
    }
}
