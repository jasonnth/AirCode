package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.models.generated.GenReferree;

public class Referree extends GenReferree {
    public static final Creator<Referree> CREATOR = new Creator<Referree>() {
        public Referree[] newArray(int size) {
            return new Referree[size];
        }

        public Referree createFromParcel(Parcel source) {
            Referree object = new Referree();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String STATUS_BOOKED = "booked";
    public static final String STATUS_BOOKING_BELOW_MIN_COST = "booking_below_min_cost";
    public static final String STATUS_BOOKING_COMPLETE = "booking_complete";
    public static final String STATUS_EMAIL_BLOCKED = "email_blocked";
    public static final String STATUS_HOSTED = "hosted";
    public static final String STATUS_INVITATION_EMAIL_SENT = "invitation_email_sent";
    public static final String STATUS_INVITATION_REMINDER_EMAIL_SENT = "invitation_reminder_email_sent";
    public static final String STATUS_INVITED = "invited";
    public static final String STATUS_JOINED = "joined";
    public static final String STATUS_LISTED = "listed";
    public static final String STATUS_REWARDED = "rewarded";
    public static final String STATUS_REWARD_EXPIRED = "reward_expired";
    public static final String STATUS_REWARD_LIMIT_REACHED = "reward_limit_reached";
    public static final String STATUS_STARTED_LISTING = "started_listing";
    public static final String STATUS_TRAVELED = "traveled";

    public String getBestDisplayName() {
        if (!TextUtils.isEmpty(this.mReferredUserFullName)) {
            return this.mReferredUserFullName;
        }
        if (!TextUtils.isEmpty(this.mReferredUserName)) {
            return this.mReferredUserName;
        }
        if (!TextUtils.isEmpty(this.mReferredUserEmail)) {
            return this.mReferredUserEmail;
        }
        return this.mReferredUserPhoneNumber;
    }

    public String getReferredUserProfilePhotoUrl() {
        if (!TextUtils.isEmpty(this.mReferredUserPhotoUrl)) {
            return this.mReferredUserPhotoUrl;
        }
        return this.mReferredUserProfilePhotoUrl;
    }

    public boolean hasBeenInvited() {
        return TextUtils.equals(this.mStatus, STATUS_INVITED);
    }

    public boolean hasJoined() {
        return TextUtils.equals(this.mStatus, STATUS_JOINED);
    }

    public boolean hasTraveled() {
        return TextUtils.equals(this.mStatus, STATUS_TRAVELED);
    }

    public boolean hasHosted() {
        return TextUtils.equals(this.mStatus, STATUS_HOSTED);
    }

    public boolean hasEmailBlocked() {
        return TextUtils.equals(this.mStatus, STATUS_EMAIL_BLOCKED);
    }
}
