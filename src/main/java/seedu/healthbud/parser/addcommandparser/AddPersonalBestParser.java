package seedu.healthbud.parser.addcommandparser;

import seedu.healthbud.LogList;
import seedu.healthbud.command.singlelog.AddPersonalBestCommand;
import seedu.healthbud.exception.InvalidDateFormatException;
import seedu.healthbud.exception.InvalidPersonalBestException;
import seedu.healthbud.parser.DateParser;
import seedu.healthbud.parser.ParserParameters;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AddPersonalBestParser {

    public static AddPersonalBestCommand parse(LogList pbLogs, String input)
            throws InvalidPersonalBestException, InvalidDateFormatException {

        assert input != null : "Input should not be null";

        if (!input.contains("/w ") || !input.contains("/d ")) {
            throw new InvalidPersonalBestException();
        }

        input = input.substring("add pb".length()).trim();

        String name = input.substring(0, input.indexOf("/")).trim();

        Map<String, String> param = ParserParameters.parseParameters(input.substring(name.length()));
        Set<String> allowedKeys = new HashSet<>(Arrays.asList("w", "d"));
        if (!param.keySet().equals(allowedKeys)) {
            throw new InvalidPersonalBestException();
        }

        if (name.isEmpty() ||
                !param.containsKey("w") || param.get("w").isEmpty() ||
                !param.containsKey("d") || param.get("d").isEmpty()) {
            throw new InvalidPersonalBestException();
        }

        if (!param.get("w").matches("\\d+")) {
            throw new InvalidPersonalBestException();
        }
        String formattedDate = DateParser.formatDate(param.get("d"));

        return new AddPersonalBestCommand(pbLogs, name, param.get("w"), formattedDate);
    }
}

