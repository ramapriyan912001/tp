package seedu.awe.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.AppUtil.checkArgument;


public class Cost {

    public static final String MESSAGE_CONSTRAINTS =
            "Cost should only contain numeric characters without spaces up to only 2 decimal places, "
                    + "and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "([0-9]+)(\\.*)([0-9]*)";

    public final Double cost;

    /**
     * Constructs a {@code Cost}.
     *
     * @param cost A valid cost.
     */
    public Cost(String cost) {
        requireNonNull(cost);
        checkArgument(isValidCost(cost), MESSAGE_CONSTRAINTS);
        this.cost = Double.parseDouble(cost);
    }

    /**
     * Constructs a {@code Cost}.
     *
     * @param cost A valid cost of type double.
     */
    public Cost(double cost) {
        String costDoubleToString = String.valueOf(cost);
        checkArgument(isValidCost(costDoubleToString), MESSAGE_CONSTRAINTS);
        this.cost = cost;
    }

    public Double getCost() {
        return cost;
    }

    /**
     * Sums two costs.
     *
     * @param c The other cost.
     * @return The sum of the two costs.
     */
    public Cost add(Cost c) {
        double result = cost + c.cost;
        if (result < 0) {
            result = 0;
        }
        return new Cost(String.format("%.2f", result));
    }

    /**
     * Subtracts two costs.
     *
     * @param c The other cost.
     * @return Result of the subtraction of the two costs.
     */
    public Cost subtract(Cost c) {
        double result = cost - c.cost;
        if (result < 0) {
            result = 0;
        }
        return new Cost(String.format("%.2f", result));
    }

    /**
     * Returns true if a given string is a valid cost.
     */
    public static boolean isValidCost(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format("%.2f", cost);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Cost // instanceof handles nulls
                && cost.equals(((Cost) other).cost)); // state check
    }

    @Override
    public int hashCode() {
        return cost.hashCode();
    }
}
