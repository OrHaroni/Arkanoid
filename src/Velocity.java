/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Constructor.
     *
     * @param dx velocity in x
     * @param dy velocity in y
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Getter for the variable.
     *
     * @return dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Getter for the variable.
     *
     * @return xy
     */
    public double getDy() {
        return this.dy;
    }


    /**
     * moves the point.
     * <p>
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy)
     * </p>
     *
     * @param p Point to be moved
     * @return a new point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * creates velocity with angle and speed.
     *
     * @param angle of the ball
     * @param speed of the ball
     * @return the new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos(Math.toRadians((angle)));
        double dy = speed * Math.sin(Math.toRadians((angle)));
        return new Velocity(dx, dy);
    }
}