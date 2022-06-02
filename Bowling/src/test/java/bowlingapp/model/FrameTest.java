package bowlingapp.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class FrameTest {


    @Test(expected = IllegalStateException.class)
    public void testExceptionThrownWHenPinDownCountIsTooHigh() {
        Frame testClazz = new Frame();
        testClazz.pinsKnockedDown(10);
        testClazz.pinsKnockedDown(10);
    }

    @Test(expected = IllegalStateException.class)
    public void testMaxBowlsPerFrame() {
        Frame testClazz = new Frame();
        testClazz.pinsKnockedDown(0);
        testClazz.pinsKnockedDown(0);
        testClazz.pinsKnockedDown(0);
    }

    @Test
    public void testInitialFrameIsNotCompleted() {
        Frame testClazz = new Frame();

        assertFalse(testClazz.isFrameCompleted());
        Assert.assertEquals(FrameState.NOT_STARTED , testClazz.getFrameState());
    }

    @Test
    public void testAfterFirstBowl() {
        Frame testClazz = new Frame();
        testClazz.pinsKnockedDown(5);

        assertFalse(testClazz.isFrameCompleted());
        assertEquals(FrameState.OPEN_FRAME , testClazz.getFrameState());
    }

    @Test
    public void testAfterSecondBowl() {
        Frame testClazz = new Frame();

        testClazz.pinsKnockedDown(0);
        testClazz.pinsKnockedDown(0);

        assertTrue(testClazz.isFrameCompleted());
        assertEquals(FrameState.OPEN_FRAME , testClazz.getFrameState());
        assertEquals(0, testClazz.getPoints());
    }

    @Test
    public void testAfterSpare() {
        Frame testClazz = new Frame();

        testClazz.pinsKnockedDown(5);
        testClazz.pinsKnockedDown(5);

        assertTrue(testClazz.isFrameCompleted());
        assertEquals(FrameState.SPARE, testClazz.getFrameState());
        assertEquals(10, testClazz.getPoints());
    }


    @Test
    public void testStrike() {
        Frame testClazz = new Frame();

        testClazz.pinsKnockedDown(10);

        assertTrue(testClazz.isFrameCompleted());
        assertEquals(FrameState.STRIKE , testClazz.getFrameState());
        assertEquals(20, testClazz.getPoints());
    }

}
