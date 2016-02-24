package optim.prime.concurent;


import optim.prime.algo.PrimeCalculable;
import optim.prime.algo.SimplePrimeCalculator1;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;

public class PrimeHalfingTask extends AbstractHalfingTask<List<Long>> {


    private final PrimeCalculable primes;

    public PrimeHalfingTask(long to) {
        this(0, to);

    }

    public PrimeHalfingTask(long from, long to) {
        this(from, to, new SimplePrimeCalculator1());

    }

    public PrimeHalfingTask(long from, long to, PrimeCalculable primes) {
        super(from, to);
        this.primes = primes;
    }

    protected List<Long> calculateDirectly(long from, long to) {
        return primes.getPrimes(from, to);
    }

    @Override
    protected List<Long> merge(List<Long> left, List<Long> right) {
        return Stream.concat(left.stream(), right.stream())
                .collect(collectingAndThen(Collectors.<Long>toList(), Collections::unmodifiableList));

    }

    @Override
    protected AbstractHalfingTask<List<Long>> getInstance(Long from, Long to) {
        return new PrimeHalfingTask(from, to);
    }

    public String toString() {
        return "Prime Calculator: " + from + " to: " + to;
    }
}
