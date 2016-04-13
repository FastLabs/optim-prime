package optim.prime.service;


import optim.prime.domain.PrimeCalculationResult;

/**
 * To be implemented by the classes that will provide primes calculation services
 */
public interface CalculableService {
    /**
     * Calculates the prime numbers up to
     * @param in - prime top range
     * @return - prime calculation result
     */
    PrimeCalculationResult calculate(long in);
}
