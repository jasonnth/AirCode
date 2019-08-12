package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAttributedText;

public class AttributedText extends GenAttributedText {
    public static final Creator<AttributedText> CREATOR = new Creator<AttributedText>() {
        public AttributedText[] newArray(int size) {
            return new AttributedText[size];
        }

        public AttributedText createFromParcel(Parcel source) {
            AttributedText object = new AttributedText();
            object.readFromParcel(source);
            return object;
        }
    };
}
