package bowlingapp.bowler;


public class PerfectStrikeAction implements BowlingAction {

    //always returns all standing pin count
    @Override
    public int generateDownedPinsCount(String input, int standingPins) {
        return standingPins;
    }

}
