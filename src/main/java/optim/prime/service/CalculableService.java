package optim.prime.service;


import optim.prime.domain.PrimeCalculationResult;

public interface CalculableService {
    PrimeCalculationResult calculate(long in);
}
