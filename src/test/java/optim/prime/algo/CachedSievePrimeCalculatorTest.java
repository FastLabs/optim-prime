package optim.prime.algo;


import org.junit.Test;

public class CachedSievePrimeCalculatorTest {

    private CachedSievePrimeCalculator primeCalculator = new CachedSievePrimeCalculator();

    @Test
    public void testIt() {
        primeCalculator.getPrimes(0L, 100L);
        primeCalculator.getPrimes(0L, 100L);
        primeCalculator.getPrimes(0L, 1500L);
        primeCalculator.getPrimes(0L, 15000L);
        primeCalculator.getPrimes(0L, 150000L);
    }


}
