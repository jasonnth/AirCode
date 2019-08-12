package com.airbnb.android.core.p008mt.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.ExploreMetaData;
import com.airbnb.android.core.models.ExploreSeeAllInfo;

/* renamed from: com.airbnb.android.core.mt.data.AutoValue_ExploreData */
final class AutoValue_ExploreData extends C$AutoValue_ExploreData {
    public static final Creator<AutoValue_ExploreData> CREATOR = new Creator<AutoValue_ExploreData>() {
        public AutoValue_ExploreData createFromParcel(Parcel in) {
            return new AutoValue_ExploreData((ExploreMetaData) in.readParcelable(ExploreMetaData.class.getClassLoader()), (ExploreSeeAllInfo) in.readParcelable(ExploreSeeAllInfo.class.getClassLoader()));
        }

        public AutoValue_ExploreData[] newArray(int size) {
            return new AutoValue_ExploreData[size];
        }
    };

    AutoValue_ExploreData(ExploreMetaData exploreMetaData, ExploreSeeAllInfo seeAllInfo) {
        super(exploreMetaData, seeAllInfo);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(exploreMetaData(), flags);
        dest.writeParcelable(seeAllInfo(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
