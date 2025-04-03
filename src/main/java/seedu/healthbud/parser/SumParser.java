package seedu.healthbud.parser;

import seedu.healthbud.LogList;
import seedu.healthbud.command.singlelog.SumCommand;
import seedu.healthbud.exception.InvalidDateFormatException;
import seedu.healthbud.exception.InvalidSumException;

public class SumParser {

    public static SumCommand parse(String input, LogList mealLogs, LogList waterLogs, LogList cardioLogs) throws
            InvalidSumException, InvalidDateFormatException {

        assert input != null : "Input should not be null";
        String[] parts = input.trim().split(" ");

        if (parts.length < 4 || !parts[2].equals("/d")) {
            throw new InvalidSumException();
        }

        String type = parts[1].toLowerCase();
        String date = parts[3];
        String parseDate = DateParser.formatDate(date);

        switch (type) {
        case "cal":
            return new SumCommand(mealLogs, type, parseDate);
        case "vol":
            return new SumCommand(waterLogs, type, parseDate);
        case "cardio":
            return new SumCommand(cardioLogs, type, parseDate);
        default:
            throw new InvalidSumException();
        }
    }
}
