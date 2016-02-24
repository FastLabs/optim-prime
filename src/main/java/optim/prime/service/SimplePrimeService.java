package optim.prime.service;

import optim.prime.algo.PrimeCalculable;
import optim.prime.algo.SimplePrimeCalculator;

import java.util.List;



public class SimplePrimeService extends PrimeCalcService {

    private final long max;

    public SimplePrimeService(PrimeCalculable algoImpl, long max) {
        super(algoImpl);
        this.max = max;
    }

    public SimplePrimeService() {
        this(new SimplePrimeCalculator(), Integer.MAX_VALUE);
    }

    @Override
    public EvaluationResult<List<Long>> calculate(Long in) {
        if(isValid(in) == RequestStatus.ERROR) {
            return EvaluationResult.error(String.format("Supplied: %s, expected value between 0 and %s", in, max));
        }
        return EvaluationResult.success(algoImpl.getPrimes(0, in));
    }

    @Override
    protected RequestStatus isValid(Long in) {
        if(in> Integer.MAX_VALUE && in <0) {
            return RequestStatus.ERROR;
        }
        return RequestStatus.SUCCESS;
    }
}
