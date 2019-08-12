package com.daon.sdk.face.module.analyzer;

import android.os.Bundle;
import com.daon.sdk.face.YUV;
import com.daon.sdk.face.module.C3290a;
import com.daon.sdk.face.module.C3290a.C3291a;

public class QualityAnalyzer implements C3290a {

    /* renamed from: a */
    private static int f3045a = 0;

    /* renamed from: b */
    private static int f3046b = 1;

    /* renamed from: c */
    private static int f3047c = 2;

    /* renamed from: d */
    private static int f3048d = 3;

    /* renamed from: e */
    private static int f3049e = 4;

    /* renamed from: f */
    private static int f3050f = 5;

    /* renamed from: g */
    private static int f3051g = 6;

    /* renamed from: h */
    private static int f3052h = 7;

    /* renamed from: i */
    private static int f3053i = 8;

    /* renamed from: j */
    private static int f3054j = 9;

    /* renamed from: k */
    private static int f3055k = 10;

    /* renamed from: l */
    private int f3056l = 0;

    private native float[] processFrameJNI(byte[] bArr, int i, int i2, int i3, boolean z);

    static {
        System.loadLibrary("FaceQuality-jni");
    }

    public QualityAnalyzer(int orientation) {
        if (orientation == 90) {
            this.f3056l = 1;
        } else if (orientation == 270) {
            this.f3056l = 2;
        }
    }

    /* renamed from: a */
    public final void mo28822a(Bundle bundle) {
    }

    /* renamed from: a */
    private static Bundle m1759a(float[] fArr) {
        Bundle bundle = new Bundle();
        float f = fArr[f3048d];
        float f2 = fArr[0];
        float f3 = fArr[f3049e];
        float f4 = fArr[f3046b];
        float f5 = fArr[f3047c];
        int i = (int) fArr[f3050f];
        int i2 = (int) fArr[f3051g];
        int i3 = (int) fArr[f3052h];
        int i4 = (int) fArr[f3053i];
        int i5 = (int) fArr[f3054j];
        float f6 = fArr[f3055k];
        bundle.putFloat("lighting.uniform.confidence", f);
        bundle.putFloat("face.found.confidence", f2);
        bundle.putFloat("face.frontal.confidence", f3);
        bundle.putFloat("eyes.found.confidence", f4);
        bundle.putFloat("eyes.open.confidence", f5);
        bundle.putInt("eyes.distance", i);
        bundle.putInt("pose.angle", i2);
        bundle.putInt("sharpness", i3);
        bundle.putInt("exposure", i4);
        bundle.putInt("grayscale.density", i5);
        bundle.putFloat("global.score", f6);
        return bundle;
    }

    /* renamed from: a */
    public final void mo28820a(YUV yuv, C3291a aVar) {
        aVar.mo28786a(m1759a(processFrameJNI(yuv.getData(), yuv.getWidth(), yuv.getHeight(), this.f3056l, true)));
    }

    /* renamed from: a */
    public final void mo28821a() {
    }
}
