package com.jumio.p311nv.barcode.environment;

import android.graphics.Point;

/* renamed from: com.jumio.nv.barcode.environment.RecognizerWrapper */
public class RecognizerWrapper {

    /* renamed from: com.jumio.nv.barcode.environment.RecognizerWrapper$a */
    public interface C4446a {
        /* renamed from: a */
        void mo43359a(Point[] pointArr, int i, int i2);
    }

    private native void terminate();

    public native String init(String str, int i);

    public native String recognize(byte[] bArr, int i, int i2, int i3);

    /* renamed from: a */
    public void mo43356a(C4446a aVar) {
        RecognizerCallback.addListener(aVar);
    }

    /* renamed from: a */
    public void mo43355a() {
        terminate();
        RecognizerCallback.releaseListeners();
    }
}
