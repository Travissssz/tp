package seedu.healthbud.command.singlelog;

import org.junit.jupiter.api.Test;
import seedu.healthbud.LogList;

import seedu.healthbud.exception.HealthBudException;
import seedu.healthbud.exception.InvalidDeleteException;
import seedu.healthbud.parser.DeleteParser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.healthbud.HealthBud.pbLogs;

class DeleteCommandTest {

    @Test
    void deleteWater_correctInput_expectSuccess() throws InvalidDeleteException {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "delete water 3";
        DeleteCommand command = DeleteParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);

        assertEquals(waterLogs, command.getLogList());
        assertEquals(3, command.getIndex());
    }

    @Test
    void deleteMeal_correctInput_expectSuccess() throws InvalidDeleteException, HealthBudException {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "delete meal 10";
        DeleteCommand command = DeleteParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);

        assertEquals(mealLogs, command.getLogList());
        assertEquals(10, command.getIndex());
    }

    @Test
    void deleteCardio_correctInput_expectSuccess() throws InvalidDeleteException, HealthBudException {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "delete cardio 3";
        DeleteCommand command = DeleteParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);

        assertEquals(cardioLogs, command.getLogList());
        assertEquals(3, command.getIndex());
    }

    @Test
    void deletePB_correctInput_expectSuccess() throws InvalidDeleteException, HealthBudException {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "delete pb 3";
        DeleteCommand command = DeleteParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);

        assertEquals(pbLogs, command.getLogList());
        assertEquals(3, command.getIndex());
    }

    @Test

    void deleteWorkout_correctInput_expectSuccess() throws InvalidDeleteException{
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "delete workout 3";
        DeleteCommand command = DeleteParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);

        assertEquals(workoutLogs, command.getLogList());
        assertEquals(3, command.getIndex());
    }

    @Test
    void deleteWater_wrongInput_expectThrowsInvalidDeleteException(){
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "delete water 1.5";
        assertThrows(InvalidDeleteException.class, () ->
                DeleteParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs));

    }

    @Test
    void deletePB_wrongInput_throwsInvalidDeleteException() {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "delete PB one";
        assertThrows(InvalidDeleteException.class, () ->
                DeleteParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs));
    }

    @Test
    void delete_invalidLogType_throwsInvalidDeleteException() {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "delete invalid 1";
        assertThrows(InvalidDeleteException.class, () ->
                DeleteParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs));
    }

    @Test
    void delete_missingIndex_throwsInvalidDeleteException() {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "delete meal";
        assertThrows(InvalidDeleteException.class, () ->
                DeleteParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs));
    }

    @Test
    void delete_missingLogType_throwsInvalidDeleteException() {
        LogList mealLogs = new LogList();
        LogList workoutLogs = new LogList();
        LogList waterLogs = new LogList();
        LogList pbLogs = new LogList();
        LogList cardioLogs = new LogList();

        String input = "delete 1";
        assertThrows(InvalidDeleteException.class, () ->
                DeleteParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs));
    }

    @Test
    void deleteLog_indexOutOfBounds_expectThrowsHealthBudException() {
        String input = "delete pb 3";
        DeleteCommand command = new DeleteCommand(pbLogs, 3);

        assertThrows(HealthBudException.class, () -> command.execute());
    }


    @Test
    void deleteLog_negativeIndex_expectThrowsHealthBudException() {
        String input = "delete workout -1";
        LogList workoutLogs = new LogList();
        DeleteCommand command = new DeleteCommand(workoutLogs, -1);

        assertThrows(HealthBudException.class, () -> command.execute());
    }

    @Test
    void deleteLog_zeroIndex_expectThrowsHealthBudException() {
        String input = "delete pb 0";
        DeleteCommand command = new DeleteCommand(pbLogs, 0);

        assertThrows(HealthBudException.class, () -> command.execute());
    }
}

