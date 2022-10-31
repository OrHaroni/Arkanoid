import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle block;
    private final List<HitListener> hitListeners;

    /**
     * Constructor.
     *
     * @param r the rectangle that represent the block.
     */
    public Block(Rectangle r) {
        this.block = r;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * Getter for the rectangle.
     *
     * @return the rectangle
     */
    public Rectangle getRect() {
        return this.block;
    }

    /**
     * return the object rectangle as the block where had collision.
     *
     * @return returns the block's rectangle object
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.block;
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
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        Rectangle rect = this.getCollisionRectangle();
        //if in range for horizontal hit
        if (rect.getUpperLeft().getX() <= collisionPoint.getX()
                && rect.getUpperLeft().getX() + rect.getWidth() >= collisionPoint.getX()) {
            //if its exactly on one of the horizontal lines
            if (collisionPoint.getY() == rect.getUpperLeft().getY()
                    || collisionPoint.getY() == rect.getUpperLeft().getY() + rect.getHeight()) {
                //Hit has occurred!
                this.notifyHit(hitter);
                return new Velocity(dx, -dy);
            }
            //if in range for vertical hit
        }
        if (rect.getUpperLeft().getY() <= collisionPoint.getY()
                && rect.getUpperLeft().getY() + rect.getHeight() >= collisionPoint.getY()) {
            //if its exactly on one of the vertical lines
            if (collisionPoint.getX() == rect.getUpperLeft().getX()
                    || collisionPoint.getX() == rect.getUpperLeft().getX() + rect.getWidth()) {
                //Hit has occurred!
                this.notifyHit(hitter);
                return new Velocity(-dx, dy);
            }
        }
        return currentVelocity;
    }

    /**
     * draw the Block on the given DrawSurface.
     *
     * @param surface to draw on
     */
    @Override
    public void drawOn(DrawSurface surface) {
        this.getRect().drawRect(surface);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * add this Block to the given game (to its list of sprites and Collidables).
     *
     * @param g the game we are adding the Block in to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Remove this Block from the game.
     *
     * @param game we delete from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);

    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify to all the listener that hit has occurred.
     *
     * @param hitter the ball that hit
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Color getColor() {
        return this.getRect().getColor();
    }
}
