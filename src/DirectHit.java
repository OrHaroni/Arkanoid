import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */

public class DirectHit implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lv = new ArrayList<>();
        lv.add(Velocity.fromAngleAndSpeed(270, 3));
        return lv;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        List<Sprite> shapes = new ArrayList<>();
        Point middle = new Point(400, 165);
        int radius = 55;
        //Add 3 circles
        for (int i = 1; i <= 3; i++) {
            shapes.add(new Circle(middle, radius, Color.blue));
            radius += 30;
        }
        //Add 4 lines
        Line tmp = new Line(middle, new Point(middle.getX(), middle.getY() - 130), Color.BLUE);
        shapes.add(tmp);
        tmp = new Line(middle, new Point(middle.getX() + 150, middle.getY()), Color.BLUE);
        shapes.add(tmp);
        tmp = new Line(middle, new Point(middle.getX(), middle.getY() + 150), Color.BLUE);
        shapes.add(tmp);
        tmp = new Line(middle, new Point(middle.getX() - 150, middle.getY()), Color.BLUE);
        shapes.add(tmp);
        //Adding the one and only block in this game so using this method
        //and not using for loop.
        shapes.add(this.blocks().get(0));
        return new Background(shapes, Color.BLACK);
    }

    @Override
    public List<Block> blocks() {
        List<Block> lb = new ArrayList<>();
        lb.add(new Block(new Rectangle(new Point(385, 150), 30, 30, Color.RED)));
        return lb;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

}
