package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.enums.UrgencyMessageType;
import com.airbnb.android.core.models.generated.GenUrgencyMessageData;

public class UrgencyMessageData extends GenUrgencyMessageData {
    public static final Creator<UrgencyMessageData> CREATOR = new Creator<UrgencyMessageData>() {
        public UrgencyMessageData[] newArray(int size) {
            return new UrgencyMessageData[size];
        }

        public UrgencyMessageData createFromParcel(Parcel source) {
            UrgencyMessageData object = new UrgencyMessageData();
            object.readFromParcel(source);
            return object;
        }
    };

    public UrgencyMessageType getType() {
        return UrgencyMessageType.fromKey(getMessageType());
    }

    public boolean hasContextualMessage() {
        return getMessage().hasContextualMessage();
    }
}
