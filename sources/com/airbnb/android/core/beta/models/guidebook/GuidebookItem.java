package com.airbnb.android.core.beta.models.guidebook;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.beta.models.guidebook.generated.GenGuidebookItem;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.primitives.imaging.ImageSize;
import java.util.Collection;

public class GuidebookItem extends GenGuidebookItem {
    public static final Creator<GuidebookItem> CREATOR = new Creator<GuidebookItem>() {
        public GuidebookItem[] newArray(int size) {
            return new GuidebookItem[size];
        }

        public GuidebookItem createFromParcel(Parcel source) {
            GuidebookItem object = new GuidebookItem();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final int HERO_PHOTO_INDEX = 1;
    private Photo cardPhoto;
    private String heroPhotoUrl;

    public String getHeroPhotoUrl() {
        if (this.heroPhotoUrl == null) {
            processAndTrimPhotos();
        }
        return this.heroPhotoUrl;
    }

    public Photo getCardPhoto() {
        if (this.cardPhoto == null) {
            processAndTrimPhotos();
        }
        return this.cardPhoto;
    }

    public void processAndTrimPhotos() {
        if (this.cardPhoto == null && !ListUtils.isEmpty((Collection<?>) this.mCoverPhotos)) {
            if (this.mCoverPhotos.size() > 1) {
                this.heroPhotoUrl = ((Photo) this.mCoverPhotos.get(1)).getXLargeUrl();
            }
            if (TextUtils.isEmpty(getLocalizedNameForExploreCard()) || this.mCoverPhotos.size() == 1) {
                this.cardPhoto = (Photo) this.mCoverPhotos.get(0);
            } else if (this.mCoverPhotos.size() == 2) {
                this.cardPhoto = (Photo) this.mCoverPhotos.get(1);
            } else {
                this.cardPhoto = (Photo) this.mCoverPhotos.get(2);
            }
            this.cardPhoto.retainSize(ImageSize.LandscapeLarge);
            this.mCoverPhotos = null;
        }
    }
}
