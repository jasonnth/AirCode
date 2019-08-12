package com.miteksystems.misnap.analyzer;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import com.miteksystems.misnap.events.ShutdownEvent;
import com.miteksystems.misnap.imaging.JPEGProcessor;
import com.miteksystems.misnap.params.ParameterManager;
import com.miteksystems.misnap.params.SDKConstants;
import com.miteksystems.misnap.utils.Utils;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import p314de.greenrobot.event.EventBus;

/* renamed from: com.miteksystems.misnap.analyzer.c */
public class C4569c extends MiSnapAnalyzer {

    /* renamed from: a */
    public static final String f3659a = C4569c.class.getSimpleName();
    /* access modifiers changed from: private */

    /* renamed from: l */
    public static File f3660l;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public byte[] f3661b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public int f3662c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public int f3663d;

    /* renamed from: e */
    private int f3664e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public int f3665f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public int f3666g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public JPEGProcessor f3667h = new JPEGProcessor();

    /* renamed from: i */
    private boolean f3668i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public boolean f3669j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public int f3670k;

    /* renamed from: com.miteksystems.misnap.analyzer.c$a */
    class C4571a extends AsyncTask<Void, Void, int[]> {
        C4571a() {
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ Object doInBackground(Object[] objArr) {
            return m2145a();
        }

        /* access modifiers changed from: protected */
        public final /* synthetic */ void onPostExecute(Object obj) {
            int[] iArr = (int[]) obj;
            super.onPostExecute(iArr);
            if (iArr == null) {
                C4569c.this.f3669j = false;
                return;
            }
            C4569c cVar = C4569c.this;
            C4569c.this.f3670k;
            File a = cVar.m2134a("yuv");
            if (a != null) {
                File file = new File(C4569c.f3660l, a.getName().replace("yuv", "jpg"));
                Log.d("TestCreation", "Saving JPEG frame: " + C4569c.f3660l + "/" + file);
                byte[] convertYUVtoJPEG = C4569c.this.f3667h.convertYUVtoJPEG(C4569c.this.f3661b, C4569c.this.f3662c, C4569c.this.f3663d, 100);
                Utils.sendMsgToUIFragment(C4569c.this.mAppContext, SDKConstants.UI_FRAGMENT_SHOW_REPLAY_FRAME, convertYUVtoJPEG, new int[]{0, 0}, new int[]{C4569c.this.f3662c - 1, 0}, new int[]{C4569c.this.f3662c - 1, C4569c.this.f3663d - 1}, new int[]{0, C4569c.this.f3663d - 1});
                C4569c.this.f3667h.saveJPEGImage(convertYUVtoJPEG, file);
                C4569c.m2135a(C4569c.this, iArr);
            } else {
                EventBus.getDefault().post(new ShutdownEvent(5, "RESULT_ERROR_TESTREPLAY_FINISHED"));
            }
            C4569c.this.f3669j = false;
        }

        /* renamed from: a */
        private int[] m2145a() {
            Log.v(C4569c.f3659a, "onPreviewFrame - received requ:" + C4569c.this.f3661b.length);
            int[] iArr = new int[28];
            try {
                C4569c.this.mScience.Analyze(C4569c.this.f3661b, C4569c.this.f3662c, C4569c.this.f3663d, C4569c.this.f3665f, C4569c.this.f3666g, iArr);
                Log.v(C4569c.f3659a, "onPreviewFrame - resp received from SL");
                return iArr;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m2135a(C4569c cVar, int[] iArr) {
        File a = cVar.m2134a("yuv");
        if (a != null) {
            File file = new File(f3660l, a.getName().replace("yuv", "csv"));
            try {
                Log.d("TestCreation", "Saving science data: " + f3660l + "/" + file);
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                StringBuilder sb = new StringBuilder();
                for (int valueOf : iArr) {
                    sb.append(String.valueOf(valueOf));
                    sb.append(',');
                }
                sb.append(cVar.f3662c);
                sb.append(',');
                sb.append(cVar.f3663d);
                sb.append(',');
                sb.append(Build.MANUFACTURER.replaceAll(" ", "-") + "_" + Build.MODEL.replaceAll(" ", "-"));
                bufferedWriter.write(sb.toString());
                bufferedWriter.close();
                fileWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.i("Returned Science value", "nScienceConfidence:" + iArr[2]);
            Log.i("Returned Science value", "nScienceBrightness:" + iArr[0]);
            Log.i("Returned Science value", "nScienceSharpness:" + iArr[1]);
            Log.i("Returned Science value", "nScienceMaxSkewAngle:" + iArr[3]);
            Log.i("Returned Science value", "nScienceRotationAngle:" + iArr[4]);
            Log.i("Returned Science value", "nScienceMinHorizontalFill:" + iArr[5]);
            Log.i("Returned Science value", "nScienceMinPadding:" + iArr[7]);
            Log.i("Returned Science value", "nScienceAreaRatio:" + iArr[6]);
            Log.i("Returned Science value", "nScienceUpperLeftCorner:" + iArr[16] + "," + iArr[17]);
            Log.i("Returned Science value", "nScienceBottomRightCorner:" + iArr[18] + "," + iArr[19]);
            Log.i("Returned Science value", "nScienceLowContrast:" + iArr[22]);
            Log.i("Returned Science value", "nScienceBusyBackground:" + iArr[21]);
            Log.i("Returned Science value", "nScienceMicrConfidence:" + iArr[23]);
        }
    }

    public C4569c(Context context, ParameterManager parameterManager) {
        super(context, parameterManager, true);
        f3660l = new File(parameterManager.getTestScienceSessionName());
    }

    public boolean init() {
        Log.d(f3659a, "Initializing TestScienceReplayAnalyzer");
        this.f3668i = true;
        return super.init();
    }

    public void deinit() {
        super.deinit();
        Log.d(f3659a, "Deinit TestScienceReplayAnalyzer");
        this.f3661b = null;
    }

    public void analyze(IAnalyzeResponse iAnalyzeResponse, byte[] bArr, int i, int i2, int i3) {
        super.analyze(iAnalyzeResponse, bArr, i, i2, i3);
        if (!this.f3669j) {
            this.f3661b = m2138b();
            if (bArr.length == 2073600) {
                this.f3662c = 1920;
                this.f3663d = 1080;
            } else if (bArr.length == 921600) {
                this.f3662c = 1280;
                this.f3663d = 720;
            } else {
                this.f3662c = i;
                this.f3663d = i2;
            }
            this.f3664e = i3;
            this.f3665f = getNativeColorSpace(i3);
            this.f3666g = getNativeDocType();
            this.f3669j = true;
            if (this.f3661b == null) {
                iAnalyzeResponse.onAnalyzeSuccess(0, null);
            } else {
                new C4571a().execute(new Void[0]);
            }
        }
    }

    /* renamed from: b */
    private byte[] m2138b() {
        int read;
        byte[] bArr = null;
        this.f3670k++;
        try {
            File a = m2134a("yuv");
            if (a != null) {
                Log.d("TestCreation", "Loading frame: " + f3660l + "/" + a.toString());
                FileInputStream fileInputStream = new FileInputStream(a);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                bArr = new byte[bufferedInputStream.available()];
                int i = 0;
                do {
                    read = bufferedInputStream.read(bArr, i, Math.min(8192, bArr.length - i));
                    i += read;
                } while (read > 0);
                bufferedInputStream.close();
                fileInputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public File m2134a(String str) {
        final String format = String.format("%03d", new Object[]{Integer.valueOf(this.f3670k)});
        File[] listFiles = f3660l.listFiles(new FilenameFilter() {
            public final boolean accept(File file, String str) {
                return str.startsWith("frame_" + format);
            }
        });
        if (listFiles != null) {
            for (File file : listFiles) {
                if (file.toString().endsWith(str)) {
                    return file;
                }
            }
        }
        EventBus.getDefault().post(new ShutdownEvent(5, "RESULT_ERROR_TESTREPLAY_FINISHED"));
        return null;
    }
}
