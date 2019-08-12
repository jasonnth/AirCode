package p004bo.app;

import android.graphics.Bitmap;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* renamed from: bo.app.gb */
public class C0536gb implements C0533fz {

    /* renamed from: a */
    private final LinkedHashMap<String, Bitmap> f570a;

    /* renamed from: b */
    private final int f571b;

    /* renamed from: c */
    private int f572c;

    public C0536gb(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f571b = i;
        this.f570a = new LinkedHashMap<>(0, 0.75f, true);
    }

    /* renamed from: a */
    public final Bitmap mo7101a(String str) {
        Bitmap bitmap;
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            bitmap = (Bitmap) this.f570a.get(str);
        }
        return bitmap;
    }

    /* renamed from: a */
    public final boolean mo7103a(String str, Bitmap bitmap) {
        if (str == null || bitmap == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.f572c += m774b(str, bitmap);
            Bitmap bitmap2 = (Bitmap) this.f570a.put(str, bitmap);
            if (bitmap2 != null) {
                this.f572c -= m774b(str, bitmap2);
            }
        }
        m773a(this.f571b);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0032, code lost:
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m773a(int r4) {
        /*
            r3 = this;
        L_0x0000:
            monitor-enter(r3)
            int r0 = r3.f572c     // Catch:{ all -> 0x0033 }
            if (r0 < 0) goto L_0x0011
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r0 = r3.f570a     // Catch:{ all -> 0x0033 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x0036
            int r0 = r3.f572c     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x0036
        L_0x0011:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0033 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0033 }
            r1.<init>()     // Catch:{ all -> 0x0033 }
            java.lang.Class r2 = r3.getClass()     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = r2.getName()     // Catch:{ all -> 0x0033 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0033 }
            java.lang.String r2 = ".sizeOf() is reporting inconsistent results!"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0033 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0033 }
            r0.<init>(r1)     // Catch:{ all -> 0x0033 }
            throw r0     // Catch:{ all -> 0x0033 }
        L_0x0033:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0033 }
            throw r0
        L_0x0036:
            int r0 = r3.f572c     // Catch:{ all -> 0x0033 }
            if (r0 <= r4) goto L_0x0042
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r0 = r3.f570a     // Catch:{ all -> 0x0033 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x0044
        L_0x0042:
            monitor-exit(r3)     // Catch:{ all -> 0x0033 }
        L_0x0043:
            return
        L_0x0044:
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r0 = r3.f570a     // Catch:{ all -> 0x0033 }
            java.util.Set r0 = r0.entrySet()     // Catch:{ all -> 0x0033 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0033 }
            java.lang.Object r0 = r0.next()     // Catch:{ all -> 0x0033 }
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch:{ all -> 0x0033 }
            if (r0 != 0) goto L_0x0058
            monitor-exit(r3)     // Catch:{ all -> 0x0033 }
            goto L_0x0043
        L_0x0058:
            java.lang.Object r1 = r0.getKey()     // Catch:{ all -> 0x0033 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0033 }
            java.lang.Object r0 = r0.getValue()     // Catch:{ all -> 0x0033 }
            android.graphics.Bitmap r0 = (android.graphics.Bitmap) r0     // Catch:{ all -> 0x0033 }
            java.util.LinkedHashMap<java.lang.String, android.graphics.Bitmap> r2 = r3.f570a     // Catch:{ all -> 0x0033 }
            r2.remove(r1)     // Catch:{ all -> 0x0033 }
            int r2 = r3.f572c     // Catch:{ all -> 0x0033 }
            int r0 = r3.m774b(r1, r0)     // Catch:{ all -> 0x0033 }
            int r0 = r2 - r0
            r3.f572c = r0     // Catch:{ all -> 0x0033 }
            monitor-exit(r3)     // Catch:{ all -> 0x0033 }
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: p004bo.app.C0536gb.m773a(int):void");
    }

    /* renamed from: b */
    public final Bitmap mo7104b(String str) {
        Bitmap bitmap;
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            bitmap = (Bitmap) this.f570a.remove(str);
            if (bitmap != null) {
                this.f572c -= m774b(str, bitmap);
            }
        }
        return bitmap;
    }

    /* renamed from: a */
    public Collection<String> mo7102a() {
        HashSet hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.f570a.keySet());
        }
        return hashSet;
    }

    /* renamed from: b */
    private int m774b(String str, Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    public final synchronized String toString() {
        return String.format("LruCache[maxSize=%d]", new Object[]{Integer.valueOf(this.f571b)});
    }
}
