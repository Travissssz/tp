package seedu.healthbud.parser;

import seedu.healthbud.command.input.BMICommand;
import seedu.healthbud.exception.InvalidBMIException;

import java.util.Map;

public class BMIParser {

    public static BMICommand parse(String input) throws InvalidBMIException {
        assert input != null : "Input should not be null";

        if (!input.contains("/h") || !input.contains("/w")) {
            throw new InvalidBMIException();
        }

        input = input.substring("bmi".length()).trim();

        Map<String, String> param = ParserParameters.parseParameters(input);

        if (!param.containsKey("w") || param.get("w").isEmpty()
                || !param.containsKey("h") || param.get("h").isEmpty()) {
            throw new InvalidBMIException();
        }

        if (!param.get("w").matches("\\d+") || !param.get("h").matches("^\\d+(\\.\\d+)?$")) {
            throw new InvalidBMIException();
        }

        double weight = Double.parseDouble(param.get("w"));
        double height = Double.parseDouble(param.get("h"));

        if(height > 3.0 || height < 0.2 || weight <= 0) {
            throw new InvalidBMIException();
        }

        return new BMICommand(input, weight, height);
    }
}
