package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.SelectListingRoom;
import com.airbnb.android.core.models.SelectRoomDescription;
import com.airbnb.android.core.models.SelectRoomMedia;
import com.airbnb.android.core.models.SelectRoomType;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.viewcomponents.models.InlineInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.p027n2.components.photorearranger.RearrangableLabeledPhotoCellModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.List;

public class ManageSelectPhotosEpoxyController extends AirEpoxyController {
    private static final SpanSizeOverrideCallback FULL_WIDTH_SPAN_CALLBACK = ManageSelectPhotosEpoxyController$$Lambda$5.lambdaFactory$();
    private static final SpanSizeOverrideCallback SINGLE_COLUMN_SPAN_CALLBACK = ManageSelectPhotosEpoxyController$$Lambda$6.lambdaFactory$();
    private final Context context;
    private SelectRoomMedia coverPhoto;
    RearrangableLabeledPhotoCellModel_ coverPhotoRow;
    private final Listener listener;
    private final SparseArray<SelectRoomType> roomTypeById = new SparseArray<>();
    private ImmutableList<SelectListingRoom> sortedRooms;
    ToolbarSpacerEpoxyModel_ spacer;

    public interface Listener {
        void captionDetailShotClicked(SelectRoomMedia selectRoomMedia);

        void roomHighlightsClicked(SelectListingRoom selectListingRoom);
    }

    public ManageSelectPhotosEpoxyController(Context context2, SelectRoomDescription roomDescriptions, Listener listener2) {
        this.context = context2;
        this.listener = listener2;
        for (SelectRoomType roomType : roomDescriptions.getRoomTypes()) {
            this.roomTypeById.put(roomType.getId(), roomType);
        }
    }

    static /* synthetic */ int lambda$static$0(int totalSpanCount, int position, int itemCount) {
        return totalSpanCount;
    }

    static /* synthetic */ int lambda$static$1(int totalSpanCount, int position, int itemCount) {
        return 1;
    }

    public void setListingRooms(List<SelectListingRoom> rooms) {
        this.sortedRooms = SelectListingRoom.sortRooms(rooms);
        this.coverPhoto = (SelectRoomMedia) FluentIterable.from((Iterable<E>) rooms).transformAndConcat(ManageSelectPhotosEpoxyController$$Lambda$1.lambdaFactory$()).firstMatch(ManageSelectPhotosEpoxyController$$Lambda$2.lambdaFactory$()).orNull();
        requestModelBuild();
    }

    private String getRoomName(SelectListingRoom room) {
        SelectRoomType type = (SelectRoomType) this.roomTypeById.get(room.getRoomType());
        if (type != null) {
            return type.getName();
        }
        return "";
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.spacer.addTo(this);
        if (this.coverPhoto != null) {
            this.coverPhotoRow.image(this.coverPhoto.getOriginalUrl()).layout(C7368R.layout.view_holder_labeled_photo_row).spanSizeOverride(FULL_WIDTH_SPAN_CALLBACK).label(this.context.getString(C7368R.string.manage_listing_photo_label_cover_photo)).addTo(this);
        }
        UnmodifiableIterator it = this.sortedRooms.iterator();
        while (it.hasNext()) {
            SelectListingRoom room = (SelectListingRoom) it.next();
            String roomName = getRoomName(room);
            if (!TextUtils.isEmpty(roomName) && !room.getMedia().isEmpty()) {
                new SectionHeaderEpoxyModel_().title(roomName).m5554id(room.getRoomId()).spanSizeOverride(FULL_WIDTH_SPAN_CALLBACK).addTo(this);
                List<SelectRoomMedia> detailShots = new ArrayList<>();
                for (SelectRoomMedia media : room.getMedia()) {
                    if (media.isDetailShot()) {
                        detailShots.add(media);
                    } else {
                        new RearrangableLabeledPhotoCellModel_().layout(C7368R.layout.view_holder_labeled_photo_cell).spanSizeOverride(SINGLE_COLUMN_SPAN_CALLBACK).image(media.getOriginalUrl()).mo11718id((CharSequence) media.getId()).addTo(this);
                    }
                }
                new InlineInputRowEpoxyModel_().titleRes(C7368R.string.manage_listing_select_room_highlights_row_title).inputMaxLines(2).m4885id((CharSequence) "room_highlights", room.getRoomId()).spanSizeOverride(FULL_WIDTH_SPAN_CALLBACK).clickListener(ManageSelectPhotosEpoxyController$$Lambda$3.lambdaFactory$(this, room)).addTo(this);
                for (SelectRoomMedia detailShot : detailShots) {
                    new RearrangableLabeledPhotoCellModel_().layout(C7368R.layout.view_holder_labeled_photo_row).spanSizeOverride(FULL_WIDTH_SPAN_CALLBACK).image(detailShot.getOriginalUrl()).mo11720id((CharSequence) "detail_shot", new CharSequence[]{detailShot.getId()}).addTo(this);
                    new InlineInputRowEpoxyModel_().titleRes(C7368R.string.manage_listing_photo_caption_input_title).inputMaxLines(2).input(detailShot.getCaption()).spanSizeOverride(FULL_WIDTH_SPAN_CALLBACK).m4886id((CharSequence) "detail_shot_caption", detailShot.getId()).clickListener(ManageSelectPhotosEpoxyController$$Lambda$4.lambdaFactory$(this, detailShot)).addTo(this);
                }
            } else if (!BuildHelper.isFuture()) {
                BugsnagWrapper.notify((Throwable) new RuntimeException("Invalid SelectListingRoom with id: " + Long.toString(room.getRoomId())));
            }
        }
    }
}
