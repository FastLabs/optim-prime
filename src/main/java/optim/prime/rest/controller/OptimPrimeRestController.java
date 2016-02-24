package optim.prime.rest.controller;


import optim.prime.domain.EvaluationResult;
import optim.prime.service.PrimeCalcService;
import optim.prime.service.PrimeRepository;
import optim.prime.domain.RequestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OptimPrimeRestController {

    @Autowired
    PrimeCalcService simplePrimeService;
    @Autowired
    PrimeRepository primeRepository;
    @Autowired
    PrimeCalcService asyncPrimeService;


    @RequestMapping(value = "/")
    String home() {
        return "Hello World";
    }

    private ResponseEntity<List<Long>> foundPrimes(List<Long> primes) {
        return new ResponseEntity<>(primes, HttpStatus.OK);
    }

    @RequestMapping(value = "/primes/{value}")
    ResponseEntity<List<Long>> primes(@PathVariable("value") Long value) {

        final List<Long> cached = primeRepository.getPrimes(value);
        if (cached != null) {
            return foundPrimes(cached);
        }

        final EvaluationResult<List<Long>> primes = simplePrimeService.calculate(value);

        if (primes.getRequestStatus() == RequestStatus.ERROR) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);//TODO: fix the http code
        }

        if (primes.getResult().isPresent()) {
            foundPrimes(primes.getResult().get());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.POST, path = "primeLong")
    public ResponseEntity<String> submitPrimeJob(final @RequestBody Long value) {
        final EvaluationResult<?> result = asyncPrimeService.calculate(value);
        if (result.getRequestStatus() == RequestStatus.ACCEPTED) {
            return new ResponseEntity<>(String.format("prime/%s", value), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

}
