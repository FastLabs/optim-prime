package optim.prime.service;


import optim.prime.algo.PrimeCalculable;
import optim.prime.domain.PrimeCalculationResult;
import optim.prime.domain.PrimeRange;
import optim.prime.domain.RequestStatus;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

/**
 * Asynchronous prime calculator uses a queue to delegate the calculation
 */
public class AsyncPrimeService extends PrimeCalcService {

    final PrimeRepository primeRepository;
    final BlockingQueue<Long> q = new ArrayBlockingQueue<>(100);

    public AsyncPrimeService(ExecutorService executorService,
                             PrimeCalculable algoImpl, PrimeRepository primeRepository) {
        super(algoImpl);
        this.primeRepository = primeRepository;

        executorService.submit(this::process);
    }

    private void process() {
        while (true) {
            try {
                final Long x = q.take();
                final List<Long> res = algoImpl.getPrimes(0, x);
                if (res != null && res.size() > 0) {
                    primeRepository.addPrimes(PrimeRange.from(0).to(x), res);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException("Jopa");
            }
        }
    }

    @Override
    protected RequestStatus isValid(Long in) {
        return RequestStatus.SUCCESS;
    }

    @Override
    public PrimeCalculationResult calculate(long in) {
        if (isValid(in) != RequestStatus.ERROR) {
            try {
                q.put(in);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return PrimeCalculationResult.error();
            }
            return PrimeCalculationResult.accepted();
        }
        return PrimeCalculationResult.error();
    }
}
