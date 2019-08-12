package com.airbnb.android.core.p008mt.data;

import com.airbnb.android.core.models.ExploreMetaData;
import com.airbnb.android.core.models.ExploreSeeAllInfo;

/* renamed from: com.airbnb.android.core.mt.data.$AutoValue_ExploreData reason: invalid class name */
abstract class C$AutoValue_ExploreData extends ExploreData {
    private final ExploreMetaData exploreMetaData;
    private final ExploreSeeAllInfo seeAllInfo;

    /* renamed from: com.airbnb.android.core.mt.data.$AutoValue_ExploreData$Builder */
    static final class Builder extends com.airbnb.android.core.p008mt.data.ExploreData.Builder {
        private ExploreMetaData exploreMetaData;
        private ExploreSeeAllInfo seeAllInfo;

        Builder() {
        }

        private Builder(ExploreData source) {
            this.exploreMetaData = source.exploreMetaData();
            this.seeAllInfo = source.seeAllInfo();
        }

        public com.airbnb.android.core.p008mt.data.ExploreData.Builder exploreMetaData(ExploreMetaData exploreMetaData2) {
            this.exploreMetaData = exploreMetaData2;
            return this;
        }

        public com.airbnb.android.core.p008mt.data.ExploreData.Builder seeAllInfo(ExploreSeeAllInfo seeAllInfo2) {
            this.seeAllInfo = seeAllInfo2;
            return this;
        }

        public ExploreData build() {
            return new AutoValue_ExploreData(this.exploreMetaData, this.seeAllInfo);
        }
    }

    C$AutoValue_ExploreData(ExploreMetaData exploreMetaData2, ExploreSeeAllInfo seeAllInfo2) {
        this.exploreMetaData = exploreMetaData2;
        this.seeAllInfo = seeAllInfo2;
    }

    public ExploreMetaData exploreMetaData() {
        return this.exploreMetaData;
    }

    public ExploreSeeAllInfo seeAllInfo() {
        return this.seeAllInfo;
    }

    public String toString() {
        return "ExploreData{exploreMetaData=" + this.exploreMetaData + ", seeAllInfo=" + this.seeAllInfo + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExploreData)) {
            return false;
        }
        ExploreData that = (ExploreData) o;
        if (this.exploreMetaData != null ? this.exploreMetaData.equals(that.exploreMetaData()) : that.exploreMetaData() == null) {
            if (this.seeAllInfo == null) {
                if (that.seeAllInfo() == null) {
                    return true;
                }
            } else if (this.seeAllInfo.equals(that.seeAllInfo())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.exploreMetaData == null ? 0 : this.exploreMetaData.hashCode())) * 1000003;
        if (this.seeAllInfo != null) {
            i = this.seeAllInfo.hashCode();
        }
        return h ^ i;
    }

    public com.airbnb.android.core.p008mt.data.ExploreData.Builder toBuilder() {
        return new Builder(this);
    }
}
