package bowlingapp.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    final private int frameCountPerGame;
    final private List<Frame> frames;

    private Frame currentFrame;

    public Game(int frameCount) {
        this.frameCountPerGame = frameCount;
        this.frames = new ArrayList<>();
        this.currentFrame = getNextFrame();
    }

    public boolean isGameCompleted() {
        if (frames.size() == frameCountPerGame) {
            return frames.get(frameCountPerGame -1 ).isFrameCompleted();
        }
        return false;
    }

    private Frame getNextFrame() {
        if(frames.size() < frameCountPerGame) {
            Frame newFrame = new Frame();
            frames.add(newFrame);
            return newFrame;
        }
        throw new IllegalStateException("");
    }

    public int bowl(int downed) {
        int standingPinCount = currentFrame.pinsKnockedDown(downed);
        System.out.println("Number of pins downed " + downed + ", remaining pins: " + standingPinCount);
        if(currentFrame.isFrameCompleted()) {
            if(!isGameCompleted()) {
                System.out.println("Moving to frame: " + (frames.size() + 1));
                currentFrame = getNextFrame();
            }
        }
        return standingPinCount;
    }

    public int getPoints() {
        int points = 0;
        boolean allFramesAreStrikes = true;

        for(Frame frame : frames) {
            points += frame.getPoints();
            if(frame.getFrameState() != FrameState.STRIKE) {
                allFramesAreStrikes = false;
            }
        }

        if(allFramesAreStrikes && isGameCompleted()) {
            return 300;
        }

        return points;
    }

}
