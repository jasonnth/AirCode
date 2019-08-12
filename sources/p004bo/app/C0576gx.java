package p004bo.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import p004bo.app.C0586hd.C0587a;

/* renamed from: bo.app.gx */
public class C0576gx implements C0579gy {

    /* renamed from: a */
    protected final boolean f771a;

    /* renamed from: bo.app.gx$a */
    public static class C0577a {

        /* renamed from: a */
        public final int f772a;

        /* renamed from: b */
        public final boolean f773b;

        protected C0577a() {
            this.f772a = 0;
            this.f773b = false;
        }

        protected C0577a(int i, boolean z) {
            this.f772a = i;
            this.f773b = z;
        }
    }

    /* renamed from: bo.app.gx$b */
    public static class C0578b {

        /* renamed from: a */
        public final C0563gp f774a;

        /* renamed from: b */
        public final C0577a f775b;

        protected C0578b(C0563gp gpVar, C0577a aVar) {
            this.f774a = gpVar;
            this.f775b = aVar;
        }
    }

    public C0576gx(boolean z) {
        this.f771a = z;
    }

    /* renamed from: a */
    public Bitmap mo7229a(C0580gz gzVar) {
        InputStream b = mo7233b(gzVar);
        if (b == null) {
            C0599hn.m1065d("No stream for image [%s]", gzVar.mo7235a());
            return null;
        }
        try {
            C0578b a = mo7232a(b, gzVar);
            b = mo7234b(b, gzVar);
            Bitmap decodeStream = BitmapFactory.decodeStream(b, null, mo7230a(a.f774a, gzVar));
            if (decodeStream != null) {
                return mo7228a(decodeStream, gzVar, a.f775b.f772a, a.f775b.f773b);
            }
            C0599hn.m1065d("Image can't be decoded [%s]", gzVar.mo7235a());
            return decodeStream;
        } finally {
            C0597hm.m1054a((Closeable) b);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public InputStream mo7233b(C0580gz gzVar) {
        return gzVar.mo7240f().mo7152a(gzVar.mo7236b(), gzVar.mo7241g());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C0578b mo7232a(InputStream inputStream, C0580gz gzVar) {
        C0577a aVar;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        String b = gzVar.mo7236b();
        if (!gzVar.mo7242h() || !m969a(b, options.outMimeType)) {
            aVar = new C0577a();
        } else {
            aVar = mo7231a(b);
        }
        return new C0578b(new C0563gp(options.outWidth, options.outHeight, aVar.f772a), aVar);
    }

    /* renamed from: a */
    private boolean m969a(String str, String str2) {
        return "image/jpeg".equalsIgnoreCase(str2) && C0587a.m1009a(str) == C0587a.FILE;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0024, code lost:
        r1 = 180;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0028, code lost:
        r1 = com.airbnb.android.core.models.MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        r1 = 90;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p004bo.app.C0576gx.C0577a mo7231a(java.lang.String r6) {
        /*
            r5 = this;
            r1 = 0
            r0 = 1
            android.media.ExifInterface r2 = new android.media.ExifInterface     // Catch:{ IOException -> 0x002b }
            bo.app.hd$a r3 = p004bo.app.C0586hd.C0587a.FILE     // Catch:{ IOException -> 0x002b }
            java.lang.String r3 = r3.mo7258c(r6)     // Catch:{ IOException -> 0x002b }
            r2.<init>(r3)     // Catch:{ IOException -> 0x002b }
            java.lang.String r3 = "Orientation"
            r4 = 1
            int r2 = r2.getAttributeInt(r3, r4)     // Catch:{ IOException -> 0x002b }
            switch(r2) {
                case 1: goto L_0x0018;
                case 2: goto L_0x0019;
                case 3: goto L_0x0023;
                case 4: goto L_0x0024;
                case 5: goto L_0x0028;
                case 6: goto L_0x001f;
                case 7: goto L_0x0020;
                case 8: goto L_0x0027;
                default: goto L_0x0018;
            }
        L_0x0018:
            r0 = r1
        L_0x0019:
            bo.app.gx$a r2 = new bo.app.gx$a
            r2.<init>(r1, r0)
            return r2
        L_0x001f:
            r0 = r1
        L_0x0020:
            r1 = 90
            goto L_0x0019
        L_0x0023:
            r0 = r1
        L_0x0024:
            r1 = 180(0xb4, float:2.52E-43)
            goto L_0x0019
        L_0x0027:
            r0 = r1
        L_0x0028:
            r1 = 270(0x10e, float:3.78E-43)
            goto L_0x0019
        L_0x002b:
            r2 = move-exception
            java.lang.String r2 = "Can't read EXIF tags from file [%s]"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r0[r1] = r6
            p004bo.app.C0599hn.m1064c(r2, r0)
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: p004bo.app.C0576gx.mo7231a(java.lang.String):bo.app.gx$a");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Options mo7230a(C0563gp gpVar, C0580gz gzVar) {
        boolean z;
        int a;
        C0562go d = gzVar.mo7238d();
        if (d == C0562go.NONE) {
            a = 1;
        } else if (d == C0562go.NONE_SAFE) {
            a = C0595hl.m1050a(gpVar);
        } else {
            C0563gp c = gzVar.mo7237c();
            if (d == C0562go.IN_SAMPLE_POWER_OF_2) {
                z = true;
            } else {
                z = false;
            }
            a = C0595hl.m1051a(gpVar, c, gzVar.mo7239e(), z);
        }
        if (a > 1 && this.f771a) {
            C0599hn.m1060a("Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]", gpVar, gpVar.mo7185a(a), Integer.valueOf(a), gzVar.mo7235a());
        }
        Options i = gzVar.mo7243i();
        i.inSampleSize = a;
        return i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public InputStream mo7234b(InputStream inputStream, C0580gz gzVar) {
        if (inputStream.markSupported()) {
            try {
                inputStream.reset();
                return inputStream;
            } catch (IOException e) {
            }
        }
        C0597hm.m1054a((Closeable) inputStream);
        return mo7233b(gzVar);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Bitmap mo7228a(Bitmap bitmap, C0580gz gzVar, int i, boolean z) {
        Matrix matrix = new Matrix();
        C0562go d = gzVar.mo7238d();
        if (d == C0562go.EXACTLY || d == C0562go.EXACTLY_STRETCHED) {
            C0563gp gpVar = new C0563gp(bitmap.getWidth(), bitmap.getHeight(), i);
            float b = C0595hl.m1053b(gpVar, gzVar.mo7237c(), gzVar.mo7239e(), d == C0562go.EXACTLY_STRETCHED);
            if (Float.compare(b, 1.0f) != 0) {
                matrix.setScale(b, b);
                if (this.f771a) {
                    C0599hn.m1060a("Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]", gpVar, gpVar.mo7184a(b), Float.valueOf(b), gzVar.mo7235a());
                }
            }
        }
        if (z) {
            matrix.postScale(-1.0f, 1.0f);
            if (this.f771a) {
                C0599hn.m1060a("Flip image horizontally [%s]", gzVar.mo7235a());
            }
        }
        if (i != 0) {
            matrix.postRotate((float) i);
            if (this.f771a) {
                C0599hn.m1060a("Rotate image on %1$d° [%2$s]", Integer.valueOf(i), gzVar.mo7235a());
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }
}
