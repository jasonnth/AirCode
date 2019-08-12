package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenInquiriesRepliesCounts;

public class InquiriesRepliesCounts extends GenInquiriesRepliesCounts {
    public static final Creator<InquiriesRepliesCounts> CREATOR = new Creator<InquiriesRepliesCounts>() {
        public InquiriesRepliesCounts[] newArray(int size) {
            return new InquiriesRepliesCounts[size];
        }

        public InquiriesRepliesCounts createFromParcel(Parcel source) {
            InquiriesRepliesCounts object = new InquiriesRepliesCounts();
            object.readFromParcel(source);
            return object;
        }
    };
}
