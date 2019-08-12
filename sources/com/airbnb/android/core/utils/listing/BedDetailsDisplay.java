package com.airbnb.android.core.utils.listing;

import android.content.Context;
import android.text.SpannableStringBuilder;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.BedDetailType;
import com.airbnb.android.core.models.BedType;
import com.airbnb.android.core.models.ListingRoom;
import com.airbnb.android.core.utils.SpannableUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;

public class BedDetailsDisplay {
    public static String getRoomName(Context context, ListingRoom room) {
        return getRoomName(context, room.getRoomNumber());
    }

    public static String getRoomName(Context context, int roomNumber) {
        if (roomNumber == 0) {
            return context.getString(C0716R.string.room_name_common_areas);
        }
        return context.getString(C0716R.string.room_name_bedroom_x, new Object[]{Integer.valueOf(roomNumber)});
    }

    public static CharSequence getIconsText(Context context, ListingRoom room, boolean keepUnknowns) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        for (BedType bedType : room.getSortedBedTypes()) {
            for (int i = 0; i < bedType.getQuantity(); i++) {
                if (keepUnknowns || bedType.getType() != BedDetailType.Unknown) {
                    SpannableUtils.appendImageSpan(context, builder, bedType.getType().iconRes);
                    builder.append(" ");
                }
            }
        }
        return builder;
    }

    public static CharSequence getRoomDescription(Context context, ListingRoom room, boolean keepUnknowns) {
        return Joiner.m1896on(", ").join(FluentIterable.from((Iterable<E>) room.getSortedBedTypes()).filter(BedDetailsDisplay$$Lambda$1.lambdaFactory$(keepUnknowns)).transform(BedDetailsDisplay$$Lambda$2.lambdaFactory$(context)));
    }

    static /* synthetic */ boolean lambda$getRoomDescription$0(boolean keepUnknowns, BedType bedType) {
        return keepUnknowns || bedType.getType() != BedDetailType.Unknown;
    }
}
