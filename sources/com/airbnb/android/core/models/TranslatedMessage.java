package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTranslatedMessage;

public class TranslatedMessage extends GenTranslatedMessage {
    public static final Creator<TranslatedMessage> CREATOR = new Creator<TranslatedMessage>() {
        public TranslatedMessage[] newArray(int size) {
            return new TranslatedMessage[size];
        }

        public TranslatedMessage createFromParcel(Parcel source) {
            TranslatedMessage object = new TranslatedMessage();
            object.readFromParcel(source);
            return object;
        }
    };
}
