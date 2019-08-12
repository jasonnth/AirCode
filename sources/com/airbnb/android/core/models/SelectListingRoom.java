package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSelectListingRoom;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.Comparator;
import java.util.List;

public class SelectListingRoom extends GenSelectListingRoom {
    public static final Creator<SelectListingRoom> CREATOR = new Creator<SelectListingRoom>() {
        public SelectListingRoom[] newArray(int size) {
            return new SelectListingRoom[size];
        }

        public SelectListingRoom createFromParcel(Parcel source) {
            SelectListingRoom object = new SelectListingRoom();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final Comparator<SelectListingRoom> GROUP_BY_LAYOUT_THEN_ROOM_COMPARATOR = SelectListingRoom$$Lambda$1.lambdaFactory$();

    static /* synthetic */ int lambda$static$0(SelectListingRoom lhs, SelectListingRoom rhs) {
        int result = lhs.getLayoutNumber() - rhs.getLayoutNumber();
        if (result == 0) {
            return lhs.getRoomNumber() - rhs.getRoomNumber();
        }
        return result;
    }

    public static ImmutableList<SelectListingRoom> sortRooms(List<SelectListingRoom> rooms) {
        return FluentIterable.from((Iterable<E>) rooms).toSortedList(GROUP_BY_LAYOUT_THEN_ROOM_COMPARATOR);
    }
}
