package com.airbnb.p027n2.primitives.imaging;

import com.bumptech.glide.request.target.SizeReadyCallback;

/* renamed from: com.airbnb.n2.primitives.imaging.ThumbnailHelper$$Lambda$1 */
final /* synthetic */ class ThumbnailHelper$$Lambda$1 implements SizeReadyCallback {
    private final ThumbnailHelper arg$1;
    private final Image arg$2;
    private final String arg$3;

    private ThumbnailHelper$$Lambda$1(ThumbnailHelper thumbnailHelper, Image image, String str) {
        this.arg$1 = thumbnailHelper;
        this.arg$2 = image;
        this.arg$3 = str;
    }

    public static SizeReadyCallback lambdaFactory$(ThumbnailHelper thumbnailHelper, Image image, String str) {
        return new ThumbnailHelper$$Lambda$1(thumbnailHelper, image, str);
    }

    public void onSizeReady(int i, int i2) {
        ThumbnailHelper.lambda$setImageDetails$0(this.arg$1, this.arg$2, this.arg$3, i, i2);
    }
}
