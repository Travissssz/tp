package seedu.healthbud.exception;

public class InvalidAddLogException extends Exception {
    public InvalidAddLogException() {
        super("Invalid log command (e.g. add pb/meal/workout/water/goal)");
    }
}
