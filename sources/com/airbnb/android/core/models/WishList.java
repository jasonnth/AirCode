package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenWishList;
import java.util.List;

public class WishList extends GenWishList {
    public static final Creator<WishList> CREATOR = new Creator<WishList>() {
        public WishList[] newArray(int size) {
            return new WishList[size];
        }

        public WishList createFromParcel(Parcel source) {
            WishList object = new WishList();
            object.readFromParcel(source);
            return object;
        }
    };
    private final long createdAt = System.currentTimeMillis();

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.mId != ((WishList) o).mId) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.mId ^ (this.mId >>> 32));
    }

    public GuestDetails getGuestDetails() {
        GuestDetails details = super.getGuestDetails();
        if (details == null) {
            details = new GuestDetails();
        }
        return details.isBringingPets(hasPets());
    }

    public void setGuestFilters(GuestDetails guestFilters) {
        setGuests(guestFilters.totalGuestCount());
        setGuestDetails(guestFilters);
        setHasPets(guestFilters.isBringingPets());
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public String getImageUrl() {
        if (this.mPictureUrls == null || this.mPictureUrls.isEmpty()) {
            return super.getImageUrl();
        }
        return (String) this.mPictureUrls.get(0);
    }

    public String toString() {
        return "WishList{, mName='" + this.mName + '\'' + ", mListingsCount=" + this.mListingsCount + ", mId=" + this.mId + '}';
    }

    public void addListingIfNotExists(long listingId) {
        this.mListingsCount += addToListIfNotExists(this.mListingIds, listingId);
    }

    public boolean removeListingIfExists(long listingId) {
        if (!this.mListingIds.remove(Long.valueOf(listingId))) {
            return false;
        }
        this.mListingsCount--;
        return true;
    }

    public void addPlaceIfNotExists(long placeId) {
        this.mPlacesCount += addToListIfNotExists(this.mPlaceIds, placeId);
    }

    public boolean removePlaceIfExists(long placeId) {
        if (!this.mPlaceIds.remove(Long.valueOf(placeId))) {
            return false;
        }
        this.mPlacesCount--;
        return true;
    }

    public void addPlaceActivityIfNotExists(long activityId) {
        this.mPlaceActivitiesCount += addToListIfNotExists(this.mPlaceActivityIds, activityId);
    }

    public boolean removePlaceActivityIfExists(long activityId) {
        if (!this.mPlaceActivityIds.remove(Long.valueOf(activityId))) {
            return false;
        }
        this.mPlaceActivitiesCount--;
        return true;
    }

    public void addStoryArticleIfNotExists(long articleId) {
        this.mArticlesCount += addToListIfNotExists(this.mArticleIds, articleId);
    }

    public boolean removeStoryArticleIfExists(long articleId) {
        if (!this.mArticleIds.remove(Long.valueOf(articleId))) {
            return false;
        }
        this.mArticlesCount--;
        return true;
    }

    public void addTripIfNotExists(long tripId) {
        this.mTripsCount += addToListIfNotExists(this.mTripIds, tripId);
    }

    public boolean removeTripIfExists(long tripId) {
        if (!this.mTripIds.remove(Long.valueOf(tripId))) {
            return false;
        }
        this.mTripsCount--;
        return true;
    }

    private static int addToListIfNotExists(List<Long> list, long id) {
        if (list.contains(Long.valueOf(id))) {
            return 0;
        }
        list.add(Long.valueOf(id));
        return 1;
    }

    public boolean hasDates() {
        return (this.mCheckin == null || this.mCheckout == null) ? false : true;
    }

    public boolean hasListings() {
        return this.mListingsCount > 0;
    }

    public boolean hasTrips() {
        return this.mTripsCount > 0;
    }

    public boolean hasPlaces() {
        return this.mPlacesCount > 0;
    }

    public boolean hasStoryArticles() {
        return this.mArticlesCount > 0;
    }

    public boolean hasPlaceActivities() {
        return this.mPlaceActivitiesCount > 0;
    }
}
