package seedu.healthbud.command.singlelog;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.healthbud.LogList;
import seedu.healthbud.exception.InvalidListException;
import seedu.healthbud.parser.ListParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ListCommandTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStream() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void restoreStream() {
        System.setOut(originalOut);
        outputStream.reset();
    }

    private String executeListCommand(String input,
                                      LogList mealLogs, LogList workoutLogs,
                                      LogList pbLogs, LogList waterLogs, LogList cardioLogs)
            throws InvalidListException {
        outputStream.reset();
        ListCommand command = ListParser.parse(input, mealLogs, workoutLogs, pbLogs, waterLogs, cardioLogs);
        command.execute();
        System.out.flush();
        return outputStream.toString();
    }

    @Test
    void listMealLogsWithNoLogsExpectNoLogsAvailable() throws InvalidListException {
        LogList mealLogs = new LogList();
        String output = executeListCommand(
                "list meal", mealLogs, new LogList(), new LogList(), new LogList(), new LogList());
        assertTrue(output.contains("No logs available."),
                "Should print 'No logs available.' for empty mealLogs.");
    }

    @Test
    void listMealLogsWithExistingLogsExpectListed() throws InvalidListException {
        LogList mealLogs = new LogList();
        // Add logs to mealLogs
        AddMealCommand addMeal1 = new AddMealCommand(
                mealLogs, "chicken rice", "550", "12 Jan 2025", "9pm");
        AddMealCommand addMeal2 = new AddMealCommand(
                mealLogs, "tom yum", "650", "13 Jan 2025", "8pm");
        addMeal1.execute();
        addMeal2.execute();
        assertEquals(2, mealLogs.getSize());

        String output = executeListCommand(
                "list meal", mealLogs, new LogList(), new LogList(), new LogList(), new LogList());
        assertTrue(output.contains("chicken rice"), "Should list 'chicken rice' log.");
        assertTrue(output.contains("tom yum"), "Should list 'tom yum' log.");
    }


    @Test
    void listWorkoutLogsWithNoLogsExpectNoLogsAvailable() throws InvalidListException {
        LogList workoutLogs = new LogList();
        String output = executeListCommand(
                "list workout", new LogList(), workoutLogs, new LogList(), new LogList(), new LogList());
        assertTrue(output.contains("No logs available."),
                "Should print 'No logs available.' for empty workoutLogs.");
    }

    @Test
    void listWaterLogsWithNoLogsExpectNoLogsAvailable() throws InvalidListException {
        LogList waterLogs = new LogList();
        String output = executeListCommand(
                "list water", new LogList(), new LogList(), new LogList(), waterLogs, new LogList());
        assertTrue(output.contains("No logs available."),
                "Should print 'No logs available.' for empty waterLogs.");
    }

    @Test
    void listPbLogsWithNoLogsExpectNoLogsAvailable() throws InvalidListException {
        LogList pbLogs = new LogList();
        String output = executeListCommand(
                "list pb", new LogList(), new LogList(), pbLogs, new LogList(), new LogList());
        assertTrue(output.contains("No logs available."),
                "Should print 'No logs available.' for empty pbLogs.");
    }

    @Test
    void listCardioLogsWithNoLogsExpectNoLogsAvailable() throws InvalidListException {
        LogList cardioLogs = new LogList();
        String output = executeListCommand(
                "list cardio", new LogList(), new LogList(), new LogList(), new LogList(), cardioLogs);
        assertTrue(output.contains("No logs available."),
                "Should print 'No logs available.' for empty cardioLogs.");
    }

    @Test
    void listMissingLogTypeExpectInvalidListException() {
        // Just "list" => no second token
        assertThrows(InvalidListException.class, () ->
                ListParser.parse(
                        "list", new LogList(), new LogList(), new LogList(), new LogList(), new LogList()));
    }

    @Test
    void listInvalidLogTypeExpectInvalidListException() {
        // "list randomType" => not recognized
        assertThrows(InvalidListException.class, () ->
                ListParser.parse("list randomType", new LogList(), new LogList(),
                        new LogList(), new LogList(), new LogList()));
    }

    @Test
    void listEmptyInputExpectInvalidListException() {
        assertThrows(InvalidListException.class, () ->
                ListParser.parse("", new LogList(), new LogList(), new LogList(), new LogList(), new LogList()));
    }
}
