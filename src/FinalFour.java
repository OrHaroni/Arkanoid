import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class FinalFour implements LevelInformation {
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 30;
    private static final int BLOCK_START_X = 725;

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lv = new ArrayList<>();
        lv.add(Velocity.fromAngleAndSpeed(315, 5));
        lv.add(Velocity.fromAngleAndSpeed(225, 5));
        lv.add(Velocity.fromAngleAndSpeed(270, 5));
        return lv;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        List<Sprite> shapes = new ArrayList<>();

        //Make the left Cloud
        //Make 10 Lines
        double x = 95;
        double y = 400;
        for (int i = 0; i < 100; i += 10) {
            shapes.add(new Line(x + i, y, x - 10 + i, y + 200, Color.WHITE));
        }

        //Make 5 Filled Circles
        x = 100;
        y = 375;
        Point middle = new Point(x, y);
        shapes.add(new FilledCircle(middle, 20,
                new Color(215, 215, 215)));
        middle = new Point(x + 20, y + 25);
        shapes.add(new FilledCircle(middle, 25,
                new Color(215, 215, 215)));
        middle = new Point(x + 35, y - 10);
        shapes.add(new FilledCircle(middle, 25,
                new Color(203, 203, 203)));
        middle = new Point(x + 50, y + 35);
        shapes.add(new FilledCircle(middle, 20,
                new Color(175, 175, 175)));
        middle = new Point(x + 70, y + 5);
        shapes.add(new FilledCircle(middle, 30,
                new Color(175, 175, 175)));

        //Make the right Cloud
        //Make 10 Lines
        x = 545;
        y = 425;
        for (int i = 0; i < 100; i += 10) {
            shapes.add(new Line(x + i, y, x - 10 + i, y + 200, Color.WHITE));
        }

        //Make 5 Filled Circles
        x = 550;
        middle = new Point(x, y);
        shapes.add(new FilledCircle(middle, 20,
                new Color(215, 215, 215)));
        middle = new Point(x + 20, y + 25);
        shapes.add(new FilledCircle(middle, 25,
                new Color(215, 215, 215)));
        middle = new Point(x + 35, y - 10);
        shapes.add(new FilledCircle(middle, 25,
                new Color(203, 203, 203)));
        middle = new Point(x + 50, y + 35);
        shapes.add(new FilledCircle(middle, 20,
                new Color(175, 175, 175)));
        middle = new Point(x + 70, y + 5);
        shapes.add(new FilledCircle(middle, 30,
                new Color(175, 175, 175)));


        return new Background(shapes, new Color(30, 152, 231));
    }

    @Override
    public List<Block> blocks() {
        List<Block> lb = new ArrayList<>();
        int y = 100;
        int number = 15;
        //Adding 15 gray blocks
        addMultipleBlocks(number, y, Color.GRAY, lb);
        y = y + BLOCK_HEIGHT;
        //Adding 15 red blocks
        addMultipleBlocks(number, y, Color.RED, lb);
        y = y + BLOCK_HEIGHT;
        //Adding 15 yellow blocks
        addMultipleBlocks(number, y, Color.YELLOW, lb);
        y = y + BLOCK_HEIGHT;
        //Adding 15 green blocks
        addMultipleBlocks(number, y, Color.GREEN, lb);
        y = y + BLOCK_HEIGHT;
        //Adding 15 white blocks
        addMultipleBlocks(number, y, Color.WHITE, lb);
        y = y + BLOCK_HEIGHT;
        //Adding 15 pink blocks
        addMultipleBlocks(number, y, Color.PINK, lb);
        y = y + BLOCK_HEIGHT;
        //Adding 15 cyan blocks
        addMultipleBlocks(number, y, Color.CYAN, lb);
        return lb;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }

    /**
     * adding number of blocks together.
     *
     * @param num    number of blocks to add.
     * @param y      starting y
     * @param color  of the block
     * @param blocks the list we are adding the blocks into
     */
    public void addMultipleBlocks(int num, int y, Color color, List<Block> blocks) {
        for (int i = 0; i < num; i++) {
            Block b = new Block(new Rectangle(new Point(BLOCK_START_X - (i * BLOCK_WIDTH), y), BLOCK_WIDTH,
                    BLOCK_HEIGHT, color));
            blocks.add(b);
        }
    }

}
