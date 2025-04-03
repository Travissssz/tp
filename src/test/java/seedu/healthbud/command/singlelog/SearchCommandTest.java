package seedu.healthbud.command.singlelog;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.healthbud.LogList;
import seedu.healthbud.log.Meal;
import seedu.healthbud.exception.InvalidDateFormatException;
import seedu.healthbud.exception.InvalidSearchException;
import seedu.healthbud.parser.SearchParser;

public class SearchCommandTest {

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

    @Test
    void execute_searchByDate_withMatchingLogs() {
        LogList mealLogs = new LogList();
        Meal meal = new Meal("chicken rice", "550", "12 Dec 2025", "9pm");
        mealLogs.addLog(meal);

        SearchCommand command = new SearchCommand(mealLogs, "12 Dec 2025", null);
        command.execute();
        String output = outputStream.toString();

        assertTrue(output.contains("chicken rice"), "Should list the log with date '12 Dec 2025'.");
        assertFalse(output.contains(
                "No logs found for this date"), "Should not print 'No logs found' message.");
    }

    @Test
    void execute_searchByDate_noMatchingLogs() {
        LogList mealLogs = new LogList();
        Meal meal = new Meal("chicken rice", "550", "13 Dec 2025", "9pm");
        mealLogs.addLog(meal);

        SearchCommand command = new SearchCommand(mealLogs, "12 Dec 2025", null);
        command.execute();
        String output = outputStream.toString();

        assertTrue(output.contains("No logs found for this date"),
                "Should print 'No logs found' message for non-matching date.");
    }

    @Test
    void execute_searchByKeyword_withMatchingLogs() {
        // Prepare logs
        LogList mealLogs = new LogList();
        Meal meal1 = new Meal("chicken salad", "400", "10 Jan 2025", "6pm");
        Meal meal2 = new Meal("beef burger", "800", "11 Jan 2025", "8pm");
        mealLogs.addLog(meal1);
        mealLogs.addLog(meal2);

        SearchCommand command = new SearchCommand(mealLogs, null, "chicken");
        command.execute();
        String output = outputStream.toString();

        assertTrue(output.contains("chicken salad"), "Should list the log containing 'chicken salad'.");
        assertFalse(output.contains("No matching 'chicken' logs found."),
                "Should not print 'No matching logs found' for a matching keyword.");
    }

    @Test
    void execute_searchByKeyword_noMatchingLogs() {
        LogList mealLogs = new LogList();
        Meal meal = new Meal("chicken salad", "400", "10 Jan 2025", "6pm");
        mealLogs.addLog(meal);

        SearchCommand command = new SearchCommand(mealLogs, null, "beef");
        command.execute();
        String output = outputStream.toString();

        assertTrue(output.contains("No matching 'beef' logs found."),
                "Should print 'No matching logs found' message for a non-matching keyword.");
    }

    @Test
    void parse_validDateInput_returnsSearchCommand() throws InvalidSearchException, InvalidDateFormatException {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "search meal /d 12/12/2025";
        SearchCommand command = SearchParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);
        assertNotNull(command, "Command should not be null for valid date input.");
    }

    @Test
    void parse_validKeywordInput_returnsSearchCommand() throws InvalidSearchException, InvalidDateFormatException {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "search meal /k chicken";
        SearchCommand command = SearchParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);
        assertNotNull(command, "Command should not be null for valid keyword input.");
    }

    @Test
    void parse_invalidPartsLength_throwsInvalidSearchException() {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "search meal /k jason extra";
        assertThrows(InvalidSearchException.class, () ->
                SearchParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs)
        );
    }

    @Test
    void parse_missingFlag_throwsInvalidSearchException() {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "search meal jason";
        assertThrows(InvalidSearchException.class, () ->
                SearchParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs)
        );
    }

    @Test
    void parse_invalidFlag_throwsInvalidSearchException() {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "search meal /x jason";
        assertThrows(InvalidSearchException.class, () ->
                SearchParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs)
        );
    }

    @Test
    void parse_invalidDateFormat_throwsInvalidDateFormatException() {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "search meal /d 31-02-2025";
        assertThrows(InvalidDateFormatException.class, () ->
                SearchParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs)
        );
    }

    @Test
    void parse_invalidLogType_throwsInvalidSearchException() {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "search snacks /k chips";
        assertThrows(InvalidSearchException.class, () ->
                SearchParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs)
        );
    }

    @Test
    void parse_validDateInputForWorkout_returnsSearchCommand()
            throws InvalidSearchException, InvalidDateFormatException {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "search workout /d 01/01/2025";
        SearchCommand command = SearchParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);
        assertNotNull(command, "Command should not be null for valid workout date search.");
    }

    @Test
    void parse_validKeywordInputForWater_returnsSearchCommand()
            throws InvalidSearchException, InvalidDateFormatException {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "search water /k hydration";
        SearchCommand command = SearchParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);
        assertNotNull(command, "Command should not be null for valid water keyword search.");
    }

    @Test
    void parse_validDateInputForPB_returnsSearchCommand() throws InvalidSearchException, InvalidDateFormatException {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "search pb /d 15/08/2025";
        SearchCommand command = SearchParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);
        assertNotNull(command, "Command should not be null for valid pb date search.");
    }

    @Test
    void parse_validDateInputForCardio_returnsSearchCommand()
            throws InvalidSearchException, InvalidDateFormatException {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "search cardio /d 20/11/2025";
        SearchCommand command = SearchParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);
        assertNotNull(command, "Command should not be null for valid cardio date search.");
    }
}
