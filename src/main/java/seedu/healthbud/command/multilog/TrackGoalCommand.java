package seedu.healthbud.command.multilog;

import seedu.healthbud.LogList;
import seedu.healthbud.Ui;
import seedu.healthbud.command.MultiLogCommand;
import seedu.healthbud.parser.DateParser;
import seedu.healthbud.exception.InvalidDateFormatException;
import seedu.healthbud.exception.InvalidTrackException;


public class TrackGoalCommand extends MultiLogCommand {
    private final String date;

    public TrackGoalCommand(String date, LogList goalLogs, LogList pbLogs, LogList mealLogs,
                            LogList workoutLogs, LogList waterLogs, LogList cardioLogs)
            throws InvalidTrackException, InvalidDateFormatException {
        super(goalLogs, pbLogs, mealLogs, workoutLogs, waterLogs, cardioLogs, date);
        this.date = DateParser.formatDate(date);
    }

    @Override
    public void execute(){
        Ui.printMessage("Here is your goal progress for " + date + ": \n");
        waterLogs.getWaterSum(date);
        mealLogs.getCaloriesSum(date);
    }
}

