package com.airbnb.android.core.beta.models.guidebook;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.beta.models.guidebook.generated.GenGuidebookPlace;
import com.airbnb.android.core.models.Mappable;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.primitives.imaging.ImageSize;
import java.util.Collection;

public class GuidebookPlace extends GenGuidebookPlace implements Mappable {
    public static final Creator<GuidebookPlace> CREATOR = new Creator<GuidebookPlace>() {
        public GuidebookPlace[] newArray(int size) {
            return new GuidebookPlace[size];
        }

        public GuidebookPlace createFromParcel(Parcel source) {
            GuidebookPlace object = new GuidebookPlace();
            object.readFromParcel(source);
            return object;
        }
    };

    public long getMappableId() {
        return this.mPrimaryPlace.getId();
    }

    public double getLatitude() {
        return getPrimaryPlace().getLat();
    }

    public double getLongitude() {
        return getPrimaryPlace().getLng();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GuidebookPlace)) {
            return false;
        }
        return this.mPrimaryPlace.equals(((GuidebookPlace) o).mPrimaryPlace);
    }

    public String getCoverPhoto() {
        if (ListUtils.isEmpty((Collection<?>) this.mCoverPhotos)) {
            return null;
        }
        return ((Photo) this.mCoverPhotos.get(0)).getLargeUrl();
    }

    public void trimForPlaceCard() {
        if (!ListUtils.isEmpty((Collection<?>) this.mCoverPhotos)) {
            this.mCoverPhotos.subList(1, this.mCoverPhotos.size()).clear();
            ((Photo) this.mCoverPhotos.get(0)).retainSize(ImageSize.LandscapeLarge);
        }
    }

    public int hashCode() {
        return this.mPrimaryPlace.hashCode();
    }
}
