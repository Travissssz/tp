package seedu.healthbud.command.singlelog;

import seedu.healthbud.LogList;
import seedu.healthbud.command.SingleLogCommand;

public class ListCommand extends SingleLogCommand {
    public ListCommand(LogList logList) {
        super(logList);
        assert logList != null : "LogList should not be null";
    }

    @Override
    public void execute() {
        logList.listLogs();
    }
}
