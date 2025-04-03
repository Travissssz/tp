package seedu.healthbud.parser.addcommandparser;

import org.junit.jupiter.api.Test;
import seedu.healthbud.LogList;
import seedu.healthbud.command.Command;
import seedu.healthbud.command.singlelog.AddCardioCommand;
import seedu.healthbud.command.singlelog.AddGoalCommand;
import seedu.healthbud.command.singlelog.AddMealCommand;
import seedu.healthbud.command.singlelog.AddPersonalBestCommand;
import seedu.healthbud.command.singlelog.AddWaterCommand;
import seedu.healthbud.command.singlelog.AddWorkoutCommand;
import seedu.healthbud.exception.InvalidAddLogException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddParserTest {

    @Test
    void parse_validMealInput_success() throws Exception {
        LogList mealLogs = new LogList();
        String input = "add meal chicken /cal 500 /d 12-04-2024 /t 1200";
        Command command = AddParser.parse(input, mealLogs, new LogList(), new LogList(),
                new LogList(), new LogList(), new LogList());
        assertEquals(AddMealCommand.class, command.getClass());
    }

    @Test
    void parse_validWaterInput_success() throws Exception {
        LogList waterLogs = new LogList();
        String input = "add water /ml 2000 /d 12-04-2024 /t 1200";
        Command command = AddParser.parse(input, new LogList(), waterLogs, new LogList(),
                new LogList(), new LogList(), new LogList());
        assertEquals(AddWaterCommand.class, command.getClass());
    }

    @Test
    void parse_validCardioInput_success() throws Exception {
        LogList cardioLogs = new LogList();
        String input = "add cardio run /s 10 /i 5 /t 30 /d 12-04-2024";
        Command command = AddParser.parse(input, new LogList(), new LogList(), cardioLogs,
                new LogList(), new LogList(), new LogList());
        assertEquals(AddCardioCommand.class, command.getClass());
    }

    @Test
    void parse_validPersonalBestInput_success() throws Exception {
        LogList pbLogs = new LogList();
        String input = "add pb deadlift /w 100 /d 12-04-2024";
        Command command = AddParser.parse(input, new LogList(), new LogList(), new LogList(),
                pbLogs, new LogList(), new LogList());
        assertEquals(AddPersonalBestCommand.class, command.getClass());
    }

    @Test
    void parse_validWorkoutInput_success() throws Exception {
        LogList workoutLogs = new LogList();
        String input = "add workout pushup /w 0 /r 10 /s 3 /d 12-04-2024";
        Command command = AddParser.parse(input, new LogList(), new LogList(), new LogList(),
                new LogList(), workoutLogs, new LogList());
        assertEquals(AddWorkoutCommand.class, command.getClass());
    }

    @Test
    void parse_validGoalInput_success() throws Exception {
        LogList goalLogs = new LogList();
        String input = "add goal sleep 8h /d 12-04-2024";
        Command command = AddParser.parse(input, new LogList(), new LogList(), new LogList(),
                new LogList(), new LogList(), goalLogs);
        assertEquals(AddGoalCommand.class, command.getClass());
    }

    @Test
    void parse_invalidType_throwsInvalidAddLogException() {
        String input = "add pizza /cal 1000 /d 12-04-2024 /t 1900";
        assertThrows(InvalidAddLogException.class, () ->
                AddParser.parse(input, new LogList(), new LogList(), new LogList(),
                        new LogList(), new LogList(), new LogList()));
    }

    @Test
    void parse_insufficientArguments_throwsInvalidAddLogException() {
        String input = "add";
        assertThrows(InvalidAddLogException.class, () ->
                AddParser.parse(input, new LogList(), new LogList(), new LogList(),
                        new LogList(), new LogList(), new LogList()));
    }

    @Test
    void parse_nullInput_throwsAssertionError() {
        assertThrows(AssertionError.class, () ->
                AddParser.parse(null, new LogList(), new LogList(), new LogList(),
                        new LogList(), new LogList(), new LogList()));
    }
}
