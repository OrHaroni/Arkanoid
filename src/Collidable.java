
/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the rectangle the collision was on
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * <p>
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * </p>
     *
     * @param collisionPoint  where the collision taken place
     * @param currentVelocity the velocity at the time of the collision
     * @param hitter the ball that hit
     * @return the new velocity after hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
