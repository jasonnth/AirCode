package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenFilterSuggestionFilters;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class FilterSuggestionFilters extends GenFilterSuggestionFilters {
    public static final Creator<FilterSuggestionFilters> CREATOR = new Creator<FilterSuggestionFilters>() {
        public FilterSuggestionFilters[] newArray(int size) {
            return new FilterSuggestionFilters[size];
        }

        public FilterSuggestionFilters createFromParcel(Parcel source) {
            FilterSuggestionFilters object = new FilterSuggestionFilters();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("room_types")
    public void setRoomTypes(List<String> roomTypeKeys) {
        if (roomTypeKeys != null) {
            this.mRoomTypes = new ArrayList(roomTypeKeys.size());
            for (String key : roomTypeKeys) {
                C6120RoomType roomType = C6120RoomType.fromKey(key);
                if (roomType != null) {
                    this.mRoomTypes.add(roomType);
                }
            }
        }
    }

    @JsonProperty("hosting_amenities")
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

    public boolean hasSetInstantBookOnly() {
        return this.mInstantBookOnly != null;
    }

    public boolean hasMinPrice() {
        return this.mMinPrice > 0;
    }

    public boolean hasMaxPrice() {
        return this.mMaxPrice > 0;
    }

    public boolean hasNumBedrooms() {
        return this.mNumBedrooms > 0;
    }

    public boolean hasNumBeds() {
        return this.mNumBeds > 0;
    }

    public boolean hasRoomTypes() {
        return this.mRoomTypes != null && this.mRoomTypes.size() > 0;
    }

    public boolean hasAmenities() {
        return this.mAmenities != null && this.mAmenities.size() > 0;
    }
}
