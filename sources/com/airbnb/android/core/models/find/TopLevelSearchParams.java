package com.airbnb.android.core.models.find;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.SearchParams;
import com.airbnb.android.core.models.TimeType;
import com.airbnb.android.utils.BundleBuilder;
import com.fasterxml.jackson.annotation.JsonProperty;
import icepick.State;

public class TopLevelSearchParams implements Parcelable {
    public static final Creator<TopLevelSearchParams> CREATOR = new Creator<TopLevelSearchParams>() {
        public TopLevelSearchParams createFromParcel(Parcel in) {
            return new TopLevelSearchParams(in);
        }

        public TopLevelSearchParams[] newArray(int size) {
            return new TopLevelSearchParams[size];
        }
    };
    private static final int MIN_NUM_DAYS_FOR_MONTHLY_PRICING = 28;
    @State
    protected String autocompletePlaceId;
    @JsonProperty("checkin")
    @State
    protected AirDate checkInDate;
    @JsonProperty("checkout")
    @State
    protected AirDate checkOutDate;
    @JsonProperty("guest_details")
    @State
    protected GuestDetails guestDetails = GuestDetails.newInstance();
    @JsonProperty("map_bounds")
    @State
    protected MapBounds mapBounds;
    @State
    protected C5809SearchInputType searchInputType;
    @JsonProperty("location")
    @State
    public String searchTerm;
    @JsonProperty("time_type")
    @State
    protected TimeType timeType;

    public static final class Builder {
        private String autocompletePlaceId;
        private AirDate checkInDate;
        private AirDate checkOutDate;
        private GuestDetails guestDetails;
        private MapBounds mapBounds;
        private C5809SearchInputType searchInputType;
        private String searchTerm;
        private TimeType timeType;

        Builder() {
        }

        private Builder(TopLevelSearchParams source) {
            this.searchInputType = source.searchInputType();
            this.searchTerm = source.searchTerm();
            this.autocompletePlaceId = source.autocompletePlaceId();
            this.checkInDate = source.checkInDate();
            this.checkOutDate = source.checkOutDate();
            this.guestDetails = source.guestDetails();
            this.mapBounds = source.mapBounds();
            this.timeType = source.timeType;
        }

        public Builder searchInputType(C5809SearchInputType searchInputType2) {
            this.searchInputType = searchInputType2;
            return this;
        }

        public Builder searchTerm(String searchTerm2) {
            this.searchTerm = searchTerm2;
            return this;
        }

        public Builder autocompletePlaceId(String autocompletePlaceId2) {
            this.autocompletePlaceId = autocompletePlaceId2;
            return this;
        }

        public Builder checkInDate(AirDate checkInDate2) {
            this.checkInDate = checkInDate2;
            return this;
        }

        public Builder checkOutDate(AirDate checkOutDate2) {
            this.checkOutDate = checkOutDate2;
            return this;
        }

        public Builder guestDetails(GuestDetails guestDetails2) {
            this.guestDetails = guestDetails2;
            return this;
        }

        public Builder mapBounds(MapBounds mapBounds2) {
            this.mapBounds = mapBounds2;
            return this;
        }

        public Builder timeType(TimeType timeType2) {
            this.timeType = timeType2;
            return this;
        }

        public TopLevelSearchParams build() {
            return new TopLevelSearchParams(this.searchInputType, this.searchTerm, this.autocompletePlaceId, this.checkInDate, this.checkOutDate, this.guestDetails, this.mapBounds, this.timeType);
        }
    }

    public static Builder builder() {
        return new Builder().guestDetails(GuestDetails.newInstance());
    }

    protected TopLevelSearchParams(Parcel in) {
        this.searchTerm = in.readString();
        this.checkInDate = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
        this.checkOutDate = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
        this.guestDetails = (GuestDetails) in.readParcelable(GuestDetails.class.getClassLoader());
        this.mapBounds = (MapBounds) in.readParcelable(MapBounds.class.getClassLoader());
        this.timeType = (TimeType) in.readParcelable(TimeType.class.getClassLoader());
        this.autocompletePlaceId = in.readString();
    }

    TopLevelSearchParams(C5809SearchInputType searchInputType2, String searchTerm2, String autocompletePlaceId2, AirDate checkInDate2, AirDate checkOutDate2, GuestDetails guestDetails2, MapBounds mapBounds2, TimeType timeType2) {
        this.searchInputType = searchInputType2;
        this.searchTerm = searchTerm2;
        this.autocompletePlaceId = autocompletePlaceId2;
        this.checkInDate = checkInDate2;
        this.checkOutDate = checkOutDate2;
        this.guestDetails = guestDetails2;
        this.mapBounds = mapBounds2;
        this.timeType = timeType2;
    }

    public TopLevelSearchParams() {
    }

    public Bundle toBundle() {
        return ((BundleBuilder) new BundleBuilder().putParcelable(getClass().getSimpleName(), this)).toBundle();
    }

    public boolean hasSearchTerm() {
        return !TextUtils.isEmpty(this.searchTerm);
    }

    public boolean hasMapBounds() {
        return this.mapBounds != null;
    }

    public boolean hasDates() {
        return this.checkInDate != null;
    }

    public boolean isNightly() {
        return this.checkInDate == null || this.checkOutDate == null || this.checkInDate.getDaysUntil(this.checkOutDate) < 28;
    }

    public String getTimeText(Context context) {
        if (this.timeType == null || TextUtils.isEmpty(this.timeType.getDisplayText())) {
            return SearchParams.getTimeText(context, this.checkInDate, this.checkOutDate);
        }
        return this.timeType.getDisplayText();
    }

    public String getTimeTextChina(Context context) {
        if (this.timeType != null && !TextUtils.isEmpty(this.timeType.getDisplayText())) {
            return this.timeType.getDisplayText();
        }
        if (this.checkInDate == null) {
            return context.getString(C0716R.string.explore_date_placeholder);
        }
        return SearchParams.getTimeText(context, this.checkInDate, this.checkOutDate);
    }

    public boolean isCleared() {
        if (!TextUtils.isEmpty(this.searchTerm) || this.mapBounds != null) {
            return false;
        }
        if ((this.searchInputType == null || searchInputType() == C5809SearchInputType.Anywhere) && this.checkInDate == null && this.checkOutDate == null) {
            return GuestDetails.newInstance().equals(this.guestDetails);
        }
        return false;
    }

    public String getTimeTypeValue() {
        if (this.timeType != null) {
            return this.timeType.getValue();
        }
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.searchTerm);
        dest.writeParcelable(this.checkInDate, flags);
        dest.writeParcelable(this.checkOutDate, flags);
        dest.writeParcelable(this.guestDetails, flags);
        dest.writeParcelable(this.mapBounds, flags);
        dest.writeParcelable(this.timeType, flags);
        dest.writeString(this.autocompletePlaceId);
    }

    public Builder toBuilder() {
        return new Builder();
    }

    public C5809SearchInputType searchInputType() {
        return this.searchInputType;
    }

    public String searchTerm() {
        return this.searchTerm;
    }

    public String autocompletePlaceId() {
        return this.autocompletePlaceId;
    }

    public AirDate checkInDate() {
        return this.checkInDate;
    }

    public AirDate checkOutDate() {
        return this.checkOutDate;
    }

    public GuestDetails guestDetails() {
        return this.guestDetails;
    }

    public MapBounds mapBounds() {
        return this.mapBounds;
    }

    public TimeType timeType() {
        return this.timeType;
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(this.searchTerm) && this.guestDetails == null && this.checkInDate == null && this.checkOutDate == null && this.mapBounds == null && this.timeType == null;
    }
}
