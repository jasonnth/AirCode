package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.models.generated.GenUrgencyMessage;

public class UrgencyMessage extends GenUrgencyMessage {
    public static final Creator<UrgencyMessage> CREATOR = new Creator<UrgencyMessage>() {
        public UrgencyMessage[] newArray(int size) {
            return new UrgencyMessage[size];
        }

        public UrgencyMessage createFromParcel(Parcel source) {
            UrgencyMessage object = new UrgencyMessage();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean hasContextualMessage() {
        return !TextUtils.isEmpty(getContextualMessage());
    }
}
