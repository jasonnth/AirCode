package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.CalendarDayExternalCalendar;
import com.airbnb.android.core.models.CalendarDayPriceInfo;
import com.airbnb.android.core.models.CalendarDayPromotion;
import com.airbnb.android.core.models.NestedBusyDetail;
import com.airbnb.android.core.models.Reservation;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenCalendarDay implements Parcelable {
    @JsonProperty("available")
    protected boolean mAvailable;
    @JsonProperty("date")
    protected AirDate mDate;
    @JsonProperty("external_calendar")
    protected CalendarDayExternalCalendar mExternalCalendar;
    @JsonProperty("group_id")
    protected String mGroupId;
    @JsonProperty("host_busy")
    protected boolean mHostBusy;
    @JsonProperty("nested_busy_details")
    protected List<NestedBusyDetail> mNestedBusyDetails;
    @JsonProperty("notes")
    protected String mNotes;
    @JsonProperty("price")
    protected CalendarDayPriceInfo mPriceInfo;
    @JsonProperty("smart_promotion")
    protected CalendarDayPromotion mPromotion;
    @JsonProperty("reason")
    protected String mReason;
    @JsonProperty("reservation")
    protected Reservation mReservation;
    @JsonProperty("selected")
    protected boolean mSelected;
    @JsonProperty("server_synced_at")
    protected AirDateTime mServerSyncedAt;
    @JsonProperty("subtype")
    protected String mSubtype;
    @JsonProperty("type")
    protected String mType;

    protected GenCalendarDay(AirDate date, AirDateTime serverSyncedAt, CalendarDayExternalCalendar externalCalendar, CalendarDayPriceInfo priceInfo, CalendarDayPromotion promotion, List<NestedBusyDetail> nestedBusyDetails, Reservation reservation, String type, String subtype, String groupId, String notes, String reason, boolean available, boolean selected, boolean hostBusy) {
        this();
        this.mDate = date;
        this.mServerSyncedAt = serverSyncedAt;
        this.mExternalCalendar = externalCalendar;
        this.mPriceInfo = priceInfo;
        this.mPromotion = promotion;
        this.mNestedBusyDetails = nestedBusyDetails;
        this.mReservation = reservation;
        this.mType = type;
        this.mSubtype = subtype;
        this.mGroupId = groupId;
        this.mNotes = notes;
        this.mReason = reason;
        this.mAvailable = available;
        this.mSelected = selected;
        this.mHostBusy = hostBusy;
    }

    protected GenCalendarDay() {
    }

    public AirDate getDate() {
        return this.mDate;
    }

    @JsonProperty("date")
    public void setDate(AirDate value) {
        this.mDate = value;
    }

    public AirDateTime getServerSyncedAt() {
        return this.mServerSyncedAt;
    }

    @JsonProperty("server_synced_at")
    public void setServerSyncedAt(AirDateTime value) {
        this.mServerSyncedAt = value;
    }

    public CalendarDayExternalCalendar getExternalCalendar() {
        return this.mExternalCalendar;
    }

    @JsonProperty("external_calendar")
    public void setExternalCalendar(CalendarDayExternalCalendar value) {
        this.mExternalCalendar = value;
    }

    public CalendarDayPriceInfo getPriceInfo() {
        return this.mPriceInfo;
    }

    @JsonProperty("price")
    public void setPriceInfo(CalendarDayPriceInfo value) {
        this.mPriceInfo = value;
    }

    public CalendarDayPromotion getPromotion() {
        return this.mPromotion;
    }

    @JsonProperty("smart_promotion")
    public void setPromotion(CalendarDayPromotion value) {
        this.mPromotion = value;
    }

    public List<NestedBusyDetail> getNestedBusyDetails() {
        return this.mNestedBusyDetails;
    }

    @JsonProperty("nested_busy_details")
    public void setNestedBusyDetails(List<NestedBusyDetail> value) {
        this.mNestedBusyDetails = value;
    }

    public Reservation getReservation() {
        return this.mReservation;
    }

    @JsonProperty("reservation")
    public void setReservation(Reservation value) {
        this.mReservation = value;
    }

    public String getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.mType = value;
    }

    public String getSubtype() {
        return this.mSubtype;
    }

    @JsonProperty("subtype")
    public void setSubtype(String value) {
        this.mSubtype = value;
    }

    public String getGroupId() {
        return this.mGroupId;
    }

    @JsonProperty("group_id")
    public void setGroupId(String value) {
        this.mGroupId = value;
    }

    public String getNotes() {
        return this.mNotes;
    }

    @JsonProperty("notes")
    public void setNotes(String value) {
        this.mNotes = value;
    }

    public String getReason() {
        return this.mReason;
    }

    @JsonProperty("reason")
    public void setReason(String value) {
        this.mReason = value;
    }

    public boolean isAvailable() {
        return this.mAvailable;
    }

    @JsonProperty("available")
    public void setAvailable(boolean value) {
        this.mAvailable = value;
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public boolean isHostBusy() {
        return this.mHostBusy;
    }

    @JsonProperty("host_busy")
    public void setHostBusy(boolean value) {
        this.mHostBusy = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mDate, 0);
        parcel.writeParcelable(this.mServerSyncedAt, 0);
        parcel.writeParcelable(this.mExternalCalendar, 0);
        parcel.writeParcelable(this.mPriceInfo, 0);
        parcel.writeParcelable(this.mPromotion, 0);
        parcel.writeTypedList(this.mNestedBusyDetails);
        parcel.writeParcelable(this.mReservation, 0);
        parcel.writeString(this.mType);
        parcel.writeString(this.mSubtype);
        parcel.writeString(this.mGroupId);
        parcel.writeString(this.mNotes);
        parcel.writeString(this.mReason);
        parcel.writeBooleanArray(new boolean[]{this.mAvailable, this.mSelected, this.mHostBusy});
    }

    public void readFromParcel(Parcel source) {
        this.mDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mServerSyncedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mExternalCalendar = (CalendarDayExternalCalendar) source.readParcelable(CalendarDayExternalCalendar.class.getClassLoader());
        this.mPriceInfo = (CalendarDayPriceInfo) source.readParcelable(CalendarDayPriceInfo.class.getClassLoader());
        this.mPromotion = (CalendarDayPromotion) source.readParcelable(CalendarDayPromotion.class.getClassLoader());
        this.mNestedBusyDetails = source.createTypedArrayList(NestedBusyDetail.CREATOR);
        this.mReservation = (Reservation) source.readParcelable(Reservation.class.getClassLoader());
        this.mType = source.readString();
        this.mSubtype = source.readString();
        this.mGroupId = source.readString();
        this.mNotes = source.readString();
        this.mReason = source.readString();
        boolean[] bools = source.createBooleanArray();
        this.mAvailable = bools[0];
        this.mSelected = bools[1];
        this.mHostBusy = bools[2];
    }
}
