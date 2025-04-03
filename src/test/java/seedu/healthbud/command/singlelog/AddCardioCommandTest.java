package seedu.healthbud.command.singlelog;

import org.junit.jupiter.api.Test;

import seedu.healthbud.LogList;
import seedu.healthbud.exception.InvalidCardioException;
import seedu.healthbud.exception.InvalidDateFormatException;
import seedu.healthbud.log.Cardio;
import seedu.healthbud.parser.addcommandparser.AddCardioParser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddCardioCommandTest {

    @Test
    void validCardioAddsToLogList_expectSuccess() throws InvalidCardioException, InvalidDateFormatException {
        LogList cardioLogs = new LogList();
        String input = "add cardio running /s 8.5 /i 2 /t 30 /d 25-12-2023";

        AddCardioCommand command = AddCardioParser.parse(cardioLogs, input);
        command.execute();

        Cardio cardio = (Cardio) cardioLogs.getLog(0);
        assertEquals("running", cardio.getName());
        assertEquals("8.5", cardio.getSpeed());
        assertEquals("2", cardio.getIncline());
        assertEquals("30", cardio.getDuration());
        assertEquals("25 Dec 2023", cardio.getDate());

        String expected = "running (speed: 8.5, incline: 2, duration: 30 mins) on 25 Dec 2023";
        assertEquals(expected, cardio.toString());
    }

    @Test
    void cardioLog_missingName_throwsInvalidCardioException() {
        String input = "add cardio /s 8 /i 5 /t 30 /d 2023-12-25";
        LogList cardioLogs = new LogList();

        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(cardioLogs, input));
    }

    @Test
    void cardioLog_missingSpeed_throwsInvalidCardioException() {
        String input = "add cardio Running /i 5 /t 30 /d 2023-12-25";
        LogList cardioLogs = new LogList();

        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(cardioLogs, input));
    }

    @Test
    void cardioLog_missingIncline_throwsInvalidCardioException() {
        String input = "add cardio Running /s 8 /t 30 /d 2023-12-25";
        LogList cardioLogs = new LogList();

        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(cardioLogs, input));
    }

    @Test
    void cardioLog_missingTime_throwsInvalidCardioException() {
        String input = "add cardio Running /s 8 /i 5 /d 2023-12-25";
        LogList cardioLogs = new LogList();

        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(cardioLogs, input));
    }

    @Test
    void cardioLog_missingDate_throwsInvalidCardioException() {
        String input = "add cardio Running /s 8 /i 5 /t 30";
        LogList cardioLogs = new LogList();

        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(cardioLogs, input));
    }

    @Test
    void cardioLog_emptySpeedValue_throwsInvalidCardioException() {
        String input = "add cardio Running /s /i 5 /t 30 /d 2023-12-25";
        LogList cardioLogs = new LogList();

        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(cardioLogs, input));
    }

    @Test
    void cardioLog_emptyInclineValue_throwsInvalidCardioException() {
        String input = "add cardio Running /s 8 /i /t 30 /d 2023-12-25";
        LogList cardioLogs = new LogList();

        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(cardioLogs, input));
    }

    @Test
    void cardioLog_emptyTimeValue_throwsInvalidCardioException() {
        String input = "add cardio Running /s 8 /i 5 /t /d 2023-12-25";
        LogList cardioLogs = new LogList();

        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(cardioLogs, input));
    }

    @Test
    void cardioLog_emptyDateValue_throwsInvalidCardioException() {
        String input = "add cardio Running /s 8 /i 5 /t 30 /d";
        LogList cardioLogs = new LogList();

        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(cardioLogs, input));
    }

    @Test
    void cardioLog_nonNumericSpeed_throwsInvalidCardioException() {
        String input = "add cardio Running /s abc /i 5 /t 30 /d 2023-12-25";
        LogList cardioLogs = new LogList();

        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(cardioLogs, input));
    }

    @Test
    void cardioLog_nonNumericIncline_throwsInvalidCardioException() {
        String input = "add cardio Running /s 8 /i abc /t 30 /d 2023-12-25";
        LogList cardioLogs = new LogList();

        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(cardioLogs, input));
    }

    @Test
    void cardioLog_nonNumericDuration_throwsInvalidCardioException() {
        String input = "add cardio Running /s 8 /i 5 /t abc /d 2023-12-25";
        LogList cardioLogs = new LogList();

        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(cardioLogs, input));
    }

    @Test
    void cardioLog_inputTooShort_expectThrowsInvalidCardioException() {
        LogList logs = new LogList();
        String input = "add cardio";
        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(logs, input));
    }


    @Test
    void cardioLog_inputEmptyAfterCommandPrefix_expectThrowsInvalidCardioException() {
        LogList logs = new LogList();
        String input = "add cardio     "; // Only command with whitespace
        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(logs, input));
    }

    @Test
    void cardioLog_extraParameterKey_expectThrowsInvalidCardioException() {
        LogList logs = new LogList();
        String input = "add cardio running /s 8.5 /i 2 /t 30 /d 25-12-2023 /x 99";
        assertThrows(InvalidCardioException.class, () -> AddCardioParser.parse(logs, input));
    }

}

