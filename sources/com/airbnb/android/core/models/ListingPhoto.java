package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingPhoto;

public class ListingPhoto extends GenListingPhoto {
    public static final Creator<ListingPhoto> CREATOR = new Creator<ListingPhoto>() {
        public ListingPhoto[] newArray(int size) {
            return new ListingPhoto[size];
        }

        public ListingPhoto createFromParcel(Parcel source) {
            ListingPhoto object = new ListingPhoto();
            object.readFromParcel(source);
            return object;
        }
    };

    public Photo toPhoto() {
        Photo photo = new Photo();
        photo.setId(Long.parseLong(getId().split("_")[1]));
        photo.setCaption(getCaption());
        photo.setSortOrder(getSortOrder());
        photo.setThumbnailUrl(getThumbnailUrl());
        photo.setSmallUrl(getSmallUrl());
        photo.setXMediumUrl(getExtraMediumUrl());
        photo.setLargeUrl(getLargeUrl());
        photo.setXLargeUrl(getExtraLargeUrl());
        return photo;
    }
}
