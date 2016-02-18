package optim.prime.rest.controller;


import optim.prime.algo.PrimeCalculator;
import optim.prime.concurent.PrimeHalfingTask;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class OptimPrimeRestControllerIntegrationTest {

    @Test
    public void simpleTest() {
        final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        List<Long> x = forkJoinPool.invoke(new PrimeHalfingTask(90));
        System.out.println(x);
    }


    @Test
    public void testNoConcurrency() {
        List<Long> r = PrimeCalculator.primesFromTo(10, 100, PrimeCalculator::isPrime1);
        System.out.println(r);
    }
}
