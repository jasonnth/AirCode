package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.UserDetailsActionRow;
import com.airbnb.p027n2.primitives.imaging.ImageSize;

public abstract class BookingListingDetailsSummaryEpoxyModel extends ListingDetailsSummaryEpoxyModel {
    public void bind(UserDetailsActionRow row) {
        super.bind(row);
        Context context = row.getContext();
        if (this.listing != null) {
            row.setTitleText(context.getString(C0716R.string.room_type_in_city, new Object[]{this.listing.getRoomType(context), this.listing.getCity()}));
            row.setHomeImageUrl(this.listing.getPhoto().getUrlForSize(ImageSize.LandscapeSmall));
            return;
        }
        row.setTitleText(null);
        row.setHomeImageUrl(null);
    }
}
