package optim.prime.concurent;


import optim.prime.algo.PrimeCalculator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static optim.prime.algo.PrimeCalculator.primesFromTo;

public class PrimeHalfingTask extends AbstractHalfingTask<List<Long>> {

    public PrimeHalfingTask(long to) {
        this(0, to);
    }


    //TODO: make the algorithm customizable
    public PrimeHalfingTask(long from, long to) {
        super(from, to);
    }

    protected List<Long> calculateDirectly(long from, long to) {
        return primesFromTo(from, to, PrimeCalculator::isPrime1);
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
