package com.airbnb.android.core.p008mt.data;

import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.core.models.ExploreMetaData;
import com.airbnb.android.core.models.ExploreSeeAllInfo;
import com.airbnb.android.utils.BundleBuilder;

/* renamed from: com.airbnb.android.core.mt.data.ExploreData */
public abstract class ExploreData implements Parcelable {

    /* renamed from: com.airbnb.android.core.mt.data.ExploreData$Builder */
    public static abstract class Builder {
        public abstract ExploreData build();

        public abstract Builder exploreMetaData(ExploreMetaData exploreMetaData);

        public abstract Builder seeAllInfo(ExploreSeeAllInfo exploreSeeAllInfo);
    }

    public abstract ExploreMetaData exploreMetaData();

    public abstract ExploreSeeAllInfo seeAllInfo();

    public abstract Builder toBuilder();

    public Bundle toBundle() {
        return ((BundleBuilder) new BundleBuilder().putParcelable(getClass().getSimpleName(), this)).toBundle();
    }

    public boolean isInSeeAllMode() {
        return seeAllInfo() != null;
    }

    public boolean hasMetaData() {
        return exploreMetaData() != null;
    }

    public static Builder builder() {
        return new Builder();
    }
}
