package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.IconWithTitles;
import com.airbnb.android.core.models.ReasonMapping;
import com.airbnb.android.core.models.ReservationCancellationReasonInfo;
import com.airbnb.android.core.models.ReservationToCancelInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public abstract class GenReservationCancellationInfo implements Parcelable {
    @JsonProperty("cancellation_fee_info")
    protected IconWithTitles mCancellationFeeInfo;
    @JsonProperty("cancellation_reason_tips_mapping")
    protected ArrayList<ReasonMapping> mCancellationReasonTipsMapping;
    @JsonProperty("cancellation_reasons_mobile")
    protected ArrayList<ReservationCancellationReasonInfo> mCancellationReasonsMobile;
    @JsonProperty("custom_cancellation_penalty_mobile")
    protected ArrayList<IconWithTitles> mCustomCancellationPenaltyMobile;
    @JsonProperty("host_behavior_reason_limit_reached")
    protected boolean mHostBehaviorReasonLimitReached;
    @JsonProperty("host_cancel_empathies_mobile")
    protected ArrayList<IconWithTitles> mHostCancelEmpathiesMobile;
    @JsonProperty("host_cancel_penalties")
    protected ArrayList<IconWithTitles> mHostCancelPenalties;
    @JsonProperty("missed_earnings_info")
    protected IconWithTitles mMissedEarningsInfo;
    @JsonProperty("reservation")
    protected ReservationToCancelInfo mReservationToCancelInfo;

    protected GenReservationCancellationInfo(ArrayList<IconWithTitles> customCancellationPenaltyMobile, ArrayList<IconWithTitles> hostCancelEmpathiesMobile, ArrayList<IconWithTitles> hostCancelPenalties, ArrayList<ReasonMapping> cancellationReasonTipsMapping, ArrayList<ReservationCancellationReasonInfo> cancellationReasonsMobile, IconWithTitles missedEarningsInfo, IconWithTitles cancellationFeeInfo, ReservationToCancelInfo reservationToCancelInfo, boolean hostBehaviorReasonLimitReached) {
        this();
        this.mCustomCancellationPenaltyMobile = customCancellationPenaltyMobile;
        this.mHostCancelEmpathiesMobile = hostCancelEmpathiesMobile;
        this.mHostCancelPenalties = hostCancelPenalties;
        this.mCancellationReasonTipsMapping = cancellationReasonTipsMapping;
        this.mCancellationReasonsMobile = cancellationReasonsMobile;
        this.mMissedEarningsInfo = missedEarningsInfo;
        this.mCancellationFeeInfo = cancellationFeeInfo;
        this.mReservationToCancelInfo = reservationToCancelInfo;
        this.mHostBehaviorReasonLimitReached = hostBehaviorReasonLimitReached;
    }

    protected GenReservationCancellationInfo() {
    }

    public ArrayList<IconWithTitles> getCustomCancellationPenaltyMobile() {
        return this.mCustomCancellationPenaltyMobile;
    }

    @JsonProperty("custom_cancellation_penalty_mobile")
    public void setCustomCancellationPenaltyMobile(ArrayList<IconWithTitles> value) {
        this.mCustomCancellationPenaltyMobile = value;
    }

    public ArrayList<IconWithTitles> getHostCancelEmpathiesMobile() {
        return this.mHostCancelEmpathiesMobile;
    }

    @JsonProperty("host_cancel_empathies_mobile")
    public void setHostCancelEmpathiesMobile(ArrayList<IconWithTitles> value) {
        this.mHostCancelEmpathiesMobile = value;
    }

    public ArrayList<IconWithTitles> getHostCancelPenalties() {
        return this.mHostCancelPenalties;
    }

    @JsonProperty("host_cancel_penalties")
    public void setHostCancelPenalties(ArrayList<IconWithTitles> value) {
        this.mHostCancelPenalties = value;
    }

    public ArrayList<ReasonMapping> getCancellationReasonTipsMapping() {
        return this.mCancellationReasonTipsMapping;
    }

    @JsonProperty("cancellation_reason_tips_mapping")
    public void setCancellationReasonTipsMapping(ArrayList<ReasonMapping> value) {
        this.mCancellationReasonTipsMapping = value;
    }

    public ArrayList<ReservationCancellationReasonInfo> getCancellationReasonsMobile() {
        return this.mCancellationReasonsMobile;
    }

    @JsonProperty("cancellation_reasons_mobile")
    public void setCancellationReasonsMobile(ArrayList<ReservationCancellationReasonInfo> value) {
        this.mCancellationReasonsMobile = value;
    }

    public IconWithTitles getMissedEarningsInfo() {
        return this.mMissedEarningsInfo;
    }

    @JsonProperty("missed_earnings_info")
    public void setMissedEarningsInfo(IconWithTitles value) {
        this.mMissedEarningsInfo = value;
    }

    public IconWithTitles getCancellationFeeInfo() {
        return this.mCancellationFeeInfo;
    }

    @JsonProperty("cancellation_fee_info")
    public void setCancellationFeeInfo(IconWithTitles value) {
        this.mCancellationFeeInfo = value;
    }

    public ReservationToCancelInfo getReservationToCancelInfo() {
        return this.mReservationToCancelInfo;
    }

    @JsonProperty("reservation")
    public void setReservationToCancelInfo(ReservationToCancelInfo value) {
        this.mReservationToCancelInfo = value;
    }

    public boolean isHostBehaviorReasonLimitReached() {
        return this.mHostBehaviorReasonLimitReached;
    }

    @JsonProperty("host_behavior_reason_limit_reached")
    public void setHostBehaviorReasonLimitReached(boolean value) {
        this.mHostBehaviorReasonLimitReached = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mCustomCancellationPenaltyMobile);
        parcel.writeTypedList(this.mHostCancelEmpathiesMobile);
        parcel.writeTypedList(this.mHostCancelPenalties);
        parcel.writeTypedList(this.mCancellationReasonTipsMapping);
        parcel.writeTypedList(this.mCancellationReasonsMobile);
        parcel.writeParcelable(this.mMissedEarningsInfo, 0);
        parcel.writeParcelable(this.mCancellationFeeInfo, 0);
        parcel.writeParcelable(this.mReservationToCancelInfo, 0);
        parcel.writeBooleanArray(new boolean[]{this.mHostBehaviorReasonLimitReached});
    }

    public void readFromParcel(Parcel source) {
        this.mCustomCancellationPenaltyMobile = source.createTypedArrayList(IconWithTitles.CREATOR);
        this.mHostCancelEmpathiesMobile = source.createTypedArrayList(IconWithTitles.CREATOR);
        this.mHostCancelPenalties = source.createTypedArrayList(IconWithTitles.CREATOR);
        this.mCancellationReasonTipsMapping = source.createTypedArrayList(ReasonMapping.CREATOR);
        this.mCancellationReasonsMobile = source.createTypedArrayList(ReservationCancellationReasonInfo.CREATOR);
        this.mMissedEarningsInfo = (IconWithTitles) source.readParcelable(IconWithTitles.class.getClassLoader());
        this.mCancellationFeeInfo = (IconWithTitles) source.readParcelable(IconWithTitles.class.getClassLoader());
        this.mReservationToCancelInfo = (ReservationToCancelInfo) source.readParcelable(ReservationToCancelInfo.class.getClassLoader());
        this.mHostBehaviorReasonLimitReached = source.createBooleanArray()[0];
    }
}
