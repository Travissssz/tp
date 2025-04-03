package seedu.healthbud.exception;

public class  InvalidMealException extends Exception {
    public InvalidMealException() {
        super("Invalid meal command (e.g. add meal <meal_name> /cal <calories> /d <DD/MM/YYYY> /t <time>)");
    }
}
