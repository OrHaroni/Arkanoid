import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class Rectangle implements Sprite {
    public static final int TWENTY = 20; //const for default size.
    private Point upperLeft;
    private double width;
    private double height;
    private final java.awt.Color color;

    /**
     * Constructor.
     *
     * @param upperLeft of the rectangle
     * @param width     of the rectangle
     * @param height    of the rectangle
     * @param c         the color of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height, java.awt.Color c) {
        setUpperLeft(upperLeft);
        setWidth(width);
        setHeight(height);
        this.color = c;
    }
    //Setters

    /**
     * Setter for the Point.
     *
     * @param point variable you want to set
     */
    public void setUpperLeft(Point point) {
        this.upperLeft = point;
    }

    /**
     * Setter for the variable.
     *
     * @param width variable you want to set
     */
    public void setWidth(double width) {
        //if its negative, set as positive
        if (width < 0) {
            this.width = -width;
            //else, if 0, set default size
        } else if (width == 0) {
            this.width = TWENTY;
        } else {
            this.width = width;
        }

    }

    /**
     * Setter for the variable.
     *
     * @param height variable you want to set
     */
    public void setHeight(double height) {
        //if its negative, set as positive
        if (height < 0) {
            this.height = -height;
            //else, if 0, set default size
        } else if (height == 0) {
            this.height = TWENTY;
        } else {
            this.height = height;
        }

    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.

    /**
     * Return a (possibly empty) List of intersection points, with the specified line.
     *
     * @param line the specified line.
     * @return list of all the intersection points between our object,
     * and the given line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> pointList = new ArrayList<>();
        Point start, end;
        Line other;
        /*The next block checks if the given line
         * is intersecting with the upper line of the rectangle*/
        start = this.getUpperLeft();
        end = new Point(start.getX() + this.width, start.getY());
        other = new Line(start, end, this.color);
        //if intersecting with the upper line of the rectangle.
        if (line.isIntersecting(other)) {
            pointList.add(line.intersectionWith(other));
        }
        /*The next block checks if the given line
         * is intersecting with the lower line of the rectangle*/
        start = new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY() + this.height);
        end = new Point(this.getUpperLeft().getX() + this.width, start.getY());
        other = new Line(start, end, this.color);
        //if intersecting with the lower line of the rectangle.
        if (line.isIntersecting(other)) {
            pointList.add(line.intersectionWith(other));
        }
        /*The next block checks if the given line
         * is intersecting with the left line of the rectangle*/
        start = this.getUpperLeft();
        end = new Point(this.getUpperLeft().getX(), start.getY() + this.height);
        other = new Line(start, end, this.color);
        //if intersecting with the left line of the rectangle.
        if (line.isIntersecting(other)) {
            pointList.add(line.intersectionWith(other));
        }
        /*The next block checks if the given line
         * is intersecting with the right line of the rectangle*/
        start = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        end = new Point(start.getX(), start.getY() + this.height);
        other = new Line(start, end, this.color);
        //if intersecting with the left line of the rectangle.
        if (line.isIntersecting(other)) {
            pointList.add(line.intersectionWith(other));
        }
        return pointList;
    }

    //getters

    /**
     * @return the color of the rectangle.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Getter for the Width.
     *
     * @return the Width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getter for the Height.
     *
     * @return the Height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Getter for the upper left point.
     *
     * @return the point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * draw a rectangle.
     *
     * @param d the surface we draw on.
     */
    public void drawRect(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) this.width, (int) this.height);
        d.setColor(color.BLACK);
        d.drawRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) this.width, (int) this.height);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) this.width, (int) this.height);
    }

    @Override
    public void timePassed() {

    }
}
