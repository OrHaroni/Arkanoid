import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class GameEnvironment {
    private final java.util.List<Collidable> collidables;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /** Getter for the list of collidable.
         * @return the list
         */
    public List<Collidable> getCollidables() {
        return collidables;
    }

    /**
     * draw all rectangles.
     *
     * @param d the surface we draw on.
     */
    public void drawAllCollidable(DrawSurface d) {
        for (Collidable c : this.collidables) {
            c.getCollisionRectangle().drawRect(d);
        }
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c the collidable we want to add.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * return the collision info of the closest Collidable.
     *
     * @param trajectory the line of the ball's ,movement.
     * @return the information about the closest collision that is going to occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closestCollidable = null;
        Point tmp;
        /*This foreach checks if we hit a collidable and then
        * if this hit is the closest from all the collidable hits */
        for (Collidable c : this.collidables) {
            Rectangle rect = c.getCollisionRectangle();
            //checking if there are intersection points.
            if (rect.intersectionPoints(trajectory).size() != 0) {
                tmp = trajectory.closestIntersectionToStartOfLine(rect);
                //both null, first insert of collidable
                if (closestPoint == null && closestCollidable == null) {
                    closestPoint = tmp;
                    closestCollidable = c;
                    //checking if the new point closer than closest
                } else if (trajectory.getStart().distance(closestPoint) > trajectory.getStart().distance(tmp)) {
                    closestPoint = tmp;
                    closestCollidable = c;
                }
            }
        }
        //nothing changed, no collidable points
        if (closestPoint == null && closestCollidable == null) {
            return null;
        }
        //else return the closest collision info.
        return new CollisionInfo(closestPoint, closestCollidable);

    }

    /**
     * Short one line description.
     * <p>
     * Longer description. If there were any, it would be here.
     * </p>
     * And even more explanations to follow in consecutive
     * paragraphs separated by HTML paragraph breaks.
     *
     * @param args Description text text text.
     */
    public static void main(String[] args) {


    }
}
