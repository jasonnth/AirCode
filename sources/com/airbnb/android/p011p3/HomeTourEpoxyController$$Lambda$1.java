package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.p011p3.models.RoomPhoto;

/* renamed from: com.airbnb.android.p3.HomeTourEpoxyController$$Lambda$1 */
final /* synthetic */ class HomeTourEpoxyController$$Lambda$1 implements OnClickListener {
    private final HomeTourEpoxyController arg$1;
    private final RoomPhoto arg$2;

    private HomeTourEpoxyController$$Lambda$1(HomeTourEpoxyController homeTourEpoxyController, RoomPhoto roomPhoto) {
        this.arg$1 = homeTourEpoxyController;
        this.arg$2 = roomPhoto;
    }

    public static OnClickListener lambdaFactory$(HomeTourEpoxyController homeTourEpoxyController, RoomPhoto roomPhoto) {
        return new HomeTourEpoxyController$$Lambda$1(homeTourEpoxyController, roomPhoto);
    }

    public void onClick(View view) {
        view.getContext().startActivity(HomeTourImageViewerActivity.intent(this.arg$1.context, this.arg$2));
    }
}
