package seedu.awe.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.AppUtil.checkArgument;


public class Cost {

    public static final String MESSAGE_CONSTRAINTS =
            "Cost should only contain numeric characters without spaces, should not be negative numbers, "
                    + "should only contain at most two decimal places otherwise the cost will be rounded off "
                    + "to the nearest two decimal places, should be one billion or less "
                    + "and it should not be blank!";

    public static final double MAX_COST = 1000000000;
    public static final int MIN_COST = 0;
    public static final int MAX_LENGTH = 50;

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
        double costAsDouble = 0;
        try {
            costAsDouble = Double.parseDouble(cost);
        } catch (NumberFormatException e) {
            checkArgument(isValidCost(cost), MESSAGE_CONSTRAINTS);
        }
        checkArgument(cost.length() <= MAX_LENGTH, MESSAGE_CONSTRAINTS);
        String costDoubleToString = String.format("%.2f", costAsDouble);
        checkArgument(isValidCost(costDoubleToString), MESSAGE_CONSTRAINTS);
        if (costAsDouble > Cost.MAX_COST) {
            costAsDouble = Cost.MAX_COST;
        }
        this.cost = costAsDouble;
    }

    /**
     * Constructs a {@code Cost}.
     *
     * @param cost A valid cost of type double.
     */
    public Cost(double cost) {
        requireNonNull(cost);
        if (cost > MAX_COST) {
            cost = MAX_COST;
        }
        String costDoubleToString = String.format("%.2f", cost);;
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
        if (result < MIN_COST) {
            result = MIN_COST;
        } else if (result > MAX_COST) {
            result = MAX_COST;
        }
        return new Cost(result);
    }

    /**
     * Subtracts two costs.
     *
     * @param c The other cost.
     * @return Result of the subtraction of the two costs.
     */
    public Cost subtract(Cost c) {
        double result = cost - c.cost;
        if (result < MIN_COST) {
            result = MIN_COST;
        }
        return new Cost(result);
    }

    /**
     * Returns true if a given string is a valid cost.
     */
    public static boolean isValidCost(String test) {
        double costAsDouble = 0;
        try {
            costAsDouble = Double.parseDouble(test);
        } catch (NumberFormatException e) {
            return false;
        }
        String costDoubleToString = String.format("%.2f", costAsDouble);
        return costDoubleToString.matches(VALIDATION_REGEX)
                && costAsDouble <= MAX_COST
                && test.length() <= MAX_LENGTH;
    }

    @Override
    public String toString() {
        return "$" + String.format("%.2f", cost);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Cost // instanceof handles nulls
                && Math.abs(cost - ((Cost) other).getCost()) < 0.01);
    }

    @Override
    public int hashCode() {
        return cost.hashCode();
    }
}
