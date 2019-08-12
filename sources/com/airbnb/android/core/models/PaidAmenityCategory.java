package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPaidAmenityCategory;
import com.airbnb.android.core.paidamenities.enums.PaidAmenityServerType;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaidAmenityCategory extends GenPaidAmenityCategory {
    public static final Creator<PaidAmenityCategory> CREATOR = new Creator<PaidAmenityCategory>() {
        public PaidAmenityCategory[] newArray(int size) {
            return new PaidAmenityCategory[size];
        }

        public PaidAmenityCategory createFromParcel(Parcel source) {
            PaidAmenityCategory object = new PaidAmenityCategory();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("amenity_type")
    public void setAmenityServerType(String serverKey) {
        this.mAmenityServerType = PaidAmenityServerType.findByServerKey(serverKey);
    }
}
