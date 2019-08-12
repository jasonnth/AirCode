package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenWhatsMyPlaceWorth;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WhatsMyPlaceWorth extends GenWhatsMyPlaceWorth {
    public static final Creator<WhatsMyPlaceWorth> CREATOR = new Creator<WhatsMyPlaceWorth>() {
        public WhatsMyPlaceWorth[] newArray(int size) {
            return new WhatsMyPlaceWorth[size];
        }

        public WhatsMyPlaceWorth createFromParcel(Parcel source) {
            WhatsMyPlaceWorth object = new WhatsMyPlaceWorth();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("room_type")
    public void setRoomType(String value) {
        this.mRoomType = C6120RoomType.fromKey(value);
    }
}
