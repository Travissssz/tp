package seedu.healthbud.exception;

public class InvalidRecommendException extends Exception {
    public InvalidRecommendException() {
        super("Invalid recommend command (e.g. recommend chest/back/biceps/triceps/legs/shoulders/abs)");
    }
}
