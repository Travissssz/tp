package seedu.healthbud.parser.addcommandparser;

import seedu.healthbud.LogList;
import seedu.healthbud.command.singlelog.AddWorkoutCommand;
import seedu.healthbud.exception.InvalidDateFormatException;
import seedu.healthbud.exception.InvalidWorkoutException;
import seedu.healthbud.parser.DateParser;
import seedu.healthbud.parser.ParserParameters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AddWorkoutParser {

    //@@author Ahmish15
    public static AddWorkoutCommand parse(LogList workoutLogs, String input)
            throws InvalidWorkoutException, InvalidDateFormatException {

        assert input != null : "Input should not be null";

        if (!input.contains("/r ") || !input.contains("/s ") || !input.contains("/d ") || !input.contains("/w ")) {
            throw new InvalidWorkoutException();
        }

        input = input.substring("add workout".length()).trim();

        String name = input.substring(0, input.indexOf("/")).trim();

        Map<String, String> param = ParserParameters.parseParameters(input.substring(name.length()));
        Set<String> allowedKeys = new HashSet<>(Arrays.asList("r", "d", "s", "w"));
        if (!param.keySet().equals(allowedKeys)) {
            throw new InvalidWorkoutException();
        }

        if (name.isEmpty() ||
                !param.containsKey("r") || param.get("r").isEmpty() ||
                !param.containsKey("s") || param.get("s").isEmpty() ||
                !param.containsKey("d") || param.get("d").isEmpty() ||
                !param.containsKey("w") || param.get("w").isEmpty()) {
            throw new InvalidWorkoutException();
        }

        if (!param.get("r").matches("\\d+") || !param.get("s").matches("\\d+") ||
                !param.get("w").matches("\\d+(\\.\\d+)?")) {
            throw new InvalidWorkoutException();
        }

        String formattedDate = DateParser.formatDate(param.get("d"));

        return new AddWorkoutCommand(workoutLogs, name, param.get("r"), param.get("s"),
                formattedDate, param.get("w"));
    }
}
