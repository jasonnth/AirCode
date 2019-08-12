package com.airbnb.android.core.models.find;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.models.SearchParams;
import com.airbnb.android.core.p008mt.models.C6213ProductType;
import com.airbnb.android.core.p008mt.models.PrimaryCategory;
import com.airbnb.android.core.utils.AndroidUtils;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.ParcelableUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExperienceFilters implements Parcelable {
    public static final Creator<ExperienceFilters> CREATOR = new Creator<ExperienceFilters>() {
        public ExperienceFilters[] newArray(int size) {
            return new ExperienceFilters[size];
        }

        public ExperienceFilters createFromParcel(Parcel source) {
            ExperienceFilters object = new ExperienceFilters();
            object.readFromParcel(source);
            return object;
        }
    };
    private final Set<OnExperienceSearchFiltersChangedListener> changeListeners = new HashSet();
    @JsonProperty("experience_categories")
    @State
    protected ArrayList<PrimaryCategory> experienceCategories = new ArrayList<>();
    @JsonProperty("experience_product_types")
    @State
    protected ArrayList<C6213ProductType> experienceProductTypes = new ArrayList<>();
    @JsonProperty("experience_social_good_only")
    @State
    protected Boolean experienceSocialGoodOnly = Boolean.valueOf(false);

    public interface OnExperienceSearchFiltersChangedListener {
        void onExperienceSearchFiltersChanged();
    }

    public ExperienceFilters(Bundle savedState) {
        IcepickWrapper.restoreInstanceState(this, savedState);
    }

    protected ExperienceFilters() {
    }

    public static ExperienceFilters cloneFrom(ExperienceFilters searchFilters, boolean keepListeners) {
        ExperienceFilters newFilters = (ExperienceFilters) ParcelableUtils.cloneParcelable(searchFilters);
        if (keepListeners) {
            newFilters.setChangeListeners(searchFilters.changeListeners);
        }
        return newFilters;
    }

    public Boolean isExperienceSocialGoodOnly() {
        return this.experienceSocialGoodOnly;
    }

    @JsonProperty("experience_social_good_only")
    public void setExperienceSocialGoodOnly(Boolean value) {
        this.experienceSocialGoodOnly = value;
    }

    public List<C6213ProductType> getExperienceProductTypes() {
        return this.experienceProductTypes;
    }

    @JsonProperty("experience_product_types")
    public void setExperienceProductTypes(ArrayList<C6213ProductType> value) {
        this.experienceProductTypes = value;
    }

    public List<PrimaryCategory> getExperienceCategories() {
        return this.experienceCategories;
    }

    @JsonProperty("experience_categories")
    public void setExperienceCategories(ArrayList<PrimaryCategory> value) {
        this.experienceCategories = value;
    }

    private void setChangeListeners(Set<OnExperienceSearchFiltersChangedListener> listeners) {
        this.changeListeners.clear();
        this.changeListeners.addAll(listeners);
    }

    public void addChangeListener(OnExperienceSearchFiltersChangedListener listener) {
        AndroidUtils.validateMainThread();
        Check.state(this.changeListeners.add(listener), "Tried to add a duplicate listener");
    }

    public void removeChangeListener(OnExperienceSearchFiltersChangedListener listener) {
        AndroidUtils.validateMainThread();
        Check.state(this.changeListeners.remove(listener), "Tried to remove a listener that didn't exist");
    }

    private void notifyChangeListeners() {
        AndroidUtils.validateMainThread();
        for (OnExperienceSearchFiltersChangedListener listener : this.changeListeners) {
            listener.onExperienceSearchFiltersChanged();
        }
    }

    public List<PrimaryCategory> getPrimaryCategories() {
        return this.experienceCategories;
    }

    public boolean hasPrimaryCategory(PrimaryCategory primaryCategory) {
        return getPrimaryCategories().contains(primaryCategory);
    }

    public void setHasPrimaryCategory(PrimaryCategory primaryCategory, boolean hasPrimaryCategory) {
        boolean changed;
        if (hasPrimaryCategory) {
            changed = this.experienceCategories.add(primaryCategory);
        } else {
            changed = this.experienceCategories.remove(primaryCategory);
        }
        if (changed) {
            notifyChangeListeners();
        }
    }

    public boolean hasProductType(C6213ProductType productType) {
        return this.experienceProductTypes.contains(productType);
    }

    public void setHasProductType(C6213ProductType productType, boolean hasProductType) {
        boolean changed;
        if (hasProductType) {
            changed = this.experienceProductTypes.add(productType);
        } else {
            changed = this.experienceProductTypes.remove(productType);
        }
        if (changed) {
            notifyChangeListeners();
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void resetToDefaults() {
        this.experienceProductTypes.clear();
        this.experienceCategories.clear();
        this.experienceSocialGoodOnly = Boolean.valueOf(false);
        notifyChangeListeners();
    }

    public int getDetailFiltersCount() {
        return 0 + getExperienceProductTypes().size() + getExperienceCategories().size() + (isExperienceSocialGoodOnly().booleanValue() ? 1 : 0);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.experienceSocialGoodOnly);
        parcel.writeList(this.experienceProductTypes);
        parcel.writeList(this.experienceCategories);
    }

    public void readFromParcel(Parcel source) {
        this.experienceSocialGoodOnly = (Boolean) source.readValue(Boolean.class.getClassLoader());
        source.readList(this.experienceProductTypes, C6213ProductType.class.getClassLoader());
        source.readList(this.experienceCategories, PrimaryCategory.class.getClassLoader());
    }

    public void updateFromSearchParams(SearchParams searchParams) {
        if (!ListUtils.isEmpty((Collection<?>) searchParams.getExperienceCategories())) {
            this.experienceCategories.clear();
            this.experienceCategories.addAll(searchParams.getExperienceCategories());
        }
        notifyChangeListeners();
    }
}
