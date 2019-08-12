package android.support.p000v4.view;

import android.content.Context;
import android.support.p000v4.p001os.BuildCompat;

/* renamed from: android.support.v4.view.PointerIconCompat */
public final class PointerIconCompat {
    static final PointerIconCompatImpl IMPL;
    private Object mPointerIcon;

    /* renamed from: android.support.v4.view.PointerIconCompat$Api24PointerIconCompatImpl */
    static class Api24PointerIconCompatImpl extends BasePointerIconCompatImpl {
        Api24PointerIconCompatImpl() {
        }

        public Object getSystemIcon(Context context, int style) {
            return PointerIconCompatApi24.getSystemIcon(context, style);
        }
    }

    /* renamed from: android.support.v4.view.PointerIconCompat$BasePointerIconCompatImpl */
    static class BasePointerIconCompatImpl implements PointerIconCompatImpl {
        BasePointerIconCompatImpl() {
        }

        public Object getSystemIcon(Context context, int style) {
            return null;
        }
    }

    /* renamed from: android.support.v4.view.PointerIconCompat$PointerIconCompatImpl */
    interface PointerIconCompatImpl {
        Object getSystemIcon(Context context, int i);
    }

    private PointerIconCompat(Object pointerIcon) {
        this.mPointerIcon = pointerIcon;
    }

    public Object getPointerIcon() {
        return this.mPointerIcon;
    }

    static {
        if (BuildCompat.isAtLeastN()) {
            IMPL = new Api24PointerIconCompatImpl();
        } else {
            IMPL = new BasePointerIconCompatImpl();
        }
    }

    public static PointerIconCompat getSystemIcon(Context context, int style) {
        return new PointerIconCompat(IMPL.getSystemIcon(context, style));
    }
}
