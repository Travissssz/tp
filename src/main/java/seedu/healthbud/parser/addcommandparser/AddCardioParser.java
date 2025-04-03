package seedu.healthbud.parser.addcommandparser;

import seedu.healthbud.LogList;
import seedu.healthbud.command.singlelog.AddCardioCommand;
import seedu.healthbud.exception.InvalidCardioException;
import seedu.healthbud.exception.InvalidDateFormatException;
import seedu.healthbud.parser.DateParser;
import seedu.healthbud.parser.ParserParameters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AddCardioParser {

    public static AddCardioCommand parse(LogList cardioLogs, String input)
            throws InvalidCardioException, InvalidDateFormatException {

        assert input != null : "Input should not be null";

        String[] parts = input.trim().split(" ");
        if (parts.length < 2) {
            throw new InvalidCardioException();
        }

        if (!input.contains("/s ") || !input.contains("/i ") || !input.contains("/t ") || !input.contains("/d ")) {
            throw new InvalidCardioException();
        }

        // Remove command prefix and trim
        input = input.replaceFirst("add cardio", "").trim();

        String name = input.substring(0, input.indexOf("/")).trim();

        if (input.isEmpty()) {
            throw new InvalidCardioException();
        }

        Map<String, String> param = ParserParameters.parseParameters(input.substring(name.length()));
        Set<String> allowedKeys = new HashSet<>(Arrays.asList("s", "i", "t", "d"));
        if (!param.keySet().equals(allowedKeys)) {
            throw new InvalidCardioException();
        }

        if (name.isEmpty() ||
                !param.containsKey("s") || param.get("s").isEmpty() ||
                !param.containsKey("i") || param.get("i").isEmpty() ||
                !param.containsKey("t") || param.get("t").isEmpty() ||
                !param.containsKey("d") || param.get("d").isEmpty()) {
            throw new InvalidCardioException();
        }

        if (!param.get("s").matches("\\d+(\\.\\d+)?") ||
                !param.get("i").matches("\\d+(\\.\\d+)?") ||
                !param.get("t").matches("\\d+(\\.\\d+)?")) {
            throw new InvalidCardioException();
        }

        // Parse and format the date
        String formattedDate = DateParser.formatDate(param.get("d"));

        return new AddCardioCommand(cardioLogs, name,
                param.get("s"), param.get("i"), param.get("t"),
                formattedDate);
    }
}

