import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class Circle implements Sprite {
    private final Color color;

    private final Point point;

    private final int radius;

    /**
     * Constructor.
     *
     * @param point  middle of the circle
     * @param radius of the circle
     * @param c      color of the circle
     */
    public Circle(Point point, int radius, Color c) {
        this.point = point;
        this.radius = radius;
        this.color = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle((int) this.point.getX(), (int) this.point.getY(), this.radius);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public Color getColor() {
        return this.color;
    }
}
