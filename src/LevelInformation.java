import java.util.List;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public interface LevelInformation {
    /**
     * @return the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return all the velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return the desired speed of the paddle
     */
    int paddleSpeed();

    /**
     * @return the desired width of the paddle
     */
    int paddleWidth();


    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();


    /**
     * @return a sprite with the background of the level
     */
    Sprite getBackground();


    /**
     * @return list of all the blocks in a level
     */
    List<Block> blocks();

    /**
     * @return Number of blocks that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}
