package com.airbnb.android.explore;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.AppboyAnalytics;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.airbnb.android.core.models.ExploreSection;
import com.airbnb.android.core.models.ExploreSection.ResultType;
import com.airbnb.android.core.models.ExploreTab;
import com.airbnb.android.core.models.SearchGeography;
import com.airbnb.android.core.models.SearchMetaData;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.explore.controllers.ExploreDataController;
import com.airbnb.android.lib.contentproviders.PlacesSearchSuggestionProvider;
import com.airbnb.android.utils.Strap;
import com.google.common.collect.FluentIterable;
import com.google.gson.jpush.Gson;
import java.util.ArrayList;
import java.util.List;

public class ExploreMParticleLogger {
    private static final int MAX_LISTINGS_IDS = 6;
    ExploreDataController exploreDataController;

    public ExploreMParticleLogger(ExploreDataController exploreDataController2) {
        this.exploreDataController = exploreDataController2;
    }

    public void homeImpression(String tabId, Context context) {
        TopLevelSearchParams topLevelSearchParams = this.exploreDataController.getTopLevelSearchParams();
        String checkInDate = formatDate(topLevelSearchParams.checkInDate());
        String checkOutDate = formatDate(topLevelSearchParams.checkOutDate());
        ExploreTab homeTab = this.exploreDataController.findTabForId(tabId);
        if (homeTab != null && !TextUtils.isEmpty(checkInDate) && !TextUtils.isEmpty(checkOutDate)) {
            List<String> listingsIds = null;
            String city = "";
            String region = "";
            String country = "";
            SearchMetaData searchMetaData = homeTab.getHomeTabMetadata();
            if (searchMetaData != null) {
                int[] remarketingIds = searchMetaData.getRemarketingIds();
                if (remarketingIds != null && remarketingIds.length > 0) {
                    listingsIds = new ArrayList<>();
                    int i = 0;
                    while (i < 6 && i < remarketingIds.length) {
                        listingsIds.add(Integer.toString(remarketingIds[i]));
                        i++;
                    }
                }
                SearchGeography geography = searchMetaData.getGeography();
                if (geography != null) {
                    city = geography.getCity();
                    region = geography.getState();
                    country = geography.getCountry();
                }
            }
            if (listingsIds == null) {
                listingsIds = FluentIterable.from((Iterable<E>) homeTab.getSections()).filter(ExploreMParticleLogger$$Lambda$1.lambdaFactory$()).transformAndConcat(ExploreMParticleLogger$$Lambda$2.lambdaFactory$()).limit(6).transform(ExploreMParticleLogger$$Lambda$3.lambdaFactory$()).transform(ExploreMParticleLogger$$Lambda$4.lambdaFactory$()).transform(ExploreMParticleLogger$$Lambda$5.lambdaFactory$()).toList();
            }
            Strap info = Strap.make().mo11639kv("checkin_date", checkInDate).mo11639kv("checkout_date", checkOutDate).mo11639kv("search_string", topLevelSearchParams.searchTerm()).mo11639kv("content_ids", new Gson().toJson((Object) listingsIds)).mo11639kv("city", city).mo11639kv(PlacesSearchSuggestionProvider.DISPLAY_REGION, region).mo11639kv("country", country);
            MParticleAnalytics.logEvent("p2_impression_with_dates", info);
            AppboyAnalytics.logEvent(context, "p2_impression_with_dates", info);
        }
    }

    static /* synthetic */ boolean lambda$homeImpression$0(ExploreSection exploreSection) {
        return (exploreSection != null ? exploreSection.getResultType() : null) == ResultType.Listings;
    }

    private String formatDate(AirDate date) {
        return date == null ? "" : date.getIsoDateString();
    }
}
