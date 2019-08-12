package android.support.p000v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.widget.ImageView;

/* renamed from: android.support.v4.widget.ImageViewCompatLollipop */
class ImageViewCompatLollipop {
    static ColorStateList getImageTintList(ImageView view) {
        return view.getImageTintList();
    }

    static void setImageTintList(ImageView view, ColorStateList tintList) {
        view.setImageTintList(tintList);
        if (VERSION.SDK_INT == 21) {
            Drawable imageViewDrawable = view.getDrawable();
            boolean hasTint = (view.getImageTintList() == null || view.getImageTintMode() == null) ? false : true;
            if (imageViewDrawable != null && hasTint) {
                if (imageViewDrawable.isStateful()) {
                    imageViewDrawable.setState(view.getDrawableState());
                }
                view.setImageDrawable(imageViewDrawable);
            }
        }
    }

    static Mode getImageTintMode(ImageView view) {
        return view.getImageTintMode();
    }

    static void setImageTintMode(ImageView view, Mode mode) {
        view.setImageTintMode(mode);
        if (VERSION.SDK_INT == 21) {
            Drawable imageViewDrawable = view.getDrawable();
            boolean hasTint = (view.getImageTintList() == null || view.getImageTintMode() == null) ? false : true;
            if (imageViewDrawable != null && hasTint) {
                if (imageViewDrawable.isStateful()) {
                    imageViewDrawable.setState(view.getDrawableState());
                }
                view.setImageDrawable(imageViewDrawable);
            }
        }
    }
}
