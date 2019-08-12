package com.jumio.commons.view;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CompatibilityLayer {
    @SuppressLint({"NewApi"})
    public static void setBackground(View view, Drawable drawable) {
        if (VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    @SuppressLint({"NewApi"})
    public static ObjectAnimator getImageViewAlphaObjectAnimator(ImageView image, int alphaFrom, int alphaTo) {
        if (VERSION.SDK_INT >= 16) {
            return ObjectAnimator.ofInt(image, "imageAlpha", new int[]{alphaFrom, alphaTo});
        }
        return ObjectAnimator.ofFloat(image, "alpha", new float[]{((float) alphaFrom) / 255.0f, ((float) alphaTo) / 255.0f});
    }

    @SuppressLint({"NewApi"})
    public static float getImageViewAlpha(ImageView image) {
        return VERSION.SDK_INT >= 16 ? (float) image.getImageAlpha() : image.getAlpha();
    }

    @SuppressLint({"NewApi"})
    public static void setImageViewAlpha(ImageView image, int alpha) {
        if (VERSION.SDK_INT >= 16) {
            image.setImageAlpha(alpha);
        } else {
            image.setAlpha(((float) alpha) / 255.0f);
        }
    }

    @SuppressLint({"NewApi"})
    public static void setTextAppearance(TextView textView, int resId) {
        if (VERSION.SDK_INT >= 16) {
            textView.setTextAppearance(textView.getContext(), resId);
        } else {
            textView.setTextAppearance(resId);
        }
    }

    @SuppressLint({"NewApi"})
    public static Drawable getDrawable(Resources resources, int resId) {
        return VERSION.SDK_INT >= 16 ? resources.getDrawable(resId) : resources.getDrawable(resId, null);
    }
}
