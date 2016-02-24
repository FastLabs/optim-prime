package optim.prime.service;


import optim.prime.algo.PrimeCalculable;
import optim.prime.domain.RequestStatus;

public abstract class PrimeCalcService implements CalculableService {

    protected final PrimeCalculable algoImpl;

    protected PrimeCalcService(PrimeCalculable algoImpl) {
        this.algoImpl = algoImpl;
    }


    protected abstract RequestStatus isValid(Long in);

}
