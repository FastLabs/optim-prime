package optim.prime.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"optim.prime.rest.controller"})
@EnableAutoConfiguration
public class AppConfig {

    /* configurable options:
    - partition size
    - algorithm name
    - maybe some cache sizes if implemented

     */
}
