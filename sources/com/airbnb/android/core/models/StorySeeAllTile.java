package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenStorySeeAllTile;

public class StorySeeAllTile extends GenStorySeeAllTile {
    public static final Creator<StorySeeAllTile> CREATOR = new Creator<StorySeeAllTile>() {
        public StorySeeAllTile[] newArray(int size) {
            return new StorySeeAllTile[size];
        }

        public StorySeeAllTile createFromParcel(Parcel source) {
            StorySeeAllTile object = new StorySeeAllTile();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return getSearchTerm().equals(((StorySeeAllTile) o).getSearchTerm());
    }

    public int hashCode() {
        return getSearchTerm().hashCode();
    }
}
