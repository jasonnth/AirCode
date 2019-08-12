package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.p011p3.models.RoomDetail;

/* renamed from: com.airbnb.android.p3.HomeTourEpoxyController$$Lambda$2 */
final /* synthetic */ class HomeTourEpoxyController$$Lambda$2 implements OnClickListener {
    private final HomeTourEpoxyController arg$1;
    private final RoomDetail arg$2;

    private HomeTourEpoxyController$$Lambda$2(HomeTourEpoxyController homeTourEpoxyController, RoomDetail roomDetail) {
        this.arg$1 = homeTourEpoxyController;
        this.arg$2 = roomDetail;
    }

    public static OnClickListener lambdaFactory$(HomeTourEpoxyController homeTourEpoxyController, RoomDetail roomDetail) {
        return new HomeTourEpoxyController$$Lambda$2(homeTourEpoxyController, roomDetail);
    }

    public void onClick(View view) {
        view.getContext().startActivity(HomeTourImageViewerActivity.intent(this.arg$1.context, this.arg$2.photo()));
    }
}
