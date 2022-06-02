package bowlingapp;

import java.util.Scanner;

import bowlingapp.bowler.RandomisedAction;
import bowlingapp.model.Game;

public class BowlingApp {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("StartingGame");

        RandomisedAction randomiser = new RandomisedAction();

        Game game = new Game(10);

        while (!game.isGameCompleted()) {
            System.out.println("Enter first bowl:");
            String input = scanner.nextLine();
            int remainingPins = game.bowl(randomiser.generateDownedPinsCount(input,10));
            if(remainingPins > 0 ) {
                System.out.println("Enter second bowl:");
                input = scanner.nextLine();
                game.bowl(randomiser.generateDownedPinsCount(input, remainingPins));
            }
            System.out.println("Running Score: " + game.getPoints());
        }

        System.out.println("*** Game over - Your final scored is " + game.getPoints());
    }

}
