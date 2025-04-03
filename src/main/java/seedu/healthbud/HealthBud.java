package seedu.healthbud;

import seedu.healthbud.parser.ParserManager;
import seedu.healthbud.storage.Storage;
import java.util.Scanner;


public class HealthBud {
    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static LogList mealLogs = new LogList();
    public static LogList workoutLogs = new LogList();
    public static LogList waterLogs = new LogList();
    public static LogList pbLogs = new LogList();
    public static LogList cardioLogs = new LogList();
    public static LogList goalLogs = new LogList();


    public static void main(String[] args) {

        Ui.printGreeting();
        Storage.loadLogs(mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs, goalLogs);
        Scanner in = new Scanner(System.in);
        assert in != null : "Scanner object should not be null";
        boolean isLooping = true;
        while (isLooping) {
            String input = in.nextLine().trim().toLowerCase();
            assert !input.isEmpty() : "Input should not be empty";
            isLooping = ParserManager.handleInput(goalLogs, pbLogs, mealLogs, workoutLogs, waterLogs, cardioLogs,
                    input);
        }
    }
}
