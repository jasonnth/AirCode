package android.support.p000v4.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.widget.CompoundButton;

/* renamed from: android.support.v4.widget.CompoundButtonCompat */
public final class CompoundButtonCompat {
    private static final CompoundButtonCompatImpl IMPL;

    /* renamed from: android.support.v4.widget.CompoundButtonCompat$Api23CompoundButtonImpl */
    static class Api23CompoundButtonImpl extends LollipopCompoundButtonImpl {
        Api23CompoundButtonImpl() {
        }

        public Drawable getButtonDrawable(CompoundButton button) {
            return CompoundButtonCompatApi23.getButtonDrawable(button);
        }
    }

    /* renamed from: android.support.v4.widget.CompoundButtonCompat$BaseCompoundButtonCompat */
    static class BaseCompoundButtonCompat implements CompoundButtonCompatImpl {
        BaseCompoundButtonCompat() {
        }

        public void setButtonTintList(CompoundButton button, ColorStateList tint) {
            CompoundButtonCompatGingerbread.setButtonTintList(button, tint);
        }

        public void setButtonTintMode(CompoundButton button, Mode tintMode) {
            CompoundButtonCompatGingerbread.setButtonTintMode(button, tintMode);
        }

        public Drawable getButtonDrawable(CompoundButton button) {
            return CompoundButtonCompatGingerbread.getButtonDrawable(button);
        }
    }

    /* renamed from: android.support.v4.widget.CompoundButtonCompat$CompoundButtonCompatImpl */
    interface CompoundButtonCompatImpl {
        Drawable getButtonDrawable(CompoundButton compoundButton);

        void setButtonTintList(CompoundButton compoundButton, ColorStateList colorStateList);

        void setButtonTintMode(CompoundButton compoundButton, Mode mode);
    }

    /* renamed from: android.support.v4.widget.CompoundButtonCompat$LollipopCompoundButtonImpl */
    static class LollipopCompoundButtonImpl extends BaseCompoundButtonCompat {
        LollipopCompoundButtonImpl() {
        }

        public void setButtonTintList(CompoundButton button, ColorStateList tint) {
            CompoundButtonCompatLollipop.setButtonTintList(button, tint);
        }

        public void setButtonTintMode(CompoundButton button, Mode tintMode) {
            CompoundButtonCompatLollipop.setButtonTintMode(button, tintMode);
        }
    }

    static {
        int sdk = VERSION.SDK_INT;
        if (sdk >= 23) {
            IMPL = new Api23CompoundButtonImpl();
        } else if (sdk >= 21) {
            IMPL = new LollipopCompoundButtonImpl();
        } else {
            IMPL = new BaseCompoundButtonCompat();
        }
    }

    public static void setButtonTintList(CompoundButton button, ColorStateList tint) {
        IMPL.setButtonTintList(button, tint);
    }

    public static void setButtonTintMode(CompoundButton button, Mode tintMode) {
        IMPL.setButtonTintMode(button, tintMode);
    }

    public static Drawable getButtonDrawable(CompoundButton button) {
        return IMPL.getButtonDrawable(button);
    }
}
