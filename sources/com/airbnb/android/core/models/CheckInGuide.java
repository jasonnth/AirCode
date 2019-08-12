package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.enums.CheckInGuideStatus;
import com.airbnb.android.core.models.generated.GenCheckInGuide;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CheckInGuide extends GenCheckInGuide {
    public static final Creator<CheckInGuide> CREATOR = new Creator<CheckInGuide>() {
        public CheckInGuide[] newArray(int size) {
            return new CheckInGuide[size];
        }

        public CheckInGuide createFromParcel(Parcel source) {
            CheckInGuide object = new CheckInGuide();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final int GUIDE_VISIBILITY_STATUS_NO_STEPS = 3;
    public static final int GUIDE_VISIBILITY_STATUS_TOO_LATE = 2;
    public static final int GUIDE_VISIBILITY_STATUS_TOO_SOON = 1;
    public static final int GUIDE_VISIBILITY_STATUS_VISIBLE = 0;
    public static final int NOTIFICATION_STATUS_ALREADY_CHECKED_IN = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface GuideVisibilityStatus {
    }

    public CheckInGuideStatus getPublishedStatus() {
        return CheckInGuideStatus.getTypeFromKeyOrDefault(Integer.valueOf(this.mPubStatus));
    }

    public boolean hasCheckedInAlready() {
        return this.mNotificationStatus != null && this.mNotificationStatus.intValue() == 1;
    }

    public void setCheckedIn() {
        this.mNotificationStatus = Integer.valueOf(1);
    }

    public static CheckInGuide getSampleCheckinGuide() {
        CheckInGuide sample = new CheckInGuide();
        sample.setAddress("493 Lawnmarket Apartment #7, Edinburgh, EH1 2PB, UK");
        sample.setSteps(FluentIterable.from((E[]) ListUtils.range(0, 2)).transform(CheckInGuide$$Lambda$1.lambdaFactory$()).toList());
        sample.setCheckinInformation(Lists.newArrayList((E[]) new CheckInInformation[]{CheckInInformation.getSampleInformation()}));
        sample.setLocalizedLanguage("sample-text");
        sample.setLanguage("en");
        return sample;
    }

    public boolean hasLocalizedNotes() {
        return (getLocalizedLanguage() == null || getLanguage() == null || getLocalizedLanguage().equals(getLanguage())) ? false : true;
    }

    public boolean isVisible() {
        if ((this.mVisibleStartingAt == null && this.mVisibleEndingAt == null) || getVisibilityStatus() == 0) {
            return true;
        }
        return false;
    }

    public int getVisibilityStatus() {
        AirDateTime now = AirDateTime.now();
        if (getVisibleStartingAt() != null && now.isBefore(getVisibleStartingAt())) {
            return 1;
        }
        if (getVisibleEndingAt() != null && now.isAfter(getVisibleEndingAt())) {
            return 2;
        }
        if (getSteps().size() == 0) {
            return 3;
        }
        return 0;
    }
}
