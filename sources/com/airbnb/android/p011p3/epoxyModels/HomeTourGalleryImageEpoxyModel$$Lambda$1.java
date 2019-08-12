package com.airbnb.android.p011p3.epoxyModels;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.p3.epoxyModels.HomeTourGalleryImageEpoxyModel$$Lambda$1 */
final /* synthetic */ class HomeTourGalleryImageEpoxyModel$$Lambda$1 implements OnClickListener {
    private final HomeTourGalleryImageEpoxyModel arg$1;

    private HomeTourGalleryImageEpoxyModel$$Lambda$1(HomeTourGalleryImageEpoxyModel homeTourGalleryImageEpoxyModel) {
        this.arg$1 = homeTourGalleryImageEpoxyModel;
    }

    public static OnClickListener lambdaFactory$(HomeTourGalleryImageEpoxyModel homeTourGalleryImageEpoxyModel) {
        return new HomeTourGalleryImageEpoxyModel$$Lambda$1(homeTourGalleryImageEpoxyModel);
    }

    public void onClick(View view) {
        this.arg$1.controller.onImageClicked(this.arg$1.imagePosition);
    }
}
