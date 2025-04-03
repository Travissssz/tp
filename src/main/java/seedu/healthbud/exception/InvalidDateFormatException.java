package seedu.healthbud.exception;

public class InvalidDateFormatException extends Exception {

    public InvalidDateFormatException() {
        super("Invalid date, try DD/MM/YYYY");
    }
}
