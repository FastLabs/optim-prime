package optim.prime.algo;


import org.junit.Test;

import java.util.List;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

public class SievePrimeCalculatorTest {
    SievePrimeCalculator c = new SievePrimeCalculator();


    @Test
    public void testIt() {

        final List<Long> result =
                c.getPrimes(0L, 100L).
                        stream().
                        collect(toList());

        final List<Long> result1 = LongStream.rangeClosed(0, 100L).filter(SimplePrimeCalculator::isPrime).
                boxed().
                collect(toList());

        System.out.println(c.getPrimes(0L, 6L));
        System.out.println(result);
        System.out.println(result1);
    }

}
