package optim.prime.service;


import optim.prime.algo.PrimeCalculable;

import java.util.List;
import java.util.concurrent.ExecutorService;

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
    public EvaluationResult<List<Long>> calculate(Long in) {
        return null;
    }
}
