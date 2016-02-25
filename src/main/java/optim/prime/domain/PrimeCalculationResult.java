package optim.prime.domain;


import java.util.List;
import java.util.Optional;

public final class PrimeCalculationResult extends EvaluationResult<List<Long>> {

    public static PrimeCalculationResult error() {
        return error(null);
    }

    public static PrimeCalculationResult error(String message) {
        return new PrimeCalculationResult(RequestStatus.ERROR, Optional.empty(), message);
    }

    /**
     * Success for a  list of prime numbers, if the list is null or is empty empty returned
     * @param values
     * @return
     */
    public static PrimeCalculationResult success(List<Long> values) {
        final Optional result = values != null && values.size() > 0? Optional.of(values): Optional.empty();
        return new PrimeCalculationResult(RequestStatus.SUCCESS, result, null);
    }

    public static PrimeCalculationResult accepted() {
        return new PrimeCalculationResult(RequestStatus.ACCEPTED, Optional.empty(), null);
    }

    public PrimeCalculationResult(RequestStatus requestStatus, Optional<List<Long>> result, String message) {
        super(requestStatus, result, message);
    }
}
