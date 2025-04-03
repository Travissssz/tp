package seedu.healthbud.exception;

public class InvalidSearchException extends RuntimeException {
    public InvalidSearchException() {
        super("Invalid search command - Try search <logType> /d <date> OR search <logType> /k <keyword>");
    }
}
