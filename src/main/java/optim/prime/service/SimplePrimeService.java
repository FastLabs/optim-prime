package optim.prime.service;

import optim.prime.algo.PrimeCalculable;
import optim.prime.algo.SimplePrimeCalculator;
import optim.prime.domain.PrimeCalculationResult;
import optim.prime.domain.EvaluationStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 *
 */
public class SimplePrimeService extends PrimeCalcService {

    private static final Log logger = LogFactory.getLog(SimplePrimeService.class);

    private final long max;

    public SimplePrimeService(PrimeCalculable algoImpl, long max) {
        super(algoImpl);
        this.max = max;
    }

    public SimplePrimeService() {
        this(new SimplePrimeCalculator(), Integer.MAX_VALUE);
    }

    @Override
    public PrimeCalculationResult calculate(long in) {
        logger.info("Eager prime calculation started");
        if (isValid(in) == EvaluationStatus.ERROR) {
            return PrimeCalculationResult.error(String.format("Supplied: %s, expected value between 0 and %s", in, max));
        }
        final List<Long> x = algoImpl.getPrimes(0, in);
        logger.info(String.format("Eager prime calculation completed. Found %s primes", x.size()));
        return PrimeCalculationResult.success(x);
    }

    @Override
    protected EvaluationStatus isValid(Long in) {
        if (in > max || in < 0) {
            return EvaluationStatus.ERROR;
        }
        return EvaluationStatus.SUCCESS;
    }
}
