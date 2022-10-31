import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class Ball implements Sprite {
    private static final int RIGHT_MARGIN = 775;
    private static final int DOWN_MARGIN = 598;
    private static final int START_MARGIN = 50;
    private Point center;
    private final int radius;
    private final java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameE;
    //where that the ball can move

    /**
     * Constructor.
     *
     * @param center of the ball
     * @param r      radius of the ball
     * @param color  of teh ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        //if you have negative radius;
        if (r < 0) {
            this.radius = -r;
        } else {
            this.radius = r;
        }
        this.center = center;
        this.color = color;
    }

    /**
     * Getter for the variable.
     *
     * @return the variable
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * Setter for the center.
     *
     * @param x for the new point we set
     * @param y for the new point we set
     */
    public void setCenter(double x, double y) {
        if (x < 0) {
            //if both negative
            if (y < 0) {
                this.center = new Point(-x, -y);
                //only x negative
            } else {
                this.center = new Point(-x, y);
            }
        } else {
            this.center = new Point(x, y);
        }
    }

    /**
     * Getter for the variable.
     *
     * @return the x of the center
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Getter for the variable.
     *
     * @return the y of the center
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Getter for size.
     *
     * @return the size of teh ball
     */
    public int getSize() {
        return (int) (Math.PI * Math.pow(this.radius, 2));
    }

    /**
     * Getter for the center.
     *
     * @return the center of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * Getter for the color.
     *
     * @return color
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param surface to draw on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        //so the middle in also red like the picture in the GIT
        surface.setColor(Color.red);
        surface.fillCircle(this.getX(), this.getY(), 1);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Setter for the velocity.
     *
     * @param v velocity you want to set
     */
    public void setVelocity(Velocity v) {
        this.v = v;
    }

    /**
     * Setter for the velocity.
     * use dx and dy to create new velocity and set it.
     *
     * @param dx variable you want to set
     * @param dy variable you want to set
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Getter for the velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * move the object point with its velocity.
     */
    public void moveOneStep() {
        CollisionInfo info = this.getGame().getClosestCollision(this.getTrajectory());
        //no collision
        if (null == info) {
            this.center = this.getVelocity().applyToPoint(this.center);
            //there is collision
        } else {
            Point closest = info.collisionPoint();
            Collidable collide = info.collisionObject();
            this.setVelocity(collide.hit(this, closest, this.getVelocity()));
            this.center = this.getVelocity().applyToPoint(this.center);
        }
        this.fixBall();
        this.fixVelocity();
    }

    /**
     * make new color from RGB.
     *
     * @return new color
     */
    public static java.awt.Color randColor() {
        Random r = new Random();
        float red = r.nextFloat();
        float green = r.nextFloat();
        float blue = r.nextFloat();
        //return a random number by RGB
        return new Color(red, green, blue);
    }

    /**
     * calculates Trajectory to board with height and width 600.
     *
     * @return Description text text text.
     */
    public Line getTrajectory() {
        Velocity v = this.getVelocity();
        Point end = new Point(this.getX() + (v.getDx() * 4), this.getY() + (v.getDy() * 4));
        return new Line(this.center, end, this.color);
    }

    /**
     * Getter for the game environment.
     *
     * @return the game environment
     */
    public GameEnvironment getGame() {
        return gameE;
    }

    /**
     * Setter for the game.
     *
     * @param game setting the game environment
     */
    public void setGame(GameEnvironment game) {
        this.gameE = game;
    }

    /**
     * move one step per time passed.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * add this ball to the given game (to its list of sprites).
     *
     * @param g the game we are adding the ball in to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Fix the ball, so it will not get inside the margins.
     */
    public void fixBall() {
        //Fixing at the edge
        if (this.getX() - this.getRadius() < START_MARGIN || this.getX() + this.getRadius() > RIGHT_MARGIN) {
            this.setVelocity(-this.getVelocity().getDx(), this.getVelocity().getDy());
            if (this.getY() - this.getRadius() < START_MARGIN || this.getY() + this.getRadius() > DOWN_MARGIN) {
                this.setVelocity(this.getVelocity().getDx(), -this.getVelocity().getDy());
            }
        }
    }

    /**
     * Remove this Ball from the game.
     *
     * @param game we delete from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
/**
     * fix the ball velocity if its suddenly parallel to the paddle.
     */
    private void fixVelocity() {
        Velocity v = this.v;
        double speed = (v.getDx() * v.getDx()) + (v.getDy() * v.getDy());
        speed = Math.sqrt(speed);
        //Velocities when the ball Parallel to the ground.
        Velocity badVelocity = Velocity.fromAngleAndSpeed(0, speed);
        double dx1 = badVelocity.getDx();
        double dy1 = badVelocity.getDy();
        Velocity badVelocity2 = Velocity.fromAngleAndSpeed(180, speed);
        double dx2 = badVelocity2.getDx();
        double dy2 = badVelocity2.getDy();
        if (v.getDx() == dx1 && v.getDy() == dy1) {
            this.setVelocity(Velocity.fromAngleAndSpeed(350, speed));
            return;
        }
        if (v.getDx() == dx2 && v.getDy() == dy2) {
            this.setVelocity(Velocity.fromAngleAndSpeed(190, speed));
            return;
        }
    }

}


