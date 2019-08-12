package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenWishList implements Parcelable {
    @JsonProperty("article_ids")
    protected List<Long> mArticleIds;
    @JsonProperty("articles_count")
    protected int mArticlesCount;
    @JsonProperty("available_listings_count")
    protected int mAvailableListingsCount;
    @JsonProperty("checkin")
    protected AirDate mCheckin;
    @JsonProperty("checkout")
    protected AirDate mCheckout;
    @JsonProperty("guest_details")
    protected GuestDetails mGuestDetails;
    @JsonProperty("guests")
    protected int mGuests;
    @JsonProperty("pets")
    protected boolean mHasPets;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("image_url")
    protected String mImageUrl;
    @JsonProperty("invite_url")
    protected String mInviteUrl;
    @JsonProperty("listing_ids")
    protected List<Long> mListingIds;
    @JsonProperty("listings_count")
    protected int mListingsCount;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("picture_urls")
    protected List<String> mPictureUrls;
    @JsonProperty("pictures")
    protected List<Photo> mPictures;
    @JsonProperty("place_activities_count")
    protected int mPlaceActivitiesCount;
    @JsonProperty("place_activity_ids")
    protected List<Long> mPlaceActivityIds;
    @JsonProperty("place_ids")
    protected List<Long> mPlaceIds;
    @JsonProperty("places_count")
    protected int mPlacesCount;
    @JsonProperty("private")
    protected boolean mPrivateWishList;
    @JsonProperty("mt_template_ids")
    protected List<Long> mTripIds;
    @JsonProperty("mt_templates_count")
    protected int mTripsCount;
    @JsonProperty("user")
    protected User mUser;
    @JsonProperty("user_id")
    protected long mUserId;
    @JsonProperty("xl_image_url")
    protected String mXlImageUrl;

    protected GenWishList(AirDate checkin, AirDate checkout, GuestDetails guestDetails, List<Long> listingIds, List<Long> tripIds, List<Long> placeIds, List<Long> placeActivityIds, List<Long> articleIds, List<Photo> pictures, List<String> pictureUrls, String imageUrl, String inviteUrl, String xlImageUrl, String name, User user, boolean privateWishList, boolean hasPets, int listingsCount, int guests, int availableListingsCount, int placesCount, int tripsCount, int placeActivitiesCount, int articlesCount, long id, long userId) {
        this();
        this.mCheckin = checkin;
        this.mCheckout = checkout;
        this.mGuestDetails = guestDetails;
        this.mListingIds = listingIds;
        this.mTripIds = tripIds;
        this.mPlaceIds = placeIds;
        this.mPlaceActivityIds = placeActivityIds;
        this.mArticleIds = articleIds;
        this.mPictures = pictures;
        this.mPictureUrls = pictureUrls;
        this.mImageUrl = imageUrl;
        this.mInviteUrl = inviteUrl;
        this.mXlImageUrl = xlImageUrl;
        this.mName = name;
        this.mUser = user;
        this.mPrivateWishList = privateWishList;
        this.mHasPets = hasPets;
        this.mListingsCount = listingsCount;
        this.mGuests = guests;
        this.mAvailableListingsCount = availableListingsCount;
        this.mPlacesCount = placesCount;
        this.mTripsCount = tripsCount;
        this.mPlaceActivitiesCount = placeActivitiesCount;
        this.mArticlesCount = articlesCount;
        this.mId = id;
        this.mUserId = userId;
    }

    protected GenWishList() {
    }

    public AirDate getCheckin() {
        return this.mCheckin;
    }

    @JsonProperty("checkin")
    public void setCheckin(AirDate value) {
        this.mCheckin = value;
    }

    public AirDate getCheckout() {
        return this.mCheckout;
    }

    @JsonProperty("checkout")
    public void setCheckout(AirDate value) {
        this.mCheckout = value;
    }

    public GuestDetails getGuestDetails() {
        return this.mGuestDetails;
    }

    @JsonProperty("guest_details")
    public void setGuestDetails(GuestDetails value) {
        this.mGuestDetails = value;
    }

    public List<Long> getListingIds() {
        return this.mListingIds;
    }

    @JsonProperty("listing_ids")
    public void setListingIds(List<Long> value) {
        this.mListingIds = value;
    }

    public List<Long> getTripIds() {
        return this.mTripIds;
    }

    @JsonProperty("mt_template_ids")
    public void setTripIds(List<Long> value) {
        this.mTripIds = value;
    }

    public List<Long> getPlaceIds() {
        return this.mPlaceIds;
    }

    @JsonProperty("place_ids")
    public void setPlaceIds(List<Long> value) {
        this.mPlaceIds = value;
    }

    public List<Long> getPlaceActivityIds() {
        return this.mPlaceActivityIds;
    }

    @JsonProperty("place_activity_ids")
    public void setPlaceActivityIds(List<Long> value) {
        this.mPlaceActivityIds = value;
    }

    public List<Long> getArticleIds() {
        return this.mArticleIds;
    }

    @JsonProperty("article_ids")
    public void setArticleIds(List<Long> value) {
        this.mArticleIds = value;
    }

    public List<Photo> getPictures() {
        return this.mPictures;
    }

    @JsonProperty("pictures")
    public void setPictures(List<Photo> value) {
        this.mPictures = value;
    }

    public List<String> getPictureUrls() {
        return this.mPictureUrls;
    }

    @JsonProperty("picture_urls")
    public void setPictureUrls(List<String> value) {
        this.mPictureUrls = value;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String value) {
        this.mImageUrl = value;
    }

    public String getInviteUrl() {
        return this.mInviteUrl;
    }

    @JsonProperty("invite_url")
    public void setInviteUrl(String value) {
        this.mInviteUrl = value;
    }

    public String getXlImageUrl() {
        return this.mXlImageUrl;
    }

    @JsonProperty("xl_image_url")
    public void setXlImageUrl(String value) {
        this.mXlImageUrl = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public User getUser() {
        return this.mUser;
    }

    @JsonProperty("user")
    public void setUser(User value) {
        this.mUser = value;
    }

    public boolean isPrivateWishList() {
        return this.mPrivateWishList;
    }

    @JsonProperty("private")
    public void setPrivateWishList(boolean value) {
        this.mPrivateWishList = value;
    }

    public boolean hasPets() {
        return this.mHasPets;
    }

    @JsonProperty("pets")
    public void setHasPets(boolean value) {
        this.mHasPets = value;
    }

    public int getListingsCount() {
        return this.mListingsCount;
    }

    @JsonProperty("listings_count")
    public void setListingsCount(int value) {
        this.mListingsCount = value;
    }

    public int getGuests() {
        return this.mGuests;
    }

    @JsonProperty("guests")
    public void setGuests(int value) {
        this.mGuests = value;
    }

    public int getAvailableListingsCount() {
        return this.mAvailableListingsCount;
    }

    @JsonProperty("available_listings_count")
    public void setAvailableListingsCount(int value) {
        this.mAvailableListingsCount = value;
    }

    public int getPlacesCount() {
        return this.mPlacesCount;
    }

    @JsonProperty("places_count")
    public void setPlacesCount(int value) {
        this.mPlacesCount = value;
    }

    public int getTripsCount() {
        return this.mTripsCount;
    }

    @JsonProperty("mt_templates_count")
    public void setTripsCount(int value) {
        this.mTripsCount = value;
    }

    public int getPlaceActivitiesCount() {
        return this.mPlaceActivitiesCount;
    }

    @JsonProperty("place_activities_count")
    public void setPlaceActivitiesCount(int value) {
        this.mPlaceActivitiesCount = value;
    }

    public int getArticlesCount() {
        return this.mArticlesCount;
    }

    @JsonProperty("articles_count")
    public void setArticlesCount(int value) {
        this.mArticlesCount = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getUserId() {
        return this.mUserId;
    }

    @JsonProperty("user_id")
    public void setUserId(long value) {
        this.mUserId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mCheckin, 0);
        parcel.writeParcelable(this.mCheckout, 0);
        parcel.writeParcelable(this.mGuestDetails, 0);
        parcel.writeValue(this.mListingIds);
        parcel.writeValue(this.mTripIds);
        parcel.writeValue(this.mPlaceIds);
        parcel.writeValue(this.mPlaceActivityIds);
        parcel.writeValue(this.mArticleIds);
        parcel.writeTypedList(this.mPictures);
        parcel.writeStringList(this.mPictureUrls);
        parcel.writeString(this.mImageUrl);
        parcel.writeString(this.mInviteUrl);
        parcel.writeString(this.mXlImageUrl);
        parcel.writeString(this.mName);
        parcel.writeParcelable(this.mUser, 0);
        parcel.writeBooleanArray(new boolean[]{this.mPrivateWishList, this.mHasPets});
        parcel.writeInt(this.mListingsCount);
        parcel.writeInt(this.mGuests);
        parcel.writeInt(this.mAvailableListingsCount);
        parcel.writeInt(this.mPlacesCount);
        parcel.writeInt(this.mTripsCount);
        parcel.writeInt(this.mPlaceActivitiesCount);
        parcel.writeInt(this.mArticlesCount);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mCheckin = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCheckout = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mGuestDetails = (GuestDetails) source.readParcelable(GuestDetails.class.getClassLoader());
        this.mListingIds = (List) source.readValue(null);
        this.mTripIds = (List) source.readValue(null);
        this.mPlaceIds = (List) source.readValue(null);
        this.mPlaceActivityIds = (List) source.readValue(null);
        this.mArticleIds = (List) source.readValue(null);
        this.mPictures = source.createTypedArrayList(Photo.CREATOR);
        this.mPictureUrls = source.createStringArrayList();
        this.mImageUrl = source.readString();
        this.mInviteUrl = source.readString();
        this.mXlImageUrl = source.readString();
        this.mName = source.readString();
        this.mUser = (User) source.readParcelable(User.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mPrivateWishList = bools[0];
        this.mHasPets = bools[1];
        this.mListingsCount = source.readInt();
        this.mGuests = source.readInt();
        this.mAvailableListingsCount = source.readInt();
        this.mPlacesCount = source.readInt();
        this.mTripsCount = source.readInt();
        this.mPlaceActivitiesCount = source.readInt();
        this.mArticlesCount = source.readInt();
        this.mId = source.readLong();
        this.mUserId = source.readLong();
    }
}
