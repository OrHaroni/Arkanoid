import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class SpriteCollection {
    private final java.util.List<Sprite> sprites;

    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * add sprite to the list.
     *
     * @param s the sprite we are adding.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> listeners = new ArrayList<>(this.sprites);
        for (Sprite l : listeners) {
            l.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the surface we draw on
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }

    /**
     * Getter for the list.
     *
     * @return the list
     */
    public java.util.List<Sprite> getSprites() {
        return this.sprites;
    }
}
