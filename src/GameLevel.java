import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class GameLevel implements Animation {
    private static final int SLEEP_TIME = 30;
    private final SpriteCollection sprites;
    private final GameEnvironment environment;

    private final Counter score;
    private final Counter blockCounter;

    private final Counter ballCounter;
    private final Color background;
    private final AnimationRunner runner;
    private boolean running;
    private final biuoop.KeyboardSensor keyboard;

    private LevelInformation level;

    /**
     * Constructor.
     *
     * @param level  a certain level
     * @param runner the Animation runner
     * @param ks     the sensor
     * @param score  the score
     */
    public GameLevel(LevelInformation level, KeyboardSensor ks, AnimationRunner runner,
                     Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.runner = runner;
        this.blockCounter = new Counter();
        this.ballCounter = new Counter();
        this.score = score;
        this.background = level.getBackground().getColor();
        this.running = false;
        this.keyboard = ks;
        this.level = level;
    }

    /**
     * Getter for the score.
     *
     * @return the current score
     */

    public Counter getScore() {
        return this.score;
    }

    /**
     * Getter for the GUI.
     *
     * @return the gui
     */
    public GUI getGui() {
        return this.runner.getGui();
    }

    /**
     * Getter for the level information.
     *
     * @return the information
     */
    public LevelInformation getLevel() {
        return level;
    }

    /**
     * add the given collidable to the game's environment.
     *
     * @param c the collidable we want to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add sprite to the game's list of sprites.
     *
     * @param s the sprite we are adding.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {
        //Score indicator
        ScoreIndicator indicator = new ScoreIndicator(this.score);
        this.addSprite(indicator);

        //Listeners
        BlockRemover blockRemover = new BlockRemover(this, this.blockCounter);
        ScoreTrackingListener stl = new ScoreTrackingListener(this.score);
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);

        /*The next block of code creates 4 margin of the screen */
        Block upMargin, downMargin, leftMargin, rightMargin;
        upMargin = new Block(new Rectangle(new Point(0, 25), 800, 25, Color.gray));
        upMargin.addToGame(this);
        downMargin = new Block(new Rectangle(new Point(0, 598), 800, 2, Color.gray));
        downMargin.addToGame(this);
        downMargin.addHitListener(ballRemover);
        leftMargin = new Block(new Rectangle(new Point(0, 50), 25, 600, Color.gray));
        leftMargin.addToGame(this);
        rightMargin = new Block(new Rectangle(new Point(775, 50), 25, 550, Color.gray));
        rightMargin.addToGame(this);

        //Adding blocks
        for (Block b : this.level.blocks()) {
            b.addHitListener(blockRemover);
            b.addHitListener(stl);
            this.addCollidable(b);
            this.addSprite(b);
        }
        this.blockCounter.increase(this.level.numberOfBlocksToRemove());

        //Adding balls
        Point center = new Point(400, 530);
        for (int i = 0; i < this.level.numberOfBalls(); i++) {
            Ball tmp = new Ball(center, 5, Color.WHITE);
            tmp.setVelocity(this.level.initialBallVelocities().get(i));
            tmp.setGame(this.environment);
            tmp.addToGame(this);
        }
        this.ballCounter.increase(this.level.numberOfBalls());


        /*add the Paddle*/
        KeyboardSensor key = this.runner.getGui().getKeyboardSensor();
        double x = 800 - this.level.paddleWidth();
        Paddle paddle = new Paddle(key, new Rectangle(new Point(x / 2, 550), this.level.paddleWidth(),
                20, Color.ORANGE),
                this.level.paddleSpeed());
        paddle.setRange(25, 775);
        paddle.addToGame(this);

    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.level.getBackground();
        this.runner.run(new CountdownAnimation(3, 3, this.sprites, this.level.getBackground()));
        this.running = true;
        Sleeper s = new Sleeper();
        while (!this.shouldStop()) {
            DrawSurface d = this.runner.getGui().getDrawSurface();
            this.runner.run(this);
            s.sleepFor(SLEEP_TIME);
        }
    }

    /**
     * Remove the given Object from the Collidable list.
     *
     * @param c The object we want to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * Remove the given Object from the Sprite list.
     *
     * @param s The object we want to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * draw the whole game on a surface.
     *
     * @param d the surface we draw on
     */
    public void drawAllGame(DrawSurface d) {
        this.level.getBackground().drawOn(d);
        this.environment.drawAllCollidable(d);
        this.sprites.drawAllOn(d);
        d.drawText(500, 24, "Level Name: " + this.level.levelName(), 18);
        d.drawText(100, 24, "Lives: " + this.ballCounter.getValue(), 18);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.drawAllGame(d);
        this.sprites.notifyAllTimePassed();
        if (this.blockCounter.getValue() == 0) {
            this.score.increase(100);
            System.out.println("The level has ended, you won with: " + this.score.getValue() + " points!");
            this.running = false;
        }
        if (this.ballCounter.getValue() == 0) {
            System.out.println("All your balls died, you lost... :(");
            this.running = false;
        }
        Animation pause = new PauseScreen(this.keyboard);
        Animation pauseKey = new KeyPressStoppableAnimation("p", pause);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(pauseKey);
        }
    }

    /**
     * Setter for the variable.
     *
     * @param running variable you want to set
     */

    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Getter for the counter.
     *
     * @return the ball counter
     */

    public Counter getBallCounter() {
        return ballCounter;
    }

    @Override
    public KeyboardSensor getKeySensor() {
        return this.keyboard;
    }
}
