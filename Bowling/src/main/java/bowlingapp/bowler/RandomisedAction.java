package bowlingapp.bowler;

import java.util.Random;

public class RandomisedAction implements BowlingAction {

    Random random = new Random();

    public int generateDownedPinsCount(String input, int standingPins) {

        char[] chars = input.toCharArray();
        long randomSeed = 0;
        for(int n : chars) {
            randomSeed += n;
        }

        //random.setSeed(randomSeed);

        return Math.abs(random.nextInt()  % standingPins + 1);
    }

}
