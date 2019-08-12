package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.ForYouMetaData;
import com.airbnb.android.core.models.PlaceActivity;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;

public class PlacesIntents {
    public static final String INTENT_EXTRA_ACTIVITY_ID = "activity_id";
    public static final String INTENT_EXTRA_FOR_YOU_METADATA = "for_you_metadata";
    public static final String INTENT_EXTRA_IS_MEETUP = "is_meetup";
    public static final String INTENT_EXTRA_PLACE_ACTIVITY_MODEL = "place_activity";
    public static final String INTENT_EXTRA_SEARCH_ID = "search_id";
    public static final String INTENT_EXTRA_SEARCH_SESSION_ID = "search_session_id";

    public static Intent intentForPlaceActivityPDP(Context context, PlaceActivity placeActivity) {
        if (placeActivity.getMeetup() != null) {
            return intentForMeetupPDP(context, (long) placeActivity.getId());
        }
        return intentForPlaceActivityPDP(context, (long) placeActivity.getId());
    }

    public static Intent intentForPlaceActivityPDP(Context context, long placeActivityId) {
        return new Intent(context, Activities.placeActivityPDP()).putExtra("activity_id", placeActivityId);
    }

    public static Intent intentForPlaceActivityPDP(Context context, long placeActivityId, ForYouMetaData forYouMetaData, C2731SearchContext searchContext) {
        Intent intent = intentForPlaceActivityPDP(context, placeActivityId).putExtra(INTENT_EXTRA_FOR_YOU_METADATA, forYouMetaData);
        if (searchContext != null) {
            intent.putExtra(INTENT_EXTRA_SEARCH_ID, searchContext.search_id).putExtra(INTENT_EXTRA_SEARCH_SESSION_ID, searchContext.mobile_search_session_id);
        }
        return intent;
    }

    public static Intent intentForMeetupPDP(Context context, long meetupId) {
        return new Intent(context, Activities.placeActivityPDP()).putExtra("activity_id", meetupId).putExtra(INTENT_EXTRA_IS_MEETUP, true);
    }

    public static Intent intentForPickAddToPlans(Context context, PlaceActivity placeActivityModel) {
        return new Intent(context, Activities.placePickAddToPlans()).putExtra("place_activity", placeActivityModel);
    }
}
