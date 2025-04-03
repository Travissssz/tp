package seedu.healthbud.parser.addcommandparser;

import seedu.healthbud.LogList;
import seedu.healthbud.command.singlelog.AddWaterCommand;
import seedu.healthbud.exception.InvalidDateFormatException;
import seedu.healthbud.exception.InvalidWaterException;
import seedu.healthbud.parser.DateParser;
import seedu.healthbud.parser.ParserParameters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AddWaterParser {

    public static AddWaterCommand parse(LogList waterLogs, String input)
            throws InvalidWaterException, InvalidDateFormatException {

        assert input != null : "Input should not be null";

        input = input.substring("add water".length()).trim();

        if (!input.contains("/ml ") || !input.contains("/d ") || !input.contains("/t ") || !input.startsWith("/")) {
            throw new InvalidWaterException();
        }

        Map<String, String> param = ParserParameters.parseParameters(input);

        Set<String> allowedKeys = new HashSet<>(Arrays.asList("ml", "d", "t"));
        if (!param.keySet().equals(allowedKeys)) {
            throw new InvalidWaterException();
        }

        if (!param.containsKey("ml") || param.get("ml").isEmpty() ||
                !param.containsKey("d") || param.get("d").isEmpty() ||
                !param.containsKey("t") || param.get("t").isEmpty()) {
            throw new InvalidWaterException();
        }

        if (!param.get("ml").matches("\\d+")) {
            throw new InvalidWaterException();
        }

        String formattedDate = DateParser.formatDate(param.get("d"));

        return new AddWaterCommand(waterLogs, param.get("ml"), formattedDate, param.get("t"));
    }
}
