package optim.prime.service;


import optim.prime.algo.PrimeCalculable;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

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
                primeRepository.addResult(x, res);
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
    public EvaluationResult<List<Long>> calculate(Long in) {
        if (isValid(in) != RequestStatus.ERROR) {
            try {
                q.put(in);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return EvaluationResult.accepted();
        }
        return EvaluationResult.error();
    }
}
