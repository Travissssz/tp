package seedu.healthbud.command.singlelog;

import seedu.healthbud.LogList;
import seedu.healthbud.command.SingleLogCommand;
import seedu.healthbud.exception.HealthBudException;

public class DeleteCommand extends SingleLogCommand {

    private final int index;

    public DeleteCommand(LogList targetLogList, int index) {
        super(targetLogList);
        assert targetLogList != null : "LogList should not be null";
        this.index = index;
    }

    @Override
    public void execute() throws HealthBudException {
        logList.deleteLog(index);
    }

    public LogList getLogList() {
        return logList;
    }

    public int getIndex() {
        return index;
    }
}
