package bowlingapp;

import bowlingapp.bowler.PerfectStrikeAction;
import bowlingapp.bowler.RandomisedAction;
import bowlingapp.model.Game;
import org.junit.Assert;
import org.junit.Test;

public class BowlingAppIT {

    @Test
    public void runAppWithPerfectBowler() {
        Game game = new Game(10);

        PerfectStrikeAction perfectStrikeBowler = new PerfectStrikeAction();

        while (!game.isGameCompleted()) {
            game.bowl(perfectStrikeBowler.generateDownedPinsCount("abc", 10));
        }
        Assert.assertEquals(300, game.getPoints());
    }

    @Test
    public void runAppWithMediumBowler() {
        Game game = new Game(10);

        while (!game.isGameCompleted()) {
            game.bowl(5);
        }
        Assert.assertEquals(100, game.getPoints());
    }

    @Test
    public void runAppWithZeroBowler() {
        Game game = new Game(10);

        while (!game.isGameCompleted()) {
            game.bowl(0);
        }
        Assert.assertEquals(0, game.getPoints());
    }

    @Test
    public void runAppWithRandomisedAction() {
        for (int i = 0; i < 10; i++) {
            runGame();
        }
    }

    private void runGame() {
        Game game = new Game(10);

        RandomisedAction randomisedAction = new RandomisedAction();

        while (!game.isGameCompleted()) {
            System.out.println("First bowl");
            int remainingPins = game.bowl(randomisedAction.generateDownedPinsCount("", 10));

            if (remainingPins > 0) {
                System.out.println("Second bowl");
                game.bowl(randomisedAction.generateDownedPinsCount("", remainingPins));
            }
        }

        Assert.assertTrue(game.getPoints() >= 0);
        Assert.assertTrue(game.getPoints() <= 300);
    }

}
