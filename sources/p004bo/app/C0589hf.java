package p004bo.app;

import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import java.lang.reflect.Field;

/* renamed from: bo.app.hf */
public class C0589hf extends C0590hg {
    public C0589hf(ImageView imageView) {
        super(imageView);
    }

    /* renamed from: a */
    public int mo7259a() {
        int a = super.mo7259a();
        if (a <= 0) {
            ImageView imageView = (ImageView) this.f801a.get();
            if (imageView != null) {
                return m1021a((Object) imageView, "mMaxWidth");
            }
        }
        return a;
    }

    /* renamed from: b */
    public int mo7262b() {
        int b = super.mo7262b();
        if (b <= 0) {
            ImageView imageView = (ImageView) this.f801a.get();
            if (imageView != null) {
                return m1021a((Object) imageView, "mMaxHeight");
            }
        }
        return b;
    }

    /* renamed from: c */
    public C0566gs mo7263c() {
        ImageView imageView = (ImageView) this.f801a.get();
        if (imageView != null) {
            return C0566gs.m945a(imageView);
        }
        return super.mo7263c();
    }

    /* renamed from: g */
    public ImageView mo7264d() {
        return (ImageView) super.mo7264d();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo7268a(Drawable drawable, View view) {
        ((ImageView) view).setImageDrawable(drawable);
        if (drawable instanceof AnimationDrawable) {
            ((AnimationDrawable) drawable).start();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo7267a(Bitmap bitmap, View view) {
        ((ImageView) view).setImageBitmap(bitmap);
    }

    /* renamed from: a */
    private static int m1021a(Object obj, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int intValue = ((Integer) declaredField.get(obj)).intValue();
            if (intValue > 0 && intValue < Integer.MAX_VALUE) {
                return intValue;
            }
        } catch (Exception e) {
            C0599hn.m1061a((Throwable) e);
        }
        return 0;
    }
}
