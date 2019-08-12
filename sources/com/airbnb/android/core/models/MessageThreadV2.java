package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenMessageThreadV2;

public class MessageThreadV2 extends GenMessageThreadV2 {
    public static final Creator<MessageThreadV2> CREATOR = new Creator<MessageThreadV2>() {
        public MessageThreadV2[] newArray(int size) {
            return new MessageThreadV2[size];
        }

        public MessageThreadV2 createFromParcel(Parcel source) {
            MessageThreadV2 object = new MessageThreadV2();
            object.readFromParcel(source);
            return object;
        }
    };
}
