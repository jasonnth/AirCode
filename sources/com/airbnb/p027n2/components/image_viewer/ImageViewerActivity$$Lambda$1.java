package com.airbnb.p027n2.components.image_viewer;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.image_viewer.ImageViewerActivity$$Lambda$1 */
final /* synthetic */ class ImageViewerActivity$$Lambda$1 implements OnClickListener {
    private final ImageViewerActivity arg$1;

    private ImageViewerActivity$$Lambda$1(ImageViewerActivity imageViewerActivity) {
        this.arg$1 = imageViewerActivity;
    }

    public static OnClickListener lambdaFactory$(ImageViewerActivity imageViewerActivity) {
        return new ImageViewerActivity$$Lambda$1(imageViewerActivity);
    }

    public void onClick(View view) {
        this.arg$1.onBackPressed();
    }
}
