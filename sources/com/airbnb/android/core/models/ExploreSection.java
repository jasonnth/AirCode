package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.beta.models.guidebook.GuidebookItem;
import com.airbnb.android.core.models.generated.GenExploreSection;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.erf.Experiments;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.util.Collection;
import java.util.List;

public class ExploreSection extends GenExploreSection {
    public static final Creator<ExploreSection> CREATOR = new Creator<ExploreSection>() {
        public ExploreSection[] newArray(int size) {
            return new ExploreSection[size];
        }

        public ExploreSection createFromParcel(Parcel source) {
            ExploreSection object = new ExploreSection();
            object.readFromParcel(source);
            return object;
        }
    };
    private long hashCode;

    public enum ResultType {
        Markets("markets"),
        Experiences("experiences"),
        Listings("listings"),
        Destinations("destinations"),
        Promotions("promotions"),
        GiftCardPromotions("gift_card_promotions"),
        GuidebookItems("guidebooks"),
        RecommendationItems("recommendation_items"),
        Banners("banners"),
        FilterSuggestions("filter_suggestions"),
        FilterRemovalSuggestions("filter_removal_suggestions"),
        InstantPromos("instant_promotions"),
        Playlists("playlists"),
        UrgencyMessages("urgency_messages"),
        LastSearchParams("last_search_params"),
        FilterBarSuggestions("filter_bar_suggestions"),
        Unknown("");
        
        public final String key;

        private ResultType(String key2) {
            this.key = key2;
        }

        public static ResultType fromKey(String key2) {
            return (ResultType) FluentIterable.from((E[]) values()).firstMatch(ExploreSection$ResultType$$Lambda$1.lambdaFactory$(key2)).mo41059or(Unknown);
        }
    }

    @JsonProperty("result_type")
    public void setResult_type(String key) {
        this.mResultType = ResultType.fromKey(key);
    }

    @JsonProperty("display_type")
    public void setDisplay_type(String key) {
        char c = 65535;
        switch (key.hashCode()) {
            case -1984141450:
                if (key.equals("vertical")) {
                    c = 0;
                    break;
                }
                break;
            case -76567660:
                if (key.equals("magazine")) {
                    c = 3;
                    break;
                }
                break;
            case 2908512:
                if (key.equals("carousel")) {
                    c = 2;
                    break;
                }
                break;
            case 3181382:
                if (key.equals("grid")) {
                    c = 1;
                    break;
                }
                break;
            case 3440953:
                if (key.equals("pill")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.mDisplayType = DisplayType.Vertical;
                return;
            case 1:
                this.mDisplayType = DisplayType.Grid;
                return;
            case 2:
                this.mDisplayType = DisplayType.Horizontal;
                return;
            case 3:
                this.mDisplayType = DisplayType.Magazine;
                return;
            case 4:
                this.mDisplayType = DisplayType.Pill;
                return;
            default:
                this.mDisplayType = DisplayType.Unknown;
                return;
        }
    }

    public long cachedHashCode() {
        if (this.hashCode == 0) {
            this.hashCode = (long) hashCode();
        }
        return this.hashCode;
    }

    @JsonProperty("listings")
    public void setListings(List<SearchResult> value) {
        this.mListings = value;
        if (!ListUtils.isEmpty((Collection<?>) this.mListings) && !Experiments.useNewFormat()) {
            for (SearchResult listing : this.mListings) {
                listing.getListing().trimForHomeCard();
            }
        }
    }

    @JsonProperty("trip_templates")
    public void setTripTemplates(List<TripTemplate> value) {
        this.mTripTemplates = value;
        if (!ListUtils.isEmpty((Collection<?>) this.mTripTemplates)) {
            for (TripTemplate tripTemplate : this.mTripTemplates) {
                tripTemplate.trimForPosterCard();
            }
        }
    }

    @JsonProperty("guidebook_items")
    public void setGuidebookItems(List<GuidebookItem> value) {
        this.mGuidebookItems = value;
        if (!ListUtils.isEmpty((Collection<?>) this.mGuidebookItems)) {
            for (GuidebookItem guidebookItem : this.mGuidebookItems) {
                guidebookItem.processAndTrimPhotos();
            }
        }
    }

    @JsonProperty("destinations")
    public void setDestinations(List<Destination> value) {
        this.mDestinations = value;
        if (!ListUtils.isEmpty((Collection<?>) this.mDestinations)) {
            for (Destination destination : this.mDestinations) {
                destination.trimForDestinationCard();
            }
        }
    }
}
