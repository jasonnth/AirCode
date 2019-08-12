package com.airbnb.android.p011p3;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.p011p3.epoxyModels.HomeTourHighlightEpoxyModel_;
import com.airbnb.android.p011p3.epoxyModels.HomeTourRoomEpoxyModel_;
import com.airbnb.android.p011p3.models.Floor;
import com.airbnb.android.p011p3.models.Room;
import com.airbnb.android.p011p3.models.RoomDetail;
import com.airbnb.android.p011p3.models.RoomPhoto;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;

/* renamed from: com.airbnb.android.p3.HomeTourEpoxyController */
public class HomeTourEpoxyController extends AirEpoxyController {
    private final Context context;
    private final HomeTourController controller;

    public HomeTourEpoxyController(Context context2, HomeTourController controller2) {
        this.context = context2;
        this.controller = controller2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        for (Floor floor : this.controller.getHomeLayout().floors()) {
            for (Room room : floor.rooms()) {
                for (RoomPhoto photo : room.photos()) {
                    new HomeTourRoomEpoxyModel_().m6347id(photo.mo11101id()).name(room.name()).subtitle(TextUtils.join(" · ", room.highlights())).image(new SimpleImage(photo.largeUrl(), photo.previewEncodedPng())).onImageClickListener(HomeTourEpoxyController$$Lambda$1.lambdaFactory$(this, photo)).addTo(this);
                }
                for (RoomDetail detail : room.details()) {
                    new HomeTourHighlightEpoxyModel_().m6335id(detail.photo().mo11101id()).image(new SimpleImage(detail.photo().largeUrl(), detail.photo().previewEncodedPng())).subtitle(detail.caption()).onImageClickListener(HomeTourEpoxyController$$Lambda$2.lambdaFactory$(this, detail)).addTo(this);
                }
            }
        }
    }
}
