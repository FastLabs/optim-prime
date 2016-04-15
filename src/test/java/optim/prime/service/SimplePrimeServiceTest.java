package optim.prime.service;


import optim.prime.algo.SimplePrimeCalculator1;
import optim.prime.domain.PrimeCalculationResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;
import static optim.prime.domain.EvaluationStatus.ERROR;
import static optim.prime.domain.EvaluationStatus.SUCCESS;
import static org.junit.Assert.*;

public class SimplePrimeServiceTest {

    private final SimplePrimeService primeService = new SimplePrimeService(new SimplePrimeCalculator1(), 100);

    @Test
    public void testBoundaries() {
        final PrimeCalculationResult primeResult = primeService.calculate(101L);
        assertEquals(ERROR, primeResult.getEvaluationStatus());
        assertNotNull(primeResult.getMessage());
        assertNotNull(primeResult.getResult());
    }

    @Test
    public void testZero() {
        final PrimeCalculationResult primeResult =  primeService.calculate(0);
        assertEquals(SUCCESS, primeResult.getEvaluationStatus());
        assertFalse( primeResult.getResult().isPresent());
    }

    @Test
    public void testOne() {
        final PrimeCalculationResult primeResult = primeService.calculate(1);
        assertEquals(SUCCESS, primeResult.getEvaluationStatus());
        assertFalse(primeResult.getResult().isPresent());
    }



    @Test
    public void testOthers() {
        final List<Long> expected = unmodifiableList( asList(2L, 3L, 5L, 7L));
        final PrimeCalculationResult primeResult = primeService.calculate(10);
        assertEquals(SUCCESS, primeResult.getEvaluationStatus());
        assertTrue(primeResult.getResult().isPresent());
        assertArrayEquals(expected.toArray(), primeResult.getResult().get().toArray());
    }
}
