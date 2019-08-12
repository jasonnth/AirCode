package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.beta.models.guidebook.Place;
import com.airbnb.android.core.models.Market;
import com.airbnb.android.core.models.Meetup;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.models.PlaceActivityAttribute;
import com.airbnb.android.core.models.RecommendationSection;
import com.airbnb.android.core.models.Restaurant;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenPlaceActivity implements Parcelable {
    @JsonProperty("action_kicker")
    protected String mActionKicker;
    @JsonProperty("action_row_subtitle")
    protected String mActionRowSubtitle;
    @JsonProperty("action_row_title")
    protected String mActionRowTitle;
    @JsonProperty("activity_attributes")
    protected List<PlaceActivityAttribute> mActivityAttributes;
    @JsonProperty("bold_subtitle")
    protected String mBoldSubtitle;
    @JsonProperty("cover_photos")
    protected List<Photo> mCoverPhotos;
    @JsonProperty("description")
    protected String mDescription;
    @JsonProperty("id")
    protected int mId;
    @JsonProperty("insider_tagline")
    protected String mInsiderTagline;
    @JsonProperty("market")
    protected Market mMarket;
    @JsonProperty("meetup")
    protected Meetup mMeetup;
    @JsonProperty("place")
    protected Place mPlace;
    @JsonProperty("recommendation_items")
    protected RecommendationSection mRecommendationSection;
    @JsonProperty("restaurant")
    protected Restaurant mRestaurant;
    @JsonProperty("share_url")
    protected String mShareUrl;
    @JsonProperty("subtitle")
    protected String mSubtitle;
    @JsonProperty("title")
    protected String mTitle;
    @JsonProperty("user")
    protected User mUser;

    protected GenPlaceActivity(List<Photo> coverPhotos, List<PlaceActivityAttribute> activityAttributes, Market market, Meetup meetup, Place place, RecommendationSection recommendationSection, Restaurant restaurant, String title, String subtitle, String boldSubtitle, String description, String insiderTagline, String actionKicker, String actionRowTitle, String actionRowSubtitle, String shareUrl, User user, int id) {
        this();
        this.mCoverPhotos = coverPhotos;
        this.mActivityAttributes = activityAttributes;
        this.mMarket = market;
        this.mMeetup = meetup;
        this.mPlace = place;
        this.mRecommendationSection = recommendationSection;
        this.mRestaurant = restaurant;
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mBoldSubtitle = boldSubtitle;
        this.mDescription = description;
        this.mInsiderTagline = insiderTagline;
        this.mActionKicker = actionKicker;
        this.mActionRowTitle = actionRowTitle;
        this.mActionRowSubtitle = actionRowSubtitle;
        this.mShareUrl = shareUrl;
        this.mUser = user;
        this.mId = id;
    }

    protected GenPlaceActivity() {
    }

    public List<Photo> getCoverPhotos() {
        return this.mCoverPhotos;
    }

    @JsonProperty("cover_photos")
    public void setCoverPhotos(List<Photo> value) {
        this.mCoverPhotos = value;
    }

    public List<PlaceActivityAttribute> getActivityAttributes() {
        return this.mActivityAttributes;
    }

    @JsonProperty("activity_attributes")
    public void setActivityAttributes(List<PlaceActivityAttribute> value) {
        this.mActivityAttributes = value;
    }

    public Market getMarket() {
        return this.mMarket;
    }

    @JsonProperty("market")
    public void setMarket(Market value) {
        this.mMarket = value;
    }

    public Meetup getMeetup() {
        return this.mMeetup;
    }

    @JsonProperty("meetup")
    public void setMeetup(Meetup value) {
        this.mMeetup = value;
    }

    public Place getPlace() {
        return this.mPlace;
    }

    @JsonProperty("place")
    public void setPlace(Place value) {
        this.mPlace = value;
    }

    public RecommendationSection getRecommendationSection() {
        return this.mRecommendationSection;
    }

    @JsonProperty("recommendation_items")
    public void setRecommendationSection(RecommendationSection value) {
        this.mRecommendationSection = value;
    }

    public Restaurant getRestaurant() {
        return this.mRestaurant;
    }

    @JsonProperty("restaurant")
    public void setRestaurant(Restaurant value) {
        this.mRestaurant = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    @JsonProperty("subtitle")
    public void setSubtitle(String value) {
        this.mSubtitle = value;
    }

    public String getBoldSubtitle() {
        return this.mBoldSubtitle;
    }

    @JsonProperty("bold_subtitle")
    public void setBoldSubtitle(String value) {
        this.mBoldSubtitle = value;
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public String getInsiderTagline() {
        return this.mInsiderTagline;
    }

    @JsonProperty("insider_tagline")
    public void setInsiderTagline(String value) {
        this.mInsiderTagline = value;
    }

    public String getActionKicker() {
        return this.mActionKicker;
    }

    @JsonProperty("action_kicker")
    public void setActionKicker(String value) {
        this.mActionKicker = value;
    }

    public String getActionRowTitle() {
        return this.mActionRowTitle;
    }

    @JsonProperty("action_row_title")
    public void setActionRowTitle(String value) {
        this.mActionRowTitle = value;
    }

    public String getActionRowSubtitle() {
        return this.mActionRowSubtitle;
    }

    @JsonProperty("action_row_subtitle")
    public void setActionRowSubtitle(String value) {
        this.mActionRowSubtitle = value;
    }

    public String getShareUrl() {
        return this.mShareUrl;
    }

    @JsonProperty("share_url")
    public void setShareUrl(String value) {
        this.mShareUrl = value;
    }

    public User getUser() {
        return this.mUser;
    }

    @JsonProperty("user")
    public void setUser(User value) {
        this.mUser = value;
    }

    public int getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(int value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mCoverPhotos);
        parcel.writeTypedList(this.mActivityAttributes);
        parcel.writeParcelable(this.mMarket, 0);
        parcel.writeParcelable(this.mMeetup, 0);
        parcel.writeParcelable(this.mPlace, 0);
        parcel.writeParcelable(this.mRecommendationSection, 0);
        parcel.writeParcelable(this.mRestaurant, 0);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mBoldSubtitle);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mInsiderTagline);
        parcel.writeString(this.mActionKicker);
        parcel.writeString(this.mActionRowTitle);
        parcel.writeString(this.mActionRowSubtitle);
        parcel.writeString(this.mShareUrl);
        parcel.writeParcelable(this.mUser, 0);
        parcel.writeInt(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mCoverPhotos = source.createTypedArrayList(Photo.CREATOR);
        this.mActivityAttributes = source.createTypedArrayList(PlaceActivityAttribute.CREATOR);
        this.mMarket = (Market) source.readParcelable(Market.class.getClassLoader());
        this.mMeetup = (Meetup) source.readParcelable(Meetup.class.getClassLoader());
        this.mPlace = (Place) source.readParcelable(Place.class.getClassLoader());
        this.mRecommendationSection = (RecommendationSection) source.readParcelable(RecommendationSection.class.getClassLoader());
        this.mRestaurant = (Restaurant) source.readParcelable(Restaurant.class.getClassLoader());
        this.mTitle = source.readString();
        this.mSubtitle = source.readString();
        this.mBoldSubtitle = source.readString();
        this.mDescription = source.readString();
        this.mInsiderTagline = source.readString();
        this.mActionKicker = source.readString();
        this.mActionRowTitle = source.readString();
        this.mActionRowSubtitle = source.readString();
        this.mShareUrl = source.readString();
        this.mUser = (User) source.readParcelable(User.class.getClassLoader());
        this.mId = source.readInt();
    }
}
