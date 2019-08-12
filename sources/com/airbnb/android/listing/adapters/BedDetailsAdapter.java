package com.airbnb.android.listing.adapters;

import android.content.Context;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.utils.listing.BedDetailsDisplay;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import java.util.List;

public class BedDetailsAdapter extends AirEpoxyAdapter {
    private final Context context;
    private final DocumentMarqueeEpoxyModel_ header = new DocumentMarqueeEpoxyModel_();
    private final Listener listener;
    private final int numBedrooms;

    public interface Listener {
        void roomSelected(int i);
    }

    public enum Mode {
        ManageListing,
        ListYourSpace
    }

    public BedDetailsAdapter(Mode mode, Context context2, List<ListingRoom> rooms, int numBedrooms2, Listener listener2) {
        super(true);
        enableDiffing();
        this.context = context2;
        this.listener = listener2;
        this.numBedrooms = numBedrooms2;
        this.header.titleRes(mode == Mode.ManageListing ? C7213R.string.manage_listing_bed_details_title : C7213R.string.lys_dls_bed_details_title).captionRes(mode == Mode.ManageListing ? C7213R.string.manage_listing_bed_details_subtitle : 0);
        addModel(this.header);
        setRoomModels(rooms);
    }

    public void updateRooms(List<ListingRoom> rooms) {
        removeAllAfterModel(this.header);
        setRoomModels(rooms);
        notifyModelsChanged();
    }

    private void setRoomModels(List<ListingRoom> rawRooms) {
        ListingRoom[] roomsByRoomNumber = getRoomsByRoomNumber(rawRooms);
        for (Integer roomNumber : ListingRoom.orderedRoomNumbers(this.numBedrooms)) {
            ListingRoom existingRoom = roomsByRoomNumber[roomNumber.intValue()];
            if (existingRoom != null) {
                addModel(createExistingRoomRow(existingRoom));
            } else {
                addModel(createNewRoomRow(roomNumber.intValue()));
            }
        }
    }

    private ListingRoom[] getRoomsByRoomNumber(List<ListingRoom> rawRooms) {
        ListingRoom[] roomsByRoomNumber = new ListingRoom[(this.numBedrooms + 1)];
        for (ListingRoom room : rawRooms) {
            if (room.getRoomNumber() <= this.numBedrooms) {
                roomsByRoomNumber[room.getRoomNumber()] = room;
            }
        }
        return roomsByRoomNumber;
    }

    private StandardRowEpoxyModel_ createExistingRoomRow(ListingRoom room) {
        return new StandardRowEpoxyModel_().title((CharSequence) BedDetailsDisplay.getRoomName(this.context, room)).subtitle(BedDetailsDisplay.getRoomDescription(this.context, room, false)).actionText(room.getBedTypes().isEmpty() ? C7213R.string.manage_listing_bed_details_add_beds_action : C7213R.string.edit).clickListener(BedDetailsAdapter$$Lambda$1.lambdaFactory$(this, room));
    }

    private StandardRowEpoxyModel_ createNewRoomRow(int roomNumber) {
        return new StandardRowEpoxyModel_().title((CharSequence) BedDetailsDisplay.getRoomName(this.context, roomNumber)).actionText(C7213R.string.manage_listing_bed_details_add_beds_action).clickListener(BedDetailsAdapter$$Lambda$2.lambdaFactory$(this, roomNumber));
    }
}
