import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class WideEasy implements LevelInformation {


    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lv = new ArrayList<>();
        int angle = 20;
        for (int i = 0; i < this.numberOfBalls() / 2; i++) {
            Velocity tmp = Velocity.fromAngleAndSpeed(angle, 3);
            lv.add(tmp);
            angle += 15;
        }
        angle += 15;
        for (int i = 0; i < this.numberOfBalls() / 2; i++) {
            Velocity tmp = Velocity.fromAngleAndSpeed(angle, 3);
            lv.add(tmp);
            angle += 15;
        }
        return lv;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        List<Sprite> shapes = new ArrayList<>();
        Point middle = new Point(150, 150);
        double changer = 0;
        //A LOT of lines!
        while (changer <= 700) {
            shapes.add(new Line(middle, new Point(changer, 250), new Color(211, 222, 76)));
            changer += 7;
        }

        //3 filled circles
        Color tmp = new Color(211, 222, 76);
        shapes.add(new FilledCircle(middle, 60, tmp));
        tmp = new Color(232, 223, 68);
        shapes.add(new FilledCircle(middle, 50, tmp));
        tmp = new Color(208, 202, 19);
        shapes.add(new FilledCircle(middle, 40, tmp));


        return new Background(shapes, Color.WHITE);

    }

    @Override
    public List<Block> blocks() {
        List<Block> lb = new ArrayList<>();
        double x = 25;
        //2 red blocks
        for (int i = 0; i < 2; i++) {
            lb.add(new Block(new Rectangle(new Point(x, 250), 50, 30, Color.RED)));
            x += 50;
        }
        //2 orange blocks
        for (int i = 0; i < 2; i++) {
            lb.add(new Block(new Rectangle(new Point(x, 250), 50, 30, Color.ORANGE)));
            x += 50;
        }
        //2 yellow blocks
        for (int i = 0; i < 2; i++) {
            lb.add(new Block(new Rectangle(new Point(x, 250), 50, 30, Color.YELLOW)));
            x += 50;
        }
        //3 green blocks
        for (int i = 0; i < 3; i++) {
            lb.add(new Block(new Rectangle(new Point(x, 250), 50, 30, Color.GREEN)));
            x += 50;
        }
        //2 blue blocks
        for (int i = 0; i < 2; i++) {
            lb.add(new Block(new Rectangle(new Point(x, 250), 50, 30, Color.BLUE)));
            x += 50;
        }
        //2 pink blocks
        for (int i = 0; i < 2; i++) {
            lb.add(new Block(new Rectangle(new Point(x, 250), 50, 30, Color.PINK)));
            x += 50;
        }
        //2 cyan blocks
        for (int i = 0; i < 2; i++) {
            lb.add(new Block(new Rectangle(new Point(x, 250), 50, 30, Color.CYAN)));
            x += 50;
        }
        return lb;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
