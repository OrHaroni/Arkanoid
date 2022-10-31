/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit block that being hit...
     * @param hitter   is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
