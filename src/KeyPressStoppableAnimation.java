import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor ks;
    private final String key; //To start the animation.
    private final Animation animation;
    private boolean isAlreadyPressed;
    private boolean stopIt;

    /**
     * Constructor.
     *
     * @param key       the name of Animation
     * @param animation the actual animation
     */
    public KeyPressStoppableAnimation(String key, Animation animation) {
        this.ks = animation.getKeySensor();
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
        this.stopIt = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);
        if (this.ks.isPressed(this.key) && !this.isAlreadyPressed) {
            this.stopIt = true;
        } else if (!this.ks.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
        if (this.ks.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stopIt = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stopIt;
    }

    @Override
    public KeyboardSensor getKeySensor() {
        return this.ks;
    }
}
