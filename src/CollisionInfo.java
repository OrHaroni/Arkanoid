
/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class CollisionInfo {

    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Constructor.
     *
     * @param p point
     * @param c the collidable
     */
    public CollisionInfo(Point p, Collidable c) {
        this.collisionPoint = p;
        this.collisionObject = c;
    }

    /**
     * Getter for the collisionPoint.
     *
     * @return the collisionPoint
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Getter for the collisionObject.
     *
     * @return the collisionObject
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
