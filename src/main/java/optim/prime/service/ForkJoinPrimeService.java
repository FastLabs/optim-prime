package optim.prime.service;

import optim.prime.algo.PrimeCalculable;
import optim.prime.domain.PrimeCalculationResult;
import optim.prime.domain.RequestStatus;

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
    public PrimeCalculationResult calculate(long in) {
        return null;
    }
}
