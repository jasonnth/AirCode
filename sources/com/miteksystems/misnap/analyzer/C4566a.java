package com.miteksystems.misnap.analyzer;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.miteksystems.misnap.events.ShutdownEvent;
import com.miteksystems.misnap.imaging.JPEGProcessor;
import com.miteksystems.misnap.params.ParameterManager;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import p314de.greenrobot.event.EventBus;

/* renamed from: com.miteksystems.misnap.analyzer.a */
public class C4566a extends MiSnapAnalyzer {

    /* renamed from: a */
    public static final String f3640a = C4566a.class.getSimpleName();

    /* renamed from: k */
    private static File f3641k;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public byte[] f3642b;

    /* renamed from: c */
    private int f3643c;

    /* renamed from: d */
    private int f3644d;

    /* renamed from: e */
    private int f3645e;

    /* renamed from: f */
    private JPEGProcessor f3646f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public boolean f3647g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public int f3648h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public long f3649i = 0;

    /* renamed from: j */
    private long f3650j;

    /* renamed from: com.miteksystems.misnap.analyzer.a$a */
    private class C4567a extends AsyncTask<Void, Void, Void> {
        private C4567a() {
        }

        /* synthetic */ C4567a(C4566a aVar, byte b) {
            this();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            if (C4566a.this.f3648h >= 10) {
                EventBus.getDefault().post(new ShutdownEvent(5, "RESULT_ERROR_TESTCAPTURE_FINISHED"));
            } else if (System.currentTimeMillis() - C4566a.this.f3649i > 500) {
                C4566a.m2128c(C4566a.this);
                C4566a.this.f3649i = System.currentTimeMillis();
                C4566a.m2126a(C4566a.this, C4566a.this.f3642b);
            }
            C4566a.this.f3647g = false;
            return null;
        }
    }

    /* renamed from: c */
    static /* synthetic */ int m2128c(C4566a aVar) {
        int i = aVar.f3648h + 1;
        aVar.f3648h = i;
        return i;
    }

    public C4566a(Context context, ParameterManager parameterManager) {
        super(context, parameterManager, true);
        f3641k = new File(parameterManager.getTestScienceSessionName());
        this.f3646f = new JPEGProcessor();
    }

    public boolean init() {
        Log.d(f3640a, "Initializing TestScienceCaptureAnalyzer");
        return true;
    }

    public void deinit() {
        super.deinit();
        Log.d(f3640a, "Deinit TestScienceCaptureAnalyzer");
        this.f3642b = null;
    }

    public void analyze(IAnalyzeResponse iAnalyzeResponse, byte[] bArr, int i, int i2, int i3) {
        super.analyze(iAnalyzeResponse, bArr, i, i2, i3);
        if (this.f3647g) {
            iAnalyzeResponse.onAnalyzeFail(3, null);
            return;
        }
        this.f3642b = bArr;
        this.f3643c = i;
        this.f3644d = i2;
        this.f3645e = i3;
        this.f3647g = true;
        new C4567a(this, 0).execute(new Void[0]);
    }

    /* renamed from: a */
    static /* synthetic */ void m2126a(C4566a aVar, byte[] bArr) {
        long currentTimeMillis = System.currentTimeMillis();
        if (aVar.f3650j == 0) {
            aVar.f3650j = currentTimeMillis;
        }
        if (aVar.f3648h == 1) {
            f3641k.mkdir();
        }
        int i = aVar.f3648h;
        long j = currentTimeMillis - aVar.f3650j;
        String str = "frame_" + String.format("%03d_", new Object[]{Integer.valueOf(i)}) + String.format("%04d", new Object[]{Long.valueOf(j)});
        try {
            Log.d("TestCreation", "Saving YUV frame: " + f3641k + "/" + str + ".yuv");
            FileOutputStream fileOutputStream = new FileOutputStream(new File(f3641k, str + ".yuv"));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            bufferedOutputStream.write(bArr);
            bufferedOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Log.d("TestCreation", "Saving frame: " + str + ".jpg");
            aVar.f3646f.saveJPEGImage(aVar.f3646f.convertYUVtoJPEG(aVar.f3642b, aVar.f3643c, aVar.f3644d, 100), new File(f3641k, str + ".jpg"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
