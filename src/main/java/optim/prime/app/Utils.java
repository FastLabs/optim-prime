package optim.prime.app;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;


public class Utils {

    public static List<Long> stubPrimes(long number ) {
        return LongStream.rangeClosed(0, number).boxed().collect(Collectors.toList());
    }

    public static List<Long> stubPrimes(long from, long number ) {
        return LongStream.rangeClosed(from, number).boxed().collect(Collectors.toList());
    }
}
