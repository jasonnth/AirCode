package com.airbnb.p027n2.components.photorearranger;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.photorearranger.PhotoRearrangerAdapter$$Lambda$1 */
final /* synthetic */ class PhotoRearrangerAdapter$$Lambda$1 implements OnClickListener {
    private final PhotoRearrangerAdapter arg$1;
    private final PhotoRearrangerItem arg$2;

    private PhotoRearrangerAdapter$$Lambda$1(PhotoRearrangerAdapter photoRearrangerAdapter, PhotoRearrangerItem photoRearrangerItem) {
        this.arg$1 = photoRearrangerAdapter;
        this.arg$2 = photoRearrangerItem;
    }

    public static OnClickListener lambdaFactory$(PhotoRearrangerAdapter photoRearrangerAdapter, PhotoRearrangerItem photoRearrangerItem) {
        return new PhotoRearrangerAdapter$$Lambda$1(photoRearrangerAdapter, photoRearrangerItem);
    }

    public void onClick(View view) {
        this.arg$1.modelSelected(this.arg$2);
    }
}
