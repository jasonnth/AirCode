package com.jumio.p311nv.gui;

import android.graphics.Bitmap;
import android.graphics.Rect;

/* renamed from: com.jumio.nv.gui.ScanOverlay */
public class ScanOverlay {
    public Rect bounds;
    public Bitmap image;

    public ScanOverlay(Bitmap bitmap) {
        this.image = bitmap;
    }
}
