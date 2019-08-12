package com.airbnb.android.listing.utils;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.listing.C7213R;
import java.util.ArrayList;
import java.util.List;

public class AdditionalRequirementsHelper {
    public static InstantBookingAllowedCategory getInstantBookingAllowedCategory(Listing listing) {
        return InstantBookingAllowedCategory.fromNullableKey(listing.getInstantBookingAllowedCategory());
    }

    public static void updateAdditionalRequirementsRow(Listing listing, Context context, StandardRowEpoxyModel_ requirementsRow) {
        InstantBookingAllowedCategory category = InstantBookingAllowedCategory.fromNullableKey(listing.getInstantBookingAllowedCategory());
        if (category == InstantBookingAllowedCategory.Off) {
            requirementsRow.subtitle(C7213R.string.lys_additional_requirements_ib_only).actionText(C7213R.string.add);
        } else if (category == InstantBookingAllowedCategory.EveryOne) {
            requirementsRow.subtitle(C7213R.string.none).actionText(C7213R.string.add);
        } else {
            List<String> additionalRequirements = new ArrayList<>();
            if (category.isGovernmentIdNeeded()) {
                additionalRequirements.add(context.getString(C7213R.string.lys_additional_requirements_government_id));
            }
            if (category.isHighRatingNeeded()) {
                additionalRequirements.add(context.getString(C7213R.string.lys_additional_requirements_host_recommendation));
            }
            requirementsRow.subtitle((CharSequence) TextUtils.join(", ", additionalRequirements)).actionText(C7213R.string.edit);
        }
    }
}
