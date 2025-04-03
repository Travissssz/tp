package seedu.healthbud.command.singlelog;

import seedu.healthbud.LogList;
import seedu.healthbud.command.SingleLogCommand;
import seedu.healthbud.log.Cardio;

//@@author Ahmish15
public class AddCardioCommand extends SingleLogCommand {

    private final String name;
    private final String sets;
    private final String intensity;
    private final String time;
    private final String date;

    public AddCardioCommand(LogList cardioLogs, String name, String sets,
                            String intensity, String time, String date) {
        super(cardioLogs);

        assert cardioLogs != null : "LogList (cardioLogs) should not be null";

        this.name = name;
        this.sets = sets;
        this.intensity = intensity;
        this.time = time;
        this.date = date;
    }

    @Override
    public void execute() {
        Cardio newCardio = new Cardio(name, sets, intensity, time, date);
        logList.addLog(newCardio);
    }

}
