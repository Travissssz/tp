package seedu.healthbud.command.singlelog;

import seedu.healthbud.LogList;
import seedu.healthbud.command.SingleLogCommand;

public class SearchCommand extends SingleLogCommand {
    private final String date;      // null if searching by keyword
    private final String keyword;   // null if searching by date

    public SearchCommand(LogList logList, String date, String keyword) {
        super(logList);
        assert logList != null : "LogList should not be null";
        this.date = date;
        this.keyword = keyword;
    }

    @Override
    public void execute() {

        if (date != null) {
            logList.findLogByDate(date);
        } else {
            logList.findLog(keyword);
        }
    }
}
