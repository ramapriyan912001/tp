package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean isHelpCommand;

    /** The application should exit. */
    private final boolean isExitCommand;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isHelpCommand, boolean isExitCommand) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isHelpCommand = isHelpCommand;
        this.isExitCommand = isExitCommand;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return isHelpCommand;
    }

    public boolean isExit() {
        return isExitCommand;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && isHelpCommand == otherCommandResult.isHelpCommand
                && isExitCommand == otherCommandResult.isExitCommand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isHelpCommand, isExitCommand);
    }

}
