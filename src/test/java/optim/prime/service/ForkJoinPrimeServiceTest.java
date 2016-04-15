package optim.prime.service;


import optim.prime.algo.SimplePrimeCalculator1;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;

import static optim.prime.utils.SomeExpectations.expected20;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class ForkJoinPrimeServiceTest {

    @Test
    public void testPrimes() {
        final ForkJoinPool poop = ForkJoinPool.commonPool();
        final ForkJoinPrimeService calculator = new ForkJoinPrimeService(poop, new SimplePrimeCalculator1());
        final Optional<List<Long>> x = calculator.calculate(20).getResult();
        assertTrue(x.isPresent());
        assertArrayEquals(expected20.toArray(), x.get().toArray());
    }
}
