package com.airbnb.android.core.models.find;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.p008mt.models.ExplorePlacesTopCategory;
import com.airbnb.android.core.p008mt.models.GuidebookItemType;
import com.airbnb.android.core.utils.AndroidUtils;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.ParcelableUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlaceFilters implements Parcelable {
    public static final Creator<PlaceFilters> CREATOR = new Creator<PlaceFilters>() {
        public PlaceFilters[] newArray(int size) {
            return new PlaceFilters[size];
        }

        public PlaceFilters createFromParcel(Parcel source) {
            PlaceFilters object = new PlaceFilters();
            object.readFromParcel(source);
            return object;
        }
    };
    @JsonProperty("guidebook_top_categories")
    @State
    protected ArrayList<ExplorePlacesTopCategory> categories = new ArrayList<>();
    private final Set<OnPlaceSearchFiltersChangedListener> changeListeners = new HashSet();
    @JsonProperty("guidebook_item_types")
    @State
    protected ArrayList<GuidebookItemType> guidebookItemTypes = new ArrayList<>();

    public interface OnPlaceSearchFiltersChangedListener {
        void onPlaceSearchFiltersChanged();
    }

    public PlaceFilters(Bundle savedState) {
        IcepickWrapper.restoreInstanceState(this, savedState);
    }

    protected PlaceFilters() {
    }

    public List<GuidebookItemType> getGuidebookItemTypes() {
        return this.guidebookItemTypes;
    }

    @JsonProperty("guidebook_item_types")
    public void setGuidebookItemTypes(ArrayList<GuidebookItemType> value) {
        this.guidebookItemTypes = value;
    }

    public List<ExplorePlacesTopCategory> getGuidebookCategories() {
        return this.categories;
    }

    @JsonProperty("guidebook_top_categories")
    public void setGuidebookCategories(ArrayList<ExplorePlacesTopCategory> value) {
        this.categories = value;
    }

    public static PlaceFilters cloneFrom(PlaceFilters searchFilters, boolean keepListeners) {
        PlaceFilters newFilters = (PlaceFilters) ParcelableUtils.cloneParcelable(searchFilters);
        if (keepListeners) {
            newFilters.setChangeListeners(newFilters.changeListeners);
        }
        return newFilters;
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    private void setChangeListeners(Set<OnPlaceSearchFiltersChangedListener> listeners) {
        this.changeListeners.clear();
        this.changeListeners.addAll(listeners);
    }

    public void addChangeListener(OnPlaceSearchFiltersChangedListener listener) {
        AndroidUtils.validateMainThread();
        Check.state(this.changeListeners.add(listener), "Tried to add a duplicate listener");
    }

    public void removeChangeListener(OnPlaceSearchFiltersChangedListener listener) {
        AndroidUtils.validateMainThread();
        Check.state(this.changeListeners.remove(listener), "Tried to remove a listener that didn't exist");
    }

    private void notifyChangeListeners() {
        AndroidUtils.validateMainThread();
        for (OnPlaceSearchFiltersChangedListener listener : this.changeListeners) {
            listener.onPlaceSearchFiltersChanged();
        }
    }

    public void setGuidebookItemTypes(Collection<GuidebookItemType> itemTypes) {
        this.guidebookItemTypes.clear();
        this.guidebookItemTypes.addAll(itemTypes);
        notifyChangeListeners();
    }

    public void setHasGuidebookItemType(GuidebookItemType itemType, boolean hasItemType) {
        boolean changed;
        if (hasItemType) {
            changed = this.guidebookItemTypes.add(itemType);
        } else {
            changed = this.guidebookItemTypes.remove(itemType);
        }
        if (changed) {
            notifyChangeListeners();
        }
    }

    public boolean hasCateory(ExplorePlacesTopCategory category) {
        return this.categories.contains(category);
    }

    public void setHasFilter(ExplorePlacesTopCategory filter, boolean hasFilter) {
        boolean changed;
        if (hasFilter) {
            changed = this.categories.add(filter);
        } else {
            changed = this.categories.remove(filter);
        }
        if (changed) {
            notifyChangeListeners();
        }
    }

    public int getDetailFiltersCount() {
        return getGuidebookCategories().size();
    }

    public void reset() {
        boolean changed = !this.guidebookItemTypes.isEmpty() || !this.categories.isEmpty();
        this.guidebookItemTypes.clear();
        this.categories.clear();
        if (changed) {
            notifyChangeListeners();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeList(this.guidebookItemTypes);
        parcel.writeList(this.categories);
    }

    public void readFromParcel(Parcel source) {
        source.readList(this.guidebookItemTypes, GuidebookItemType.class.getClassLoader());
        source.readList(this.categories, ExplorePlacesTopCategory.class.getClassLoader());
    }
}
