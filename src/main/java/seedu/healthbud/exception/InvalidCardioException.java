package seedu.healthbud.exception;

public class InvalidCardioException extends Exception {
    public InvalidCardioException() {
        super("Invalid cardio command (e.g add cardio <<exercise>> /s <<speed>> " +
                "/i <<incline>> /t <<duration>> /d <<date>>)");
    }

}
