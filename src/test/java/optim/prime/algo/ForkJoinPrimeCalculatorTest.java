package optim.prime.algo;


import org.junit.Test;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import static optim.prime.utils.SomeExpectations.expected20;
import static org.junit.Assert.assertArrayEquals;

public class ForkJoinPrimeCalculatorTest {


    @Test
    public void testPrimes() {
        final ForkJoinPool poop = ForkJoinPool.commonPool();
        final ForkJoinPrimeCalculator calculator = new ForkJoinPrimeCalculator(poop, new SimplePrimeCalculator1());
        final List<Long> x = calculator.getPrimes(0, 20);
        assertArrayEquals(expected20.toArray(), x.toArray());

    }
}
