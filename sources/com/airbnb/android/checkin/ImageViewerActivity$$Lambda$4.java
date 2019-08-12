package com.airbnb.android.checkin;

import com.airbnb.p027n2.collections.Carousel.OnSnapToPositionListener;
import java.util.List;

final /* synthetic */ class ImageViewerActivity$$Lambda$4 implements OnSnapToPositionListener {
    private final ImageViewerActivity arg$1;
    private final List arg$2;

    private ImageViewerActivity$$Lambda$4(ImageViewerActivity imageViewerActivity, List list) {
        this.arg$1 = imageViewerActivity;
        this.arg$2 = list;
    }

    public static OnSnapToPositionListener lambdaFactory$(ImageViewerActivity imageViewerActivity, List list) {
        return new ImageViewerActivity$$Lambda$4(imageViewerActivity, list);
    }

    public void onSnappedToPosition(int i, boolean z, boolean z2) {
        this.arg$1.currentUrl = (String) this.arg$2.get(i);
    }
}
