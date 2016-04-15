package optim.prime.domain;


import java.util.Optional;

public class EvaluationResult<O> {
    private final EvaluationStatus evaluationStatus;
    private final Optional<O> result;
    private final String message;




    public EvaluationResult(EvaluationStatus evaluationStatus, Optional<O> result, String message) {
        this.evaluationStatus = evaluationStatus;
        this.result = result;
        this.message = message;
    }


    public EvaluationStatus getEvaluationStatus() {
        return evaluationStatus;
    }

    public Optional<O> getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }
}
