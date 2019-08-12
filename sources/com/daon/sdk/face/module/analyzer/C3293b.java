package com.daon.sdk.face.module.analyzer;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import com.daon.sdk.face.YUV;
import com.daon.sdk.face.hmd.HeadMovementDetection;
import com.daon.sdk.face.hmd.HmdLivenessListener;
import com.daon.sdk.face.module.C3290a;
import com.daon.sdk.face.module.C3290a.C3291a;

/* renamed from: com.daon.sdk.face.module.analyzer.b */
public final class C3293b implements C3290a {

    /* renamed from: a */
    private HeadMovementDetection f3066a = new HeadMovementDetection();

    /* renamed from: b */
    private Context f3067b;

    /* renamed from: c */
    private int f3068c;

    /* renamed from: d */
    private Handler f3069d = new Handler(Looper.getMainLooper());

    public C3293b(Context context, int i) {
        this.f3067b = context;
        this.f3068c = i;
        Bundle bundle = new Bundle();
        bundle.putInt("hmd.settings.distance.cutoff", -1);
        bundle.putFloat("hmd.settings.threshold", 0.8f);
        bundle.putInt("hmd.settings.time.limit.nods", 800);
        bundle.putInt("hmd.settings.time.limit.shakes", 800);
        mo28822a(bundle);
    }

    /* renamed from: a */
    public final void mo28820a(YUV yuv, final C3291a aVar) {
        if (!this.f3066a.isStarted()) {
            this.f3066a.initialise(this.f3067b);
            this.f3066a.start(yuv.getWidth(), yuv.getHeight(), this.f3068c, 0);
            this.f3066a.setListener(new HmdLivenessListener() {
            });
        }
        this.f3066a.processImage(yuv.getData(), null);
    }

    /* renamed from: a */
    public final void mo28821a() {
        this.f3066a.stop();
        this.f3066a.destroy();
    }

    /* renamed from: a */
    public final void mo28822a(Bundle bundle) {
        this.f3066a.setConfig(bundle);
    }
}
