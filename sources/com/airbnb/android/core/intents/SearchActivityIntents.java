package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.deeplinks.WebLink;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.models.SearchParams;
import com.jumio.p311nv.data.NVStrings;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SearchActivityIntents {
    public static final String EXTRA_PLAYLIST_ID = "playlist_id";
    public static final String EXTRA_QUERY = "search_query";
    public static final String EXTRA_SAVED_SEARCH = "saved_search";
    public static final String EXTRA_SEARCH_PARAMS = "search_params";
    public static final String EXTRA_SOURCE = "extra_source";
    public static final String EXTRA_URI = "extra_uri";
    public static final int RC_P3_PROPOGATE_GUEST = 1800;
    public static final String SOURCE_CONTENT_FRAMEWORK_COLLAGE_ARTICLE = "content_framework_collage";
    public static final String SOURCE_CONTENT_FRAMEWORK_SIMPLE_ARTICLE = "content_framework_simple";
    public static final String SOURCE_GUEST_RECOVERY = "guest_recovery";
    public static final String SOURCE_JUST_FOR_WEEKEND = "discover_just_for_the_weekend";
    public static final String SOURCE_POPULAR_DESTINATIONS = "discover_popular_destinations";
    public static final String SOURCE_POST_INQUIRY_IB_UPSELL = "post_inquiry_ib_upsell";
    public static final String SOURCE_SAVED_SEARCH = "saved_search";
    public static final String SOURCE_WEB_INTENT = "deep_link";

    @Retention(RetentionPolicy.SOURCE)
    public @interface FindResultsSource {
    }

    public static Intent intent(Context context) {
        return new Intent(context, Activities.searchIntent());
    }

    @WebLink({"s/{search}", "listings/search/{search}", "s", "listings/search"})
    public static Intent intentForWebLink(Context context, Bundle bundle) {
        return intentForWebLink(context, bundle.getString(NVStrings.SEARCH), Uri.parse(bundle.getString("deep_link_uri")));
    }

    public static Intent intentForWebLink(Context context, String query, Uri uri) {
        return intent(context).putExtra(EXTRA_QUERY, query).putExtra(EXTRA_SOURCE, "deep_link").putExtra(EXTRA_URI, uri);
    }

    public static Intent intentForPostInquiryInstantBookUpsell(Context context, Listing listing, GuestDetails guestDetails, AirDate checkin, AirDate checkout) {
        SearchParams params = new SearchParams();
        params.setGuestDetails(guestDetails);
        params.setCheckin(checkin);
        params.setCheckout(checkout);
        params.setLocation(listing.getLocation());
        params.setTabId(Tab.HOME.getTabId());
        return intent(context).putExtra(EXTRA_SEARCH_PARAMS, params).putExtra(EXTRA_SOURCE, SOURCE_POST_INQUIRY_IB_UPSELL);
    }

    public static Intent forSavedSearch(Context context, SavedSearch savedSearch) {
        return intent(context).putExtra("saved_search", savedSearch).putExtra(EXTRA_SOURCE, "saved_search");
    }

    public static Intent forQuery(Context context, String query, String searchSource) {
        return intent(context).putExtra(EXTRA_QUERY, query).putExtra(EXTRA_SOURCE, searchSource);
    }

    public static Intent intentForGuestRecovery(Context context, SearchParams params) {
        return intent(context).putExtra(EXTRA_SEARCH_PARAMS, params).putExtra(EXTRA_SOURCE, "guest_recovery");
    }
}
