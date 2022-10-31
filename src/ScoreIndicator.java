import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class ScoreIndicator implements Sprite {
    private static final int MIDDLE_SCREEN = 300;
    private static final int HEIGHT = 24;
    private static final int FONT = 18;
    private final Counter score;

    /**
     * Constructor.
     *
     * @param score the score we want to display.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        String s = "Score: " + this.score.getValue();
        d.drawText(MIDDLE_SCREEN, HEIGHT, s, FONT);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public Color getColor() {
        return Color.WHITE;
    }
}
