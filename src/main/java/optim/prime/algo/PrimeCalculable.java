package optim.prime.algo;


import java.util.Collections;
import java.util.List;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.collectingAndThen;

public interface PrimeCalculable {

    static List<Long> primesFromTo(final long from, final long to, LongPredicate isPrm) {
        return LongStream.range(from, to)
                .filter(isPrm)
                .boxed()
                .collect(collectingAndThen(Collectors.<Long>toList(), Collections::unmodifiableList));


    }

     List<Long> getPrimes(long from, long to);
}
