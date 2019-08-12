package com.jumio.commons.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.ArrayList;

public class ImageSwitcherViewDrawable extends RelativeLayout implements OnClickListener {
    private static final int ANIMATION_DURATION_IN = 160;
    private static final int ANIMATION_DURATION_OUT = 130;
    private static final int IMAGE_VIEW_POOL_SIZE = 2;
    private int currentDrawableIndex = 0;
    private int currentImageViewIndex = 0;
    private ArrayList<Drawable> drawables;
    /* access modifiers changed from: private */
    public OnImageSwitchedListener externalListener;
    private ImageView[] imagesPool = new ImageView[2];

    public interface OnImageSwitchedListener {
        void onImageSwitchFinished(ImageSwitcherViewDrawable imageSwitcherViewDrawable);

        void onImageSwitchStarted(ImageSwitcherViewDrawable imageSwitcherViewDrawable);
    }

    public ImageSwitcherViewDrawable(Context context) {
        super(context);
        init(context);
    }

    public ImageSwitcherViewDrawable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageSwitcherViewDrawable(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        setOnClickListener(this);
    }

    public void onClick(View v) {
        this.externalListener.onImageSwitchStarted(this);
        startSwitchAnimation();
    }

    private void startSwitchAnimation() {
        int i;
        int i2 = 0;
        ImageView oldImage = this.imagesPool[this.currentImageViewIndex];
        if (this.currentDrawableIndex < this.drawables.size() - 1) {
            i = this.currentDrawableIndex + 1;
        } else {
            i = 0;
        }
        this.currentDrawableIndex = i;
        if (this.currentImageViewIndex < 1) {
            i2 = this.currentImageViewIndex + 1;
        }
        this.currentImageViewIndex = i2;
        ImageView imageView = this.imagesPool[this.currentImageViewIndex];
        Drawable drawable = (Drawable) this.drawables.get(this.currentDrawableIndex);
        int height = (int) Math.floor((double) drawable.getIntrinsicHeight());
        imageView.setImageDrawable(drawable);
        new Handler(Looper.getMainLooper()).post(getAnimatorTask(oldImage, imageView, height, this));
    }

    private Runnable getAnimatorTask(ImageView oldImage, ImageView newImage, int newBmpHeight, ImageSwitcherViewDrawable ref) {
        final ImageView imageView = oldImage;
        final int i = newBmpHeight;
        final ImageView imageView2 = newImage;
        final ImageSwitcherViewDrawable imageSwitcherViewDrawable = ref;
        return new Runnable() {
            public void run() {
                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(imageView, "alpha", new float[]{255.0f, 0.0f});
                fadeOut.setDuration(130);
                ObjectAnimator slideOut = ObjectAnimator.ofFloat(imageView, "translationY", new float[]{0.0f, (float) i});
                slideOut.setInterpolator(new DecelerateInterpolator());
                slideOut.setDuration(130);
                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(imageView2, "alpha", new float[]{0.0f, 255.0f});
                fadeIn.setDuration(160);
                ObjectAnimator slideIn = ObjectAnimator.ofFloat(imageView2, "translationY", new float[]{(float) (-i), 0.0f});
                slideOut.setInterpolator(new AccelerateInterpolator());
                slideIn.setDuration(160);
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(new Animator[]{slideOut, fadeOut, slideIn, fadeIn});
                animatorSet.addListener(new AnimatorListener() {
                    public void onAnimationStart(Animator animation) {
                    }

                    public void onAnimationEnd(Animator animation) {
                        ImageSwitcherViewDrawable.this.externalListener.onImageSwitchFinished(imageSwitcherViewDrawable);
                    }

                    public void onAnimationCancel(Animator animation) {
                    }

                    public void onAnimationRepeat(Animator animation) {
                    }
                });
                animatorSet.start();
            }
        };
    }

    public ImageSwitcherViewDrawable setImages(ArrayList<Drawable> drawables2, int padding) {
        this.drawables = drawables2;
        this.currentDrawableIndex = 0;
        this.currentImageViewIndex = 0;
        Drawable drawable = (Drawable) drawables2.get(this.currentDrawableIndex);
        int intrinsicHeight = drawable.getIntrinsicHeight() / 2;
        for (int i = 0; i < 2; i++) {
            ImageView image = new ImageView(getContext());
            image.setPadding(0, padding, 0, padding);
            addView(image);
            this.imagesPool[i] = image;
        }
        this.imagesPool[this.currentImageViewIndex].setImageDrawable(drawable);
        return this;
    }

    public void switchToImageWithIndex(int index) {
        if (index < this.drawables.size()) {
            this.currentDrawableIndex = index;
            this.imagesPool[this.currentImageViewIndex].setImageDrawable((Drawable) this.drawables.get(this.currentDrawableIndex));
        }
    }

    public void setOnImageSwitchedListener(OnImageSwitchedListener l) {
        this.externalListener = l;
    }
}
