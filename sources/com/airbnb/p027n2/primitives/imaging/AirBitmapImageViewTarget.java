package com.airbnb.p027n2.primitives.imaging;

import android.graphics.Bitmap;
import android.widget.ImageView;

/* renamed from: com.airbnb.n2.primitives.imaging.AirBitmapImageViewTarget */
public class AirBitmapImageViewTarget extends AirBaseImageViewTarget<Bitmap> {
    public AirBitmapImageViewTarget(ImageView view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public void setResource(Bitmap resource) {
        ((ImageView) getView()).setImageBitmap(resource);
    }
}
