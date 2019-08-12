package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCalendarDayPromotion;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CalendarDayPromotion extends GenCalendarDayPromotion {
    public static final Creator<CalendarDayPromotion> CREATOR = new Creator<CalendarDayPromotion>() {
        public CalendarDayPromotion[] newArray(int size) {
            return new CalendarDayPromotion[size];
        }

        public CalendarDayPromotion createFromParcel(Parcel source) {
            CalendarDayPromotion object = new CalendarDayPromotion();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum PromotionType {
        NewHostingPromotion("new_hosting_promotion"),
        SmartPromotion("smart_promotion");
        
        String serverKey;

        private PromotionType(String serverKey2) {
            this.serverKey = serverKey2;
        }

        public static PromotionType fromKey(String type) {
            PromotionType[] values;
            for (PromotionType t : values()) {
                if (t.serverKey.equals(type)) {
                    return t;
                }
            }
            return null;
        }
    }

    @JsonProperty("promotion_type")
    public void setPromotionType(String promotionType) {
        super.setPromotionType(PromotionType.fromKey(promotionType));
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CalendarDayPromotion)) {
            return false;
        }
        if (((CalendarDayPromotion) o).getPromotionId() != getPromotionId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Long.valueOf(getPromotionId()).hashCode();
    }
}
