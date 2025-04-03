package seedu.healthbud.log;

public class Log {

    private String logType;
    private String date;

    public Log(String date, String logType) {
        assert date != null : "Date should not be null";
        assert logType != null : "Log type should not be null";

        this.date = date;
        this.logType = logType;
    }

    public String getLogType() {
        return logType;
    }

    public String getDate() {
        return date;
    }

}
