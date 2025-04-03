package seedu.healthbud.parser.addcommandparser;

import seedu.healthbud.LogList;
import seedu.healthbud.command.Command;
import seedu.healthbud.exception.InvalidAddLogException;
import seedu.healthbud.exception.InvalidCardioException;
import seedu.healthbud.exception.InvalidDateFormatException;
import seedu.healthbud.exception.InvalidGoalException;
import seedu.healthbud.exception.InvalidMealException;
import seedu.healthbud.exception.InvalidPersonalBestException;
import seedu.healthbud.exception.InvalidWaterException;
import seedu.healthbud.exception.InvalidWorkoutException;

public class AddParser {

    public static Command parse(String input, LogList mealLogs, LogList waterLogs, LogList cardioLogs,
                                LogList pbLogs, LogList workoutLogs, LogList goalLogs) throws
            InvalidAddLogException, InvalidCardioException, InvalidMealException,
                                InvalidPersonalBestException, InvalidWaterException, InvalidWorkoutException,
                                InvalidDateFormatException, InvalidGoalException {

        assert input != null : "Input should not be null";

        String[] parts = input.trim().split(" ");
        if (parts.length < 2) {
            throw new InvalidAddLogException();
        }

        switch (parts[1]) {
        case "meal":
            return AddMealParser.parse(mealLogs, input);
        case "water":
            return AddWaterParser.parse(waterLogs, input);
        case "cardio":
            return AddCardioParser.parse(cardioLogs, input);
        case "pb":
            return AddPersonalBestParser.parse(pbLogs, input);
        case "workout":
            return AddWorkoutParser.parse(workoutLogs, input);
        case "goal":
            return AddGoalParser.parse(goalLogs, input);
        default:
            throw new InvalidAddLogException();
        }
    }
}
