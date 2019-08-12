package com.airbnb.android.p011p3;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.TextRowEpoxyModel_;
import com.airbnb.android.p011p3.epoxyModels.HomeTourGalleryImageEpoxyModel_;
import com.airbnb.android.p011p3.models.Floor;
import com.airbnb.android.p011p3.models.Room;
import com.airbnb.android.p011p3.models.RoomDetail;
import com.airbnb.android.p011p3.models.RoomPhoto;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.airbnb.p027n2.primitives.imaging.SimpleImage;
import com.airbnb.p027n2.utils.TextUtil;

/* renamed from: com.airbnb.android.p3.HomeTourGalleryEpoxyController */
public class HomeTourGalleryEpoxyController extends AirEpoxyController {
    private final Context context;
    private final HomeTourController controller;
    TextRowEpoxyModel_ homeDescriptionModel;
    DocumentMarqueeEpoxyModel_ homeTitleHeaderModel;

    public HomeTourGalleryEpoxyController(Context context2, HomeTourController controller2) {
        this.context = context2;
        this.controller = controller2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.homeTitleHeaderModel.titleText((CharSequence) "Modern and Cozy Designer Home in Los Angeles");
        this.homeDescriptionModel.text("This modern two story designer home features a private entry, two bedrooms, one bath, a full kitchen and balcony.");
        addRoomGalleryModels();
    }

    private void addRoomGalleryModels() {
        int imagePosition = 0;
        for (Floor floor : this.controller.getHomeLayout().floors()) {
            new SectionHeaderEpoxyModel_().m5554id(floor.mo11014id()).title(floor.name()).addTo(this);
            for (Room room : floor.rooms()) {
                SpannableStringBuilder builder = new SpannableStringBuilder();
                builder.append(TextUtil.makeCircularBold(this.context, room.name()));
                builder.append(" · ");
                builder.append(TextUtils.join(" · ", room.highlights()));
                new TextRowEpoxyModel_().m5686id(room.mo11086id()).text(builder).addTo(this);
                for (RoomPhoto roomPhoto : room.photos()) {
                    new HomeTourGalleryImageEpoxyModel_().m6323id(roomPhoto.mo11101id()).image(new SimpleImage(roomPhoto.largeUrl(), roomPhoto.previewEncodedPng())).controller(this.controller).imagePosition(imagePosition).addTo(this);
                    imagePosition++;
                }
                for (RoomDetail detail : room.details()) {
                    new HomeTourGalleryImageEpoxyModel_().m6323id(detail.photo().mo11101id()).image(new SimpleImage(detail.photo().largeUrl(), detail.photo().previewEncodedPng())).controller(this.controller).imagePosition(imagePosition).addTo(this);
                    imagePosition++;
                }
            }
        }
    }

    public int getSelectedRoomPosition() {
        EpoxyModel<?> model = getAdapter().getModelById(this.controller.getSelectedRoomId());
        if (model != null) {
            return getAdapter().getModelPosition(model);
        }
        return 0;
    }
}
