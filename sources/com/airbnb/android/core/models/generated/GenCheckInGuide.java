package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.CheckInGuideReservation;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.core.models.CheckInStep;
import com.airbnb.android.core.models.ListingWirelessInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenCheckInGuide implements Parcelable {
    @JsonProperty("address")
    protected String mAddress;
    @JsonProperty("checkin_information")
    protected List<CheckInInformation> mCheckinInformation;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("language")
    protected String mLanguage;
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("localized_check_in_time_window")
    protected String mLocalizedCheckInTimeWindow;
    @JsonProperty("localized_language")
    protected String mLocalizedLanguage;
    @JsonProperty("notification_status")
    protected Integer mNotificationStatus;
    @JsonProperty("phone")
    protected String mPhone;
    @JsonProperty("pub_status")
    protected int mPubStatus;
    @JsonProperty("reservation")
    protected CheckInGuideReservation mReservation;
    @JsonProperty("check_in_guide_steps")
    protected List<CheckInStep> mSteps;
    @JsonProperty("updated_at")
    protected AirDateTime mUpdatedAt;
    @JsonProperty("visible_ending_at")
    protected AirDateTime mVisibleEndingAt;
    @JsonProperty("visible_starting_at")
    protected AirDateTime mVisibleStartingAt;
    @JsonProperty("wireless_info")
    protected ListingWirelessInfo mWirelessInfo;

    protected GenCheckInGuide(AirDateTime visibleStartingAt, AirDateTime visibleEndingAt, AirDateTime updatedAt, CheckInGuideReservation reservation, Integer notificationStatus, List<CheckInInformation> checkinInformation, List<CheckInStep> steps, ListingWirelessInfo wirelessInfo, String address, String language, String localizedLanguage, String localizedCheckInTimeWindow, String phone, int pubStatus, long listingId, long id) {
        this();
        this.mVisibleStartingAt = visibleStartingAt;
        this.mVisibleEndingAt = visibleEndingAt;
        this.mUpdatedAt = updatedAt;
        this.mReservation = reservation;
        this.mNotificationStatus = notificationStatus;
        this.mCheckinInformation = checkinInformation;
        this.mSteps = steps;
        this.mWirelessInfo = wirelessInfo;
        this.mAddress = address;
        this.mLanguage = language;
        this.mLocalizedLanguage = localizedLanguage;
        this.mLocalizedCheckInTimeWindow = localizedCheckInTimeWindow;
        this.mPhone = phone;
        this.mPubStatus = pubStatus;
        this.mListingId = listingId;
        this.mId = id;
    }

    protected GenCheckInGuide() {
    }

    public AirDateTime getVisibleStartingAt() {
        return this.mVisibleStartingAt;
    }

    @JsonProperty("visible_starting_at")
    public void setVisibleStartingAt(AirDateTime value) {
        this.mVisibleStartingAt = value;
    }

    public AirDateTime getVisibleEndingAt() {
        return this.mVisibleEndingAt;
    }

    @JsonProperty("visible_ending_at")
    public void setVisibleEndingAt(AirDateTime value) {
        this.mVisibleEndingAt = value;
    }

    public AirDateTime getUpdatedAt() {
        return this.mUpdatedAt;
    }

    @JsonProperty("updated_at")
    public void setUpdatedAt(AirDateTime value) {
        this.mUpdatedAt = value;
    }

    public CheckInGuideReservation getReservation() {
        return this.mReservation;
    }

    @JsonProperty("reservation")
    public void setReservation(CheckInGuideReservation value) {
        this.mReservation = value;
    }

    public Integer getNotificationStatus() {
        return this.mNotificationStatus;
    }

    @JsonProperty("notification_status")
    public void setNotificationStatus(Integer value) {
        this.mNotificationStatus = value;
    }

    public List<CheckInInformation> getCheckinInformation() {
        return this.mCheckinInformation;
    }

    @JsonProperty("checkin_information")
    public void setCheckinInformation(List<CheckInInformation> value) {
        this.mCheckinInformation = value;
    }

    public List<CheckInStep> getSteps() {
        return this.mSteps;
    }

    @JsonProperty("check_in_guide_steps")
    public void setSteps(List<CheckInStep> value) {
        this.mSteps = value;
    }

    public ListingWirelessInfo getWirelessInfo() {
        return this.mWirelessInfo;
    }

    @JsonProperty("wireless_info")
    public void setWirelessInfo(ListingWirelessInfo value) {
        this.mWirelessInfo = value;
    }

    public String getAddress() {
        return this.mAddress;
    }

    @JsonProperty("address")
    public void setAddress(String value) {
        this.mAddress = value;
    }

    public String getLanguage() {
        return this.mLanguage;
    }

    @JsonProperty("language")
    public void setLanguage(String value) {
        this.mLanguage = value;
    }

    public String getLocalizedLanguage() {
        return this.mLocalizedLanguage;
    }

    @JsonProperty("localized_language")
    public void setLocalizedLanguage(String value) {
        this.mLocalizedLanguage = value;
    }

    public String getLocalizedCheckInTimeWindow() {
        return this.mLocalizedCheckInTimeWindow;
    }

    @JsonProperty("localized_check_in_time_window")
    public void setLocalizedCheckInTimeWindow(String value) {
        this.mLocalizedCheckInTimeWindow = value;
    }

    public String getPhone() {
        return this.mPhone;
    }

    @JsonProperty("phone")
    public void setPhone(String value) {
        this.mPhone = value;
    }

    public int getPubStatus() {
        return this.mPubStatus;
    }

    @JsonProperty("pub_status")
    public void setPubStatus(int value) {
        this.mPubStatus = value;
    }

    public long getListingId() {
        return this.mListingId;
    }

    @JsonProperty("listing_id")
    public void setListingId(long value) {
        this.mListingId = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mVisibleStartingAt, 0);
        parcel.writeParcelable(this.mVisibleEndingAt, 0);
        parcel.writeParcelable(this.mUpdatedAt, 0);
        parcel.writeParcelable(this.mReservation, 0);
        parcel.writeValue(this.mNotificationStatus);
        parcel.writeTypedList(this.mCheckinInformation);
        parcel.writeTypedList(this.mSteps);
        parcel.writeParcelable(this.mWirelessInfo, 0);
        parcel.writeString(this.mAddress);
        parcel.writeString(this.mLanguage);
        parcel.writeString(this.mLocalizedLanguage);
        parcel.writeString(this.mLocalizedCheckInTimeWindow);
        parcel.writeString(this.mPhone);
        parcel.writeInt(this.mPubStatus);
        parcel.writeLong(this.mListingId);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mVisibleStartingAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mVisibleEndingAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mUpdatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mReservation = (CheckInGuideReservation) source.readParcelable(CheckInGuideReservation.class.getClassLoader());
        this.mNotificationStatus = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mCheckinInformation = source.createTypedArrayList(CheckInInformation.CREATOR);
        this.mSteps = source.createTypedArrayList(CheckInStep.CREATOR);
        this.mWirelessInfo = (ListingWirelessInfo) source.readParcelable(ListingWirelessInfo.class.getClassLoader());
        this.mAddress = source.readString();
        this.mLanguage = source.readString();
        this.mLocalizedLanguage = source.readString();
        this.mLocalizedCheckInTimeWindow = source.readString();
        this.mPhone = source.readString();
        this.mPubStatus = source.readInt();
        this.mListingId = source.readLong();
        this.mId = source.readLong();
    }
}
