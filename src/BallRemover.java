/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class BallRemover implements HitListener {

    private final GameLevel game;
    private final Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param game  the game that the remover assigned to.
     * @param balls the balls at the beginning.
     */
    public BallRemover(GameLevel game, Counter balls) {
        this.game = game;
        this.remainingBalls = balls;
    }

    /**
     * Getter for the counter.
     *
     * @return the counter
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
        System.out.println("ball death!");
    }
}
