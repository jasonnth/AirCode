package com.airbnb.p027n2.components.image_viewer;

import com.airbnb.p027n2.components.image_viewer.ImageViewer.ImageViewerData;
import com.google.common.base.Function;

/* renamed from: com.airbnb.n2.components.image_viewer.ImageViewerActivity$$Lambda$2 */
final /* synthetic */ class ImageViewerActivity$$Lambda$2 implements Function {
    private static final ImageViewerActivity$$Lambda$2 instance = new ImageViewerActivity$$Lambda$2();

    private ImageViewerActivity$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new ImageViewerData((String) obj);
    }
}
