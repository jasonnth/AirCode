package com.miteksystems.misnap.events;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnFrameProcessedEvent {
    public static final int ALL_CHECKS = 4095;
    public static final int FRAME_BUSY_BACKGROUND_CHECK = 512;
    public static final int FRAME_CONFIDENCE_CHECK = 1;
    public static final int FRAME_CONFIDENCE_CHECK_PASSED = 1;
    public static final int FRAME_GLARE_CHECK = 1024;
    public static final int FRAME_HORIZONTAL_MINFILL_CHECK = 2;
    public static final int FRAME_HORIZONTAL_MINFILL_CHECK_PASSED = 2;
    public static final int FRAME_LOW_CONTRAST_CHECK = 256;
    public static final int FRAME_MAX_BRIGHTNESS_CHECK = 8;
    public static final int FRAME_MAX_SKEW_ANGLE_CHECK = 16;
    public static final int FRAME_MIN_BRIGHTNESS_CHECK = 4;
    public static final int FRAME_MIN_PADDING_CHECK = 64;
    public static final int FRAME_ROTATION_ANGLE_CHECK = 128;
    public static final int FRAME_SHARPNESS_CHECK = 32;
    public static final int FRAME_WRONG_DOCUMENT_CHECK = 2048;
    private static final List<Integer> WARNING_RANKS = Arrays.asList(new Integer[]{Integer.valueOf(1024), Integer.valueOf(512), Integer.valueOf(256), Integer.valueOf(2048), Integer.valueOf(1), Integer.valueOf(32), Integer.valueOf(128), Integer.valueOf(16), Integer.valueOf(2), Integer.valueOf(64), Integer.valueOf(8), Integer.valueOf(4)});
    public final int[][] fourCorners;
    public final int frameChecksPassed;
    public final int[][] glareRect;

    public OnFrameProcessedEvent(int frameChecksPassed2, int[][] fourCorners2, int[][] gRect) {
        this.frameChecksPassed = frameChecksPassed2;
        this.fourCorners = fourCorners2;
        this.glareRect = gRect;
    }

    public OnFrameProcessedEvent(int frameChecksPassed2, int[][] fourCorners2) {
        this(frameChecksPassed2, fourCorners2, (int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 2}));
    }

    public boolean didFrameCheckPass(int frameCheck) {
        return (this.frameChecksPassed & frameCheck) == frameCheck;
    }

    public boolean didFrameCheckFail(int frameCheck) {
        return (this.frameChecksPassed & frameCheck) != frameCheck;
    }

    public int getFrameChecksFailed() {
        return 4095 - this.frameChecksPassed;
    }

    public static List<Integer> getRankedWarnings(int warningCodes) {
        List<Integer> rankedWarnings = new ArrayList<>();
        for (Integer intValue : WARNING_RANKS) {
            int warning = intValue.intValue();
            if ((warning & warningCodes) == warning) {
                rankedWarnings.add(Integer.valueOf(warning));
            }
        }
        return rankedWarnings;
    }
}
