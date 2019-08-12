package jumio.p317nv.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import com.jumio.commons.camera.CameraUtils;
import com.jumio.commons.log.Log;
import com.jumio.core.environment.Environment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: jumio.nv.core.ac */
/* compiled from: LivenessSavingTask */
public class C4889ac extends Thread {

    /* renamed from: a */
    private Queue<C4950y> f4710a = new ConcurrentLinkedQueue();

    /* renamed from: b */
    private LinkedList<String> f4711b = new LinkedList<>();

    /* renamed from: c */
    private int f4712c;

    /* renamed from: d */
    private int f4713d;

    /* renamed from: e */
    private File f4714e;

    /* renamed from: f */
    private long f4715f;

    /* renamed from: g */
    private boolean f4716g;

    public C4889ac(Context context, int i, int i2) {
        this.f4714e = Environment.getDataDirectory(context);
        this.f4712c = i;
        this.f4713d = i2;
        mo46800a();
    }

    /* renamed from: a */
    public void mo46800a() {
        this.f4710a.clear();
        this.f4711b.clear();
        System.gc();
    }

    /* renamed from: a */
    public void mo46801a(C4950y yVar) {
        try {
            if (this.f4712c != 0) {
                long currentTimeMillis = System.currentTimeMillis();
                if (!isInterrupted() && currentTimeMillis - this.f4715f >= 500 && mo46804c()) {
                    this.f4715f = currentTimeMillis;
                    this.f4710a.add(yVar);
                }
            }
        } catch (OutOfMemoryError e) {
            this.f4710a.clear();
            Log.printStackTrace(e);
            System.gc();
        }
    }

    /* renamed from: b */
    public String[] mo46803b() {
        mo46802a(false);
        if (this.f4711b.size() <= this.f4712c) {
            String[] strArr = new String[this.f4711b.size()];
            for (int i = 0; i < this.f4711b.size(); i++) {
                strArr[(this.f4711b.size() - 1) - i] = (String) this.f4711b.get(i);
            }
            return strArr;
        }
        String[] strArr2 = new String[this.f4712c];
        int i2 = this.f4712c / 2;
        int i3 = 0;
        int i4 = this.f4712c - 1;
        while (i3 < i2) {
            int i5 = i4 - 1;
            strArr2[i4] = (String) this.f4711b.get(i3);
            i3++;
            i4 = i5;
        }
        int i6 = (this.f4712c / 2) + (this.f4712c % 2);
        int size = ((this.f4711b.size() - 1) - i6) / i6;
        int i7 = 0;
        int size2 = this.f4711b.size() - 1;
        while (size2 >= i6 && i7 < i6) {
            int i8 = i7 + 1;
            strArr2[i7] = (String) this.f4711b.get(size2);
            size2 -= size;
            i7 = i8;
        }
        return strArr2;
    }

    public void run() {
        Throwable th;
        int i = 0;
        while (true) {
            int i2 = i;
            if (!isInterrupted()) {
                C4950y yVar = (C4950y) this.f4710a.poll();
                if (yVar != null) {
                    String str = "tmp_%04d";
                    try {
                        Object[] objArr = new Object[1];
                        int i3 = i2 + 1;
                        try {
                            objArr[0] = Integer.valueOf(i2);
                            String format = String.format(str, objArr);
                            m2946a(CameraUtils.rgb2bitmap(yVar.f4871a, yVar.f4872b, yVar.f4873c), this.f4714e, format, CompressFormat.JPEG, 70);
                            this.f4711b.addFirst(this.f4714e.getAbsolutePath() + "/" + format);
                            if (this.f4711b.size() > this.f4713d) {
                                new File(this.f4714e, (String) this.f4711b.removeLast()).delete();
                            }
                            System.gc();
                            i = i3;
                        } catch (Exception e) {
                            th = e;
                            i = i3;
                            Log.printStackTrace(th);
                        }
                    } catch (Exception e2) {
                        Throwable th2 = e2;
                        i = i2;
                        th = th2;
                        Log.printStackTrace(th);
                    }
                } else {
                    i = i2;
                }
            } else {
                mo46800a();
                return;
            }
        }
    }

    /* renamed from: a */
    private void m2946a(Bitmap bitmap, File file, String str, CompressFormat compressFormat, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, i, byteArrayOutputStream);
        m2947a(byteArrayOutputStream.toByteArray(), file, str, false);
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2947a(byte[] r3, java.io.File r4, java.lang.String r5, boolean r6) {
        /*
            r2 = this;
            java.io.File r0 = new java.io.File     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
            r0.<init>(r4, r5)     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
            boolean r1 = r0.exists()     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
            if (r1 != 0) goto L_0x000e
            r0.createNewFile()     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
        L_0x000e:
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
            r1.<init>(r0, r6)     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
            r1.write(r3)     // Catch:{ all -> 0x001a }
            r1.close()     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
        L_0x0019:
            return
        L_0x001a:
            r0 = move-exception
            r1.close()     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
            throw r0     // Catch:{ FileNotFoundException -> 0x001f, IOException -> 0x0024 }
        L_0x001f:
            r0 = move-exception
            com.jumio.commons.log.Log.printStackTrace(r0)
            goto L_0x0019
        L_0x0024:
            r0 = move-exception
            com.jumio.commons.log.Log.printStackTrace(r0)
            goto L_0x0019
        */
        throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.core.C4889ac.m2947a(byte[], java.io.File, java.lang.String, boolean):void");
    }

    /* renamed from: c */
    public synchronized boolean mo46804c() {
        return this.f4716g;
    }

    /* renamed from: a */
    public synchronized void mo46802a(boolean z) {
        this.f4716g = z;
    }
}
