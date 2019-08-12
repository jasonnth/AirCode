package com.airbnb.android.checkin;

import com.airbnb.p027n2.components.image_viewer.ImageViewer.ImageViewerData;
import com.google.common.base.Function;

final /* synthetic */ class ImageViewerActivity$$Lambda$1 implements Function {
    private static final ImageViewerActivity$$Lambda$1 instance = new ImageViewerActivity$$Lambda$1();

    private ImageViewerActivity$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new ImageViewerData((String) obj);
    }
}
