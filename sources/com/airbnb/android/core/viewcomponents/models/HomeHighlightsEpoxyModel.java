package com.airbnb.android.core.viewcomponents.models;

import android.content.res.Resources;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.BathroomType;
import com.airbnb.android.core.enums.SpaceType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.ListingUtils;
import com.airbnb.p027n2.components.HomeHighlights;
import com.airbnb.p027n2.components.HomeHighlights.IconRowData;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.ArrayList;

public abstract class HomeHighlightsEpoxyModel extends AirEpoxyModel<HomeHighlights> {
    float bathroomCount;
    BathroomType bathroomType;
    int bedCount;
    int bedroomCount;
    int personCapacity;
    SpaceType roomType;

    public void bind(HomeHighlights view) {
        String roomCount;
        super.bind(view);
        Resources res = view.getResources();
        ArrayList<IconRowData> list = new ArrayList<>(4);
        list.add(new IconRowData(C0716R.C0717drawable.p3_guest, res.getQuantityString(C0716R.plurals.x_guests, this.personCapacity, new Object[]{Integer.valueOf(this.personCapacity)})));
        if (this.bedroomCount == 0) {
            roomCount = res.getString(C0716R.string.listing_rooms_studio);
        } else {
            roomCount = res.getQuantityString(C0716R.plurals.rooms, this.bedroomCount, new Object[]{Integer.valueOf(this.bedroomCount)});
        }
        list.add(new IconRowData(C0716R.C0717drawable.p3_rooms, roomCount));
        list.add(new IconRowData(C0716R.C0717drawable.p3_beds, res.getQuantityString(C0716R.plurals.beds, this.bedCount, new Object[]{Integer.valueOf(this.bedCount)})));
        list.add(new IconRowData(C0716R.C0717drawable.p3_bathrooms, getBathString(res)));
        view.setData(list);
    }

    private String getBathString(Resources res) {
        boolean halfBath;
        if (this.bathroomCount == 0.5f) {
            halfBath = true;
        } else {
            halfBath = false;
        }
        String bathroomCountAsString = ListingUtils.getBathroomCountAsString(this.bathroomCount);
        if (this.roomType == SpaceType.PrivateRoom) {
            if (this.bathroomType == BathroomType.SharedBathroom) {
                if (halfBath) {
                    return res.getString(C0716R.string.p3_shared_half_bath);
                }
                return res.getString(C0716R.string.p3_listing_shared_bath, new Object[]{bathroomCountAsString});
            } else if (this.bathroomType != BathroomType.PrivateBathroom) {
                return res.getString(C0716R.string.listing_bath, new Object[]{bathroomCountAsString});
            } else if (halfBath) {
                return res.getString(C0716R.string.p3_private_half_bath);
            } else {
                return res.getString(C0716R.string.p3_listing_private_bath, new Object[]{bathroomCountAsString});
            }
        } else if (halfBath) {
            return res.getString(C0716R.string.p3_half_bath);
        } else {
            return res.getString(C0716R.string.listing_bath, new Object[]{bathroomCountAsString});
        }
    }

    public HomeHighlightsEpoxyModel listing(Listing listing) {
        this.personCapacity = listing.getPersonCapacity();
        this.bedroomCount = listing.getBedrooms();
        this.bedCount = listing.getBedCount();
        this.bathroomCount = listing.getBathrooms();
        this.bathroomType = listing.getBathroomType();
        this.roomType = listing.getSpaceType();
        return this;
    }

    public int getDividerViewType() {
        return 0;
    }
}
