package com.daon.sdk.face;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.airbnb.android.core.responses.OfficialIdStatusResponse;
import com.daon.sdk.face.license.C3289a;
import com.daon.sdk.face.module.C3290a;
import com.daon.sdk.face.module.C3290a.C3291a;
import com.daon.sdk.face.module.C3296b;
import com.daon.sdk.face.module.C3297c;
import com.daon.sdk.face.module.analyzer.C3292a;
import com.daon.sdk.face.module.analyzer.C3293b;
import com.daon.sdk.face.module.analyzer.C3295c;
import com.daon.sdk.face.module.analyzer.QualityAnalyzer;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class DaonFace {
    public static int OPTION_DEVICE_POSITION = 8;
    public static int OPTION_LIVENESS_BLINK = 1;
    public static int OPTION_LIVENESS_HMD = 2;
    public static int OPTION_QUALITY = 4;
    public static int OPTION_RECOGNITION = 16;

    /* renamed from: a */
    Bundle f3018a = new Bundle();

    /* renamed from: b */
    private int f3019b = 640;

    /* renamed from: c */
    private int f3020c = 480;

    /* renamed from: d */
    private int f3021d;

    /* renamed from: e */
    private C3297c f3022e = null;

    /* renamed from: f */
    private ArrayList<C3290a> f3023f = new ArrayList<>();

    public interface AnalysisCallback {
        void onAnalysisResult(YUV yuv, Result result);
    }

    public DaonFace(Context context, int options) {
        C3289a aVar = new C3289a(m1746a(context, "license.txt"));
        if (aVar.mo28818b()) {
            Log.d("DAON", "Initialize: License OK");
            String packageName = context.getPackageName();
            String c = aVar.mo28819c();
            if (!packageName.startsWith(c)) {
                m1747b(context, "Package name mismatch.\n\n" + packageName + "\n\nLicense:\n" + c);
            }
            if (aVar.mo28817a()) {
                m1747b(context, "License has expired");
            }
        } else {
            Log.d("DAON", "Initialize: License not verified");
            m1747b(context, "Unable to verify license");
        }
        this.f3021d = m1745a();
        this.f3022e = new C3295c(context);
        if ((OPTION_QUALITY & options) == OPTION_QUALITY) {
            this.f3023f.add(new QualityAnalyzer(this.f3021d));
        }
        if ((OPTION_LIVENESS_HMD & options) == OPTION_LIVENESS_HMD) {
            this.f3023f.add(new C3293b(context, this.f3021d));
        }
        if ((OPTION_LIVENESS_BLINK & options) == OPTION_LIVENESS_BLINK) {
            this.f3023f.add((C3290a) this.f3022e);
        }
        if ((OPTION_DEVICE_POSITION & options) == OPTION_DEVICE_POSITION) {
            this.f3023f.add(new C3292a(context));
        }
    }

    /* renamed from: a */
    private static int m1745a() {
        int numberOfCameras = Camera.getNumberOfCameras();
        CameraInfo cameraInfo = new CameraInfo();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == 1) {
                return cameraInfo.orientation;
            }
        }
        return 0;
    }

    public void setImageSize(int width, int height) {
        this.f3019b = width;
        this.f3020c = height;
        if (this.f3022e != null) {
            this.f3022e.mo28825a(width, height);
        }
    }

    public void setConfiguration(Bundle config) {
        Iterator it = this.f3023f.iterator();
        while (it.hasNext()) {
            C3296b bVar = (C3296b) it.next();
            if (bVar instanceof C3296b) {
                bVar.mo28822a(config);
            }
        }
    }

    public void analyze(byte[] image, final AnalysisCallback callback) {
        final YUV yuv = new YUV(image, this.f3019b, this.f3020c);
        Iterator it = this.f3023f.iterator();
        while (it.hasNext()) {
            ((C3290a) it.next()).mo28820a(yuv, new C3291a() {
                /* renamed from: a */
                public final void mo28786a(Bundle bundle) {
                    DaonFace.this.f3018a.putAll(bundle);
                    callback.onAnalysisResult(yuv, new Result(DaonFace.this.f3018a));
                }
            });
        }
    }

    public void stop() {
        Iterator it = this.f3023f.iterator();
        while (it.hasNext()) {
            ((C3290a) it.next()).mo28821a();
        }
    }

    /* renamed from: a */
    private static InputStream m1746a(Context context, String str) {
        try {
            return context.getAssets().open(str);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    private static void m1747b(final Context context, final String str) {
        if (context != null) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public final void run() {
                    Builder builder = new Builder(context);
                    builder.setMessage(str).setTitle("IdentityX Device SDK");
                    builder.setPositiveButton(OfficialIdStatusResponse.f1090OK, new OnClickListener(this) {
                        public final void onClick(DialogInterface dialog, int i) {
                            dialog.cancel();
                        }
                    });
                    try {
                        builder.create().show();
                    } catch (Exception e) {
                        Log.d("DAON", str);
                    }
                }
            });
        }
    }
}
