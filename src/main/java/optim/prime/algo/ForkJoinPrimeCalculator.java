package optim.prime.algo;


import optim.prime.concurent.PrimeHalfingTask;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Deprecated
//TODO: this logic goes at the service layer
public class ForkJoinPrimeCalculator extends AbstractPrimeCalculator {

    private final ForkJoinPool pool;
    private final PrimeCalculable alg;

    public ForkJoinPrimeCalculator(ForkJoinPool pool, PrimeCalculable alg) {
        this.pool = pool;
        this.alg = alg;
    }


    @Override
    public List<Long> getPrimes(long from, long to) {
        return pool.invoke(new PrimeHalfingTask(from, to, alg));
    }
}
