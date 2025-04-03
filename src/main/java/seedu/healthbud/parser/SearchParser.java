package seedu.healthbud.parser;

import seedu.healthbud.LogList;
import seedu.healthbud.command.singlelog.SearchCommand;
import seedu.healthbud.exception.InvalidDateFormatException;
import seedu.healthbud.exception.InvalidSearchException;

public class SearchParser {

    public static SearchCommand parse(String input,
                                      LogList mealLogs, LogList workoutLogs,
                                      LogList waterLogs, LogList pbLogs, LogList cardioLogs)
            throws InvalidSearchException, InvalidDateFormatException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidSearchException(
                    "Invalid search command - Try search <logType> /d <date> OR search <logType> /k <keyword>");
        }
        // Remove "search" keyword and trim the remainder.
        String paramsString = input.replaceFirst("(?i)^search", "").trim();
        if (paramsString.isEmpty()) {
            throw new InvalidSearchException(
                    "Invalid search command - Try search <logType> /d <date> OR search <logType> /k <keyword>");
        }

        // Expected format: <logType> /d <date> OR <logType> /k <keyword>
        String[] parts = paramsString.split("\\s+");
        if (parts.length < 3) {
            throw new InvalidSearchException("Invalid search command - not enough parameters.");
        }

        String logType = parts[0].toLowerCase();
        String flag = parts[1].toLowerCase();
        String parameter = parts[2];

        String date = null;
        String keyword = null;
        if (flag.equals("/d")) {
            date = DateParser.formatDate(parameter); // may throw InvalidDateFormatException
        } else if (flag.equals("/k")) {
            keyword = parameter;
        } else {
            throw new InvalidSearchException("Invalid parameter flag. Use /d for date or /k for keyword.");
        }

        // Ensure exactly one of date or keyword is provided.
        if ((date != null && keyword != null) || (date == null && keyword == null)) {
            throw new InvalidSearchException(
                    "Invalid search command - Provide exactly one of /d <date> or /k <keyword>.");
        }

        // Select the appropriate LogList based on logType.
        LogList targetLogList;
        switch (logType) {
        case "meal":
            targetLogList = mealLogs;
            break;
        case "workout":
            targetLogList = workoutLogs;
            break;
        case "water":
            targetLogList = waterLogs;
            break;
        case "pb":
            targetLogList = pbLogs;
            break;
        case "cardio":
            targetLogList = cardioLogs;
            break;
        default:
            throw new InvalidSearchException("Invalid log type. Valid types: meal, workout, water, pb, cardio.");
        }

        return new SearchCommand(input, targetLogList, date, keyword);
    }
}
