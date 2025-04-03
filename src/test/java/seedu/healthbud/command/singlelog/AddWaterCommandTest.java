package seedu.healthbud.command.singlelog;

import org.junit.jupiter.api.Test;
import seedu.healthbud.LogList;
import seedu.healthbud.exception.InvalidWaterException;
import seedu.healthbud.exception.InvalidDateFormatException;
import seedu.healthbud.log.Water;
import seedu.healthbud.parser.addcommandparser.AddWaterParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddWaterCommandTest {

    @Test
    void addWater_correctInput_expectSuccess()
            throws InvalidDateFormatException, InvalidWaterException {

        LogList waterLogs = new LogList();
        String input = "add water /ml 1000 /d 12-04-2002 /t 1200";

        AddWaterCommand command = AddWaterParser.parse(waterLogs, input);
        command.execute();

        Water water = (Water) waterLogs.getLog(0);
        assertEquals("1000", water.getAmount());
        assertEquals("12 Apr 2002", water.getDate());
        assertEquals("1200", water.getTime());

        String expected = "4.0 glass of water on (12 Apr 2002) at 1200";
        assertEquals(expected, water.toString());
    }

    @Test
    void addWater_missingML_expectThrowsInvalidWaterException(){
        LogList waterLogs = new LogList();
        String input  = "add water /d 12-02-2002 /t 1200";

        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_missingDate_expectThrowsInvalidWaterException(){
        LogList waterLogs = new LogList();
        String input  = "add water /ml 1000 /t 1200";

        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_missingTime_expectThrowsInvalidWaterException(){
        LogList waterLogs = new LogList();
        String input  = "add water /ml 1200 /d 12-02-2002";

        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_emptyInput_expectThrowsInvalidWaterException(){
        LogList waterLogs = new LogList();
        String input  = "add water";

        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_missingParameters_expectThrowsInvalidWaterException() {
        LogList waterLogs = new LogList();
        String input  = "add water /ml";

        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_missingAllParameters_expectThrowsInvalidWaterException() {
        LogList waterLogs = new LogList();
        String input  = "add water /ml /d /t";

        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_extraParameters_expectThrowsInvalidWaterException() {
        LogList waterLogs = new LogList();
        String input  = "add water /ml 1000 /d 12-02-2000 /t 1200 /e 10000";

        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_wrongParametersML_expectThrowsInvalidWaterException(){
        LogList waterLogs = new LogList();
        String input  = "add water /ml messi /d 12-02-2000 /t 1000";

        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_extraLines_expectThrowsInvalidWaterException(){
        LogList waterLogs = new LogList();
        String input  = "add water morning hydration /ml 1000 /d 12-02-2000 /t 1000";

        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_missingTParameters_expectThrowsInvalidWaterException(){
        LogList waterLogs = new LogList();
        String input  = "add water /ml 2000 /d 12-02-2002 /t";

        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_noSlashPrefix_expectThrowsInvalidWaterException() {
        LogList waterLogs = new LogList();
        String input = "add water ml 1000 /d 12-02-2002 /t 1000"; // missing '/' before 'ml'
        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_emptyMl_expectThrowsInvalidWaterException() {
        LogList waterLogs = new LogList();
        String input = "add water /ml   /d 12-02-2002 /t 1000";
        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_emptyDate_expectThrowsInvalidWaterException() {
        LogList waterLogs = new LogList();
        String input = "add water /ml 1000 /d   /t 1000";
        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_whitespaceOnlyInput_expectThrowsInvalidWaterException() {
        LogList waterLogs = new LogList();
        String input = "add water     ";
        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_missingOneKey_expectThrowsInvalidWaterException() {
        LogList waterLogs = new LogList();
        String input = "add water /ml 1000 /d 12-02-2002";
        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_nullInput_expectThrowsAssertionError() {
        LogList waterLogs = new LogList();
        String input = null;
        assertThrows(AssertionError.class, () -> AddWaterParser.parse(waterLogs, input));
    }

    @Test
    void addWater_missingSlashAtStart_expectThrowsInvalidWaterException() {
        LogList waterLogs = new LogList();
        String input = "add water ml 1000 /d 12-02-2022 /t 1000"; // missing '/' before ml
        assertThrows(InvalidWaterException.class, () -> AddWaterParser.parse(waterLogs, input));
    }

}
