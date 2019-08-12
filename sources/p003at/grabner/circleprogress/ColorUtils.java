package p003at.grabner.circleprogress;

import android.graphics.Color;

/* renamed from: at.grabner.circleprogress.ColorUtils */
public class ColorUtils {
    public static int getRGBGradient(int i, int i2, float f) {
        int[] iArr = {interpolate((float) Color.red(i), (float) Color.red(i2), f), interpolate((float) Color.green(i), (float) Color.green(i2), f), interpolate((float) Color.blue(i), (float) Color.blue(i2), f)};
        return Color.argb(255, iArr[0], iArr[1], iArr[2]);
    }

    private static int interpolate(float f, float f2, float f3) {
        return Math.round((f * f3) + ((1.0f - f3) * f2));
    }
}
