package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingRoom;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import java.util.Comparator;
import java.util.List;

public class ListingRoom extends GenListingRoom {
    public static Comparator<ListingRoom> COMMON_AREAS_LAST_COMPARATOR = ListingRoom$$Lambda$1.lambdaFactory$();
    public static final Creator<ListingRoom> CREATOR = new Creator<ListingRoom>() {
        public ListingRoom[] newArray(int size) {
            return new ListingRoom[size];
        }

        public ListingRoom createFromParcel(Parcel source) {
            ListingRoom object = new ListingRoom();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final int ROOM_NUMBER_COMMON_AREAS = 0;
    private List<BedType> memoizedSortedBedTypes;

    static /* synthetic */ int lambda$static$0(ListingRoom lhs, ListingRoom rhs) {
        if (lhs.getRoomNumber() == 0) {
            return 1;
        }
        if (rhs.getRoomNumber() == 0) {
            return -1;
        }
        return lhs.getRoomNumber() - rhs.getRoomNumber();
    }

    public List<BedType> getSortedBedTypes() {
        if (this.memoizedSortedBedTypes == null) {
            this.memoizedSortedBedTypes = BedType.sortedBedTypes(this.mBedTypes);
        }
        return this.memoizedSortedBedTypes;
    }

    public void setBedTypes(List<BedType> value) {
        super.setBedTypes(value);
        this.memoizedSortedBedTypes = null;
    }

    public static List<ListingRoom> sortedRooms(List<ListingRoom> unsorted) {
        return FluentIterable.from((Iterable<E>) unsorted).toSortedList(COMMON_AREAS_LAST_COMPARATOR);
    }

    public static Iterable<Integer> orderedRoomNumbers(int numBedrooms) {
        if (numBedrooms == 0) {
            return FluentIterable.m1282of(Integer.valueOf(0), new Integer[0]);
        }
        return FluentIterable.from((E[]) ListUtils.range(1, numBedrooms)).append((E[]) new Integer[]{Integer.valueOf(0)});
    }

    public int hashCode() {
        return ((int) (this.mId ^ (this.mId >>> 32))) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof ListingRoom)) {
            return false;
        }
        if (this.mId != ((ListingRoom) obj).mId) {
            return false;
        }
        return true;
    }
}
