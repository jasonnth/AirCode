package android.support.design.widget;

import android.annotation.TargetApi;
import android.graphics.Outline;

@TargetApi(21)
class CircularBorderDrawableLollipop extends CircularBorderDrawable {
    CircularBorderDrawableLollipop() {
    }

    public void getOutline(Outline outline) {
        copyBounds(this.mRect);
        outline.setOval(this.mRect);
    }
}
