package bowlingapp.model;


import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testGamesIsCompletedAfterFramesCompleted() {
        int framesPerGames = 3;

        Game game = new Game(framesPerGames);

        for(int i=0; i < framesPerGames; i++) {
            assertFalse(game.isGameCompleted());
            game.bowl(10);
        }

        assertTrue(game.isGameCompleted());
    }

    @Test(expected = IllegalStateException.class)
    public void testWhenTooManyStrikes() {
        Game game = new Game(3);
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
        game.bowl(10);
    }

    @Test(expected = IllegalStateException.class)
    public void testWhenTooManyScores() {
        int framesPerGames = 3;
        Game game = new Game(framesPerGames);
        for(int i=0; i < framesPerGames; i++) {
            game.bowl(5);
            game.bowl(5);
        }
        game.bowl(5);   //extra bowl
    }


    @Test
    public void testLowScore() {
        Game game = new Game(10);
        while (!game.isGameCompleted()) {
            game.bowl(3);
        }
        assertEquals(60, game.getPoints());
    }

    @Test
    public void testAllScores() {
        Game game = new Game(10);
        while (!game.isGameCompleted()) {
            game.bowl(5);
            game.bowl(5);
        }
        assertEquals(100, game.getPoints());
    }

    @Test
    public void testMaxScore() {
        Game game = new Game(10);
        while (!game.isGameCompleted()) {
            game.bowl(10);
        }
        assertEquals(300, game.getPoints());
    }

}
