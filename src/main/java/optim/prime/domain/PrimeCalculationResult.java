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

    public static PrimeCalculationResult success(List<Long> value) {
        return new PrimeCalculationResult(RequestStatus.SUCCESS, Optional.ofNullable(value), null);
    }

    public static PrimeCalculationResult accepted() {
        return new PrimeCalculationResult(RequestStatus.ACCEPTED, Optional.empty(), null);
    }

    public PrimeCalculationResult(RequestStatus requestStatus, Optional<List<Long>> result, String message) {
        super(requestStatus, result, message);
    }
}
