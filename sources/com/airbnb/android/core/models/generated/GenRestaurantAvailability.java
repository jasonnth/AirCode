package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenRestaurantAvailability implements Parcelable {
    @JsonProperty("confirmation_messages")
    protected List<String> mConfirmationMessages;
    @JsonProperty("id")
    protected int mId;
    @JsonProperty("starts_at_in_restaurant_timezone")
    protected AirDateTime mStartsAtInRestaurantTimezone;
    @JsonProperty("table_type")
    protected String mTableType;

    protected GenRestaurantAvailability(AirDateTime startsAtInRestaurantTimezone, List<String> confirmationMessages, String tableType, int id) {
        this();
        this.mStartsAtInRestaurantTimezone = startsAtInRestaurantTimezone;
        this.mConfirmationMessages = confirmationMessages;
        this.mTableType = tableType;
        this.mId = id;
    }

    protected GenRestaurantAvailability() {
    }

    public AirDateTime getStartsAtInRestaurantTimezone() {
        return this.mStartsAtInRestaurantTimezone;
    }

    public List<String> getConfirmationMessages() {
        return this.mConfirmationMessages;
    }

    @JsonProperty("confirmation_messages")
    public void setConfirmationMessages(List<String> value) {
        this.mConfirmationMessages = value;
    }

    public String getTableType() {
        return this.mTableType;
    }

    @JsonProperty("table_type")
    public void setTableType(String value) {
        this.mTableType = value;
    }

    public int getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(int value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mStartsAtInRestaurantTimezone, 0);
        parcel.writeStringList(this.mConfirmationMessages);
        parcel.writeString(this.mTableType);
        parcel.writeInt(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mStartsAtInRestaurantTimezone = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mConfirmationMessages = source.createStringArrayList();
        this.mTableType = source.readString();
        this.mId = source.readInt();
    }
}
