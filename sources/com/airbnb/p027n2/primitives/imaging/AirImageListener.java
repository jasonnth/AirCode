package com.airbnb.p027n2.primitives.imaging;

import android.graphics.Bitmap;

/* renamed from: com.airbnb.n2.primitives.imaging.AirImageListener */
public interface AirImageListener {
    void onErrorResponse(Exception exc);

    void onResponse(Bitmap bitmap, boolean z);
}
