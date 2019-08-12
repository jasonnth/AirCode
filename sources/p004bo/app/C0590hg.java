package p004bo.app;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/* renamed from: bo.app.hg */
public abstract class C0590hg implements C0588he {

    /* renamed from: a */
    protected Reference<View> f801a;

    /* renamed from: b */
    protected boolean f802b;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo7267a(Bitmap bitmap, View view);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo7268a(Drawable drawable, View view);

    public C0590hg(View view) {
        this(view, true);
    }

    public C0590hg(View view, boolean z) {
        if (view == null) {
            throw new IllegalArgumentException("view must not be null");
        }
        this.f801a = new WeakReference(view);
        this.f802b = z;
    }

    /* renamed from: a */
    public int mo7259a() {
        int i;
        View view = (View) this.f801a.get();
        if (view == null) {
            return 0;
        }
        LayoutParams layoutParams = view.getLayoutParams();
        if (!this.f802b || layoutParams == null || layoutParams.width == -2) {
            i = 0;
        } else {
            i = view.getWidth();
        }
        if (i > 0 || layoutParams == null) {
            return i;
        }
        return layoutParams.width;
    }

    /* renamed from: b */
    public int mo7262b() {
        int i;
        View view = (View) this.f801a.get();
        if (view == null) {
            return 0;
        }
        LayoutParams layoutParams = view.getLayoutParams();
        if (!this.f802b || layoutParams == null || layoutParams.height == -2) {
            i = 0;
        } else {
            i = view.getHeight();
        }
        if (i > 0 || layoutParams == null) {
            return i;
        }
        return layoutParams.height;
    }

    /* renamed from: c */
    public C0566gs mo7263c() {
        return C0566gs.CROP;
    }

    /* renamed from: d */
    public View mo7264d() {
        return (View) this.f801a.get();
    }

    /* renamed from: e */
    public boolean mo7265e() {
        return this.f801a.get() == null;
    }

    /* renamed from: f */
    public int mo7266f() {
        View view = (View) this.f801a.get();
        return view == null ? super.hashCode() : view.hashCode();
    }

    /* renamed from: a */
    public boolean mo7261a(Drawable drawable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = (View) this.f801a.get();
            if (view != null) {
                mo7268a(drawable, view);
                return true;
            }
        } else {
            C0599hn.m1064c("Can't set a drawable into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        }
        return false;
    }

    /* renamed from: a */
    public boolean mo7260a(Bitmap bitmap) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            View view = (View) this.f801a.get();
            if (view != null) {
                mo7267a(bitmap, view);
                return true;
            }
        } else {
            C0599hn.m1064c("Can't set a bitmap into view. You should call ImageLoader on UI thread for it.", new Object[0]);
        }
        return false;
    }
}
