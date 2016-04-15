package optim.prime.rest.controller;


import optim.prime.domain.EvaluationResult;
import optim.prime.domain.PrimeCalculationResult;
import optim.prime.domain.PrimeRange;
import optim.prime.service.PrimeCalcService;
import optim.prime.service.PrimeRepository;
import optim.prime.domain.EvaluationStatus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.HttpStatus.ACCEPTED;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class OptimPrimeRestController {

    private static final Log logger = LogFactory.getLog(OptimPrimeRestController.class);

    @Autowired
    PrimeCalcService simplePrimeService;
    @Autowired
    PrimeRepository primeRepository;
    @Autowired
    PrimeCalcService asyncPrimeService;

    @Autowired
    PrimeCalcService forkJoinCalcService;

    private ResponseEntity<List<Long>> foundPrimes(List<Long> primes) {

        return ResponseEntity.ok(primes);
    }

    private ResponseEntity<List<Long>> foundPrimesFromCache(List<Long> primes) {

        return ResponseEntity.status(HttpStatus.OK)
                .cacheControl(CacheControl.maxAge(10, TimeUnit.HOURS))
                .body(primes);
    }

    @RequestMapping(method = GET, value = "/primes/{value}")
    ResponseEntity<List<Long>> primes(@PathVariable("value") Long value) {

        logger.info(String.format("Prime Range requested for %s", value));

        final List<Long> cached = primeRepository.getPrimes(value);
        if (cached != null) {
            if (logger.isDebugEnabled()) {
                logger.debug(String.format("Matched a calculated value from cache for :%s", value));
            }
            return foundPrimesFromCache(cached);
        }

        final EvaluationResult<List<Long>> primes = simplePrimeService.calculate(value);

        if (primes.getEvaluationStatus() == EvaluationStatus.ERROR) {
            //maybe a redirect is required, but at the moment there is no other get resources
            logger.error("Prime could not be evaluated by the default algorithm, check other implementations");
            return new ResponseEntity<>(NOT_FOUND);
        }

        if (primes.getResult().isPresent()) {
            primeRepository.addPrimes(PrimeRange.from(0).to(value), primes.getResult().get());
            return foundPrimes(primes.getResult().get());
        }

        return new ResponseEntity<>(NOT_FOUND);
    }

    @RequestMapping(method = GET, value = "/primes/fork/{value}")
    public ResponseEntity<List<Long>> forkPrimes(@PathVariable("value") Long value) {
        logger.info("fork join algorithm invocation");
        final PrimeCalculationResult result = forkJoinCalcService.calculate(value);
        if (result.getEvaluationStatus() == EvaluationStatus.SUCCESS) {
            return foundPrimes(result.getResult().get());
        }
        return new ResponseEntity<>(NOT_FOUND);
    }

    @RequestMapping(method = POST, path = "primes")
    public ResponseEntity<String> submitPrimeJob(final @RequestBody Long value) {

        final EvaluationResult<?> result = asyncPrimeService.calculate(value);
        if (result.getEvaluationStatus() == EvaluationStatus.ACCEPTED) {
            return new ResponseEntity<>(String.format("prime/%s", value), ACCEPTED);
        }
        return new ResponseEntity<>(NOT_ACCEPTABLE);
    }

}
