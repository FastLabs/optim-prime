package optim.prime.app;


import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class OptimPrimeClient {
    public void main(String... args) {
        final RestTemplate rest = new RestTemplate();
        ResponseEntity<List> restult = rest.getForEntity("http://localhost:8080/primes/10", List.class);

        System.out.println(restult.getBody());
    }
}
