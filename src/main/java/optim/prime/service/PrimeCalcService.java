package optim.prime.service;


import optim.prime.algo.PrimeCalculable;

import java.util.List;

public abstract class PrimeCalcService implements CalculableService<Long, List<Long>> {

    protected final PrimeCalculable algoImpl;

    protected PrimeCalcService(PrimeCalculable algoImpl) {
        this.algoImpl = algoImpl;
    }


    protected abstract RequestStatus isValid(Long in);

}
