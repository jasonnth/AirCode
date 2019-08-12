package com.airbnb.p027n2.components.photorearranger;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;

/* renamed from: com.airbnb.n2.components.photorearranger.RearrangableLabeledPhotoCell */
public class RearrangableLabeledPhotoCell extends PercentRelativeLayout {
    @BindView
    AirImageView imageView;
    @BindView
    AirTextView labelView;
    @BindView
    AirTextView placeholderTextView;
    private final ValueAnimator selectedAnimator;

    public RearrangableLabeledPhotoCell(Context context) {
        super(context);
        this.selectedAnimator = PhotoRearrangerAnimator.create(300, 0.0f, 1.0f, RearrangableLabeledPhotoCell$$Lambda$1.lambdaFactory$(this));
        init();
    }

    public RearrangableLabeledPhotoCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.selectedAnimator = PhotoRearrangerAnimator.create(300, 0.0f, 1.0f, RearrangableLabeledPhotoCell$$Lambda$2.lambdaFactory$(this));
        init();
    }

    public RearrangableLabeledPhotoCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.selectedAnimator = PhotoRearrangerAnimator.create(300, 0.0f, 1.0f, RearrangableLabeledPhotoCell$$Lambda$3.lambdaFactory$(this));
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_rearrangable_labeled_photo_cell, this);
        ButterKnife.bind((View) this);
    }

    public void setImage(CharSequence imageUrl) {
        if (TextUtils.isEmpty(imageUrl)) {
            this.imageView.clear();
        } else {
            this.imageView.setImageUrl(imageUrl.toString());
        }
    }

    public void setLabel(CharSequence label) {
        ViewLibUtils.bindOptionalTextView((TextView) this.labelView, label);
    }

    public void setPlaceholderText(CharSequence placeholderText) {
        ViewLibUtils.bindOptionalTextView((TextView) this.placeholderTextView, placeholderText);
    }

    public void onDrag() {
        this.selectedAnimator.start();
    }

    public void onDrop() {
        this.selectedAnimator.reverse();
    }

    /* access modifiers changed from: private */
    public void updateAnimation(float progress) {
        float scale = 1.0f + (0.08f * progress);
        setAlpha(1.0f - (0.12f * progress));
        setScaleX(scale);
        setScaleY(scale);
    }

    public static void mock(RearrangableLabeledPhotoCell view) {
        view.setLabel("Cover photo");
        view.setImage("https://a0.muscache.com/im/pictures/6071fed8-e562-4652-9884-7280f82ff491.jpg");
    }

    public static void mockWithBackupText(RearrangableLabeledPhotoCell view) {
        view.setLabel("Cover photo");
        view.setPlaceholderText("This text show if as a placeholder if you don't have an image");
    }
}
