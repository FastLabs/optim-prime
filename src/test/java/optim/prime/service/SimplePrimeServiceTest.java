package optim.prime.service;


import optim.prime.algo.SimplePrimeCalculator1;
import org.junit.Test;

import java.util.List;

public class SimplePrimeServiceTest {

    private final SimplePrimeService primeService = new SimplePrimeService(new SimplePrimeCalculator1(), 100);

    @Test
    public void testBoundaries() {
        EvaluationResult<List<Long>> primes = primeService.calculate(101L);
    }
}
