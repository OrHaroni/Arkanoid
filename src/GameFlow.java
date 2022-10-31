import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.List;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class GameFlow {

    private final KeyboardSensor keyboard;
    private final AnimationRunner runner;
    private final Counter score;

    /**
     * Constructor.
     *
     * @param ar the Animation runner
     * @param ks the keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.keyboard = ks;
        this.runner = ar;
        this.score = new Counter();
    }

    /**
     * running the levels.
     *
     * @param levels list of all the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.score);
            level.initialize();
            level.run();
            level.setRunning(true);
            System.out.println(level.shouldStop());
            if (level.getBallCounter().getValue() == 0) {
                this.losingEndScreen(this.runner.getGui());
            }
        }
        this.winningEndScreen(this.runner.getGui());
    }

    /**
     * Winning screen.
     *
     * @param gui the game's gui we are about to close.
     */
    public void winningEndScreen(GUI gui) {
        DrawSurface d = gui.getDrawSurface();
        d.setColor(Color.WHITE);
        d.drawRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(100, 250, "You Win! Your score is " + this.score.getValue(), 40);
        d.drawText(100, 350, "Please press spacebar to exit", 40);
        gui.show(d);
        while (true) {
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                gui.close();
                System.exit(1);
            }
        }
    }

    /**
     * Losing screen.
     *
     * @param gui the game's gui we are about to close.
     */
    public void losingEndScreen(GUI gui) {
        DrawSurface d = gui.getDrawSurface();
        d.setColor(Color.WHITE);
        d.drawRectangle(0, 0, 800, 600);
        d.setColor(Color.BLACK);
        d.drawText(100, 250, "Game Over. Your score is " + this.score.getValue(), 40);
        d.drawText(100, 350, "Please press spacebar to exit", 40);
        gui.show(d);
        while (true) {
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                gui.close();
                System.exit(1);
            }
        }
    }
}
