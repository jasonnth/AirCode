package android.support.p000v4.view;

import android.os.Build.VERSION;

/* renamed from: android.support.v4.view.WindowInsetsCompat */
public class WindowInsetsCompat {
    private static final WindowInsetsCompatImpl IMPL;
    private final Object mInsets;

    /* renamed from: android.support.v4.view.WindowInsetsCompat$WindowInsetsCompatApi20Impl */
    private static class WindowInsetsCompatApi20Impl extends WindowInsetsCompatBaseImpl {
        WindowInsetsCompatApi20Impl() {
        }

        public int getSystemWindowInsetBottom(Object insets) {
            return WindowInsetsCompatApi20.getSystemWindowInsetBottom(insets);
        }

        public int getSystemWindowInsetLeft(Object insets) {
            return WindowInsetsCompatApi20.getSystemWindowInsetLeft(insets);
        }

        public int getSystemWindowInsetRight(Object insets) {
            return WindowInsetsCompatApi20.getSystemWindowInsetRight(insets);
        }

        public int getSystemWindowInsetTop(Object insets) {
            return WindowInsetsCompatApi20.getSystemWindowInsetTop(insets);
        }

        public WindowInsetsCompat replaceSystemWindowInsets(Object insets, int left, int top, int right, int bottom) {
            return new WindowInsetsCompat(WindowInsetsCompatApi20.replaceSystemWindowInsets(insets, left, top, right, bottom));
        }
    }

    /* renamed from: android.support.v4.view.WindowInsetsCompat$WindowInsetsCompatApi21Impl */
    private static class WindowInsetsCompatApi21Impl extends WindowInsetsCompatApi20Impl {
        WindowInsetsCompatApi21Impl() {
        }

        public boolean isConsumed(Object insets) {
            return WindowInsetsCompatApi21.isConsumed(insets);
        }
    }

    /* renamed from: android.support.v4.view.WindowInsetsCompat$WindowInsetsCompatBaseImpl */
    private static class WindowInsetsCompatBaseImpl implements WindowInsetsCompatImpl {
        WindowInsetsCompatBaseImpl() {
        }

        public int getSystemWindowInsetLeft(Object insets) {
            return 0;
        }

        public int getSystemWindowInsetTop(Object insets) {
            return 0;
        }

        public int getSystemWindowInsetRight(Object insets) {
            return 0;
        }

        public int getSystemWindowInsetBottom(Object insets) {
            return 0;
        }

        public boolean isConsumed(Object insets) {
            return false;
        }

        public WindowInsetsCompat replaceSystemWindowInsets(Object insets, int left, int top, int right, int bottom) {
            return null;
        }
    }

    /* renamed from: android.support.v4.view.WindowInsetsCompat$WindowInsetsCompatImpl */
    private interface WindowInsetsCompatImpl {
        int getSystemWindowInsetBottom(Object obj);

        int getSystemWindowInsetLeft(Object obj);

        int getSystemWindowInsetRight(Object obj);

        int getSystemWindowInsetTop(Object obj);

        boolean isConsumed(Object obj);

        WindowInsetsCompat replaceSystemWindowInsets(Object obj, int i, int i2, int i3, int i4);
    }

    static {
        int version = VERSION.SDK_INT;
        if (version >= 21) {
            IMPL = new WindowInsetsCompatApi21Impl();
        } else if (version >= 20) {
            IMPL = new WindowInsetsCompatApi20Impl();
        } else {
            IMPL = new WindowInsetsCompatBaseImpl();
        }
    }

    WindowInsetsCompat(Object insets) {
        this.mInsets = insets;
    }

    public int getSystemWindowInsetLeft() {
        return IMPL.getSystemWindowInsetLeft(this.mInsets);
    }

    public int getSystemWindowInsetTop() {
        return IMPL.getSystemWindowInsetTop(this.mInsets);
    }

    public int getSystemWindowInsetRight() {
        return IMPL.getSystemWindowInsetRight(this.mInsets);
    }

    public int getSystemWindowInsetBottom() {
        return IMPL.getSystemWindowInsetBottom(this.mInsets);
    }

    public boolean isConsumed() {
        return IMPL.isConsumed(this.mInsets);
    }

    public WindowInsetsCompat replaceSystemWindowInsets(int left, int top, int right, int bottom) {
        return IMPL.replaceSystemWindowInsets(this.mInsets, left, top, right, bottom);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WindowInsetsCompat other = (WindowInsetsCompat) o;
        if (this.mInsets != null) {
            return this.mInsets.equals(other.mInsets);
        }
        if (other.mInsets != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        if (this.mInsets == null) {
            return 0;
        }
        return this.mInsets.hashCode();
    }

    static WindowInsetsCompat wrap(Object insets) {
        if (insets == null) {
            return null;
        }
        return new WindowInsetsCompat(insets);
    }

    static Object unwrap(WindowInsetsCompat insets) {
        if (insets == null) {
            return null;
        }
        return insets.mInsets;
    }
}