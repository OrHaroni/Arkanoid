import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class PauseScreen implements Animation {
    private final KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Constructor.
     *
     * @param k the key sensor we are using
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

    @Override
    public KeyboardSensor getKeySensor() {
        return this.keyboard;
    }
}

