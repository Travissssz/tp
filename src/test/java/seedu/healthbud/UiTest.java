package seedu.healthbud;

import org.junit.jupiter.api.Test;
import seedu.healthbud.log.Meal;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class UiTest {

    @Test
    void testPrintMessage() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String message = "Test message";
        Ui.printMessage(message);
        assertTrue(outContent.toString().contains("    Test message"));
    }

    //    @Test
    //    void testPrintHealthBuds() {
    //        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    //        System.setOut(new PrintStream(outContent));
    //        Ui.printHealthBuds();
    //        assertTrue(outContent.toString().contains("⠤⣀⡀⠀⠀⠀⠀⠀⣀⣤⣤⣀⡀"));
    //    }

    @Test
    void testPrintGreeting() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui.printGreeting();
        assertTrue(outContent.toString().contains("Hello! I'm HealthBud"));
    }

    @Test
    void testPrintGoodbye() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        boolean result = Ui.printGoodbye();
        assertTrue(outContent.toString().contains("Bye. Hope to see you again soon!"));
        assertFalse(result);
    }

    @Test
    void testPrintHelp() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui.printHelp();
        assertTrue(outContent.toString().contains("1. help - Display this list of commands"));
    }

    @Test
    void testPrintUnknownCommand() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui.printUnknownCommand();
        assertTrue(outContent.toString().contains("I don't recognize that command"));
    }

    @Test
    void testPrintListedFormat() {
        LogList logList = new LogList();
        Meal meal = new Meal("Lunch", "500", "12-04-2025", "12:00 PM");
        logList.addLog(meal);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Ui.printListedFormat(logList, 0);
        assertTrue(outContent.toString().contains("1. Lunch (500 cal) on: 12-04-2025 at: 12:00 PM"));
    }
}
