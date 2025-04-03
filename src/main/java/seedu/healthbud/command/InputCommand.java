package seedu.healthbud.command;

// FOR COMMANDS THAT OINLY NEED INPUT AND NO NEED LOGS AT ALL

public abstract class InputCommand implements Command {
    protected String input;

    public InputCommand(String input) {
        assert input != null : "Input should not be null";
        this.input = input;
    }
}

