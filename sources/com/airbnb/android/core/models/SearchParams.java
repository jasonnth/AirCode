package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.PropertyType;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.models.find.SearchFilters;
import com.airbnb.android.core.models.generated.GenSearchParams;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SearchParams extends GenSearchParams {
    public static final Creator<SearchParams> CREATOR = new Creator<SearchParams>() {
        public SearchParams[] newArray(int size) {
            return new SearchParams[size];
        }

        public SearchParams createFromParcel(Parcel source) {
            SearchParams object = new SearchParams();
            object.readFromParcel(source);
            return object;
        }
    };
    private String checkInString;
    private String checkOutString;

    @JsonProperty("room_types")
    public void setRoomTypes(List<String> roomTypeKeys) {
        if (roomTypeKeys != null) {
            this.mRoomTypes = new ArrayList(roomTypeKeys.size());
            for (String key : roomTypeKeys) {
                this.mRoomTypes.add(C6120RoomType.fromKey(key));
            }
        }
    }

    public void setRoomTypeEnums(List<C6120RoomType> roomTypes) {
        this.mRoomTypes = roomTypes;
    }

    @JsonProperty("amenities")
    public void setAmenities(List<Integer> amenityIds) {
        if (amenityIds != null) {
            this.mAmenities = new ArrayList(amenityIds.size());
            for (Integer id : amenityIds) {
                Amenity amenity = Amenity.forId(id.intValue());
                if (amenity != null) {
                    this.mAmenities.add(amenity);
                }
            }
        }
    }

    @JsonProperty("amenities_to_filter_out")
    public void setAmenitiesToFilterOut(List<Integer> amenityIds) {
        if (amenityIds != null) {
            this.mAmenitiesToFilterOut = new ArrayList(amenityIds.size());
            for (Integer id : amenityIds) {
                Amenity amenity = Amenity.forId(id.intValue());
                if (amenity != null) {
                    this.mAmenitiesToFilterOut.add(amenity);
                }
            }
        }
    }

    @JsonProperty("property_type_id")
    public void setPropertyTypes(List<Integer> propertyTypes) {
        if (propertyTypes != null) {
            Set<PropertyType> propertyTypeSet = Sets.newHashSet();
            for (Integer id : propertyTypes) {
                propertyTypeSet.add(PropertyType.getTypeFromKey(id.intValue()));
            }
            this.mPropertyTypes = new ArrayList(propertyTypeSet);
        }
    }

    public boolean hasSetInstantBookOnly() {
        return this.mInstantBookOnly != null;
    }

    public Boolean isInstantBookOnly() {
        return Boolean.valueOf(hasSetInstantBookOnly() ? super.isInstantBookOnly().booleanValue() : false);
    }

    private boolean hasSetBusinessTravelReady() {
        return this.mBusinessTravelReady != null;
    }

    public Boolean isBusinessTravelReady() {
        return Boolean.valueOf(hasSetBusinessTravelReady() ? super.isBusinessTravelReady().booleanValue() : false);
    }

    public void setGuestDetails(GuestDetails guestDetails) {
        setAdults(guestDetails.adultsCount());
        setChildren(guestDetails.childrenCount());
        setInfants(guestDetails.infantsCount());
        setGuests(guestDetails.totalGuestCount());
        setPets(guestDetails.isBringingPets());
    }

    public GuestDetails getGuestDetails() {
        GuestDetails guestDetails = GuestDetails.newInstance().childrenCount(this.mChildren).infantsCount(this.mInfants).isBringingPets(this.mPets);
        int numAdults = this.mAdults > 0 ? this.mAdults : this.mGuests;
        if (numAdults > 0) {
            guestDetails.adultsCount(numAdults);
        }
        return guestDetails;
    }

    public void fromSearchFilters(SearchFilters filters) {
        setMinPrice(filters.getMinPrice());
        setMaxPrice(filters.getMaxPrice());
        setNumBeds(filters.getNumBeds());
        setNumBedrooms(filters.getNumBedrooms());
        setNumBathrooms(filters.getNumBathrooms());
        setRoomTypeEnums(new ArrayList(filters.getRoomTypes()));
        setInstantBookOnly(Boolean.valueOf(filters.isInstantBookOnly()));
        setBusinessTravelReady(Boolean.valueOf(filters.isBusinessTravelReady()));
        this.mAmenities = filters.getAmenities();
        this.mAmenitiesToFilterOut = filters.getAmenitiesToFilterOut();
        setLanguages(filters.getLanguages());
        this.mPropertyTypes = filters.getPropertyTypes();
    }

    @JsonProperty("trip_purpose")
    public void setTripPurpose(String tripPurpose) {
        this.mTripPurpose = TripPurpose.fromKey(tripPurpose);
    }

    public int getNumOtherFilters() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        int numFilters = 0 + ((!hasSetInstantBookOnly() || !isInstantBookOnly().booleanValue()) ? 0 : 1);
        if (this.mTripPurpose != null) {
            i = 1;
        } else {
            i = 0;
        }
        int numFilters2 = numFilters + i;
        if (this.mNumBeds != 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int numFilters3 = numFilters2 + i2;
        if (this.mNumBedrooms != 0) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int numFilters4 = numFilters3 + i3;
        if (this.mNumBathrooms != 0) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int numFilters5 = numFilters4 + i4 + (this.mAmenities == null ? 0 : this.mAmenities.size()) + (this.mPropertyTypes == null ? 0 : this.mPropertyTypes.size()) + (this.mRoomTypes == null ? 0 : this.mRoomTypes.size());
        if (!(this.mMinPrice == 0 && this.mMaxPrice == 0)) {
            i5 = 1;
        }
        return numFilters5 + i5;
    }

    public boolean hasLatLngBounds() {
        return (getNeLat() == 0.0d || getNeLng() == 0.0d || getSwLat() == 0.0d || getSwLng() == 0.0d) ? false : true;
    }

    public String getTimeText(Context context) {
        return getTimeText(context, this.mCheckin, this.mCheckout);
    }

    public static String getTimeText(Context context, AirDate checkInDate, AirDate checkOutDate) {
        if (checkInDate == null) {
            return context.getString(C0716R.string.explore_anytime);
        }
        if (checkOutDate != null) {
            return checkInDate.getDateSpanString(context, checkOutDate);
        }
        if (AirDate.isToday(checkInDate)) {
            return context.getString(C0716R.string.search_now);
        }
        return checkInDate.getDateString(context);
    }

    public void setCheckInString(String checkInString2) {
        this.checkInString = checkInString2;
    }

    public void setCheckOutString(String checkOutString2) {
        this.checkOutString = checkOutString2;
    }

    public AirDate getCheckin() {
        if (this.checkInString != null) {
            return AirDate.fromISODateString(this.checkInString);
        }
        return super.getCheckin();
    }

    public AirDate getCheckout() {
        if (this.checkOutString != null) {
            return AirDate.fromISODateString(this.checkOutString);
        }
        return super.getCheckout();
    }
}
