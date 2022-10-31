/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the listener we are adding to the list
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener we are removing to the list
     */
    void removeHitListener(HitListener hl);
}
