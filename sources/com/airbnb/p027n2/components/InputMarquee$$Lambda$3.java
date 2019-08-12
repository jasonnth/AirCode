package com.airbnb.p027n2.components;

import android.graphics.Rect;
import android.view.TouchDelegate;

/* renamed from: com.airbnb.n2.components.InputMarquee$$Lambda$3 */
final /* synthetic */ class InputMarquee$$Lambda$3 implements Runnable {
    private final InputMarquee arg$1;

    private InputMarquee$$Lambda$3(InputMarquee inputMarquee) {
        this.arg$1 = inputMarquee;
    }

    public static Runnable lambdaFactory$(InputMarquee inputMarquee) {
        return new InputMarquee$$Lambda$3(inputMarquee);
    }

    public void run() {
        this.arg$1.setTouchDelegate(new TouchDelegate(new Rect(0, 0, this.arg$1.getWidth(), this.arg$1.getHeight()), this.arg$1.editTextView));
    }
}
