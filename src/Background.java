import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class Background implements Sprite {

    private final List<Sprite> shapes;

    private final Color color;

    /**
     * Constructor.
     *
     * @param shapes all the shapes in this background.
     * @param color  of this background
     */
    public Background(List<Sprite> shapes, Color color) {
        this.shapes = shapes;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 40, 800, 600);
        for (Sprite shape : this.shapes) {
            d.setColor(shape.getColor());
            shape.drawOn(d);
        }
    }

    @Override
    public void timePassed() {

    }

    @Override
    public Color getColor() {
        return this.color;
    }

    /** Getter for the shapes.
         * @return list of all the shapes
         */
    public List<Sprite> getShapes() {
        return shapes;
    }
}
