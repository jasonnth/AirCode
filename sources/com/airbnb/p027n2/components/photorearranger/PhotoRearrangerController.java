package com.airbnb.p027n2.components.photorearranger;

import android.animation.ValueAnimator;
import android.support.p002v7.widget.RecyclerView;

/* renamed from: com.airbnb.n2.components.photorearranger.PhotoRearrangerController */
public class PhotoRearrangerController {
    private final ValueAnimator editAnimator = PhotoRearrangerAnimator.create(300, 1.0f, 0.92f, PhotoRearrangerController$$Lambda$1.lambdaFactory$(this));
    private Mode mode;
    private final RearrangingCallback rearrangingCallback;
    private final RecyclerView recyclerView;

    /* renamed from: com.airbnb.n2.components.photorearranger.PhotoRearrangerController$Mode */
    public enum Mode {
        NonRearranging,
        Rearranging,
        RearrangingLocked
    }

    PhotoRearrangerController(RecyclerView recyclerView2, RearrangingCallback rearrangingCallback2, Mode initalMode) {
        this.recyclerView = recyclerView2;
        this.rearrangingCallback = rearrangingCallback2;
        setMode(initalMode);
        this.editAnimator.end();
    }

    public void setMode(Mode newMode) {
        if (this.mode != newMode) {
            if (this.mode == Mode.NonRearranging) {
                this.editAnimator.start();
            } else if (newMode == Mode.NonRearranging) {
                this.editAnimator.reverse();
            }
            this.rearrangingCallback.setEnabled(newMode == Mode.Rearranging);
            this.mode = newMode;
        }
    }

    public Mode getMode() {
        return this.mode;
    }

    /* access modifiers changed from: private */
    public void updateView(Float scale) {
        this.recyclerView.setScaleX(scale.floatValue());
        this.recyclerView.setScaleY(scale.floatValue());
    }
}
