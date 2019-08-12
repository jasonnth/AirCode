package com.airbnb.android.core.p008mt.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.fasterxml.jackson.annotation.JsonCreator;

/* renamed from: com.airbnb.android.core.mt.models.PrimaryCategory */
public enum PrimaryCategory implements Parcelable {
    ARTS(1, C0716R.string.experience_category_arts),
    FASHION(2, C0716R.string.experience_category_fashion),
    ENTERTAINMENT(3, C0716R.string.experience_category_entertainment),
    SPORTS(4, C0716R.string.experience_category_sports),
    WELLNESS(5, C0716R.string.experience_category_wellness),
    NATURE(6, C0716R.string.experience_category_nature),
    FOOD_AND_DRINK(7, C0716R.string.experience_category_food_and_drink),
    TECHNOLOGY(8, C0716R.string.experience_category_technology),
    LIFESTYLE(9, C0716R.string.experience_category_lifestyle),
    HISTORY(10, C0716R.string.experience_category_history),
    MUSIC(11, C0716R.string.experience_category_music),
    BUSINESS(12, C0716R.string.experience_category_business),
    NIGHTLIFE(13, C0716R.string.experience_category_nightlife);
    
    public static final Creator<PrimaryCategory> CREATOR = null;

    /* renamed from: id */
    public final int f1083id;
    public final int titleRes;

    static {
        CREATOR = new Creator<PrimaryCategory>() {
            public PrimaryCategory createFromParcel(Parcel source) {
                return PrimaryCategory.values()[source.readInt()];
            }

            public PrimaryCategory[] newArray(int size) {
                return new PrimaryCategory[size];
            }
        };
    }

    private PrimaryCategory(int id, int titleRes2) {
        this.f1083id = id;
        this.titleRes = titleRes2;
    }

    @JsonCreator
    public static PrimaryCategory from(int id) {
        PrimaryCategory[] values;
        for (PrimaryCategory category : values()) {
            if (category.f1083id == id) {
                return category;
            }
        }
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
