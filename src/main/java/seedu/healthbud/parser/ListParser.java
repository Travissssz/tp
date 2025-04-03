package seedu.healthbud.parser;

import seedu.healthbud.LogList;
import seedu.healthbud.command.singlelog.ListCommand;
import seedu.healthbud.exception.InvalidListException;

public class ListParser {

    public static ListCommand parse(String input, LogList mealLogs, LogList workoutLogs,
                                    LogList waterLogs, LogList pbLogs, LogList cardioLogs)
            throws InvalidListException {
        assert input != null : "Input should not be null";
        String[] parts = input.trim().split(" ");

        if (parts.length != 2) {
            throw new InvalidListException();
        }

        switch (parts[1]) {
        case "meal":
            return new ListCommand(mealLogs);
        case "workout":
            return new ListCommand(workoutLogs);
        case "water":
            return new ListCommand(waterLogs);
        case "pb":
            return new ListCommand(pbLogs);
        case "cardio":
            return new ListCommand(cardioLogs);
        default:
            throw new InvalidListException();
        }
    }
}
