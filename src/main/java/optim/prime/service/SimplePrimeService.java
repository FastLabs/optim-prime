package optim.prime.service;

import optim.prime.algo.PrimeCalculable;
import optim.prime.algo.SimplePrimeCalculator;
import optim.prime.domain.PrimeCalculationResult;
import optim.prime.domain.RequestStatus;


public class SimplePrimeService extends PrimeCalcService {

    private final long max;

    public SimplePrimeService(PrimeCalculable algoImpl, long max) {
        super(algoImpl);
        this.max = max;
    }
//TODO: do I still need this constructor?
    public SimplePrimeService() {
        this(new SimplePrimeCalculator(), Integer.MAX_VALUE);
    }

    @Override
    public PrimeCalculationResult calculate(long in) {
        if (isValid(in) == RequestStatus.ERROR) {
            return PrimeCalculationResult.error(String.format("Supplied: %s, expected value between 0 and %s", in, max));
        }
        return PrimeCalculationResult.success(algoImpl.getPrimes(0, in));
    }

    @Override
    protected RequestStatus isValid(Long in) {
        if (in > max || in < 0) {
            return RequestStatus.ERROR;
        }
        return RequestStatus.SUCCESS;
    }
}
