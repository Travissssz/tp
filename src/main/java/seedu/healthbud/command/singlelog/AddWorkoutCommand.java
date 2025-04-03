package seedu.healthbud.command.singlelog;

import seedu.healthbud.LogList;
import seedu.healthbud.command.SingleLogCommand;
import seedu.healthbud.log.Workout;


//@@ author Ahmish15
public class AddWorkoutCommand extends SingleLogCommand {

    private final String name;
    private final String reps;
    private final String sets;
    private final String date;
    private final String weight;

    public AddWorkoutCommand(LogList workoutLogs, String name,
                             String reps, String sets, String date, String weight) {
        super(workoutLogs);

        assert workoutLogs != null : "LogList (workoutLogs) should not be null";

        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.date = date;
        this.weight = weight;
    }

    @Override
    public void execute(){
        Workout newWorkout = new Workout(name, reps, sets, date, weight);
        logList.addLog(newWorkout);
    }

}
