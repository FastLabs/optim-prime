package optim.prime.domain;


import java.util.Optional;

public class EvaluationResult<O> {
    private final RequestStatus requestStatus;
    private final Optional<O> result;
    private final String message;




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
