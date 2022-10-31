import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class Line implements Sprite {
    private final Point start;
    private final Point end;

    private final Color color;

    /**
     * Constructor.
     *
     * @param start the point that starts the line
     * @param end   the point that ends the line
     * @param color of the line
     */
    public Line(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    /**
     * Constructor.
     *
     * @param x1    the x of the point that starts the line
     * @param y1    the y of the  point that ends the line
     * @param x2    the x of the point that starts the line
     * @param y2    the y of the point that ends the line
     * @param color of the line
     */
    public Line(double x1, double y1, double x2, double y2, Color color) {
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        this.start = start;
        this.end = end;
        this.color = color;
    }

    /**
     * calculate the distance between start to end.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * calculate the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double x = this.start.getX() + this.end.getX();
        x = x / 2;
        double y = this.start.getY() + this.end.getY();
        y = y / 2;
        return new Point(x, y);
    }

    /**
     * Getter for the variable.
     *
     * @return the variable
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * Getter for the variable.
     *
     * @return the variable
     */
    // Returns the end point of the line
    public Point getEnd() {
        return this.end;
    }

    /**
     * checks if object the line other are intersecting.
     *
     * @param other line to check with
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //if they are the same of course they meet.
        if (this.equals(other)) {
            return true;
        }
        Point check = this.intersectionWith(other);
        //if there is no point
        if (check == null) {
            return false;
        }
        //checks if the point is between those lines
        if (this.inRangeX(check.getX()) && this.inRangeY(check.getY())) {
            if (other.inRangeX(check.getX()) && other.inRangeY(check.getY())) {
                return true;
            }
        }
        //there is a point for limitless lines,
        //but, they have a limit (start and end) so return false.
        return false;
    }


    /**
     * calculates the intersection point.
     * <p>
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     * </p>
     *
     * @param other line to check with
     * @return the intersecting point
     */
    public Point intersectionWith(Line other) {
        //intersect at the edge
        if (this.getEnd().equals(other.getStart())) {
            return this.end;
        }
        //intersect at the other edge
        if (this.getStart().equals(other.getEnd())) {
            return this.start;
        }
        //intersect at start and different
        if (this.getStart().equals(other.getStart()) && !this.equals(other)) {
            return this.start;
        }
        //intersect at end and different
        if (this.getEnd().equals(other.getEnd()) && !this.equals(other)) {
            return this.end;
        }
        // Line this represented as a1x + b1y = c1
        double a1 = this.getEnd().getY() - this.getStart().getY();
        double b1 = this.getStart().getX() - this.getEnd().getX();
        double c1 = a1 * (this.getStart().getX()) + b1 * (this.getStart().getY());

        // Line other represented as a2x + b2y = c2
        double a2 = other.getEnd().getY() - other.getStart().getY();
        double b2 = other.getStart().getX() - other.getEnd().getX();
        double c2 = a2 * (other.getStart().getX()) + b2 * (other.getStart().getY());
        //checks if they are even
        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            return null;
            //there is a point
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            Point check = new Point(x, y);
            //checks if the point is between those lines
            if (this.inRangeX(check.getX()) && this.inRangeY(check.getY())) {
                if (other.inRangeX(check.getX()) && other.inRangeY(check.getY())) {
                    return check;
                }
            }
            //if the line were limitless, there was a point
            //but, they are not (they have start and end), so returning null;
            return null;
        }
    }

    /**
     * checks if the object and other are equal.
     *
     * @param other line we are checking on
     * @return true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return this.getStart().equals((other.getStart())) && this.getEnd().equals((other.getEnd()));
    }

    /**
     * checks if given x is in range of the line.
     *
     * @param x the number we are checking
     * @return TRUE if in range, FALSE otherwise
     */
    public boolean inRangeX(double x) {
        //function goes backwards
        if (this.start.getX() <= this.end.getX()) {
            return (x >= this.start.getX() && x <= this.end.getX());
        } else {
            return (x <= this.start.getX() && x >= this.end.getX());
        }
    }

    /**
     * checks if given y is in range of the line.
     *
     * @param y the number we are checking
     * @return TRUE if in range, FALSE otherwise
     */
    public boolean inRangeY(double y) {
        //function goes up
        if (this.getStart().getY() <= this.getEnd().getY()) {
            return (y >= this.getStart().getY() && y <= this.getEnd().getY());
            //function goes down
        } else {
            return (y <= this.getStart().getY() && y >= this.getEnd().getY());
        }
    }


    /**
     * returns the closest intersection point to the start of this line.
     * <p>
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * </p>
     * And even more explanations to follow in consecutive
     *
     * @param rect the rectangle that intersect (or not) with this line
     * @return the closest point or null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> pointList = rect.intersectionPoints(this);
        //there are no intersection points
        if (pointList.size() == 0) {
            return null;
        }
        //the list is not empty, so I can take the first one.
        Point closest = pointList.get(0);
        for (int i = 0; i < pointList.size(); i++) {
            if (this.start.distance(closest) > this.start.distance(pointList.get(i))) {
                closest = pointList.get(i);
            }
        }
        return closest;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawLine((int) start.getX(), (int) start.getY(),
                (int) end.getX(), (int) end.getY());
    }

    @Override
    public void timePassed() {

    }

    @Override
    public Color getColor() {
        return this.color;
    }
}