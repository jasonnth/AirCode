package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.enums.BedDetailType;
import com.airbnb.android.core.models.generated.GenBedType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.util.Comparator;
import java.util.List;

public class BedType extends GenBedType {
    public static final Creator<BedType> CREATOR = new Creator<BedType>() {
        public BedType[] newArray(int size) {
            return new BedType[size];
        }

        public BedType createFromParcel(Parcel source) {
            BedType object = new BedType();
            object.readFromParcel(source);
            return object;
        }
    };
    public static Comparator<BedType> ENUM_COMPARATOR = BedType$$Lambda$1.lambdaFactory$();

    @JsonProperty("type")
    public void setType(String type) {
        setType(BedDetailType.getTypeFromKeyOrDefault(type));
    }

    public void setType(BedDetailType type) {
        this.mType = type;
    }

    public static List<BedType> sortedBedTypes(List<BedType> unsorted) {
        return FluentIterable.from((Iterable<E>) unsorted).toSortedList(ENUM_COMPARATOR);
    }
}
