package seedu.healthbud.parser;

import seedu.healthbud.LogList;
import seedu.healthbud.command.singlelog.DeleteCommand;
import seedu.healthbud.exception.InvalidDeleteException;

public class DeleteParser {

    public static DeleteCommand parse(String input, LogList mealLogs, LogList workoutLogs, LogList waterLogs,
                                      LogList pbLogs, LogList cardioLogs)
            throws InvalidDeleteException {

        assert input != null : "Input should not be null";

        String[] parts = input.trim().split(" ");
        if (parts.length != 3) {
            throw new InvalidDeleteException();
        }

        int index;

        try {
            index = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            throw new InvalidDeleteException();
        }

        switch (parts[1]) {
        case "meal":
            return new DeleteCommand(mealLogs, index);
        case "workout":
            return new DeleteCommand(workoutLogs, index);
        case "water":
            return new DeleteCommand(waterLogs, index);
        case "pb":
            return new DeleteCommand(pbLogs, index);
        case "cardio":
            return new DeleteCommand(cardioLogs, index);
        default:
            throw new InvalidDeleteException();
        }
    }
}
