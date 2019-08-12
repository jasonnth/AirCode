package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.GeneralAnalytics;
import com.airbnb.android.core.data.AffiliateInfo;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.intents.SearchActivityIntents;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.SearchParams;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.content.SearchDeepLinkParser;
import com.airbnb.android.lib.utils.WebIntentHelper;
import dagger.Lazy;

public class SearchIntentActivity extends AirActivity {
    Lazy<AffiliateInfo> affiliateInfo;

    public static Intent forDeepLink(Context context) {
        return SearchActivityIntents.intent(context).putExtra(SearchActivityIntents.EXTRA_SOURCE, "deep_link");
    }

    public static Intent forPlaylistDeeplink(Context context, Bundle bundle) {
        return SearchActivityIntents.intent(context).putExtra(SearchActivityIntents.EXTRA_SOURCE, "deep_link").putExtra("playlist_id", FeatureToggles.arePlaylistsEnabled() ? Long.valueOf(DeepLinkUtils.getParamAsId(bundle, "id")) : null);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        Intent intent = getIntent();
        String source = intent.getStringExtra(SearchActivityIntents.EXTRA_SOURCE);
        long playlistId = intent.getLongExtra("playlist_id", 0);
        String query = getQuery(intent);
        Uri uri = intent.hasExtra(SearchActivityIntents.EXTRA_URI) ? (Uri) intent.getParcelableExtra(SearchActivityIntents.EXTRA_URI) : intent.getData();
        if ("deep_link".equals(source)) {
            if (uri != null) {
                ((AffiliateInfo) this.affiliateInfo.get()).storeAffiliateParams(uri);
            }
            AirbnbEventLogger.track(GeneralAnalytics.DeepLinking, GeneralAnalytics.AppOpen, "search_intent");
            query = SearchDeepLinkParser.cleanupSearchQuery(query);
        }
        startActivity(buildIntent(source, intent.hasExtra(SearchActivityIntents.EXTRA_SEARCH_PARAMS) ? (SearchParams) intent.getParcelableExtra(SearchActivityIntents.EXTRA_SEARCH_PARAMS) : buildSearchParams(query, uri, getTab(intent)), playlistId));
        finish();
    }

    private String getQuery(Intent intent) {
        if (isSearchAction(intent) && intent.hasExtra("query")) {
            return intent.getStringExtra("query");
        }
        if (DeepLinkUtils.isDeepLink(intent)) {
            return new SearchDeepLinkParser(intent).getSearchQuery();
        }
        return intent.getStringExtra(SearchActivityIntents.EXTRA_QUERY);
    }

    private Tab getTab(Intent intent) {
        if (DeepLinkUtils.isDeepLink(intent)) {
            return new SearchDeepLinkParser(intent).getTab();
        }
        if (!intent.hasExtra(SearchActivityIntents.EXTRA_URI)) {
            return null;
        }
        Intent newIntent = new Intent(intent);
        newIntent.setData((Uri) intent.getParcelableExtra(SearchActivityIntents.EXTRA_URI));
        return new SearchDeepLinkParser(newIntent).getTab();
    }

    private Intent buildIntent(String source, SearchParams params, long playlistId) {
        Intent intent = HomeActivityIntents.intentForSearchLanding(this);
        intent.putExtra(SearchActivityIntents.EXTRA_SOURCE, source);
        if (params != null) {
            intent.putExtra(SearchActivityIntents.EXTRA_SEARCH_PARAMS, params);
        }
        intent.putExtra("playlist_id", playlistId);
        intent.putExtra("saved_search", getIntent().getParcelableExtra("saved_search"));
        return intent;
    }

    static boolean isSearchAction(Intent intent) {
        String action = intent.getAction();
        return "com.google.android.gms.actions.SEARCH_ACTION".equals(action) || "com.airbnb.android.actions.SEARCH_NEARBY".equals(action) || "android.intent.action.SEARCH".equals(action);
    }

    static SearchParams buildSearchParams(String query, Uri uri, Tab tab) {
        SearchParams params = new SearchParams();
        params.setLocation(query);
        if (tab != null) {
            params.setTabId(tab.getTabId());
        }
        if (uri != null) {
            AirDate checkin = WebIntentHelper.parseCheckIn(uri);
            AirDate checkout = WebIntentHelper.parseCheckOut(uri);
            if (!(checkin == null || checkout == null || !checkin.isBefore(checkout))) {
                params.setCheckin(checkin);
                params.setCheckout(checkout);
            }
            params.setGuests(WebIntentHelper.parseGuestCount(uri));
            params.setAdults(WebIntentHelper.parseAdultCount(uri));
            params.setChildren(WebIntentHelper.parseChildCount(uri));
            params.setInfants(WebIntentHelper.parseInfantCount(uri));
            if (WebIntentHelper.parseInstantBook(uri)) {
                params.setInstantBookOnly(Boolean.valueOf(true));
            }
            params.setRoomTypeEnums(WebIntentHelper.parseRoomTypes(uri));
            params.setNumBedrooms(WebIntentHelper.parseBedRoomCount(uri));
            params.setNumBathrooms(WebIntentHelper.parseBathroomCount(uri));
            params.setNumBeds(WebIntentHelper.parseBedCount(uri));
            if (params.getTabId() == null) {
                params.setTabId(WebIntentHelper.parseTabId(uri));
            }
            params.setExperienceCategories(WebIntentHelper.parseExperienceCategories(uri));
        }
        return params;
    }
}
