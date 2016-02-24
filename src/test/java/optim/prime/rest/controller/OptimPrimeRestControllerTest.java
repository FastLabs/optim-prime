package optim.prime.rest.controller;


import optim.prime.concurent.PrimeHalfingTask;
import optim.prime.config.AppConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AppConfig.class)
@WebIntegrationTest({"server.port=9090"})
public class OptimPrimeRestControllerTest {

    private final RestTemplate rest = new TestRestTemplate();

    @Test
    @Ignore
    public void simpleTest() {
        final ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        List<Long> x = forkJoinPool.invoke(new PrimeHalfingTask(90));
        System.out.println(x);
    }





}
