import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d the surface we draw on
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * @return the color of the Sprite.
     */
    Color getColor();
}
