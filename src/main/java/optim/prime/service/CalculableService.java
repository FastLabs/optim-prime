package optim.prime.service;


public interface CalculableService<I, O> {
    EvaluationResult<O> calculate(I in);
}
