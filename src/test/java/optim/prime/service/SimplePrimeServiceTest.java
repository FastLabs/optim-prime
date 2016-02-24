package optim.prime.service;


import optim.prime.algo.SimplePrimeCalculator1;
import optim.prime.domain.PrimeCalculationResult;
import optim.prime.domain.RequestStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SimplePrimeServiceTest {

    private final SimplePrimeService primeService = new SimplePrimeService(new SimplePrimeCalculator1(), 100);

    @Test
    public void testBoundaries() {
        final PrimeCalculationResult primeResult = primeService.calculate(101L);
        assertEquals(RequestStatus.ERROR, primeResult.getRequestStatus());
        assertNotNull(primeResult.getMessage());
        assertNotNull(primeResult.getResult());
    }

    @Test
    public void testZero() {
        final PrimeCalculationResult primeResult =  primeService.calculate(0);
        assertEquals(RequestStatus.SUCCESS, primeResult.getRequestStatus());
    }
}
