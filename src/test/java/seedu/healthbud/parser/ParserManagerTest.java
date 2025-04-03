package seedu.healthbud.parser;

import org.junit.jupiter.api.Test;
import seedu.healthbud.LogList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserManagerTest {

    private final LogList empty = new LogList();

    @Test
    void handleInput_add_expectSuccess() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "add meal Lunch /c 300 /d 2024-04-01 /t 12:00");
        assertEquals(true, result);
    }

    @Test
    void handleInput_list_expectSuccess() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "list meal");
        assertEquals(true, result);
    }

    @Test
    void handleInput_recommend_expectSuccess() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "recommend goal");
        assertEquals(true, result);
    }

    @Test
    void handleInput_bmi_expectSuccess() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "bmi 70 1.75");
        assertEquals(true, result);
    }

    @Test
    void handleInput_delete_expectSuccess() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "delete meal 1");
        assertEquals(true, result);
    }

    @Test
    void handleInput_clear_expectSuccess() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "clear meal");
        assertEquals(true, result);
    }

    @Test
    void handleInput_sum_expectSuccess() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "sum meal");
        assertEquals(true, result);
    }

    @Test
    void handleInput_status_expectSuccess() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "status change bulking");
        assertEquals(true, result);
    }

    @Test
    void handleInput_search_expectSuccess() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "search meal rice");
        assertEquals(true, result);
    }

    @Test
    void handleInput_view_expectSuccess() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "view goals");
        assertEquals(true, result);
    }

    @Test
    void handleInput_track_expectSuccess() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "track goal /d 2024-04-01");
        assertEquals(true, result);
    }

    @Test
    void handleInput_help_expectSuccess() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "help");
        assertEquals(true, result);
    }

    @Test
    void handleInput_bye_expectSuccess() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "bye");
        assertEquals(false, result); // 'bye' returns false
    }

    @Test
    void handleInput_unknownCommand_expectFailure() {
        boolean result = ParserManager.handleInput(empty, empty, empty, empty, empty, empty,
                "unknowncommand");
        assertEquals(true, result); // still returns true, but error printed
    }
}
