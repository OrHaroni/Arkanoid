import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public interface Animation {

    /**
     * is in charge of the logic of the game.
     *
     * @param d surface we draw on
     */
    void doOneFrame(DrawSurface d);

    /**
     * Is in charge of stopping condition of the animation.
     *
     * @return whether to stop or not
     */
    boolean shouldStop();

    /**
     * @return the sensor of the game.
     */
    KeyboardSensor getKeySensor();
}
