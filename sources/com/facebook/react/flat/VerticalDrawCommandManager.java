package com.facebook.react.flat;

import android.util.SparseIntArray;
import java.util.Arrays;

final class VerticalDrawCommandManager extends ClippingDrawCommandManager {
    VerticalDrawCommandManager(FlatViewGroup flatViewGroup, DrawCommand[] drawCommands) {
        super(flatViewGroup, drawCommands);
    }

    /* access modifiers changed from: 0000 */
    public int commandStartIndex() {
        int start = Arrays.binarySearch(this.mCommandMaxBottom, (float) this.mClippingRect.top);
        return start < 0 ? start ^ -1 : start;
    }

    /* access modifiers changed from: 0000 */
    public int commandStopIndex(int start) {
        int stop = Arrays.binarySearch(this.mCommandMinTop, start, this.mCommandMinTop.length, (float) this.mClippingRect.bottom);
        return stop < 0 ? stop ^ -1 : stop;
    }

    /* access modifiers changed from: 0000 */
    public int regionStopIndex(float touchX, float touchY) {
        int stop = Arrays.binarySearch(this.mRegionMinTop, 1.0E-4f + touchY);
        return stop < 0 ? stop ^ -1 : stop;
    }

    /* access modifiers changed from: 0000 */
    public boolean regionAboveTouch(int index, float touchX, float touchY) {
        return this.mRegionMaxBottom[index] < touchY;
    }

    public static void fillMaxMinArrays(NodeRegion[] regions, float[] maxBottom, float[] minTop) {
        float last = 0.0f;
        for (int i = 0; i < regions.length; i++) {
            last = Math.max(last, regions[i].getTouchableBottom());
            maxBottom[i] = last;
        }
        for (int i2 = regions.length - 1; i2 >= 0; i2--) {
            last = Math.min(last, regions[i2].getTouchableTop());
            minTop[i2] = last;
        }
    }

    public static void fillMaxMinArrays(DrawCommand[] commands, float[] maxBottom, float[] minTop, SparseIntArray drawViewIndexMap) {
        float last;
        float last2 = 0.0f;
        for (int i = 0; i < commands.length; i++) {
            if (commands[i] instanceof DrawView) {
                DrawView drawView = commands[i];
                drawViewIndexMap.append(drawView.reactTag, i);
                last2 = Math.max(last2, drawView.mLogicalBottom);
            } else {
                last2 = Math.max(last2, commands[i].getBottom());
            }
            maxBottom[i] = last2;
        }
        for (int i2 = commands.length - 1; i2 >= 0; i2--) {
            if (commands[i2] instanceof DrawView) {
                last = Math.min(last2, commands[i2].mLogicalTop);
            } else {
                last = Math.min(last2, commands[i2].getTop());
            }
            minTop[i2] = last2;
        }
    }
}
