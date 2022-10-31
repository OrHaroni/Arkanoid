import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class AnimationRunner {

    private final GUI gui;

    private final int framesPerSecond;

    /**
     * Constructor.
     *
     * @param gui the gui we print on
     * @param fps the fps of the animation
     */
    public AnimationRunner(GUI gui, int fps) {
        this.gui = gui;
        this.framesPerSecond = fps;
    }

    /**
     * Run the game -- start the animation loop.
     *
     * @param animation the game we are using.
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        while (!animation.shouldStop()) {
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d);
            this.gui.show(d);
            sleeper.sleepFor(this.framesPerSecond);
        }
    }

    /**
     * Getter for the gui.
     *
     * @return the gui
     */
    public GUI getGui() {
        return this.gui;
    }
}
