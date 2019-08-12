package com.airbnb.android.core.models.find;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.businesstravel.models.BusinessTravelReadyFilterCriteria;
import com.airbnb.android.core.enums.PropertyType;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.android.core.models.SearchParams;
import com.airbnb.android.core.utils.AndroidUtils;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.ParcelableUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchFilters implements Parcelable {
    public static final Creator<SearchFilters> CREATOR = new Creator<SearchFilters>() {
        public SearchFilters createFromParcel(Parcel in) {
            return new SearchFilters(in);
        }

        public SearchFilters[] newArray(int size) {
            return new SearchFilters[size];
        }
    };
    public static final int DEFAULT_BATHROOM_COUNT = 0;
    public static final int DEFAULT_BEDROOM_COUNT = 0;
    public static final int DEFAULT_BED_COUNT = 0;
    @State
    ArrayList<Amenity> amenities = new ArrayList<>();
    @State
    ArrayList<Amenity> amenitiesToFilterOut = new ArrayList<>();
    private final Set<OnSearchFiltersChangedListener> changeListeners = new HashSet();
    @State
    String currencyType;
    @State
    boolean isBusinessTravelReady;
    @State
    Boolean isInstantBookOnly;
    @State
    ArrayList<String> keywords = new ArrayList<>();
    @State
    ArrayList<Integer> languages = new ArrayList<>();
    @State
    int maxPrice;
    @State
    int minPrice;
    @State
    AirDateTime modifiedAt = AirDateTime.now();
    @State
    int numBathrooms = 0;
    @State
    int numBedrooms = 0;
    @State
    int numBeds = 0;
    @State
    int priceFilterSelection;
    @State
    ArrayList<PropertyType> propertyTypes = new ArrayList<>();
    @State
    ArrayList<C6120RoomType> roomTypes = new ArrayList<>();
    private boolean skipChangeNotifications;
    @State
    TripPurpose tripPurpose;

    public interface OnSearchFiltersChangedListener {
        void onSearchFiltersChanged(int i);
    }

    protected SearchFilters(Parcel in) {
        boolean z = false;
        if (in.readByte() != 0) {
            z = true;
        }
        this.skipChangeNotifications = z;
        this.modifiedAt = (AirDateTime) in.readParcelable(AirDateTime.class.getClassLoader());
        this.minPrice = in.readInt();
        this.maxPrice = in.readInt();
        this.numBeds = in.readInt();
        this.numBedrooms = in.readInt();
        this.numBathrooms = in.readInt();
        this.currencyType = in.readString();
        this.amenities = in.createTypedArrayList(Amenity.CREATOR);
        this.amenitiesToFilterOut = in.createTypedArrayList(Amenity.CREATOR);
        this.roomTypes = in.createTypedArrayList(C6120RoomType.CREATOR);
        this.propertyTypes = in.createTypedArrayList(PropertyType.CREATOR);
        this.tripPurpose = (TripPurpose) in.readParcelable(TripPurpose.class.getClassLoader());
        this.keywords = in.createStringArrayList();
        this.isInstantBookOnly = (Boolean) in.readValue(Integer.class.getClassLoader());
        this.isBusinessTravelReady = ((Boolean) in.readValue(Integer.class.getClassLoader())).booleanValue();
        in.readList(this.languages, Integer.class.getClassLoader());
        this.priceFilterSelection = in.readInt();
    }

    public static SearchFilters cloneFrom(SearchFilters searchFilters, boolean keepListeners) {
        SearchFilters newFilters = (SearchFilters) ParcelableUtils.cloneParcelable(searchFilters);
        if (keepListeners) {
            newFilters.setChangeListeners(searchFilters.changeListeners);
        }
        return newFilters;
    }

    public SearchFilters() {
        resetToDefaults(false);
    }

    public SearchFilters(Bundle savedState) {
        IcepickWrapper.restoreInstanceState(this, savedState);
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void addChangeListener(OnSearchFiltersChangedListener listener) {
        AndroidUtils.validateMainThread();
        Check.state(this.changeListeners.add(listener), "Tried to add a duplicate listener");
    }

    public void removeChangeListener(OnSearchFiltersChangedListener listener) {
        AndroidUtils.validateMainThread();
        Check.state(this.changeListeners.remove(listener), "Tried to remove a listener that didn't exist");
    }

    private void notifyChangeListeners(int filterType) {
        AndroidUtils.validateMainThread();
        if (!this.skipChangeNotifications) {
            this.modifiedAt = AirDateTime.now();
            for (OnSearchFiltersChangedListener listener : this.changeListeners) {
                listener.onSearchFiltersChanged(filterType);
            }
        }
    }

    private void notifyChangeListeners() {
        notifyChangeListeners(1);
    }

    public AirDateTime getModifiedAt() {
        return this.modifiedAt;
    }

    public TripPurpose getTripPurpose() {
        return this.tripPurpose;
    }

    public boolean hasTripPurpose() {
        return this.tripPurpose != null;
    }

    public void setTripPurpose(TripPurpose tripPurpose2) {
        this.tripPurpose = tripPurpose2;
    }

    public boolean hasSetInstantBookOnly() {
        return this.isInstantBookOnly != null;
    }

    public boolean isInstantBookOnly() {
        return hasSetInstantBookOnly() && this.isInstantBookOnly.booleanValue();
    }

    public void setIsInstantBookOnly(boolean newInstantBookOnlyValue) {
        boolean changed = this.isInstantBookOnly == null || newInstantBookOnlyValue != this.isInstantBookOnly.booleanValue();
        this.isInstantBookOnly = Boolean.valueOf(newInstantBookOnlyValue);
        if (changed) {
            notifyChangeListeners();
        }
    }

    public boolean isBusinessTravelReady() {
        return this.isBusinessTravelReady;
    }

    public void setBusinessTravelReady(boolean businessTravelReady, BusinessTravelReadyFilterCriteria btrCriteria) {
        boolean changed;
        if (businessTravelReady != this.isBusinessTravelReady) {
            changed = true;
        } else {
            changed = false;
        }
        if (changed) {
            this.isBusinessTravelReady = businessTravelReady;
            this.skipChangeNotifications = true;
            updateBusinessTravelReadyFilters(btrCriteria, this.isBusinessTravelReady);
            this.skipChangeNotifications = false;
            notifyChangeListeners();
        }
    }

    private void updateBusinessTravelReadyFilters(BusinessTravelReadyFilterCriteria btrCriteria, boolean addToFilters) {
        for (Amenity amenity : btrCriteria.getHostingAmenitiesFromIds()) {
            setHasAmenity(amenity, addToFilters);
        }
        for (Amenity amenityToFilterOut : btrCriteria.getAmenitiesToFilterOutFromIds()) {
            setHasAmenityToFilterOut(amenityToFilterOut, addToFilters);
        }
        for (C6120RoomType roomType : btrCriteria.getRoomTypesFromServerKeys()) {
            setHasRoomType(roomType, addToFilters);
        }
        for (PropertyType propertyType : btrCriteria.getPropertyTypesFromIds()) {
            setHasPropertyType(propertyType, addToFilters);
        }
    }

    public int getMinPrice() {
        return this.minPrice;
    }

    public void setMinPrice(int minPrice2) {
        boolean changed = minPrice2 != this.minPrice;
        this.minPrice = minPrice2;
        if (changed) {
            notifyChangeListeners();
        }
    }

    public int getMaxPrice() {
        return this.maxPrice;
    }

    public void setMaxPrice(int maxPrice2) {
        boolean changed = maxPrice2 != this.maxPrice;
        this.maxPrice = maxPrice2;
        if (changed) {
            notifyChangeListeners();
        }
    }

    public void clearPrice() {
        boolean changed;
        if (this.minPrice == 0 && this.maxPrice == 0) {
            changed = false;
        } else {
            changed = true;
        }
        this.minPrice = 0;
        this.maxPrice = 0;
        if (changed) {
            notifyChangeListeners();
        }
    }

    public void setPriceFilterSelection(int priceFilterSelection2) {
        this.priceFilterSelection = priceFilterSelection2;
    }

    public int getPriceFilterSelection() {
        return this.priceFilterSelection;
    }

    public int getNumBeds() {
        return this.numBeds;
    }

    public void setNumBeds(int numBeds2) {
        boolean changed = numBeds2 != this.numBeds;
        this.numBeds = numBeds2;
        if (changed) {
            notifyChangeListeners();
        }
    }

    public int getNumBedrooms() {
        return this.numBedrooms;
    }

    public void setNumBedrooms(int numBedrooms2) {
        boolean changed = numBedrooms2 != this.numBedrooms;
        this.numBedrooms = numBedrooms2;
        if (changed) {
            notifyChangeListeners();
        }
    }

    public int getNumBathrooms() {
        return this.numBathrooms;
    }

    public void setNumBathrooms(int numBathrooms2) {
        boolean changed = numBathrooms2 != this.numBathrooms;
        this.numBathrooms = numBathrooms2;
        if (changed) {
            notifyChangeListeners();
        }
    }

    public String getCurrencyType() {
        return this.currencyType;
    }

    public List<Amenity> getAmenities() {
        return ImmutableList.copyOf((Collection<? extends E>) this.amenities);
    }

    public void setAmenities(List<Amenity> amenities2) {
        this.amenities.clear();
        this.amenities.addAll(amenities2);
        notifyChangeListeners();
    }

    public void addAmenity(Amenity amenity) {
        if (!this.amenities.contains(amenity)) {
            this.amenities.add(amenity);
            notifyChangeListeners();
        }
    }

    public void removeAmenity(Amenity amenity) {
        if (this.amenities.contains(amenity)) {
            this.amenities.remove(amenity);
            notifyChangeListeners();
        }
    }

    public void setHasAmenity(Amenity amenity, boolean hasAmenity) {
        updateFilterTypes(amenity, this.amenities, hasAmenity);
    }

    public List<Amenity> getAmenitiesToFilterOut() {
        return ImmutableList.copyOf((Collection<? extends E>) this.amenitiesToFilterOut);
    }

    public void setAmenitiesToFilterOut(List<Amenity> amenitiesToFilterOut2) {
        this.amenitiesToFilterOut.clear();
        this.amenitiesToFilterOut.addAll(amenitiesToFilterOut2);
        notifyChangeListeners();
    }

    public void setHasAmenityToFilterOut(Amenity amenityToFilterOut, boolean hasAmenityToFilterOut) {
        updateFilterTypes(amenityToFilterOut, this.amenitiesToFilterOut, hasAmenityToFilterOut);
    }

    public Set<C6120RoomType> getRoomTypes() {
        return ImmutableSet.copyOf((Collection<? extends E>) this.roomTypes);
    }

    public boolean hasRoomType(C6120RoomType roomType) {
        return getRoomTypes().contains(roomType);
    }

    public void setRoomTypes(Collection<C6120RoomType> roomTypes2) {
        this.roomTypes.clear();
        this.roomTypes.addAll(roomTypes2);
        notifyChangeListeners();
    }

    public void setHasRoomType(C6120RoomType roomType, boolean hasRoomType) {
        updateFilterTypes(roomType, this.roomTypes, hasRoomType);
    }

    /* access modifiers changed from: 0000 */
    public <T> void updateFilterTypes(T filterToUpdate, ArrayList<T> originalFilters, boolean isAdd) {
        boolean changed;
        if (isAdd) {
            changed = !originalFilters.contains(filterToUpdate);
            if (changed) {
                originalFilters.add(filterToUpdate);
            }
        } else {
            changed = originalFilters.remove(filterToUpdate);
        }
        if (changed) {
            notifyChangeListeners();
        }
    }

    public List<String> getKeywords() {
        return ImmutableList.copyOf((Collection<? extends E>) this.keywords);
    }

    public void setKeywords(List<String> keywords2) {
        this.keywords.clear();
        this.keywords.addAll(keywords2);
        notifyChangeListeners();
    }

    public List<Integer> getLanguages() {
        return ImmutableList.copyOf((Collection<? extends E>) this.languages);
    }

    public void setLanguages(List<Integer> languages2) {
        this.languages.clear();
        this.languages.addAll(languages2);
        notifyChangeListeners();
    }

    public List<PropertyType> getPropertyTypes() {
        return ImmutableList.copyOf((Collection<? extends E>) this.propertyTypes);
    }

    public boolean hasPropertyTypes() {
        return !ListUtils.isEmpty((Collection<?>) this.propertyTypes);
    }

    public void setPropertyTypes(List<PropertyType> propertyTypes2) {
        this.propertyTypes.clear();
        this.propertyTypes.addAll(propertyTypes2);
        notifyChangeListeners();
    }

    public void setHasPropertyType(PropertyType propertyType, boolean hasPropertyType) {
        updateFilterTypes(propertyType, this.propertyTypes, hasPropertyType);
    }

    private void setChangeListeners(Set<OnSearchFiltersChangedListener> listeners) {
        this.changeListeners.clear();
        this.changeListeners.addAll(listeners);
    }

    public void updateFromSearchParams(SearchParams searchParams) {
        updateFromSearchParams(searchParams, true);
    }

    public void updateFromSearchParams(SearchParams searchParams, boolean includeSecondarySearchParams) {
        this.skipChangeNotifications = true;
        resetToDefaults(false);
        if (includeSecondarySearchParams) {
            int minPrice2 = searchParams.getMinPrice();
            if (minPrice2 > 0) {
                this.minPrice = minPrice2;
            }
            int maxPrice2 = searchParams.getMaxPrice();
            if (maxPrice2 > 0) {
                this.maxPrice = maxPrice2;
            }
            String currency = searchParams.getCurrency();
            if (!TextUtils.isEmpty(currency)) {
                this.currencyType = currency;
            }
            this.numBeds = searchParams.getNumBeds();
            this.numBedrooms = searchParams.getNumBedrooms();
            this.numBathrooms = searchParams.getNumBathrooms();
            List<C6120RoomType> roomTypes2 = searchParams.getRoomTypes();
            if (roomTypes2 != null) {
                setRoomTypes(roomTypes2);
            }
            this.isInstantBookOnly = searchParams.hasSetInstantBookOnly() ? searchParams.isInstantBookOnly() : null;
            this.isBusinessTravelReady = searchParams.isBusinessTravelReady().booleanValue();
            setTripPurpose(searchParams.getTripPurpose());
            List<Amenity> amenities2 = searchParams.getAmenities();
            if (amenities2 != null) {
                setAmenities(amenities2);
            }
            List<Amenity> amenitiesToFilterOut2 = searchParams.getAmenitiesToFilterOut();
            if (amenitiesToFilterOut2 != null) {
                setAmenitiesToFilterOut(amenitiesToFilterOut2);
            }
            List<String> keywords2 = searchParams.getKeywords();
            if (keywords2 != null) {
                setKeywords(keywords2);
            }
            List<Integer> languages2 = searchParams.getLanguages();
            if (languages2 != null) {
                setLanguages(languages2);
            }
            List<PropertyType> propertyTypes2 = searchParams.getPropertyTypes();
            if (propertyTypes2 != null) {
                setPropertyTypes(propertyTypes2);
            }
        }
        this.skipChangeNotifications = false;
        notifyChangeListeners();
    }

    public void resetToDefaults() {
        resetToDefaults(true);
    }

    public void resetToDefaults(boolean turnOffInstantBookAutoOn) {
        this.isInstantBookOnly = turnOffInstantBookAutoOn ? Boolean.valueOf(false) : null;
        this.isBusinessTravelReady = false;
        this.minPrice = 0;
        this.maxPrice = 0;
        this.priceFilterSelection = 0;
        this.numBeds = 0;
        this.numBedrooms = 0;
        this.numBathrooms = 0;
        this.currencyType = null;
        this.amenities.clear();
        this.keywords.clear();
        this.languages.clear();
        this.amenitiesToFilterOut.clear();
        this.propertyTypes.clear();
        this.roomTypes.clear();
        this.tripPurpose = null;
        notifyChangeListeners(2);
    }

    public int getDetailFiltersCount() {
        int i;
        int i2;
        int i3;
        int i4 = 1;
        int count = 0 + this.roomTypes.size() + (isInstantBookOnly() ? 1 : 0);
        if (this.minPrice > 0 || this.maxPrice > 0) {
            i = 1;
        } else {
            i = 0;
        }
        int count2 = count + i;
        if (this.numBeds != 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int count3 = count2 + i2;
        if (this.numBedrooms != 0) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int count4 = count3 + i3;
        if (this.numBathrooms == 0) {
            i4 = 0;
        }
        return count4 + i4 + this.amenities.size();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.skipChangeNotifications ? 1 : 0));
        dest.writeParcelable(this.modifiedAt, flags);
        dest.writeInt(this.minPrice);
        dest.writeInt(this.maxPrice);
        dest.writeInt(this.numBeds);
        dest.writeInt(this.numBedrooms);
        dest.writeInt(this.numBathrooms);
        dest.writeString(this.currencyType);
        dest.writeTypedList(this.amenities);
        dest.writeTypedList(this.amenitiesToFilterOut);
        dest.writeTypedList(this.roomTypes);
        dest.writeTypedList(this.propertyTypes);
        dest.writeParcelable(this.tripPurpose, flags);
        dest.writeStringList(this.keywords);
        dest.writeValue(this.isInstantBookOnly);
        dest.writeValue(Boolean.valueOf(this.isBusinessTravelReady));
        dest.writeList(this.languages);
        dest.writeInt(this.priceFilterSelection);
    }
}
