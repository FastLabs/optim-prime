package optim.prime.service;


import optim.prime.algo.PrimeCalculable;
import optim.prime.domain.PrimeCalculationResult;
import optim.prime.domain.PrimeRange;
import optim.prime.domain.EvaluationStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

/**
 * Asynchronous prime calculator uses a queue to delegate the calculation
 */
public class AsyncPrimeService extends PrimeCalcService {
    private static final Log logger = LogFactory.getLog(AsyncPrimeService.class);
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
                logger.error("Calculation interrupted", e);
                throw new RuntimeException("Calculation interrupted");
            }
        }
    }

    @Override
    protected EvaluationStatus isValid(Long in) {
        return EvaluationStatus.SUCCESS;
    }

    @Override
    public PrimeCalculationResult calculate(long in) {
        if (isValid(in) != EvaluationStatus.ERROR) {
            try {
                q.put(in);
            } catch (InterruptedException e) {
                logger.error("Calculation interrupted", e);
                return PrimeCalculationResult.error();
            }
            return PrimeCalculationResult.accepted();
        }
        return PrimeCalculationResult.error();
    }
}
