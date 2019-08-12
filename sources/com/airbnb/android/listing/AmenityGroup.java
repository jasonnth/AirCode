package com.airbnb.android.listing;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.Amenity;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class AmenityGroup implements Parcelable {
    public static final Creator<AmenityGroup> CREATOR = new Creator<AmenityGroup>() {
        public AmenityGroup[] newArray(int size) {
            return new AmenityGroup[size];
        }

        public AmenityGroup createFromParcel(Parcel source) {
            return new AmenityGroup(source);
        }
    };
    private Amenity[] amenities;

    /* renamed from: id */
    private final String f9672id;
    private final int titleRes;

    public AmenityGroup(int titleRes2, Amenity[] amenities2) {
        this.titleRes = titleRes2;
        this.amenities = amenities2;
        this.f9672id = null;
    }

    public AmenityGroup(Amenity[] amenities2, String id) {
        this.titleRes = 0;
        this.amenities = amenities2;
        this.f9672id = id;
    }

    public AmenityGroup(Amenity[] amenities2) {
        this.titleRes = 0;
        this.amenities = amenities2;
        this.f9672id = null;
    }

    public AmenityGroup(List<Integer> ids, String groupId) {
        this.titleRes = 0;
        this.amenities = (Amenity[]) FluentIterable.from((Iterable<E>) ids).transform(AmenityGroup$$Lambda$1.lambdaFactory$()).filter(Predicates.notNull()).toArray(Amenity.class);
        this.f9672id = groupId;
    }

    public Amenity[] getAmenities() {
        return this.amenities;
    }

    public int getTitleRes() {
        return this.titleRes;
    }

    public String getId() {
        return this.f9672id;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.titleRes);
        dest.writeArray(this.amenities);
        dest.writeString(this.f9672id);
    }

    private AmenityGroup(Parcel source) {
        this.titleRes = source.readInt();
        this.amenities = (Amenity[]) source.readParcelableArray(Amenity.class.getClassLoader());
        this.f9672id = source.readString();
    }
}
