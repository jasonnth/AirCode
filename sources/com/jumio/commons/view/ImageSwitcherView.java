package com.jumio.commons.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
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

public class ImageSwitcherView extends RelativeLayout implements OnClickListener {
    private static final int ANIMATION_DURATION_IN = 160;
    private static final int ANIMATION_DURATION_OUT = 130;
    private static final int IMAGE_VIEW_POOL_SIZE = 2;
    private ArrayList<Bitmap> bitmaps;
    private int currentBitmapIndex = 0;
    private int currentImagePoolIndex = 0;
    /* access modifiers changed from: private */
    public OnImageSwitchedListener externalListener;
    private ImageView[] imagesPool = new ImageView[2];

    public interface OnImageSwitchedListener {
        void onImageSwitchFinished(ImageSwitcherView imageSwitcherView);

        void onImageSwitchStarted(ImageSwitcherView imageSwitcherView);
    }

    public ImageSwitcherView(Context context) {
        super(context);
        init(context);
    }

    public ImageSwitcherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImageSwitcherView(Context context, AttributeSet attrs, int defStyle) {
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
        ImageView oldImage = this.imagesPool[this.currentImagePoolIndex];
        if (this.currentBitmapIndex < this.bitmaps.size() - 1) {
            i = this.currentBitmapIndex + 1;
        } else {
            i = 0;
        }
        this.currentBitmapIndex = i;
        if (this.currentImagePoolIndex < 1) {
            i2 = this.currentImagePoolIndex + 1;
        }
        this.currentImagePoolIndex = i2;
        ImageView newImage = this.imagesPool[this.currentImagePoolIndex];
        Bitmap newBmp = (Bitmap) this.bitmaps.get(this.currentBitmapIndex);
        int newBmpHeight = (int) Math.floor((double) newBmp.getHeight());
        newImage.setImageBitmap(newBmp);
        new Handler(Looper.getMainLooper()).post(getAnimatorTask(oldImage, newImage, newBmpHeight, this));
    }

    private Runnable getAnimatorTask(ImageView oldImage, ImageView newImage, int newBmpHeight, ImageSwitcherView ref) {
        final ImageView imageView = oldImage;
        final int i = newBmpHeight;
        final ImageView imageView2 = newImage;
        final ImageSwitcherView imageSwitcherView = ref;
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
                        ImageSwitcherView.this.externalListener.onImageSwitchFinished(imageSwitcherView);
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

    public ImageSwitcherView setImages(ArrayList<Bitmap> bitmaps2) {
        this.bitmaps = bitmaps2;
        this.currentBitmapIndex = 0;
        this.currentImagePoolIndex = 0;
        Bitmap firstBitmap = (Bitmap) bitmaps2.get(this.currentBitmapIndex);
        int firstBitmapHeightHalf = firstBitmap.getHeight() / 2;
        for (int i = 0; i < 2; i++) {
            ImageView image = new ImageView(getContext());
            image.setPadding(0, firstBitmapHeightHalf, 0, firstBitmapHeightHalf);
            addView(image);
            this.imagesPool[i] = image;
        }
        this.imagesPool[this.currentImagePoolIndex].setImageBitmap(firstBitmap);
        return this;
    }

    public void switchToImageWithIndex(int index) {
        if (index < this.bitmaps.size()) {
            this.currentBitmapIndex = index;
            this.imagesPool[this.currentImagePoolIndex].setImageBitmap((Bitmap) this.bitmaps.get(this.currentBitmapIndex));
        }
    }

    public void setOnImageSwitchedListener(OnImageSwitchedListener l) {
        this.externalListener = l;
    }
}
