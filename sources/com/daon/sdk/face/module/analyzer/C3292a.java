package com.daon.sdk.face.module.analyzer;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import com.daon.sdk.face.YUV;
import com.daon.sdk.face.module.C3290a;
import com.daon.sdk.face.module.C3290a.C3291a;

/* renamed from: com.daon.sdk.face.module.analyzer.a */
public final class C3292a implements SensorEventListener, C3290a {

    /* renamed from: a */
    private SensorManager f3057a;

    /* renamed from: b */
    private float[] f3058b;

    /* renamed from: c */
    private float[] f3059c;

    /* renamed from: d */
    private Bundle f3060d = new Bundle();

    /* renamed from: e */
    private C3291a f3061e;

    /* renamed from: f */
    private Sensor f3062f;

    /* renamed from: g */
    private Sensor f3063g;

    /* renamed from: h */
    private boolean f3064h = false;

    /* renamed from: i */
    private boolean f3065i = false;

    public C3292a(Context context) {
        this.f3057a = (SensorManager) context.getSystemService("sensor");
        if (this.f3057a != null) {
            this.f3064h = (this.f3057a.getDefaultSensor(1) == null || this.f3057a.getDefaultSensor(2) == null) ? false : true;
            if (this.f3064h) {
                this.f3062f = this.f3057a.getDefaultSensor(1);
                this.f3063g = this.f3057a.getDefaultSensor(2);
                return;
            }
            Log.i("DAON", "Device position sensor not supported.");
            return;
        }
        Log.i("DAON", "Sensor service not supported.");
    }

    /* renamed from: b */
    private void m1763b() {
        if (!this.f3065i && this.f3064h) {
            Log.d("DAON", "Device Position: Started");
            this.f3057a.registerListener(this, this.f3062f, 3);
            this.f3057a.registerListener(this, this.f3063g, 3);
            this.f3065i = true;
        }
    }

    public final void onAccuracyChanged(Sensor sensor, int i) {
    }

    public final void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case 1:
                this.f3058b = (float[]) event.values.clone();
                break;
            case 2:
                this.f3059c = (float[]) event.values.clone();
                break;
            default:
                return;
        }
        if (this.f3058b != null && this.f3059c != null) {
            float[] fArr = new float[9];
            if (SensorManager.getRotationMatrix(fArr, new float[9], this.f3058b, this.f3059c)) {
                float[] fArr2 = new float[3];
                SensorManager.getOrientation(fArr, fArr2);
                float f = (float) (((double) fArr2[0]) * 57.29577951308232d);
                float f2 = (float) (((double) fArr2[1]) * 57.29577951308232d);
                float f3 = (float) (((double) fArr2[2]) * 57.29577951308232d);
                this.f3060d.putFloat("result.sensor.azimuth", f);
                this.f3060d.putFloat("result.sensor.pitch", f2);
                this.f3060d.putFloat("result.sensor.roll", f3);
                if (this.f3061e != null) {
                    this.f3061e.mo28786a(this.f3060d);
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo28820a(YUV yuv, C3291a aVar) {
        this.f3061e = aVar;
        m1763b();
    }

    /* renamed from: a */
    public final void mo28821a() {
        this.f3065i = false;
        this.f3057a.unregisterListener(this);
    }

    /* renamed from: a */
    public final void mo28822a(Bundle bundle) {
    }
}
