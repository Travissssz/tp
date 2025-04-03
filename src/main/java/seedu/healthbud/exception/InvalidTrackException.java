package seedu.healthbud.exception;

public class InvalidTrackException extends RuntimeException {
    public InvalidTrackException() {
        super("Unable to Track, Please enter a valid tracking command in the form of - track goal /d <date>");
    }
}
