package optim.prime.service;

import optim.prime.algo.PrimeCalculable;

import java.util.List;
import java.util.concurrent.ForkJoinPool;


public class ForkJoinPrimeService extends PrimeCalcService {
    private final ForkJoinPool forkJoinPool;

    public ForkJoinPrimeService(ForkJoinPool forkJoinPool, PrimeCalculable algoImpl) {
        super(algoImpl);
        this.forkJoinPool = forkJoinPool;
    }

    @Override
    protected RequestStatus isValid(Long in) {
        return null;
    }

    @Override
    public EvaluationResult<List<Long>> calculate(Long in) {
        return null;
    }
}
