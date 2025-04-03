package seedu.healthbud.command.singlelog;

import seedu.healthbud.LogList;
import seedu.healthbud.command.SingleLogCommand;

public class SumCommand extends SingleLogCommand {

    private final String type;
    private final String date;

    public SumCommand(LogList logList, String type, String date) {
        super(logList);
        assert logList != null : "LogList should not be null";
        assert type != null : "Type should not be null";
        assert date != null : "Date should not be null";
        this.type = type;
        this.date = date;
    }

    @Override
    public void execute(){
        switch (type) {
        case "cal":
            logList.getCaloriesSum(date);
            break;
        case "vol":
            logList.getWaterSum(date);
            break;
        case "cardio":
            logList.getCardioSum(date);
            break;
        default:
            break;
        }
    }
}
