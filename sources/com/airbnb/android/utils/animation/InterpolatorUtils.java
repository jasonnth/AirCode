package com.airbnb.android.utils.animation;

import android.view.animation.Interpolator;

public class InterpolatorUtils {
    private static final int MAX_ITERATIONS = 100;

    public static float binarySearch(Interpolator interpolator, float value, float epsilon) {
        float min = 0.0f;
        float max = 1.0f;
        float error = Float.MAX_VALUE;
        int iterations = 0;
        while (error > epsilon) {
            float testInput = (max + min) / 2.0f;
            float testValue = interpolator.getInterpolation(testInput);
            error = Math.abs(value - testValue);
            if (error < epsilon) {
                return testInput;
            }
            if (testValue > value) {
                max = testInput;
            } else {
                min = testInput;
            }
            iterations++;
            if (iterations >= 100) {
                throw new IllegalStateException("Unable to find " + value + " in " + 100 + " iterations. Error is " + error + ".");
            }
        }
        return -1.0f;
    }
}
