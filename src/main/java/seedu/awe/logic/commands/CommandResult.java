package seedu.awe.logic.commands;

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

    /** The application should show groups */
    private final boolean isShowGroupsCommand;

    /** The application should show contacts */
    private final boolean isShowContactsCommand;

    /** The application should show expenses */
    private final boolean isShowExpensesCommand;

    /** The application should show transaction summary */
    private final boolean isShowTransactionSummaryCommand;

    /** The application should show payments to make */
    private final boolean isShowPaymentsCommand;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean isHelpCommand, boolean isExitCommand,
                         boolean isShowGroupsCommand, boolean isShowContactsCommand,
                                boolean isShowExpensesCommand, boolean isShowTransactionSummaryCommand,
                         boolean isShowPaymentsCommand) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.isHelpCommand = isHelpCommand;
        this.isExitCommand = isExitCommand;
        this.isShowGroupsCommand = isShowGroupsCommand;
        this.isShowContactsCommand = isShowContactsCommand;
        this.isShowExpensesCommand = isShowExpensesCommand;
        this.isShowTransactionSummaryCommand = isShowTransactionSummaryCommand;
        this.isShowPaymentsCommand = isShowPaymentsCommand;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false, false, false, false, false, false, false);
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

    public boolean isShowGroups() {
        return isShowGroupsCommand;
    }

    public boolean isShowContacts() {
        return isShowContactsCommand;
    }

    public boolean isShowExpenses() {
        return isShowExpensesCommand;
    }

    public boolean isShowTransactionSummary() {
        return isShowTransactionSummaryCommand;
    }

    public boolean isShowPaymentsCommand() {
        return isShowPaymentsCommand;
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
                && isExitCommand == otherCommandResult.isExitCommand
                && isShowGroupsCommand == otherCommandResult.isShowGroupsCommand
                && isShowContactsCommand == otherCommandResult.isShowContactsCommand
                && isShowExpensesCommand == otherCommandResult.isShowExpensesCommand
                && isShowTransactionSummaryCommand == otherCommandResult.isShowTransactionSummaryCommand
                && isShowPaymentsCommand == otherCommandResult.isShowPaymentsCommand;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, isHelpCommand, isExitCommand,
                isShowGroupsCommand, isShowContactsCommand, isShowExpensesCommand, isShowTransactionSummaryCommand,
                isShowPaymentsCommand);

    }

}
