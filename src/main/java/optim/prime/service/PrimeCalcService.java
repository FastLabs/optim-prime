package optim.prime.service;


import optim.prime.algo.PrimeCalculable;
import optim.prime.domain.EvaluationStatus;

/**
 * Abstract class for the services that evaluates primes based on
 */
public abstract class PrimeCalcService implements CalculableService {

    protected final PrimeCalculable algoImpl;

    protected PrimeCalcService(PrimeCalculable algoImpl) {
        this.algoImpl = algoImpl;
    }

    /**
     *
     * @param in
     * @return
     */
    protected abstract EvaluationStatus isValid(Long in);

}
