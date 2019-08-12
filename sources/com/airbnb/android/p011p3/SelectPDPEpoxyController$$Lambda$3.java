package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.p011p3.models.Room;

/* renamed from: com.airbnb.android.p3.SelectPDPEpoxyController$$Lambda$3 */
final /* synthetic */ class SelectPDPEpoxyController$$Lambda$3 implements OnClickListener {
    private final SelectPDPEpoxyController arg$1;
    private final Room arg$2;

    private SelectPDPEpoxyController$$Lambda$3(SelectPDPEpoxyController selectPDPEpoxyController, Room room) {
        this.arg$1 = selectPDPEpoxyController;
        this.arg$2 = room;
    }

    public static OnClickListener lambdaFactory$(SelectPDPEpoxyController selectPDPEpoxyController, Room room) {
        return new SelectPDPEpoxyController$$Lambda$3(selectPDPEpoxyController, room);
    }

    public void onClick(View view) {
        this.arg$1.context.startActivity(HomeTourActivity.intentWithRoomId(this.arg$1.context, this.arg$1.controller.getMockHomeLayout(), this.arg$2.mo11086id()));
    }
}
