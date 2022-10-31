import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private int countFrom;
    private final SpriteCollection gameScreen;
    private final Sprite background;

    /**
     * Constructor.
     *
     * @param numOfSeconds remaining seconds
     * @param countFrom    count from
     * @param gameScreen   all the screen
     * @param background all the background sprites.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen,
                              Sprite background) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.background = background;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        this.background.drawOn(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.RED);
        if (this.countFrom == 0) {
            d.drawText(350, 350, "Go!", 50);
            sleeper.sleepFor(1000); //Sleeps for a second
            this.countFrom--;
            return;
        }
        String s = this.countFrom + "...";
        d.drawText(350, 350, s, 50);
        sleeper.sleepFor(1000); //Sleeps for a second
        this.countFrom--;
    }

    @Override
    public boolean shouldStop() {
        if (this.countFrom >= 0) {
            return false;
        }
        return true;
    }

    @Override
    public KeyboardSensor getKeySensor() {
        return null;
    }
}
