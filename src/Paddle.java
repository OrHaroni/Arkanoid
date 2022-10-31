import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class Paddle implements Sprite, Collidable {
    private final biuoop.KeyboardSensor keyboard;
    private final Rectangle rect;
    private int startRange;
    private int endRange;
    private final Color color;

    private final int speed;
    /**
     * Constructor.
     *
     * @param keyboard so we know where to move
     * @param rect     the actual block of the paddle
     * @param speed of the paddle
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rect, int speed) {
        this.keyboard = keyboard;
        this.rect = rect;
        this.color = this.rect.getColor();
        this.speed = speed;
    }

    /**
     * Setter for the Range.
     *
     * @param s start of the range
     * @param e end of the range
     */
    public void setRange(int s, int e) {
        this.startRange = s;
        this.endRange = e;
    }

    /**
     * Move the paddle left.
     */
    public void moveLeft() {
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        //in bounce
        if (x - this.speed > this.startRange) {
            this.rect.setUpperLeft(new Point(x - this.speed, y));
        } else {
            //put 2, so we can see the end of the paddle.
            this.rect.setUpperLeft(new Point(this.startRange + 2, y));
        }
    }

    /**
     * Move the paddle right.
     */
    public void moveRight() {
        double x = this.rect.getUpperLeft().getX();
        double y = this.rect.getUpperLeft().getY();
        //in bounce
        if (x + this.rect.getWidth() + this.speed < this.endRange) {
            this.rect.setUpperLeft(new Point(x + this.speed, y));
        } else { // 2 so we can see the end of the paddle
            this.rect.setUpperLeft(new Point(this.endRange - this.rect.getWidth() - 2, y));
        }
    }

    // Sprite

    /**
     * check if the "left" or "right" keys are pressed, and if so move it accordingly.
     */
    @Override
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * draw a rectangle.
     *
     * @param d the surface we draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.rect.drawRect(d);
    }
    // Collidable

    /**
     * Return the "collision shape" of the object.
     *
     * @return the rectangle the collision was on
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * changes the velocity by the type of hit.
     * <p>
     * if the ball hits a vertical edge of the block, the horizontal direction should change,
     * and if the ball hits an horizontal edge of the block, the vertical direction should change.
     * </p>
     *
     * @param collisionPoint  the point where the collision occur
     * @param currentVelocity the velocity when the collision occur
     * @return new velocity after hit
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //If the ball hits the paddle not at the top,
        //make it abnormal hit and let it fall.
        //(right side or left side of the paddle)
        if (collisionPoint.getY() > this.rect.getUpperLeft().getY()) {
                double dx = hitter.getVelocity().getDx();
                double dy = hitter.getVelocity().getDy();
                return new Velocity(-dx, dy);
        }
        //Else, make a normal hit.
        Velocity newVelocity = currentVelocity;
        //speed is calculated with Pythagoras
        double speed = newVelocity.getDx() * newVelocity.getDx() + newVelocity.getDy() * newVelocity.getDy();
        speed = Math.sqrt(speed);
        double distance = this.rect.getWidth() / 5;
        Line l1, l2, l3, l4, l5; //l1 is the most left and l5 is the most right
        /*The next block of code represent the creation of
         * 5 lines equally from the upper line of the paddle
         * every time we change start and end X parameter by adding distance,
         * so we are getting 5 different but tight lines */
        Point start = this.rect.getUpperLeft();
        Point end = new Point(start.getX() + distance, start.getY());
        l1 = new Line(start, end, this.color);
        start = end;
        end = new Point(start.getX() + distance, start.getY());
        l2 = new Line(start, end, this.color);
        start = end;
        end = new Point(start.getX() + distance, start.getY());
        l3 = new Line(start, end, this.color);
        start = end;
        end = new Point(start.getX() + distance, start.getY());
        l4 = new Line(start, end, this.color);
        start = end;
        end = new Point(start.getX() + distance, start.getY());
        l5 = new Line(start, end, this.color);

        /*The next block determine which part of the line got hit,
         * and then returns the correct new velocity */
        if (l1.inRangeX(collisionPoint.getX())) {
            newVelocity = Velocity.fromAngleAndSpeed(180, speed);
            return newVelocity;
        }
        if (l2.inRangeX(collisionPoint.getX())) {
            return Velocity.fromAngleAndSpeed(210, speed);
        }
        if (l3.inRangeX(collisionPoint.getX())) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (l4.inRangeX(collisionPoint.getX())) {
            return Velocity.fromAngleAndSpeed(300, speed);
        }
        if (l5.inRangeX(collisionPoint.getX())) {
            return Velocity.fromAngleAndSpeed(330, speed);
        }
        //if nothing happens
        return currentVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game we are adding the paddle into
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    @Override
    public Color getColor() {
        return this.rect.getColor();
    }

}