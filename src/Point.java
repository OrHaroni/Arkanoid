/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * Constructor.
     *
     * @param x where the point on X
     * @param y where the point on Y
     */
    public Point(double x, double y) {
        //if negative, return positive
        if (x < 0) {
            this.x = 0;
        } else {
            this.x = x;
        }
        if (y < 0) {
            this.y = 0;
        } else {
            this.y = y;
        }
    }

    /**
     * return the distance of this point to the other point.
     *
     * @param other point to check the distance
     * @return the distance between the object point to other point
     */
    public double distance(Point other) {
        double xDis = (this.x - other.getX()) * (this.x - other.getX());
        double yDis = (this.y - other.getY()) * (this.y - other.getY());
        double num = xDis + yDis;
        return Math.sqrt(num);
    }

    /**
     * checks if 2 points are equal.
     *
     * @param other point to check on
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return (this.x == other.getX()) && (this.y == other.getY());
    }

    /**
     * Getter for the variable.
     *
     * @return x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Getter for the variable.
     *
     * @return t
     */
    public double getY() {
        return this.y;
    }

}
