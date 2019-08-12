package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.enums.FilterSuggestionType;
import com.airbnb.android.core.models.generated.GenCategorizedFilters;
import com.airbnb.android.utils.ListUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;

public class CategorizedFilters extends GenCategorizedFilters {
    public static final Creator<CategorizedFilters> CREATOR = new Creator<CategorizedFilters>() {
        public CategorizedFilters[] newArray(int size) {
            return new CategorizedFilters[size];
        }

        public CategorizedFilters createFromParcel(Parcel source) {
            CategorizedFilters object = new CategorizedFilters();
            object.readFromParcel(source);
            return object;
        }
    };
    private List<FilterSuggestionItem> mOriginalItems;

    @JsonProperty("filter_bar_items")
    public void setItems(List<FilterSuggestionItem> value) {
        this.mItems = value;
        this.mOriginalItems = Lists.newArrayList((Iterable<? extends E>) value);
        if (isRoomTypeFilters()) {
            this.mType = FilterSuggestionType.RoomTypes;
        } else if (isBedroomFilters()) {
            this.mType = FilterSuggestionType.Bedrooms;
        } else if (isBedFilters()) {
            this.mType = FilterSuggestionType.Beds;
        }
    }

    public boolean isRoomTypeFilters() {
        return !ListUtils.isEmpty((Collection<?>) this.mItems) && firstFilter().hasRoomTypes();
    }

    public boolean isBedroomFilters() {
        return !ListUtils.isEmpty((Collection<?>) this.mItems) && firstFilter().hasNumBedrooms();
    }

    public boolean isBedFilters() {
        return !ListUtils.isEmpty((Collection<?>) this.mItems) && firstFilter().hasNumBeds();
    }

    private FilterSuggestionFilters firstFilter() {
        return ((FilterSuggestionItem) this.mItems.get(0)).getFilters();
    }

    public void clearFilterItem(FilterSuggestionItem item) {
        if (this.mItems.size() == 1) {
            resetItems();
        } else {
            this.mItems.remove(item);
        }
    }

    public void setRoomTypeFilters(Collection<C6120RoomType> roomTypes) {
        if (ListUtils.isEmpty(roomTypes)) {
            resetItems();
            return;
        }
        List<FilterSuggestionItem> newItems = Lists.newArrayList();
        for (C6120RoomType roomType : roomTypes) {
            for (FilterSuggestionItem item : this.mOriginalItems) {
                if (item.getFilters().getRoomTypes().contains(roomType)) {
                    item.setSelected(true);
                    newItems.add(item);
                }
            }
        }
        this.mItems = newItems;
    }

    public void setBedroomFilters(int bedroomCount) {
        if (bedroomCount == 0) {
            resetItems();
            return;
        }
        List<FilterSuggestionItem> newItems = Lists.newArrayList();
        for (FilterSuggestionItem item : this.mOriginalItems) {
            if (item.getFilters().getNumBedrooms() == bedroomCount) {
                item.setSelected(true);
                newItems.add(item);
            }
        }
        this.mItems = newItems;
    }

    public void setBedFilters(int bedCount) {
        if (bedCount == 0) {
            resetItems();
            return;
        }
        List<FilterSuggestionItem> newItems = Lists.newArrayList();
        for (FilterSuggestionItem item : this.mOriginalItems) {
            if (item.getFilters().getNumBeds() == bedCount) {
                item.setSelected(true);
                newItems.add(item);
            }
        }
        this.mItems = newItems;
    }

    private void resetItems() {
        this.mItems.clear();
        for (FilterSuggestionItem item : this.mOriginalItems) {
            item.setSelected(false);
        }
        this.mItems.addAll(this.mOriginalItems);
    }
}
