package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.beta.models.guidebook.GuidebookItem;
import com.airbnb.android.core.instant_promo.models.InstantPromotion;
import com.airbnb.android.core.models.Banner;
import com.airbnb.android.core.models.CategorizedFilters;
import com.airbnb.android.core.models.Destination;
import com.airbnb.android.core.models.ExplorePromotion;
import com.airbnb.android.core.models.ExploreSection.ResultType;
import com.airbnb.android.core.models.ExploreSeeAllInfo;
import com.airbnb.android.core.models.FilterRemovalSuggestionItem;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.core.models.GiftCardPromotion;
import com.airbnb.android.core.models.Market;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.models.SearchResult;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.core.models.UrgencyMessageData;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenExploreSection implements Parcelable {
    @JsonProperty("banners")
    protected List<Banner> mBanners;
    @JsonProperty("cancellation_fully_refundable")
    protected boolean mCancellationFullyRefundable;
    @JsonProperty("destinations")
    protected List<Destination> mDestinations;
    @JsonProperty("display_layout")
    protected List<Integer> mDisplayLayout;
    @JsonProperty("display_type")
    protected DisplayType mDisplayType;
    @JsonProperty("filter_bar_suggestions")
    protected List<CategorizedFilters> mFilterBarSuggestions;
    @JsonProperty("filter_removal_suggestions")
    protected List<FilterRemovalSuggestionItem> mFilterRemovalSuggestionItems;
    @JsonProperty("filter_suggestions")
    protected List<FilterSuggestionItem> mFilterSuggestionItems;
    @JsonProperty("gift_card_promotions")
    protected List<GiftCardPromotion> mGiftCardPromotions;
    @JsonProperty("guidebook_items")
    protected List<GuidebookItem> mGuidebookItems;
    @JsonProperty("instant_promotions")
    protected List<InstantPromotion> mInstantPromotions;
    @JsonProperty("last_search_params")
    protected List<SavedSearch> mLastSearchParams;
    @JsonProperty("listings")
    protected List<SearchResult> mListings;
    @JsonProperty("markets")
    protected List<Market> mMarkets;
    @JsonProperty("promotions")
    protected List<ExplorePromotion> mPromotions;
    @JsonProperty("recommendation_items")
    protected List<RecommendationItem> mRecommendationItems;
    @JsonProperty("result_type")
    protected ResultType mResultType;
    @JsonProperty("section_id")
    protected String mSectionId;
    @JsonProperty("see_all_info")
    protected ExploreSeeAllInfo mSeeAllInfo;
    @JsonProperty("subtitle")
    protected String mSubtitle;
    @JsonProperty("title")
    protected String mTitle;
    @JsonProperty("trip_templates")
    protected List<TripTemplate> mTripTemplates;
    @JsonProperty("urgency_messages")
    protected List<UrgencyMessageData> mUrgencyMessages;

    protected GenExploreSection(DisplayType displayType, ExploreSeeAllInfo seeAllInfo, List<Banner> banners, List<CategorizedFilters> filterBarSuggestions, List<Destination> destinations, List<ExplorePromotion> promotions, List<FilterRemovalSuggestionItem> filterRemovalSuggestionItems, List<FilterSuggestionItem> filterSuggestionItems, List<GiftCardPromotion> giftCardPromotions, List<GuidebookItem> guidebookItems, List<InstantPromotion> instantPromotions, List<Integer> displayLayout, List<Market> markets, List<RecommendationItem> recommendationItems, List<SavedSearch> lastSearchParams, List<SearchResult> listings, List<TripTemplate> tripTemplates, List<UrgencyMessageData> urgencyMessages, ResultType resultType, String title, String subtitle, String sectionId, boolean cancellationFullyRefundable) {
        this();
        this.mDisplayType = displayType;
        this.mSeeAllInfo = seeAllInfo;
        this.mBanners = banners;
        this.mFilterBarSuggestions = filterBarSuggestions;
        this.mDestinations = destinations;
        this.mPromotions = promotions;
        this.mFilterRemovalSuggestionItems = filterRemovalSuggestionItems;
        this.mFilterSuggestionItems = filterSuggestionItems;
        this.mGiftCardPromotions = giftCardPromotions;
        this.mGuidebookItems = guidebookItems;
        this.mInstantPromotions = instantPromotions;
        this.mDisplayLayout = displayLayout;
        this.mMarkets = markets;
        this.mRecommendationItems = recommendationItems;
        this.mLastSearchParams = lastSearchParams;
        this.mListings = listings;
        this.mTripTemplates = tripTemplates;
        this.mUrgencyMessages = urgencyMessages;
        this.mResultType = resultType;
        this.mTitle = title;
        this.mSubtitle = subtitle;
        this.mSectionId = sectionId;
        this.mCancellationFullyRefundable = cancellationFullyRefundable;
    }

    protected GenExploreSection() {
    }

    public DisplayType getDisplayType() {
        return this.mDisplayType;
    }

    public ExploreSeeAllInfo getSeeAllInfo() {
        return this.mSeeAllInfo;
    }

    @JsonProperty("see_all_info")
    public void setSeeAllInfo(ExploreSeeAllInfo value) {
        this.mSeeAllInfo = value;
    }

    public List<Banner> getBanners() {
        return this.mBanners;
    }

    @JsonProperty("banners")
    public void setBanners(List<Banner> value) {
        this.mBanners = value;
    }

    public List<CategorizedFilters> getFilterBarSuggestions() {
        return this.mFilterBarSuggestions;
    }

    @JsonProperty("filter_bar_suggestions")
    public void setFilterBarSuggestions(List<CategorizedFilters> value) {
        this.mFilterBarSuggestions = value;
    }

    public List<Destination> getDestinations() {
        return this.mDestinations;
    }

    public List<ExplorePromotion> getPromotions() {
        return this.mPromotions;
    }

    @JsonProperty("promotions")
    public void setPromotions(List<ExplorePromotion> value) {
        this.mPromotions = value;
    }

    public List<FilterRemovalSuggestionItem> getFilterRemovalSuggestionItems() {
        return this.mFilterRemovalSuggestionItems;
    }

    @JsonProperty("filter_removal_suggestions")
    public void setFilterRemovalSuggestionItems(List<FilterRemovalSuggestionItem> value) {
        this.mFilterRemovalSuggestionItems = value;
    }

    public List<FilterSuggestionItem> getFilterSuggestionItems() {
        return this.mFilterSuggestionItems;
    }

    @JsonProperty("filter_suggestions")
    public void setFilterSuggestionItems(List<FilterSuggestionItem> value) {
        this.mFilterSuggestionItems = value;
    }

    public List<GiftCardPromotion> getGiftCardPromotions() {
        return this.mGiftCardPromotions;
    }

    @JsonProperty("gift_card_promotions")
    public void setGiftCardPromotions(List<GiftCardPromotion> value) {
        this.mGiftCardPromotions = value;
    }

    public List<GuidebookItem> getGuidebookItems() {
        return this.mGuidebookItems;
    }

    public List<InstantPromotion> getInstantPromotions() {
        return this.mInstantPromotions;
    }

    @JsonProperty("instant_promotions")
    public void setInstantPromotions(List<InstantPromotion> value) {
        this.mInstantPromotions = value;
    }

    public List<Integer> getDisplayLayout() {
        return this.mDisplayLayout;
    }

    @JsonProperty("display_layout")
    public void setDisplayLayout(List<Integer> value) {
        this.mDisplayLayout = value;
    }

    public List<Market> getMarkets() {
        return this.mMarkets;
    }

    @JsonProperty("markets")
    public void setMarkets(List<Market> value) {
        this.mMarkets = value;
    }

    public List<RecommendationItem> getRecommendationItems() {
        return this.mRecommendationItems;
    }

    @JsonProperty("recommendation_items")
    public void setRecommendationItems(List<RecommendationItem> value) {
        this.mRecommendationItems = value;
    }

    public List<SavedSearch> getLastSearchParams() {
        return this.mLastSearchParams;
    }

    @JsonProperty("last_search_params")
    public void setLastSearchParams(List<SavedSearch> value) {
        this.mLastSearchParams = value;
    }

    public List<SearchResult> getListings() {
        return this.mListings;
    }

    public List<TripTemplate> getTripTemplates() {
        return this.mTripTemplates;
    }

    public List<UrgencyMessageData> getUrgencyMessages() {
        return this.mUrgencyMessages;
    }

    @JsonProperty("urgency_messages")
    public void setUrgencyMessages(List<UrgencyMessageData> value) {
        this.mUrgencyMessages = value;
    }

    public ResultType getResultType() {
        return this.mResultType;
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

    public String getSectionId() {
        return this.mSectionId;
    }

    @JsonProperty("section_id")
    public void setSectionId(String value) {
        this.mSectionId = value;
    }

    public boolean isCancellationFullyRefundable() {
        return this.mCancellationFullyRefundable;
    }

    @JsonProperty("cancellation_fully_refundable")
    public void setCancellationFullyRefundable(boolean value) {
        this.mCancellationFullyRefundable = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeSerializable(this.mDisplayType);
        parcel.writeParcelable(this.mSeeAllInfo, 0);
        parcel.writeTypedList(this.mBanners);
        parcel.writeTypedList(this.mFilterBarSuggestions);
        parcel.writeTypedList(this.mDestinations);
        parcel.writeTypedList(this.mPromotions);
        parcel.writeTypedList(this.mFilterRemovalSuggestionItems);
        parcel.writeTypedList(this.mFilterSuggestionItems);
        parcel.writeTypedList(this.mGiftCardPromotions);
        parcel.writeTypedList(this.mGuidebookItems);
        parcel.writeTypedList(this.mInstantPromotions);
        parcel.writeValue(this.mDisplayLayout);
        parcel.writeTypedList(this.mMarkets);
        parcel.writeTypedList(this.mRecommendationItems);
        parcel.writeTypedList(this.mLastSearchParams);
        parcel.writeTypedList(this.mListings);
        parcel.writeTypedList(this.mTripTemplates);
        parcel.writeTypedList(this.mUrgencyMessages);
        parcel.writeSerializable(this.mResultType);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mSectionId);
        parcel.writeBooleanArray(new boolean[]{this.mCancellationFullyRefundable});
    }

    public void readFromParcel(Parcel source) {
        this.mDisplayType = (DisplayType) source.readSerializable();
        this.mSeeAllInfo = (ExploreSeeAllInfo) source.readParcelable(ExploreSeeAllInfo.class.getClassLoader());
        this.mBanners = source.createTypedArrayList(Banner.CREATOR);
        this.mFilterBarSuggestions = source.createTypedArrayList(CategorizedFilters.CREATOR);
        this.mDestinations = source.createTypedArrayList(Destination.CREATOR);
        this.mPromotions = source.createTypedArrayList(ExplorePromotion.CREATOR);
        this.mFilterRemovalSuggestionItems = source.createTypedArrayList(FilterRemovalSuggestionItem.CREATOR);
        this.mFilterSuggestionItems = source.createTypedArrayList(FilterSuggestionItem.CREATOR);
        this.mGiftCardPromotions = source.createTypedArrayList(GiftCardPromotion.CREATOR);
        this.mGuidebookItems = source.createTypedArrayList(GuidebookItem.CREATOR);
        this.mInstantPromotions = source.createTypedArrayList(InstantPromotion.CREATOR);
        this.mDisplayLayout = (List) source.readValue(null);
        this.mMarkets = source.createTypedArrayList(Market.CREATOR);
        this.mRecommendationItems = source.createTypedArrayList(RecommendationItem.CREATOR);
        this.mLastSearchParams = source.createTypedArrayList(SavedSearch.CREATOR);
        this.mListings = source.createTypedArrayList(SearchResult.CREATOR);
        this.mTripTemplates = source.createTypedArrayList(TripTemplate.CREATOR);
        this.mUrgencyMessages = source.createTypedArrayList(UrgencyMessageData.CREATOR);
        this.mResultType = (ResultType) source.readSerializable();
        this.mTitle = source.readString();
        this.mSubtitle = source.readString();
        this.mSectionId = source.readString();
        this.mCancellationFullyRefundable = source.createBooleanArray()[0];
    }
}
