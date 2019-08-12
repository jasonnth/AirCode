package com.jumio.commons.utils;

import android.graphics.Path;
import android.graphics.Rect;

public class DrawingUtil {
    public static Path createRectWithRoundedCornersAsPath(Rect bounds, int radiusTL, int radiusTR, int radiusBR, int radiusBL) {
        int l = bounds.left;
        int r = bounds.right;
        int t = bounds.top;
        int b = bounds.bottom;
        Path path = new Path();
        if (radiusTL == 0) {
            path.moveTo((float) l, (float) t);
        } else {
            path.moveTo((float) (l + radiusTL), (float) t);
        }
        if (radiusTR == 0) {
            path.lineTo((float) r, (float) t);
        } else {
            path.lineTo((float) (r - radiusTL), (float) t);
            path.quadTo((float) r, (float) t, (float) r, (float) (t + radiusTR));
        }
        if (radiusBR == 0) {
            path.lineTo((float) r, (float) b);
        } else {
            path.lineTo((float) r, (float) (b - radiusBR));
            path.quadTo((float) r, (float) b, (float) (r - radiusBR), (float) b);
        }
        if (radiusBL == 0) {
            path.lineTo((float) l, (float) b);
        } else {
            path.lineTo((float) (l + radiusBL), (float) b);
            path.quadTo((float) l, (float) b, (float) l, (float) (b - radiusBL));
        }
        if (radiusTL == 0) {
            path.lineTo((float) l, (float) t);
        } else {
            path.lineTo((float) l, (float) (t + radiusTL));
            path.quadTo((float) l, (float) t, (float) (l + radiusTL), (float) t);
        }
        path.close();
        return path;
    }
}
