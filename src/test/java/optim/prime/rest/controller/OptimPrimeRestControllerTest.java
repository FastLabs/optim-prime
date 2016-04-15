package optim.prime.rest.controller;


import optim.prime.config.AppConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)


@SpringBootTest(classes = {AppConfig.class}, properties = {"server.port=9090"}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OptimPrimeRestControllerTest {

    private final RestTemplate rest = new TestRestTemplate();
    private static final String url = "http://localhost:9090";


    @Test
    public void simpleTest() {
        final ResponseEntity<List> result = rest.getForEntity(String.format("%s/primes/10", url), List.class);
        assertNull(result.getHeaders().getCacheControl());
        assertArrayEquals(new Integer[]{2, 3, 5, 7}, result.getBody().toArray());
        ResponseEntity<List> result1 = rest.getForEntity(String.format("%s/primes/10", url), List.class);
        assertEquals("max-age=36000",result1.getHeaders().getCacheControl());
        assertArrayEquals(new Integer[]{2, 3, 5, 7}, result1.getBody().toArray());
    }

    @Test
    public void testPost() {
        final ResponseEntity<String> result =  rest.postForEntity(String.format("%s/primes", url), 20, String.class);

        assertEquals(HttpStatus.ACCEPTED, result.getStatusCode());
        ResponseEntity<List> result1 = rest.getForEntity(String.format("%s/primes/%s", url, 20), List.class); //this will be fetched from cache
        assertEquals("max-age=36000",result1.getHeaders().getCacheControl());
        assertArrayEquals(new Integer[]{2, 3, 5, 7, 11, 13, 17, 19}, result1.getBody().toArray());
    }


    @Test
    public void forkJoinTest() {
        final ResponseEntity<List> result = rest.getForEntity(String.format("%s/primes/fork/10", url), List.class);
        assertNull(result.getHeaders().getCacheControl());
        assertArrayEquals(new Integer[]{2, 3, 5, 7}, result.getBody().toArray());
    }


}
