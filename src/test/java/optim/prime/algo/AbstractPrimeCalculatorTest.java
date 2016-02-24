package optim.prime.algo;


import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.unmodifiableList;

public abstract class AbstractPrimeCalculatorTest {

    protected final List<Long> somePrimes = unmodifiableList(asList(2L, 3L, 5L, 7L));
    protected final List<Long> someNonPrimes = unmodifiableList(asList(1L, 4L, 6L, 8L));


    public abstract void testPrimes();

    public abstract void testNonPrimes();
}
