package seedu.awe.model.expense;

import java.util.List;
import java.util.function.Predicate;

import seedu.awe.commons.util.StringUtil;

/**
 * Tests that an {@code Expense}'s {@code Description} matches any of the keywords given.
 */
public class DescriptionContainsKeywordsPredicate implements Predicate<Expense> {

    private final List<String> keywords;

    /**
     * A public constructor to initialize the list of keywords
     * to the given one.
     *
     * @param keywords The list of keywords.
     */
    public DescriptionContainsKeywordsPredicate(List<String> keywords) {

        this.keywords = keywords;
    }

    @Override
    public boolean test(Expense expense) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(expense.getDescription().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DescriptionContainsKeywordsPredicate)) { // instanceof handles nulls
            return false;
        }

        return keywords.equals(((DescriptionContainsKeywordsPredicate) other).keywords); // state check
    }

}

