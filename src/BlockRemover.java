/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param game            we are adding to
     * @param remainingBlocks number of the remaining block in the game
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /** Getter for the counter.
         * @return the counter
         */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit the block that got hit.
     * @param hitter   the ball that hit it
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.getRemainingBlocks().decrease(1);
    }
}
