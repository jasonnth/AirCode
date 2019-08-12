package com.airbnb.p027n2.components.onboarding_overlay;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.support.p000v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import com.airbnb.n2.R;
import p334uk.p335co.deanwild.materialshowcaseview.MaterialShowcaseView;
import p334uk.p335co.deanwild.materialshowcaseview.MaterialShowcaseView.Builder;
import p334uk.p335co.deanwild.materialshowcaseview.shape.Shape;

/* renamed from: com.airbnb.n2.components.onboarding_overlay.OnboardingOverlayView */
public class OnboardingOverlayView extends MaterialShowcaseView {
    private final PorterDuffXfermode OVERLAY_PORTER_DUFF_XFERMODE = new PorterDuffXfermode(Mode.OVERLAY);
    private boolean gestureIsForTargetView;
    private int strokeInnerColor;
    private int strokeOuterColor;
    private int targetStrokeWidth;

    /* renamed from: com.airbnb.n2.components.onboarding_overlay.OnboardingOverlayView$OnboardingOverlayViewBuilder */
    public static class OnboardingOverlayViewBuilder extends Builder {
        private OnboardingOverlayView onboardingOverlayView;

        public OnboardingOverlayViewBuilder(Activity activity) {
            super(activity);
        }

        /* access modifiers changed from: protected */
        public void init(Activity activity) {
            this.showcaseView = new OnboardingOverlayView(activity);
            this.onboardingOverlayView = (OnboardingOverlayView) this.showcaseView;
        }

        public OnboardingOverlayViewBuilder setTargetStrokeWidth(int width) {
            this.onboardingOverlayView.setTargetStrokeWidth(width);
            return this;
        }
    }

    public OnboardingOverlayView(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.strokeOuterColor = ContextCompat.getColor(getContext(), R.color.n2_babu_dark);
        this.strokeInnerColor = ContextCompat.getColor(getContext(), R.color.n2_transparent);
    }

    public int getLayoutFile() {
        return R.layout.n2_onboarding_overlay_view;
    }

    public void setTargetStrokeWidth(int width) {
        this.targetStrokeWidth = width;
    }

    /* access modifiers changed from: protected */
    public void drawStroke(Canvas canvas, Paint eraser, Shape shape, int xPosition, int yPosition, int shapePadding) {
        eraser.setColor(this.strokeOuterColor);
        eraser.setXfermode(this.OVERLAY_PORTER_DUFF_XFERMODE);
        shape.draw(canvas, eraser, xPosition, yPosition, shapePadding);
        eraser.setColor(this.strokeInnerColor);
        eraser.setXfermode(CLEAR_PORTER_DUFF_XFER_MODE);
        shape.draw(canvas, eraser, xPosition, yPosition, shapePadding - this.targetStrokeWidth);
    }

    public boolean onTouch(View v, MotionEvent event) {
        boolean isActiveActionOnTargetView;
        switch (event.getAction()) {
            case 0:
                this.gestureIsForTargetView = isTouchOnTargetView(event);
                break;
            case 1:
                if (!isTouchOnTargetView(event) || !this.gestureIsForTargetView) {
                    isActiveActionOnTargetView = false;
                } else {
                    isActiveActionOnTargetView = true;
                }
                this.gestureIsForTargetView = false;
                if (isActiveActionOnTargetView) {
                    hide();
                    return false;
                }
                break;
        }
        return super.onTouch(v, event);
    }

    /* access modifiers changed from: protected */
    public int getContentBoxViewId() {
        return R.id.content_wrapper;
    }

    /* access modifiers changed from: protected */
    public int getTitleViewId() {
        return R.id.text;
    }

    /* access modifiers changed from: protected */
    public int getButtonViewId() {
        return R.id.button;
    }
}
