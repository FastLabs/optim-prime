package optim.prime.rest.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@RestController
public class OptimPrimeRestController {

    private final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();



    @RequestMapping(value = "/")
    String home() {
        return "Hello World";
    }

    @RequestMapping(value = "/primes/{value}") // this serivce will check the cache
    List<Long> primes(@PathVariable("value") Long value) {
        return new ArrayList<Long>();
    }

}
