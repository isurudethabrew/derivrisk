package bowlingapp.model;

public class Frame {

    private final int initialPinCount = 10;

    private int standingPinCount = initialPinCount;
    private int throwCount = 0;
    private FrameState frameState = FrameState.NOT_STARTED;

    public int pinsKnockedDown(int knockedDownCount) {
        throwCount++;
        standingPinCount = standingPinCount - knockedDownCount;

        if(throwCount > 2) {
            throw new IllegalStateException("Number of bowls per frame exceeded");
        }
        if(standingPinCount < 0) {
            throw new IllegalStateException("Number of pins standing is negative");
        }

        if(throwCount == 1 && knockedDownCount == initialPinCount) {
            frameState = FrameState.STRIKE;
        } else if(throwCount == 2 && standingPinCount == 0){
            frameState = FrameState.SPARE;
        } else {
            frameState = FrameState.OPEN_FRAME;
        }

        return standingPinCount;
    }

    public FrameState getFrameState() {
        return frameState;
    }

    public int getPoints() {
        if(frameState == FrameState.STRIKE) {
            return 20;
        }
        if(frameState == FrameState.SPARE) {
            return 10;
        }
        return initialPinCount - standingPinCount;
    }

    boolean isFrameCompleted() {
        return frameState == FrameState.STRIKE || throwCount == 2 ;
    }

}
