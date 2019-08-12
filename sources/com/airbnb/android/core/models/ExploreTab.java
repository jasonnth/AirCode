package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenExploreTab;
import com.airbnb.android.utils.ListUtils;
import java.util.Collection;

public class ExploreTab extends GenExploreTab {
    public static final Creator<ExploreTab> CREATOR = new Creator<ExploreTab>() {
        public ExploreTab[] newArray(int size) {
            return new ExploreTab[size];
        }

        public ExploreTab createFromParcel(Parcel source) {
            ExploreTab object = new ExploreTab();
            object.readFromParcel(source);
            return object;
        }
    };
    private boolean hasError;

    public enum Tab {
        HOME("home_tab"),
        EXPERIENCE("experience_tab"),
        ALL("all_tab"),
        PLACES("place_tab");
        
        private final String tabId;

        public String getTabId() {
            return this.tabId;
        }

        private Tab(String tabId2) {
            this.tabId = tabId2;
        }

        public boolean isSameAs(String tabId2) {
            return getTabId().equals(tabId2);
        }
    }

    public void clearData() {
        getSections().clear();
        setPaginationMetadata(null);
        setHomeTabMetadata(null);
    }

    public boolean hasResults() {
        if (ListUtils.isEmpty((Collection<?>) getSections())) {
            return false;
        }
        for (ExploreSection section : getSections()) {
            if (getResultCountForSection(section) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasNextPage() {
        return getPaginationMetadata() != null && getPaginationMetadata().hasNextPage();
    }

    private int getResultCountForSection(ExploreSection section) {
        switch (section.getResultType()) {
            case Markets:
                return section.getMarkets().size();
            case Experiences:
                return section.getTripTemplates().size();
            case Listings:
                return section.getListings().size();
            case Destinations:
                return section.getDestinations().size();
            case Promotions:
                return section.getPromotions().size();
            case GuidebookItems:
                return section.getGuidebookItems().size();
            case RecommendationItems:
                return section.getRecommendationItems().size();
            case Banners:
                return section.getBanners().size();
            case FilterRemovalSuggestions:
                return section.getFilterRemovalSuggestionItems().size();
            case UrgencyMessages:
                return section.getUrgencyMessages().size();
            default:
                return 0;
        }
    }

    public boolean hasError() {
        return this.hasError;
    }

    public void setHasError(boolean error) {
        this.hasError = error;
    }
}
