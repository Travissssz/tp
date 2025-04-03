package seedu.healthbud.exception;

public class  InvalidBMIException extends Exception {
    public InvalidBMIException() {
        super("Invalid BMI command (e.g. bmi /w <weight_in_kg> /h <height_in_m>)");
    }
}

