package seedu.healthbud;

import org.junit.jupiter.api.Test;
import seedu.healthbud.log.Cardio;
import seedu.healthbud.log.Meal;
import seedu.healthbud.log.Water;
import seedu.healthbud.exception.HealthBudException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LogListTest {

    @Test
    void testAddLog() {
        LogList logList = new LogList();
        Meal meal = new Meal("Lunch", "500", "12-04-2025", "12:00 PM");

        logList.addLog(meal);
        assertEquals(1, logList.getSize());
        assertEquals(meal, logList.getLog(0));
    }

    @Test
    void testDeleteLog() throws HealthBudException {
        LogList logList = new LogList();
        Meal meal = new Meal("Lunch", "500", "12-04-2025", "12:00 PM");

        logList.addLog(meal);
        assertEquals(1, logList.getSize());

        logList.deleteLog(1);
        assertEquals(0, logList.getSize());
    }

    @Test
    void testLoadLog() {
        LogList logList = new LogList();
        Meal meal = new Meal("Lunch", "500", "12-04-2025", "12:00 PM");
        logList.loadLog(meal);
        assertEquals(1, logList.getSize());
        assertEquals(meal, logList.getLog(0));
    }

    @Test
    void testLoadNullLog() {
        LogList logList = new LogList();
        assertThrows(AssertionError.class, () -> logList.loadLog(null));
    }

    @Test
    void testDeleteLogIndexOutOfBounds() {
        LogList logList = new LogList();
        assertThrows(HealthBudException.class, () -> logList.deleteLog(1));
    }

    @Test
    void testDeleteLogWhenEmpty() {
        LogList logList = new LogList();
        assertThrows(HealthBudException.class, () -> logList.deleteLog(1));
    }


    @Test
    void testGetCaloriesSum() {
        LogList logList = new LogList();
        Meal meal1 = new Meal("Breakfast", "300", "12-04-2025", "7:00 AM");
        Meal meal2 = new Meal("Lunch", "500", "12-04-2025", "12:00 PM");

        logList.addLog(meal1);
        logList.addLog(meal2);

        assertEquals(800, logList.getCaloriesSum("12-04-2025"));
    }

    @Test
    void testGetCardioSum() {
        LogList logList = new LogList();
        Cardio cardio1 = new Cardio("Running", "5", "2", "30", "12-04-2025");
        Cardio cardio2 = new Cardio("Cycling", "10", "3", "40", "12-04-2025");

        logList.addLog(cardio1);
        logList.addLog(cardio2);

        assertEquals(3333, logList.getCardioSum("12-04-2025"));
    }

    @Test
    void testGetWaterSum() {
        LogList logList = new LogList();
        Water water1 = new Water("500", "12-04-2025", "7:00 AM");
        Water water2 = new Water("1000", "12-04-2025", "12:00 PM");

        logList.addLog(water1);
        logList.addLog(water2);

        assertEquals(1500, logList.getWaterSum("12-04-2025"));
    }

    @Test
    void testClearLogs() {
        LogList logList = new LogList();
        Meal meal = new Meal("Lunch", "500", "12-04-2025", "12:00 PM");

        logList.addLog(meal);
        assertEquals(1, logList.getSize());

        logList.clearLogs();
        assertEquals(0, logList.getSize());
    }

    @Test
    void testClearLogsEmpty() {
        LogList logList = new LogList();
        logList.clearLogs();
        assertTrue(logList.isEmpty());
    }

    @Test
    void testClearLogsOnEmptyList() {
        LogList logList = new LogList();
        logList.clearLogs();
        assertTrue(logList.isEmpty());
    }

    @Test
    void testIsEmpty() {
        LogList logList = new LogList();
        assertTrue(logList.isEmpty());

        Meal meal = new Meal("Lunch", "500", "12-04-2025", "12:00 PM");
        logList.addLog(meal);
        assertTrue(!logList.isEmpty());
    }

    @Test
    void testFindLog() {
        LogList logList = new LogList();
        Meal meal1 = new Meal("Breakfast", "300", "12-04-2025", "7:00 AM");
        Meal meal2 = new Meal("Lunch", "500", "12-04-2025", "12:00 PM");

        logList.addLog(meal1);
        logList.addLog(meal2);

        logList.findLog("Lunch");
        logList.findLog("Snack");
    }

    @Test
    void testFindLogWithNullKeyword() {
        LogList logList = new LogList();
        Meal meal = new Meal("Lunch", "500", "12-04-2025", "12:00 PM");
        logList.addLog(meal);

        assertThrows(AssertionError.class, () -> logList.findLog(null));
    }

    @Test
    void testListLogs() {
        LogList logList = new LogList();
        Meal meal = new Meal("Lunch", "500", "12-04-2025", "12:00 PM");
        logList.addLog(meal);
        logList.listLogs();
        assertEquals(1, logList.getSize());
    }

    @Test
    void testListLogsEmpty() {
        LogList logList = new LogList();
        logList.listLogs();
        assertTrue(logList.isEmpty());
    }
}
