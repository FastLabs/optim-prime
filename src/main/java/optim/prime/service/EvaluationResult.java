package optim.prime.service;


import java.util.Optional;

public class EvaluationResult<O> {
    private final RequestStatus requestStatus;
    private final Optional<O> result;
    private final String message;

    public static <O> EvaluationResult<O> error() {
        return new EvaluationResult<>(RequestStatus.ERROR, Optional.empty(), null);
    }

    public static <O> EvaluationResult<O> error(String message) {
        return new EvaluationResult<>(RequestStatus.ERROR, Optional.empty(), null);
    }

    public static <O> EvaluationResult<O> success(O value) {
        return new EvaluationResult<>(RequestStatus.SUCCESS, Optional.ofNullable(value), null);
    }

    public static <O> EvaluationResult<O> accepted() {
        return new EvaluationResult<>(RequestStatus.ACCEPTED, Optional.empty(), null);
    }


    public EvaluationResult(RequestStatus requestStatus, Optional<O> result, String message) {
        this.requestStatus = requestStatus;
        this.result = result;
        this.message = message;
    }


    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public Optional<O> getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
