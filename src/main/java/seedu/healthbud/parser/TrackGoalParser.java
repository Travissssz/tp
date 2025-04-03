package seedu.healthbud.parser;

import seedu.healthbud.LogList;
import seedu.healthbud.command.multilog.TrackGoalCommand;
import seedu.healthbud.exception.InvalidDateFormatException;
import seedu.healthbud.exception.InvalidTrackException;

public class TrackGoalParser {

    public static TrackGoalCommand parse(String input, LogList goalLogs, LogList pbLogs, LogList mealLogs,
                                         LogList workoutLogs, LogList waterLogs, LogList cardioLogs)
            throws InvalidTrackException, InvalidDateFormatException {

        assert input != null : "Input should not be null";
        String[] parts = input.trim().split(" ");

        if (parts.length < 4 || !parts[2].equals("/d")) {
            throw new InvalidTrackException();
        }

        String date = parts[3];


        return new TrackGoalCommand(date, goalLogs, pbLogs, mealLogs, workoutLogs, waterLogs, cardioLogs);
    }
}

