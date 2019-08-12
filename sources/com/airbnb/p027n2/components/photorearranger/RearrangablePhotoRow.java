package com.airbnb.p027n2.components.photorearranger;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.percent.PercentFrameLayout;
import android.util.AttributeSet;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.photorearranger.RearrangablePhotoRow */
public class RearrangablePhotoRow extends PercentFrameLayout {
    @BindView
    AirImageView errorIconView;
    @BindView
    AirImageView imageView;
    private final ValueAnimator labelAnimator;
    @BindView
    AirTextView labelView;
    @BindView
    LoadingView loadingView;
    private final ValueAnimator selectedAnimator;

    /* renamed from: com.airbnb.n2.components.photorearranger.RearrangablePhotoRow$State */
    public enum State {
        Normal,
        Sending,
        Failed
    }

    public RearrangablePhotoRow(Context context) {
        super(context);
        this.selectedAnimator = PhotoRearrangerAnimator.create(300, 0.0f, 1.0f, RearrangablePhotoRow$$Lambda$1.lambdaFactory$(this));
        this.labelAnimator = PhotoRearrangerAnimator.create(200, 0.0f, 1.0f, RearrangablePhotoRow$$Lambda$2.lambdaFactory$(this));
        init();
    }

    public RearrangablePhotoRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.selectedAnimator = PhotoRearrangerAnimator.create(300, 0.0f, 1.0f, RearrangablePhotoRow$$Lambda$3.lambdaFactory$(this));
        this.labelAnimator = PhotoRearrangerAnimator.create(200, 0.0f, 1.0f, RearrangablePhotoRow$$Lambda$4.lambdaFactory$(this));
        init();
    }

    public RearrangablePhotoRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.selectedAnimator = PhotoRearrangerAnimator.create(300, 0.0f, 1.0f, RearrangablePhotoRow$$Lambda$5.lambdaFactory$(this));
        this.labelAnimator = PhotoRearrangerAnimator.create(200, 0.0f, 1.0f, RearrangablePhotoRow$$Lambda$6.lambdaFactory$(this));
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_rearrangable_photo, this);
        ButterKnife.bind((View) this);
    }

    public void setImage(Image image) {
        this.imageView.setImage(image);
    }

    public void setLabelRes(int stringRes) {
        if (stringRes == 0) {
            this.labelView.setText("");
        } else {
            this.labelView.setText(stringRes);
        }
    }

    public void setLabelVisible(boolean visible) {
        float targetAlpha = visible ? 1.0f : 0.0f;
        if (!this.labelAnimator.isRunning() && this.labelView.getAlpha() == targetAlpha) {
            return;
        }
        if (visible) {
            this.labelAnimator.start();
        } else {
            this.labelAnimator.reverse();
        }
    }

    public void setState(State state) {
        boolean z;
        boolean z2 = true;
        this.imageView.setAlpha(state == State.Normal ? 1.0f : 0.4f);
        AirImageView airImageView = this.errorIconView;
        if (state == State.Failed) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setVisibleIf(airImageView, z);
        LoadingView loadingView2 = this.loadingView;
        if (state != State.Sending) {
            z2 = false;
        }
        ViewLibUtils.setVisibleIf(loadingView2, z2);
    }

    public void onDrag() {
        this.selectedAnimator.start();
    }

    public void onDrop() {
        this.selectedAnimator.reverse();
    }

    public void endAnimations() {
        if (this.selectedAnimator.isRunning()) {
            this.selectedAnimator.end();
        }
        if (this.labelAnimator.isRunning()) {
            this.labelAnimator.end();
        }
    }

    /* access modifiers changed from: private */
    public void updateAnimation(float progress) {
        float scale = 1.0f + (0.08f * progress);
        setAlpha(1.0f - (0.12f * progress));
        setScaleX(scale);
        setScaleY(scale);
    }

    /* access modifiers changed from: private */
    public void setLabelAlpha(float alpha) {
        this.labelView.setAlpha(alpha);
    }
}
