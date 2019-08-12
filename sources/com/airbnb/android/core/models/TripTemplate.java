package com.airbnb.android.core.models;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.generated.GenTripTemplate;
import com.airbnb.android.utils.CurrencyUtils;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.primitives.imaging.ImageSize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.util.Collection;

public class TripTemplate extends GenTripTemplate {
    public static final Creator<TripTemplate> CREATOR = new Creator<TripTemplate>() {
        public TripTemplate[] newArray(int size) {
            return new TripTemplate[size];
        }

        public TripTemplate createFromParcel(Parcel source) {
            TripTemplate object = new TripTemplate();
            object.readFromParcel(source);
            return object;
        }
    };
    private String formattedPrice;

    public enum Type {
        Unknown(Integer.MIN_VALUE),
        Immersion(0),
        Experience(1);
        
        private final int typeId;

        private Type(int typeId2) {
            this.typeId = typeId2;
        }

        public static Type fromId(int typeId2) {
            return (Type) FluentIterable.m1283of(values()).firstMatch(TripTemplate$Type$$Lambda$1.lambdaFactory$(typeId2)).mo41059or(Unknown);
        }
    }

    @JsonProperty("product_type")
    public void setProductType(int value) {
        this.mProductType = Type.fromId(value);
    }

    public boolean isImmersion() {
        return this.mProductType == Type.Immersion;
    }

    public boolean isExperience() {
        return this.mProductType == Type.Experience;
    }

    public String getFormattedPrice() {
        if (this.formattedPrice == null) {
            this.formattedPrice = CurrencyUtils.formatCurrencyAmount((double) getBasePrice(), getCurrency().getCurrency());
        }
        return this.formattedPrice;
    }

    public CharSequence getNumReviewsText(Context context) {
        Resources r = context.getResources();
        int reviewCount = getReviewCount();
        return r.getQuantityString(C0716R.plurals.reviews, reviewCount, new Object[]{Integer.toString(reviewCount)});
    }

    public String getPosterUrl() {
        Photo photo = getPhoto();
        if (photo == null) {
            return null;
        }
        return photo.getUrlForSize(ImageSize.PortraitLarge);
    }

    public Photo getPhoto() {
        if (ListUtils.isEmpty((Collection<?>) this.mPosterPictures)) {
            return null;
        }
        return (Photo) this.mPosterPictures.get(0);
    }

    public void trimPhotos() {
        if (this.mPosterPictures != null) {
            for (Photo picture : this.mPosterPictures) {
                picture.retainSize(ImageSize.PortraitLarge);
            }
        }
    }

    public void trimForPosterCard() {
        trimPhotos();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TripTemplate)) {
            return false;
        }
        if (this.mId != ((TripTemplate) o).mId) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.mId ^ (this.mId >>> 32));
    }
}
