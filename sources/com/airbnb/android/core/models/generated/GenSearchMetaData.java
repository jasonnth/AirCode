package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.InlineSearchFeedItem;
import com.airbnb.android.core.models.SearchFacets;
import com.airbnb.android.core.models.SearchGeography;
import com.airbnb.android.core.models.SearchOverrides;
import com.airbnb.android.core.models.SearchPriceHistogram;
import com.airbnb.android.core.models.SearchPriceMetaData;
import com.airbnb.android.core.models.SearchUrgencyCommitment;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenSearchMetaData implements Parcelable {
    @JsonProperty("facets")
    protected SearchFacets mFacets;
    @JsonProperty("geography")
    protected SearchGeography mGeography;
    @JsonProperty("listings_count")
    protected int mListingsCount;
    @JsonProperty("overrides")
    protected SearchOverrides mOverrides;
    @JsonProperty("price_histogram")
    protected SearchPriceHistogram mPriceHistogram;
    @JsonProperty("remarketing_ids")
    protected int[] mRemarketingIds;
    @JsonProperty("search")
    protected SearchPriceMetaData mSearch;
    @JsonProperty("search_feed_items")
    protected List<InlineSearchFeedItem> mSearchFeedItems;
    @JsonProperty("urgency_commitment")
    protected SearchUrgencyCommitment mUrgencyCommitment;

    protected GenSearchMetaData(List<InlineSearchFeedItem> searchFeedItems, SearchFacets facets, SearchGeography geography, SearchOverrides overrides, SearchPriceHistogram priceHistogram, SearchPriceMetaData search, SearchUrgencyCommitment urgencyCommitment, int listingsCount, int[] remarketingIds) {
        this();
        this.mSearchFeedItems = searchFeedItems;
        this.mFacets = facets;
        this.mGeography = geography;
        this.mOverrides = overrides;
        this.mPriceHistogram = priceHistogram;
        this.mSearch = search;
        this.mUrgencyCommitment = urgencyCommitment;
        this.mListingsCount = listingsCount;
        this.mRemarketingIds = remarketingIds;
    }

    protected GenSearchMetaData() {
    }

    public List<InlineSearchFeedItem> getSearchFeedItems() {
        return this.mSearchFeedItems;
    }

    @JsonProperty("search_feed_items")
    public void setSearchFeedItems(List<InlineSearchFeedItem> value) {
        this.mSearchFeedItems = value;
    }

    public SearchFacets getFacets() {
        return this.mFacets;
    }

    @JsonProperty("facets")
    public void setFacets(SearchFacets value) {
        this.mFacets = value;
    }

    public SearchGeography getGeography() {
        return this.mGeography;
    }

    @JsonProperty("geography")
    public void setGeography(SearchGeography value) {
        this.mGeography = value;
    }

    public SearchOverrides getOverrides() {
        return this.mOverrides;
    }

    @JsonProperty("overrides")
    public void setOverrides(SearchOverrides value) {
        this.mOverrides = value;
    }

    public SearchPriceHistogram getPriceHistogram() {
        return this.mPriceHistogram;
    }

    @JsonProperty("price_histogram")
    public void setPriceHistogram(SearchPriceHistogram value) {
        this.mPriceHistogram = value;
    }

    public SearchPriceMetaData getSearch() {
        return this.mSearch;
    }

    @JsonProperty("search")
    public void setSearch(SearchPriceMetaData value) {
        this.mSearch = value;
    }

    public SearchUrgencyCommitment getUrgencyCommitment() {
        return this.mUrgencyCommitment;
    }

    @JsonProperty("urgency_commitment")
    public void setUrgencyCommitment(SearchUrgencyCommitment value) {
        this.mUrgencyCommitment = value;
    }

    public int getListingsCount() {
        return this.mListingsCount;
    }

    @JsonProperty("listings_count")
    public void setListingsCount(int value) {
        this.mListingsCount = value;
    }

    public int[] getRemarketingIds() {
        return this.mRemarketingIds;
    }

    @JsonProperty("remarketing_ids")
    public void setRemarketingIds(int[] value) {
        this.mRemarketingIds = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mSearchFeedItems);
        parcel.writeParcelable(this.mFacets, 0);
        parcel.writeParcelable(this.mGeography, 0);
        parcel.writeParcelable(this.mOverrides, 0);
        parcel.writeParcelable(this.mPriceHistogram, 0);
        parcel.writeParcelable(this.mSearch, 0);
        parcel.writeParcelable(this.mUrgencyCommitment, 0);
        parcel.writeInt(this.mListingsCount);
        parcel.writeIntArray(this.mRemarketingIds);
    }

    public void readFromParcel(Parcel source) {
        this.mSearchFeedItems = source.createTypedArrayList(InlineSearchFeedItem.CREATOR);
        this.mFacets = (SearchFacets) source.readParcelable(SearchFacets.class.getClassLoader());
        this.mGeography = (SearchGeography) source.readParcelable(SearchGeography.class.getClassLoader());
        this.mOverrides = (SearchOverrides) source.readParcelable(SearchOverrides.class.getClassLoader());
        this.mPriceHistogram = (SearchPriceHistogram) source.readParcelable(SearchPriceHistogram.class.getClassLoader());
        this.mSearch = (SearchPriceMetaData) source.readParcelable(SearchPriceMetaData.class.getClassLoader());
        this.mUrgencyCommitment = (SearchUrgencyCommitment) source.readParcelable(SearchUrgencyCommitment.class.getClassLoader());
        this.mListingsCount = source.readInt();
        this.mRemarketingIds = source.createIntArray();
    }
}
