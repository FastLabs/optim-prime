package optim.prime.algo;


import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

public class SimplePrimeCalculator1Test extends AbstractPrimeCalculatorTest {
    private final SimplePrimeCalculator1 primes = new SimplePrimeCalculator1();

    @Test
    public void testPrimes() {
        final long primeCount = somePrimes.stream().
                filter(primes::isPrime).
                count();
        assertEquals(4, primeCount);

    }

    @Test
    public void testNonPrimes() {
        final long nonPrimesCount = someNonPrimes.stream().
                filter((p) -> !primes.isPrime(p)).
                count();

        assertEquals(4, nonPrimesCount);
    }

    @Test
    public void testRange() {
        List<Long> l= LongStream.rangeClosed(0, 100).filter(primes::isPrime)
                .boxed()
                .collect(Collectors.toList());
        //TODO : finsh the assertion
        System.out.println(l);
    }

}
