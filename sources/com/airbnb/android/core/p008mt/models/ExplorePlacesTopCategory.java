package com.airbnb.android.core.p008mt.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.fasterxml.jackson.annotation.JsonCreator;

/* renamed from: com.airbnb.android.core.mt.models.ExplorePlacesTopCategory */
public enum ExplorePlacesTopCategory implements Parcelable {
    DrinksAndNightlife(C0716R.string.explore_places_category_drinks, "Drinks and Nightlife"),
    FoodScene(C0716R.string.explore_places_category_food_scene, "Food Scene"),
    ArtsAndCulture(C0716R.string.explore_places_category_art_gallery, "Arts and Culture"),
    ParksAndNature(C0716R.string.explore_places_category_parks_and_nature, "Parks and Nature"),
    Sightseeing(C0716R.string.explore_places_category_sightseeing, "Sightseeing"),
    Wellness(C0716R.string.explore_places_category_wellness, "Wellness"),
    Shopping(C0716R.string.explore_places_category_shopping, "Shopping"),
    EntertainmentAndActivities(C0716R.string.explore_places_category_entertainment_and_activities, "Entertainment and Activities");
    
    public static final Creator<ExplorePlacesTopCategory> CREATOR = null;
    private final int stringRes;
    private final String topCategory;

    static {
        CREATOR = new Creator<ExplorePlacesTopCategory>() {
            public ExplorePlacesTopCategory createFromParcel(Parcel source) {
                return ExplorePlacesTopCategory.values()[source.readInt()];
            }

            public ExplorePlacesTopCategory[] newArray(int size) {
                return new ExplorePlacesTopCategory[size];
            }
        };
    }

    private ExplorePlacesTopCategory(int stringRes2, String s) {
        this.stringRes = stringRes2;
        this.topCategory = s;
    }

    public String getCategory() {
        return this.topCategory;
    }

    public int getStringRes() {
        return this.stringRes;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    @JsonCreator
    public static ExplorePlacesTopCategory from(String query) {
        ExplorePlacesTopCategory[] values;
        for (ExplorePlacesTopCategory category : values()) {
            if (category.topCategory.equals(query)) {
                return category;
            }
        }
        return null;
    }
}
