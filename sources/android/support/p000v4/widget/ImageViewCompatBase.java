package android.support.p000v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.widget.ImageView;

/* renamed from: android.support.v4.widget.ImageViewCompatBase */
class ImageViewCompatBase {
    static ColorStateList getImageTintList(ImageView view) {
        if (view instanceof TintableImageSourceView) {
            return ((TintableImageSourceView) view).getSupportImageTintList();
        }
        return null;
    }

    static void setImageTintList(ImageView view, ColorStateList tintList) {
        if (view instanceof TintableImageSourceView) {
            ((TintableImageSourceView) view).setSupportImageTintList(tintList);
        }
    }

    static Mode getImageTintMode(ImageView view) {
        if (view instanceof TintableImageSourceView) {
            return ((TintableImageSourceView) view).getSupportImageTintMode();
        }
        return null;
    }

    static void setImageTintMode(ImageView view, Mode mode) {
        if (view instanceof TintableImageSourceView) {
            ((TintableImageSourceView) view).setSupportImageTintMode(mode);
        }
    }
}
