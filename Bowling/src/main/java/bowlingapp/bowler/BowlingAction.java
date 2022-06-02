package bowlingapp.bowler;


public interface BowlingAction {

     // Given the number of standing pins, this method will return the of pins knocked down
    int generateDownedPinsCount(String input, int standingPins);

}
