package seedu.healthbud.command.singlelog;

import org.junit.jupiter.api.Test;
import seedu.healthbud.LogList;
import seedu.healthbud.exception.InvalidSumException;
import seedu.healthbud.log.Meal;
import seedu.healthbud.log.Cardio;
import seedu.healthbud.log.Water;
import seedu.healthbud.parser.SumParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SumCommandTest {

    @Test
    public void sumCommand_calorieSum_correctSumReturned() {
        LogList mealLogList = new LogList();
        mealLogList.addLog(new Meal("Eggs", "150", "2024-04-03", "08:00"));
        mealLogList.addLog(new Meal("Chicken", "300", "2024-04-03", "12:00"));
        mealLogList.addLog(new Meal("Soda", "100", "2024-04-02", "15:00"));

        SumCommand command = new SumCommand(mealLogList, "cal", "2024-04-03");
        command.execute();

        int expected = 450;
        int actual = mealLogList.getCaloriesSum("2024-04-03");
        assertEquals(expected, actual);
    }

    @Test
    public void sumCommand_waterSum_correctSumReturned() {
        LogList waterLogList = new LogList();
        waterLogList.addLog(new Water("250", "2024-04-03", "09:00"));
        waterLogList.addLog(new Water("500", "2024-04-03", "11:00"));
        waterLogList.addLog(new Water("300", "2024-04-02", "18:00"));

        SumCommand command = new SumCommand(waterLogList, "vol", "2024-04-03");
        command.execute();

        int expected = 750;
        int actual = waterLogList.getWaterSum("2024-04-03");
        assertEquals(expected, actual);
    }

    @Test
    public void sumCommand_cardioSum_correctCaloriesBurned() {
        LogList cardioLogList = new LogList();
        cardioLogList.addLog(new Cardio("Run", "6", "2", "30", "2024-04-03"));
        cardioLogList.addLog(new Cardio("Cycle", "4", "1", "60", "2024-04-03"));
        cardioLogList.addLog(new Cardio("Walk", "2", "1", "60", "2024-04-02"));

        SumCommand command = new SumCommand(cardioLogList, "cardio", "2024-04-03");
        command.execute();

        int expected = 2400;
        int actual = cardioLogList.getCardioSum("2024-04-03");
        assertEquals(expected, actual);
    }

    @Test
    public void sumCommand_emptyLogList_returnsZero() {
        LogList mealLogList = new LogList();

        SumCommand command = new SumCommand(mealLogList, "cal", "2024-04-03");
        command.execute();

        int expected = 0;
        int actual = mealLogList.getCaloriesSum("2024-04-03");
        assertEquals(expected, actual);
    }

    @Test
    public void sumCommand_validCalInput_executesCorrectly() throws Exception {
        LogList mealLogs = new LogList();
        mealLogs.addLog(new Meal("Chicken Rice", "500", "04-03-2024", "12:00"));
        mealLogs.addLog(new Meal("Soup", "200", "04-03-2024", "18:00"));

        LogList waterLogs = new LogList();
        LogList cardioLogs = new LogList();

        SumCommand command = SumParser.parse("sum cal /d 04-03-2024", mealLogs, waterLogs, cardioLogs);
        command.execute();

        assertEquals(700, mealLogs.getCaloriesSum("04-03-2024"));
    }

    @Test
    public void sumCommand_validVolInput_executesCorrectly() throws Exception {
        LogList waterLogs = new LogList();
        waterLogs.addLog(new Water("250", "04-03-2024", "09:00"));
        waterLogs.addLog(new Water("500", "04-03-2024", "14:00"));

        LogList mealLogs = new LogList();
        LogList cardioLogs = new LogList();

        SumCommand command = SumParser.parse("sum vol /d 04-03-2024", mealLogs, waterLogs, cardioLogs);
        command.execute();

        int expected = 750;
        assertEquals(expected, waterLogs.getWaterSum("04-03-2024"));
    }

    @Test
    public void sumCommand_validCardioInput_executesCorrectly() throws Exception {
        LogList cardioLogs = new LogList();
        cardioLogs.addLog(new Cardio("Run", "5", "2", "60", "04-03-2024"));

        LogList mealLogs = new LogList();
        LogList waterLogs = new LogList();

        SumCommand command = SumParser.parse("sum cardio /d 04-03-2024", mealLogs, waterLogs, cardioLogs);
        command.execute();

        assertEquals(2000, cardioLogs.getCardioSum("04-03-2024"));
    }

    @Test
    public void sumCommand_invalidType_throwsInvalidSumException() {
        LogList mealLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList cardioLogs = new LogList();

        assertThrows(InvalidSumException.class, () ->
                SumParser.parse("sum sleep /d 04-03-2024", mealLogs, waterLogs, cardioLogs));
    }

    @Test
    public void sumCommand_missingDatePrefix_throwsInvalidSumException() {
        LogList mealLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList cardioLogs = new LogList();

        assertThrows(InvalidSumException.class, () ->
                SumParser.parse("sum cal 2024-04-03", mealLogs, waterLogs, cardioLogs));
    }

}
