package seedu.awe.testutil;

import java.util.List;

import seedu.awe.model.expense.Cost;
import seedu.awe.model.transactionsummary.TransactionSummary;


public class TypicalTransactionSummary {

    public static final Cost ALICE_COST = new Cost(20);
    public static final Cost AMY_COST = new Cost(71.34);
    public static final Cost DANIEL_COST = new Cost(65.88);
    public static final Cost CARL_COST = new Cost(190.59);

    public static final TransactionSummary ALICE_TRANSACTION_SUMMARY =
            new TransactionSummary(TypicalPersons.ALICE, ALICE_COST);

    public static final TransactionSummary AMY_TRANSACTION_SUMMARY =
            new TransactionSummary(TypicalPersons.AMY, AMY_COST);

    public static final TransactionSummary DANIEL_TRANSACTION_SUMMARY =
            new TransactionSummary(TypicalPersons.DANIEL, DANIEL_COST);

    public static final TransactionSummary CARL_TRANSACTION_SUMMARY =
            new TransactionSummary(TypicalPersons.CARL, CARL_COST);

    public static final List<TransactionSummary> VALID_TRANSACTION_SUMMARY_LIST =
            List.of(ALICE_TRANSACTION_SUMMARY, ALICE_TRANSACTION_SUMMARY, CARL_TRANSACTION_SUMMARY,
            DANIEL_TRANSACTION_SUMMARY);

}
