package seedu.healthbud.parser.addcommandparser;

import seedu.healthbud.LogList;
import seedu.healthbud.command.singlelog.AddMealCommand;
import seedu.healthbud.exception.InvalidDateFormatException;
import seedu.healthbud.exception.InvalidMealException;
import seedu.healthbud.parser.DateParser;
import seedu.healthbud.parser.ParserParameters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class AddMealParser {

    //@@author Ahmish15
    public static AddMealCommand parse(LogList mealLogs, String input)
            throws InvalidMealException, InvalidDateFormatException {

        assert input != null : "Input should not be null";
        if (!input.contains("/cal ") || !input.contains("/d ") || !input.contains("/t ")) {
            throw new InvalidMealException();
        }

        input = input.substring("add meal".length()).trim();

        String name = input.substring(0, input.indexOf("/")).trim();

        Map<String, String> param = ParserParameters.parseParameters(input.substring(name.length()));
        Set<String> allowedKeys = new HashSet<>(Arrays.asList("cal", "d", "t"));
        if (!param.keySet().equals(allowedKeys)) {
            throw new InvalidMealException();
        }

        if (name.isEmpty() || !param.containsKey("cal") || param.get("cal").isEmpty() || !param.containsKey("d")
                || param.get("d").isEmpty() || !param.containsKey("t") || param.get("t").isEmpty()) {
            throw new InvalidMealException();
        }

        if (!param.get("cal").matches("\\d+")) {
            throw new InvalidMealException();
        }

        String formattedDate = DateParser.formatDate(param.get("d"));

        return new AddMealCommand(mealLogs, name, param.get("cal"), formattedDate, param.get("t"));
    }
}
