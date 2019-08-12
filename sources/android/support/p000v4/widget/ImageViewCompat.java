package android.support.p000v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.widget.ImageView;

/* renamed from: android.support.v4.widget.ImageViewCompat */
public class ImageViewCompat {
    static final ImageViewCompatImpl IMPL;

    /* renamed from: android.support.v4.widget.ImageViewCompat$BaseViewCompatImpl */
    static class BaseViewCompatImpl implements ImageViewCompatImpl {
        BaseViewCompatImpl() {
        }

        public ColorStateList getImageTintList(ImageView view) {
            return ImageViewCompatBase.getImageTintList(view);
        }

        public void setImageTintList(ImageView view, ColorStateList tintList) {
            ImageViewCompatBase.setImageTintList(view, tintList);
        }

        public void setImageTintMode(ImageView view, Mode mode) {
            ImageViewCompatBase.setImageTintMode(view, mode);
        }

        public Mode getImageTintMode(ImageView view) {
            return ImageViewCompatBase.getImageTintMode(view);
        }
    }

    /* renamed from: android.support.v4.widget.ImageViewCompat$ImageViewCompatImpl */
    interface ImageViewCompatImpl {
        ColorStateList getImageTintList(ImageView imageView);

        Mode getImageTintMode(ImageView imageView);

        void setImageTintList(ImageView imageView, ColorStateList colorStateList);

        void setImageTintMode(ImageView imageView, Mode mode);
    }

    /* renamed from: android.support.v4.widget.ImageViewCompat$LollipopViewCompatImpl */
    static class LollipopViewCompatImpl extends BaseViewCompatImpl {
        LollipopViewCompatImpl() {
        }

        public ColorStateList getImageTintList(ImageView view) {
            return ImageViewCompatLollipop.getImageTintList(view);
        }

        public void setImageTintList(ImageView view, ColorStateList tintList) {
            ImageViewCompatLollipop.setImageTintList(view, tintList);
        }

        public void setImageTintMode(ImageView view, Mode mode) {
            ImageViewCompatLollipop.setImageTintMode(view, mode);
        }

        public Mode getImageTintMode(ImageView view) {
            return ImageViewCompatLollipop.getImageTintMode(view);
        }
    }

    static {
        if (VERSION.SDK_INT >= 21) {
            IMPL = new LollipopViewCompatImpl();
        } else {
            IMPL = new BaseViewCompatImpl();
        }
    }

    public static ColorStateList getImageTintList(ImageView view) {
        return IMPL.getImageTintList(view);
    }

    public static void setImageTintList(ImageView view, ColorStateList tintList) {
        IMPL.setImageTintList(view, tintList);
    }

    public static Mode getImageTintMode(ImageView view) {
        return IMPL.getImageTintMode(view);
    }

    public static void setImageTintMode(ImageView view, Mode mode) {
        IMPL.setImageTintMode(view, mode);
    }
}
