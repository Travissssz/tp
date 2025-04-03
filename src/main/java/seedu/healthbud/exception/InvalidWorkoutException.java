package seedu.healthbud.exception;

public class InvalidWorkoutException extends Exception {
    public InvalidWorkoutException() {
        super("Invalid workout command (e.g. add workout <exercise> /w <weight> /r <reps> /s <sets> /d <DD/MM/YYYY>)");
    }
}
