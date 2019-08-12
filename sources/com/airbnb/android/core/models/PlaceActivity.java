package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPlaceActivity;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import java.util.Collection;
import java.util.List;

public class PlaceActivity extends GenPlaceActivity {
    public static final Creator<PlaceActivity> CREATOR = new Creator<PlaceActivity>() {
        public PlaceActivity[] newArray(int size) {
            return new PlaceActivity[size];
        }

        public PlaceActivity createFromParcel(Parcel source) {
            PlaceActivity object = new PlaceActivity();
            object.readFromParcel(source);
            return object;
        }
    };

    public List<String> getCoverPhotoUrls() {
        return FluentIterable.from((Iterable<E>) getCoverPhotos()).transform(PlaceActivity$$Lambda$1.lambdaFactory$()).toList();
    }

    public String getCoverPhoto() {
        if (ListUtils.isEmpty((Collection<?>) this.mCoverPhotos)) {
            return null;
        }
        return ((Photo) this.mCoverPhotos.get(0)).getLargeUrl();
    }

    public Photo getFirstCoverPhoto() {
        if (ListUtils.isEmpty((Collection<?>) getCoverPhotos())) {
            return null;
        }
        return (Photo) getCoverPhotos().get(0);
    }
}
