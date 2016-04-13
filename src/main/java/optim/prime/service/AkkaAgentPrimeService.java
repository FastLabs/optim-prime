package optim.prime.service;


import optim.prime.algo.PrimeCalculable;
import optim.prime.domain.PrimeCalculationResult;
import optim.prime.domain.RequestStatus;

import java.util.concurrent.ExecutorService;


//TODO: review if this is still required
public class AkkaAgentPrimeService extends PrimeCalcService {
    private final ExecutorService executorService;

    public AkkaAgentPrimeService(ExecutorService executorService, PrimeCalculable algoImpl) {
        super(algoImpl);
        this.executorService = executorService;
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
