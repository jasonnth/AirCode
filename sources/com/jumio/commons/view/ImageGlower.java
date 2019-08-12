package com.jumio.commons.view;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class ImageGlower {
    /* access modifiers changed from: private */
    public static int MAX_COUNT = 10;
    /* access modifiers changed from: private */
    public AlphaAnimation lAnimation1;
    /* access modifiers changed from: private */
    public AlphaAnimation lAnimation2;
    /* access modifiers changed from: private */
    public int mGlowCount = 0;
    private int mGlowRadius = 24;
    /* access modifiers changed from: private */
    public ImageView mGlowView;
    private int mHalfMargin = (this.mMargin / 2);
    private ImageView mImageView;
    private int mMargin = 0;
    private Paint mPaint;
    private Bitmap mSource;

    public ImageGlower(Bitmap p_Bitmap, ImageView p_ImageView, ImageView p_GlowView, float density) {
        this.mSource = p_Bitmap;
        this.mImageView = p_ImageView;
        this.mGlowView = p_GlowView;
        this.mPaint = new Paint();
        this.mPaint.setMaskFilter(new BlurMaskFilter((((float) this.mGlowRadius) * density) + 0.5f, Blur.SOLID));
        Bitmap lResult = Bitmap.createBitmap(this.mSource.getWidth() + this.mMargin, this.mSource.getHeight() + this.mMargin, Config.ARGB_8888);
        new Canvas(lResult).drawBitmap(this.mSource, (float) this.mHalfMargin, (float) this.mHalfMargin, null);
        this.mImageView.setImageBitmap(lResult);
        this.lAnimation1 = new AlphaAnimation(0.0f, 1.0f);
        this.lAnimation2 = new AlphaAnimation(1.0f, 0.0f);
        this.lAnimation1.setDuration(350);
        this.lAnimation2.setDuration(350);
        this.lAnimation1.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                ImageGlower.this.mGlowCount = ImageGlower.this.mGlowCount + 1;
                if (ImageGlower.this.mGlowCount < ImageGlower.MAX_COUNT) {
                    ImageGlower.this.mGlowView.startAnimation(ImageGlower.this.lAnimation2);
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        this.lAnimation2.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                ImageGlower.this.mGlowCount = ImageGlower.this.mGlowCount + 1;
                if (ImageGlower.this.mGlowCount < ImageGlower.MAX_COUNT) {
                    ImageGlower.this.mGlowView.startAnimation(ImageGlower.this.lAnimation1);
                }
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
    }

    public void cancel() {
        this.mGlowCount = MAX_COUNT;
    }

    public void glow(boolean p_On) {
        this.mGlowView.setLayoutParams(this.mImageView.getLayoutParams());
        this.mGlowView.setVisibility(p_On ? 0 : 4);
    }

    public void setColor(int p_Glow) {
        this.mPaint.setColor(p_Glow);
        Bitmap lResult = Bitmap.createBitmap(this.mSource.getWidth() + this.mMargin, this.mSource.getHeight() + this.mMargin, Config.ARGB_8888);
        lResult.eraseColor(0);
        new Canvas(lResult).drawBitmap(this.mSource.extractAlpha(), (float) this.mHalfMargin, (float) this.mHalfMargin, this.mPaint);
        this.mGlowView.setImageBitmap(lResult);
        this.mGlowView.setVisibility(4);
    }

    public void pulsate() {
        this.mGlowCount = 0;
        this.mGlowView.startAnimation(this.lAnimation1);
    }
}
