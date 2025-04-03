package seedu.healthbud.parser;

import seedu.healthbud.LogList;
import seedu.healthbud.Ui;
import seedu.healthbud.command.Command;
import seedu.healthbud.parser.addcommandparser.AddParser;

public class ParserManager {

    public static final String NEW_LINE = "\n     ";

    public static boolean handleInput(LogList goalLogs, LogList pbLogs, LogList mealLogs, LogList workoutLogs,
                                      LogList waterLogs, LogList cardioLogs, String input) {

        assert input != null : "Input should not be null";
        String function = input.split(" ")[0];

        try {
            Command command;
            switch (function) {
            case "bye":
                return Ui.printGoodbye();
            case "add":
                command = AddParser.parse(input, mealLogs, waterLogs, cardioLogs, pbLogs, workoutLogs, goalLogs);
                break;
            case "help":
                Ui.printHelp();
                return true;
            case "list":
                command = ListParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);
                break;
            case "recommend":
                command = RecommendParser.parse(input);
                break;
            case "bmi":
                command = BMIParser.parse(input);
                break;
            case "delete":
                command = DeleteParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);
                break;
            case "clear":
                command = ClearParser.parse(input, pbLogs, mealLogs, workoutLogs, waterLogs, cardioLogs);
                break;
            case "sum":
                command = SumParser.parse(input, mealLogs, waterLogs, cardioLogs);
                break;
            case "status":
                command = StatusParser.parse(input, goalLogs, pbLogs, mealLogs, workoutLogs, waterLogs, cardioLogs);
                break;
            case "search":
                command = SearchParser.parse(input, mealLogs, workoutLogs, waterLogs, pbLogs, cardioLogs);
                break;
            case "view":
                ViewGoalsParser.parse(input);
                return true;
            case "track":
                command = TrackGoalParser.parse(input, goalLogs, pbLogs, mealLogs, workoutLogs, waterLogs, cardioLogs);
                break;
            default:
                Ui.printUnknownCommand();
                return true;
            }
            command.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
