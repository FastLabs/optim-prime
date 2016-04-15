# optim-prime

## packages:

- optim.prime.service - calculation related services. Algorithms are exposed as services. This package contains few implementations
with different scalability aproaches
- optim.prime.controller - Exposes services as REST
- optim.prime.domain - prime calculation domain: classes used to represent prime calcualtion domain
- optim.prime.concurrent - mainly fork-join  classes
- optim.prime.config - application configuration, Spring is used for service wiring
- optim.prime.app - entry point and utility classes
- optim.prime.algo - different algorithms for prime calculation
  
  TODO: 
 # add logging
 
 # add one more service for fork join
  
  
## Execution:
  mvn package
  java -jar ./target/prime-boot-0.0.1-SNAPSHOT.jar