import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 206656290 Or Haroni or.haroni@gmail.com
 * @version 18.0.1.1
 * @since 2022-04-22
 */
public class Ass6Game {

    private static final int ONE = 49;
    private static final int FOUR = 52;

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
        int[] numbers = Ass6Game.changeToInt(args);
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui, 30);
        KeyboardSensor ks = gui.getKeyboardSensor();
        DirectHit level1 = new DirectHit();
        WideEasy level2 = new WideEasy();
        GreenThree level3 = new GreenThree();
        FinalFour level4 = new FinalFour();
        List<LevelInformation> levels = new ArrayList<>();
        //if there is no arguments that are numbers in range.
        if (numbers.length == 0) {
            levels.add(level1);
            levels.add(level2);
            levels.add(level3);
            levels.add(level4);
        } else {
            //Adding by sort of arguments inputs
            for (int i : numbers) {
                if (i == 1) {
                    levels.add(level1);
                } else if (i == 2) {
                    levels.add(level2);
                } else if (i == 3) {
                    levels.add(level3);
                } else if (i == 4) {
                    levels.add(level4);
                }
            }
        }
        GameFlow play = new GameFlow(ar, ks);
        play.runLevels(levels);
    }

    /**
     * gets the arguments array and returns array with only number between 1 and 4.
     * @param args arguments from the main.
     * @return same args but with only number between 1 and 4
     */
    public static int[] changeToInt(String[] args) {
        int[] numbers = new int[args.length];
        int index = 0;
        for (String s : args) {
            if (s.length() == 1) {
                if (Ass6Game.isNumber(s)) {
                    numbers[index] = Integer.parseInt(s);
                    index++;
                }
            }
        }
        int[] fixed = new int[index];
        for (int i = 0; i < index; i++) {
            fixed[i] = numbers[i];
        }
        return fixed;
    }

    /**
     * @param s the string we are checking
     * @return if the number in between 1 and 4 or not.
     */
    private static boolean isNumber(String s) {
        char c = s.charAt(0);
        return (c >= ONE) && (c <= FOUR);
    }
}
