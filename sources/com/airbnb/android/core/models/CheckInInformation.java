package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCheckInInformation;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import java.util.Comparator;

public class CheckInInformation extends GenCheckInInformation {
    public static final Creator<CheckInInformation> CREATOR = new Creator<CheckInInformation>() {
        public CheckInInformation[] newArray(int size) {
            return new CheckInInformation[size];
        }

        public CheckInInformation createFromParcel(Parcel source) {
            CheckInInformation object = new CheckInInformation();
            object.readFromParcel(source);
            return object;
        }
    };
    public static Comparator<CheckInInformation> GROUP_AND_SORT_COMPARATOR = CheckInInformation$$Lambda$2.lambdaFactory$();
    public static final int GROUP_ID_ALL = 0;
    public static final int GROUP_ID_SELF_CHECKIN = 1;

    public int getAmenityNumber() {
        return getAmenity().getAmenityId();
    }

    public long getListingAmenityId() {
        return ((Number) SanitizeUtils.defaultIfNull(getAmenity().getListingAmenityId(), Integer.valueOf(-1))).longValue();
    }

    static /* synthetic */ int lambda$static$0(CheckInInformation lhs, CheckInInformation rhs) {
        int result = lhs.getGroupId() - rhs.getGroupId();
        if (result == 0) {
            return lhs.getAmenity().getAmenityId() - rhs.getAmenity().getAmenityId();
        }
        return result;
    }

    public static ImmutableList<CheckInInformation> sortedAndGroupedMethods(Iterable<CheckInInformation> methods) {
        return FluentIterable.from(methods).toSortedList(GROUP_AND_SORT_COMPARATOR);
    }

    public static ImmutableList<CheckInInformation> selfCheckinMethods(Iterable<CheckInInformation> methods) {
        return FluentIterable.from(methods).filter(CheckInInformation$$Lambda$1.lambdaFactory$()).toList();
    }

    static /* synthetic */ boolean lambda$selfCheckinMethods$1(CheckInInformation method) {
        return method.getGroupId() == 1;
    }

    public static CheckInInformation getSampleInformation() {
        CheckInInformation info = new CheckInInformation();
        info.setGroupId(54);
        info.setInstruction("The smart lock code is set to the last 4 digits of your cell phone number during your stay.");
        info.setIsInstructionVisible(Boolean.valueOf(true));
        info.setIsOptionAvailable(Boolean.valueOf(true));
        ListingAmenityInformation amenity = new ListingAmenityInformation();
        amenity.setName("Smart lock");
        amenity.setAmenityId(54);
        info.setAmenity(amenity);
        return info;
    }
}
