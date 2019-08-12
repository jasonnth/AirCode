package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReviewKeyword;

public class ReviewKeyword extends GenReviewKeyword {
    public static final Creator<ReviewKeyword> CREATOR = new Creator<ReviewKeyword>() {
        public ReviewKeyword[] newArray(int size) {
            return new ReviewKeyword[size];
        }

        public ReviewKeyword createFromParcel(Parcel source) {
            ReviewKeyword object = new ReviewKeyword();
            object.readFromParcel(source);
            return object;
        }
    };
}
