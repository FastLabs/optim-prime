package optim.prime.config;


import optim.prime.algo.SimplePrimeCalculator1;
import optim.prime.app.Utils;
import optim.prime.service.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ForkJoinPool;

@Configuration
@ComponentScan(basePackages = {"optim.prime.rest.controller"})
@EnableAutoConfiguration
public class AppConfig {

    /* configurable options:
    - partition size
    - algorithm name
    - maybe some cache sizes if implemented

     */

    @Bean
    ForkJoinPool threadPool() {
        return ForkJoinPool.commonPool();
    }

    @Bean
    PrimeRepository primerepository() {
        return new PrimeRepository();
    }

    @Bean
    PrimeCalcService forkJoinCalcService(ForkJoinPool pool) {
        return new ForkJoinPrimeService(pool, Utils::stubPrimes);
    }

    @Bean
    PrimeCalcService simplePrimeService() {
        return new SimplePrimeService();
    }

    @Bean
    PrimeCalcService akkaAgentService(ForkJoinPool pool) {
        return new AkkaAgentPrimeService(pool, Utils::stubPrimes);
    }

    @Bean
    PrimeCalcService asyncPrimeService(ForkJoinPool pool, PrimeRepository repository) {
        return new AsyncPrimeService(pool, new SimplePrimeCalculator1(), repository);
    }


}
