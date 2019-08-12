package com.airbnb.android.core.models;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.p000v4.graphics.ColorUtils;
import android.text.TextUtils;
import android.widget.Toast;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.models.generated.GenPhoto;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.p027n2.primitives.imaging.Image;
import com.airbnb.p027n2.primitives.imaging.ImageSize;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Photo extends GenPhoto implements Image, Comparable<Photo> {
    private static final String AKI_POLICY_PARAM = "aki_policy=";
    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        public Photo[] newArray(int size) {
            return new Photo[size];
        }

        public Photo createFromParcel(Parcel source) {
            Photo object = new Photo();
            object.readFromParcel(source);
            return object;
        }
    };
    public static Comparator<Photo> ORDER_COMPARATOR = Photo$$Lambda$5.lambdaFactory$();
    private String baseUrl;

    public Photo() {
    }

    public Photo(long id) {
        this.mId = id;
    }

    @JsonProperty("scrim_color")
    public void setScrimColor(String colorString) {
        if (TextUtils.isEmpty(colorString)) {
            this.mScrimColor = 0;
            return;
        }
        try {
            this.mScrimColor = Color.parseColor(colorString);
        } catch (IllegalArgumentException e) {
            BugsnagWrapper.throwOrNotify((RuntimeException) e);
            this.mScrimColor = 0;
        }
    }

    @JsonProperty("dominant_saturated_color")
    public void setDominantSaturatedColor(String colorString) {
        if (TextUtils.isEmpty(colorString)) {
            this.mDominantSaturatedColor = 0;
            return;
        }
        try {
            this.mDominantSaturatedColor = Color.parseColor(colorString);
        } catch (IllegalArgumentException e) {
            BugsnagWrapper.throwOrNotify((RuntimeException) e);
            this.mDominantSaturatedColor = 0;
        }
    }

    public void setScrimColor(int color) {
        this.mScrimColor = color;
    }

    public int getScrimColorWithAlpha(int alpha) {
        return ColorUtils.setAlphaComponent(this.mScrimColor, alpha);
    }

    public int getScrimColor() {
        return super.getScrimColor();
    }

    @JsonProperty("xl_picture")
    @Deprecated
    public void setXlPictureUrl(String url) {
        this.mXLargeUrl = url;
    }

    @JsonProperty("picture")
    @Deprecated
    public void setPictureUrl(String url) {
        this.mLargeUrl = url;
    }

    @JsonProperty("thumbnail")
    @Deprecated
    public void setThumbnailUrl(String url) {
        this.mSmallUrl = url;
    }

    public String getLargeUrl() {
        if ((shouldForceLowBandwidth() || TextUtils.isEmpty(super.getLargeUrl())) && !TextUtils.isEmpty(super.getSmallUrl())) {
            return super.getSmallUrl();
        }
        return super.getLargeUrl();
    }

    public boolean isCoverPhoto() {
        return getSortOrder() == 1;
    }

    public String getXLargeUrl() {
        if (!shouldForceLowBandwidth() || TextUtils.isEmpty(super.getLargeUrl())) {
            return super.getXLargeUrl();
        }
        return super.getLargeUrl();
    }

    private boolean shouldForceLowBandwidth() {
        return CoreApplication.instance().component().lowBandwidthUtils().shouldForceLowBandwidth();
    }

    public int compareTo(Photo another) {
        if (this.mSortOrder == another.mSortOrder) {
            return 0;
        }
        return this.mSortOrder > another.mSortOrder ? 1 : -1;
    }

    public int hashCode() {
        return ((int) (this.mId ^ (this.mId >>> 32))) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.mId != ((Photo) obj).mId) {
            return false;
        }
        return true;
    }

    public String getUrlForSize(ImageSize size) {
        return getUrlForSizeSafe(size);
    }

    private String getUrlForSizeSafe(ImageSize size) {
        String url = getUrlForSizeUnsafe(size);
        if (url != null) {
            return url;
        }
        String url2 = buildUrlForSize(size);
        if (url2 == null) {
            url2 = getBackupUrlForSize(size);
        }
        setUrlForSize(size, url2);
        return url2;
    }

    private String buildUrlForSize(ImageSize size) {
        String baseUrl2 = getBaseUrl();
        if (baseUrl2 != null) {
            return baseUrl2 + size.akamaiKey;
        }
        BugsnagWrapper.notify((Throwable) new IllegalStateException("Could not get base url off photo"));
        if (BuildHelper.isDevelopmentBuild()) {
            Toast.makeText(CoreApplication.appContext(), "Could not get base url off photo", 1).show();
            C0715L.m1189d("Image failure", "Could not get base url off photo: " + toString());
        }
        return null;
    }

    private String getBaseUrl() {
        if (this.baseUrl == null) {
            Iterator it = ImageSize.ALL_SIZES.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String url = getUrlForSizeUnsafe((ImageSize) it.next());
                if (url != null) {
                    int policyPosition = url.indexOf(AKI_POLICY_PARAM);
                    if (policyPosition != -1) {
                        this.baseUrl = url.substring(0, policyPosition + AKI_POLICY_PARAM.length());
                        break;
                    }
                }
            }
        }
        return this.baseUrl;
    }

    private String getBackupUrlForSize(ImageSize targetSize) {
        List<ImageSize> sizesToUse;
        FluentIterable<ImageSize> availableSizes = FluentIterable.from((Iterable<E>) ImageSize.ALL_SIZES).filter(Photo$$Lambda$1.lambdaFactory$(this));
        Comparator<ImageSize> sizeComparator = Photo$$Lambda$2.lambdaFactory$(targetSize);
        List<ImageSize> sortedList = availableSizes.filter(Photo$$Lambda$3.lambdaFactory$()).toSortedList(sizeComparator);
        List<ImageSize> sortedList2 = availableSizes.filter(Photo$$Lambda$4.lambdaFactory$()).toSortedList(sizeComparator);
        if (!targetSize.portrait || sortedList.isEmpty()) {
            sizesToUse = sortedList2;
        } else {
            sizesToUse = sortedList;
        }
        if (!sizesToUse.isEmpty()) {
            return getUrlForSizeUnsafe((ImageSize) sizesToUse.get(sizesToUse.size() - 1));
        }
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Photo didn't have ANY pictures!?! " + toString()));
        return null;
    }

    static /* synthetic */ boolean lambda$getBackupUrlForSize$1(Photo photo, ImageSize s) {
        return photo.getUrlForSizeUnsafe(s) != null;
    }

    static /* synthetic */ int lambda$getBackupUrlForSize$2(ImageSize targetSize, ImageSize size1, ImageSize size2) {
        return Math.abs(targetSize.heightPx - size2.heightPx) - Math.abs(targetSize.heightPx - size1.heightPx);
    }

    static /* synthetic */ boolean lambda$getBackupUrlForSize$4(ImageSize s) {
        return !s.portrait;
    }

    private void setUrlForSize(ImageSize size, String url) {
        switch (size) {
            case LandscapeXSmall:
                this.mXSmallUrl = url;
                return;
            case LandscapeSmall:
                this.mSmallUrl = url;
                return;
            case LandscapeMedium:
                this.mMediumUrl = url;
                return;
            case LandscapeXMedium:
                this.mXMediumUrl = url;
                return;
            case LandscapeLarge:
                this.mLargeUrl = url;
                return;
            case LandscapeXLarge:
                this.mXLargeUrl = url;
                return;
            case LandscapeXXLarge:
                this.mXxLargeUrl = url;
                return;
            case PortraitLarge:
                this.mPosterUrl = url;
                return;
            case PortraitXLarge:
                this.mXlPosterUrl = url;
                return;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Unknown size: " + size));
                return;
        }
    }

    private String getUrlForSizeUnsafe(ImageSize size) {
        switch (size) {
            case LandscapeXSmall:
                return this.mXSmallUrl;
            case LandscapeSmall:
                return this.mSmallUrl;
            case LandscapeMedium:
                return this.mMediumUrl;
            case LandscapeXMedium:
                return this.mXMediumUrl;
            case LandscapeLarge:
                return this.mLargeUrl;
            case LandscapeXLarge:
                return this.mXLargeUrl;
            case LandscapeXXLarge:
                return this.mXxLargeUrl;
            case PortraitLarge:
                return this.mPosterUrl;
            case PortraitXLarge:
                return this.mXlPosterUrl;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Unknown size: " + size));
                return null;
        }
    }

    public String getBase64Preview() {
        return this.mPreviewEncodedPng;
    }

    public void retainSize(ImageSize sizeToKeep) {
        if (getUrlForSizeUnsafe(sizeToKeep) == null) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Size to retain does not exist: " + sizeToKeep));
            return;
        }
        for (ImageSize size : ImageSize.ALL_SIZES) {
            if (size != sizeToKeep) {
                setUrlForSize(size, null);
            }
        }
    }

    public String toString() {
        return "Photo{mXlPosterUrl='" + this.mXlPosterUrl + '\'' + ", mPosterUrl='" + this.mPosterUrl + '\'' + ", mXSmallUrl='" + this.mXSmallUrl + '\'' + ", mSmallUrl='" + this.mSmallUrl + '\'' + ", mMediumUrl='" + this.mMediumUrl + '\'' + ", mXMediumUrl='" + this.mXMediumUrl + '\'' + ", mLargeUrl='" + this.mLargeUrl + '\'' + ", mXLargeUrl='" + this.mXLargeUrl + '\'' + ", mXxLargeUrl='" + this.mXxLargeUrl + '\'' + ", baseUrl='" + this.baseUrl + '\'' + '}';
    }
}
