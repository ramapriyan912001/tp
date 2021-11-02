package seedu.awe.model.expense;

import static java.util.Objects.requireNonNull;
import static seedu.awe.commons.util.AppUtil.checkArgument;


public class Cost {

    public static final String MESSAGE_CONSTRAINTS =
            "Cost should only contain numeric characters without spaces, should not be negative numbers, "
                    + "should only contain at most two decimal places otherwise the cost will be rounded off"
                    + "to the nearest two decimal places, "
                    + "and it should not be blank!";

    public static final double MAX_COST = 10000000;

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
        double costAsDouble = Double.parseDouble(cost);;
        String costDoubleToString = String.format("%.2f", costAsDouble);;
        checkArgument(isValidCost(costDoubleToString), MESSAGE_CONSTRAINTS);
        if (costAsDouble > 10000000) {
            costAsDouble = 10000000;
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
        if (result < 0) {
            result = 0;
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
        if (result < 0) {
            result = 0;
        }
        return new Cost(result);
    }

    /**
     * Multiplies the cost by the inputted value c.
     *
     * @param c Value to multiply the cost by.
     * @return Result of the multiplication.
     */
    public Cost multiply(double c) {
        double result = cost * c;
        if (result < 0) {
            result = 0;
        }
        return new Cost(result);
    }

    /**
     * Divides the cost by the inputted value c.
     *
     * @param c Value to divide the cost by.
     * @return Result of the division.
     */
    public Cost divide(double c) {
        double result = cost / c;
        if (result < 0) {
            result = 0;
        }
        return new Cost(result);
    }

    /**
     * Returns true if a given string is a valid cost.
     */
    public static boolean isValidCost(String test) {
        double costAsDouble = Double.parseDouble(test);
        String costDoubleToString = String.format("%.2f", costAsDouble);
        return costDoubleToString.matches(VALIDATION_REGEX);
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
