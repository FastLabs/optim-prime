package optim.prime.service;

import optim.prime.algo.PrimeCalculable;
import optim.prime.concurent.PrimeHalfingTask;
import optim.prime.domain.PrimeCalculationResult;
import optim.prime.domain.EvaluationStatus;

import java.util.List;
import java.util.concurrent.ForkJoinPool;


public class ForkJoinPrimeService extends PrimeCalcService {
    private final ForkJoinPool forkJoinPool;

    public ForkJoinPrimeService(ForkJoinPool forkJoinPool, PrimeCalculable algoImpl) {
        super(algoImpl);
        this.forkJoinPool = forkJoinPool;
    }

    @Override
    protected EvaluationStatus isValid(Long in) {
        if(in < 0 ) {
            return EvaluationStatus.ERROR;
        }
        return EvaluationStatus.SUCCESS;
    }

    @Override
    public PrimeCalculationResult calculate(long in) {
        final List<Long> primes = forkJoinPool.invoke(new PrimeHalfingTask(0, in, super.algoImpl));
        return  PrimeCalculationResult.success(primes);
    }

}
