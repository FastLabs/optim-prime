package optim.prime.service;


import optim.prime.algo.SimplePrimeCalculator1;
import optim.prime.domain.PrimeCalculationResult;
import optim.prime.domain.PrimeRange;
import optim.prime.utils.StubPrimeRepository;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static optim.prime.domain.EvaluationStatus.ACCEPTED;
import static optim.prime.utils.SomeExpectations.expected10;
import static optim.prime.utils.SomeExpectations.expected20;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class AsyncPrimeServiceTest {
    private final ExecutorService es = Executors.newCachedThreadPool();
    private final StubPrimeRepository repository = new StubPrimeRepository();
    private final AsyncPrimeService primeService = new AsyncPrimeService(es, new SimplePrimeCalculator1(), repository);

    @Test
    public void testZero() {
        final PrimeCalculationResult primeResult = primeService.calculate(0);
        assertEquals(ACCEPTED, primeResult.getEvaluationStatus());
    }


    @Test
    public void tesPrimeStorage() throws Exception {
        repository.setAddLatch(1);
        repository.setGetLatch(1);
        final PrimeCalculationResult primeResult = primeService.calculate(10);
        assertEquals(ACCEPTED, primeResult.getEvaluationStatus());
        repository.addLatch.await();
        final List<Long> tenPrimes = repository.getPrimes(PrimeRange.from(0).to(10));
        assertEquals(0, repository.getLatch.getCount());
        assertArrayEquals(expected10.toArray(), tenPrimes.toArray());
    }

    @Test
    public void testMultipleInvoc() throws  Exception {
        repository.setAddLatch(2);
        repository.setGetLatch(2);
        final PrimeCalculationResult primeResult = primeService.calculate(10);
        final PrimeCalculationResult primeResult2 = primeService.calculate(20);
        assertEquals(ACCEPTED, primeResult.getEvaluationStatus());
        assertEquals(ACCEPTED, primeResult2.getEvaluationStatus());
        repository.addLatch.await(2, TimeUnit.SECONDS);
        final List<Long> tenPrimes = repository.getPrimes(PrimeRange.from(0).to(10));
        final List<Long> twentyPrimes = repository.getPrimes(PrimeRange.from(0).to(20));
        assertEquals(0, repository.getLatch.getCount());
        assertArrayEquals(expected10.toArray(), tenPrimes.toArray());
        assertArrayEquals(expected20.toArray(), twentyPrimes.toArray());

    }
}
