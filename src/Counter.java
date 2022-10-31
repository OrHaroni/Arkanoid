/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class Counter {
    private int number;

    /**
     * Constructor.
     */
    public Counter() {
        this.number = 0;
    }

    /**
     * add number to current count.
     *
     * @param number we are adding.
     */
    void increase(int number) {
        this.number += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number we are subtracting.
     */
    void decrease(int number) {
        this.number -= number;
    }

    /**
     * Getter for the current value.
     *
     * @return the value
     */
    int getValue() {
        return this.number;
    }
}