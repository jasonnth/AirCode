package com.airbnb.android.core.instant_promo.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.instant_promo.models.generated.GenInstantPromotionContent;

public class InstantPromotionContent extends GenInstantPromotionContent {
    public static final Creator<InstantPromotionContent> CREATOR = new Creator<InstantPromotionContent>() {
        public InstantPromotionContent[] newArray(int size) {
            return new InstantPromotionContent[size];
        }

        public InstantPromotionContent createFromParcel(Parcel source) {
            InstantPromotionContent object = new InstantPromotionContent();
            object.readFromParcel(source);
            return object;
        }
    };
}
