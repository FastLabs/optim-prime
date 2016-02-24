package optim.prime.algo;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimplePrimeCalculatorTest extends AbstractPrimeCalculatorTest {
    private final SimplePrimeCalculator primes = new SimplePrimeCalculator();


    @Test
    public void testPrimes() {
        final long primeCount = somePrimes.stream().
                filter(SimplePrimeCalculator::isPrime)
                .count();
        assertEquals(4, primeCount);

    }

    @Test
    public void testNonPrimes() {
        final long nonPrimes = someNonPrimes.stream().
                filter((p) -> !SimplePrimeCalculator.isPrime(p))
                .count();

        assertEquals(4, nonPrimes);
    }


}
