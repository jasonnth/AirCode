package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAttributedTextRange;

public class AttributedTextRange extends GenAttributedTextRange {
    public static final Creator<AttributedTextRange> CREATOR = new Creator<AttributedTextRange>() {
        public AttributedTextRange[] newArray(int size) {
            return new AttributedTextRange[size];
        }

        public AttributedTextRange createFromParcel(Parcel source) {
            AttributedTextRange object = new AttributedTextRange();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AttributedTextRange that = (AttributedTextRange) o;
        if (this.mStart != that.mStart) {
            return false;
        }
        if (this.mLength != that.mLength) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.mStart * 31) + this.mLength;
    }
}
