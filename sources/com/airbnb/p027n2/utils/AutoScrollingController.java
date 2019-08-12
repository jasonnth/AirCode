package com.airbnb.p027n2.utils;

import android.os.Handler;
import android.os.Looper;
import com.facebook.login.widget.ToolTipPopup;

/* renamed from: com.airbnb.n2.utils.AutoScrollingController */
public class AutoScrollingController {
    /* access modifiers changed from: private */
    public Handler automationHandler;
    /* access modifiers changed from: private */
    public boolean isCancelled;
    /* access modifiers changed from: private */
    public boolean isRunning;
    /* access modifiers changed from: private */
    public int itemIndex;
    /* access modifiers changed from: private */
    public final Target target;

    /* renamed from: com.airbnb.n2.utils.AutoScrollingController$Target */
    public interface Target {
        boolean scrollToPosition(int i);
    }

    public AutoScrollingController(Target target2) {
        this.target = target2;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.automationHandler = new Handler();
        }
    }

    public void start() {
        if (this.automationHandler != null) {
            this.isCancelled = false;
            this.isRunning = true;
            this.automationHandler.postDelayed(new Runnable() {
                public void run() {
                    AutoScrollingController.this.itemIndex = AutoScrollingController.this.itemIndex + 1;
                    if (!AutoScrollingController.this.target.scrollToPosition(AutoScrollingController.this.itemIndex) || AutoScrollingController.this.itemIndex >= 2 || AutoScrollingController.this.isCancelled) {
                        AutoScrollingController.this.isRunning = false;
                    } else {
                        AutoScrollingController.this.automationHandler.postDelayed(this, 3000);
                    }
                }
            }, ToolTipPopup.DEFAULT_POPUP_DISPLAY_TIME);
        }
    }

    public void cancel() {
        this.isCancelled = true;
        this.isRunning = false;
        if (this.automationHandler != null) {
            this.automationHandler.removeCallbacksAndMessages(null);
        }
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }
}
