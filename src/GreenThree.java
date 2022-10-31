import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class GreenThree implements LevelInformation {
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 30;
    private static final int BLOCK_START_X = 725;

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lv = new ArrayList<>();
        lv.add(Velocity.fromAngleAndSpeed(315, 5));
        lv.add(Velocity.fromAngleAndSpeed(225, 5));
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
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        List<Sprite> shapes = new ArrayList<>();
        //Adding 3 rectangle
        Rectangle tmp = new Rectangle(new Point(75, 425), 110, 175,
                new Color(40, 40, 40));
        shapes.add(tmp);
        tmp = new Rectangle(new Point(115, 365), 30, 60,
                new Color(60, 60, 60));
        shapes.add(tmp);
        tmp = new Rectangle(new Point(125, 165), 10, 200,
                new Color(80, 80, 80));
        shapes.add(tmp);


        //Adding little white rectangles
        double x = 85;
        int xDiff = 20;
        double y = 440;
        int yDiff = 35;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tmp = new Rectangle(new Point(x + (xDiff * j), y + (yDiff * i)),
                        10, 25, Color.white);
                shapes.add(tmp);
            }
        }

        //Adding 3 Circles
        Point middle = new Point(130, 155);
        shapes.add(new FilledCircle(middle, 12,
                new Color(227, 194, 109)));
        shapes.add(new FilledCircle(middle, 8,
                new Color(243, 47, 47)));
        shapes.add(new FilledCircle(middle, 3, Color.WHITE));


        return new Background(shapes, new Color(57, 114, 57));
    }

    @Override
    public List<Block> blocks() {
        List<Block> lb = new ArrayList<>();
        int y = 150;
        int number = 10;
        //Adding 10 gray blocks
        addMultipleBlocks(number--, y, Color.GRAY, lb);
        y = y + BLOCK_HEIGHT;
        //Adding 9 red blocks
        addMultipleBlocks(number--, y, Color.RED, lb);
        y = y + BLOCK_HEIGHT;
        //Adding 8 yellow blocks
        addMultipleBlocks(number--, y, Color.YELLOW, lb);
        y = y + BLOCK_HEIGHT;
        //Adding 7 blue blocks
        addMultipleBlocks(number--, y, Color.BLUE, lb);
        y = y + BLOCK_HEIGHT;
        //Adding 6 white blocks
        addMultipleBlocks(number, y, Color.WHITE, lb);
        return lb;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
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
