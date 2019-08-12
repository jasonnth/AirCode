package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.generated.GenSuperhostData;

public class SuperhostData extends GenSuperhostData {
    public static final Creator<SuperhostData> CREATOR = new Creator<SuperhostData>() {
        public SuperhostData[] newArray(int size) {
            return new SuperhostData[size];
        }

        public SuperhostData createFromParcel(Parcel source) {
            SuperhostData object = new SuperhostData();
            object.readFromParcel(source);
            return object;
        }
    };

    public void setNextElibilityDate(AirDate nextEligibilityDate) {
        this.mNextEligibilityDate = nextEligibilityDate;
    }

    public void setFiveStarReviewRateData(SuperhostMetric fiveStarReviewRateData) {
        this.mFiveStarReviewRateData = fiveStarReviewRateData;
    }

    public void setCommitmentRateData(SuperhostMetric commitmentRateData) {
        this.mCommitmentRateData = commitmentRateData;
    }

    public void setYearlyResponseRateData(SuperhostMetric yearlyResponseRateData) {
        this.mYearlyResponseRateData = yearlyResponseRateData;
    }

    public void setTripsAsHostLastYearData(SuperhostMetric tripsAsHostLastYearData) {
        this.mTripsAsHostLastYearData = tripsAsHostLastYearData;
    }

    public void setReviewRateData(SuperhostMetric reviewRateData) {
        this.mReviewRateData = reviewRateData;
    }

    public void setInquiriesRepliesCount(InquiriesRepliesCounts inquiriesRepliesCounts) {
        this.mInquiriesRepliesCounts = inquiriesRepliesCounts;
    }
}
