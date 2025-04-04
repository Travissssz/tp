package seedu.healthbud.command;

import seedu.healthbud.LogList;

// FOR COMMANDS THAT NEEDS MORE THAN 1 LOG LIST AND INPUT

public abstract class MultiLogCommand implements Command {
    protected LogList goalLogs;
    protected LogList pbLogs;
    protected LogList mealLogs;
    protected LogList workoutLogs;
    protected LogList waterLogs;
    protected LogList cardioLogs;
    protected String input;

    public MultiLogCommand(LogList goalLogs, LogList pbLogs, LogList mealLogs, LogList workoutLogs,
                           LogList waterLogs, LogList cardioLogs, String input) {
        assert goalLogs != null : "Goal logs should not be null";
        assert pbLogs != null : "Personal best logs should not be null";
        assert mealLogs != null : "Meal logs should not be null";
        assert workoutLogs != null : "Workout logs should not be null";
        assert waterLogs != null : "Water logs should not be null";
        assert cardioLogs != null : "Cardio logs should not be null";
        assert input != null : "Input should not be null";
        this.goalLogs = goalLogs;
        this.pbLogs = pbLogs;
        this.mealLogs = mealLogs;
        this.workoutLogs = workoutLogs;
        this.waterLogs = waterLogs;
        this.cardioLogs = cardioLogs;
        this.input = input;
    }
}
