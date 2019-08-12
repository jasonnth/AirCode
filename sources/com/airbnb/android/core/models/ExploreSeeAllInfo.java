package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.models.generated.GenExploreSeeAllInfo;

public class ExploreSeeAllInfo extends GenExploreSeeAllInfo {
    public static final Creator<ExploreSeeAllInfo> CREATOR = new Creator<ExploreSeeAllInfo>() {
        public ExploreSeeAllInfo[] newArray(int size) {
            return new ExploreSeeAllInfo[size];
        }

        public ExploreSeeAllInfo createFromParcel(Parcel source) {
            ExploreSeeAllInfo object = new ExploreSeeAllInfo();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean hasQuery() {
        return getQuery() != null;
    }

    public boolean hasSearchSessionId() {
        return !TextUtils.isEmpty(this.mSearchSessionId);
    }
}
