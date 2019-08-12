package com.airbnb.android.places.activities;

import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.beta.models.guidebook.Place;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.PlacesIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.ForYouMetaData;
import com.airbnb.android.core.models.MeetupPDPArguments;
import com.airbnb.android.core.models.PlaceActivity;
import com.airbnb.android.core.react.ReactExposedActivityParamsConstants;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.MapUtil;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.ResyController;
import com.airbnb.android.places.ResyController.ResyControllerProvider;
import com.airbnb.android.places.ResyState;
import com.airbnb.android.places.fragments.PlaceActivityHoursFragment;
import com.airbnb.android.places.fragments.PlaceActivityMapFragment;
import com.airbnb.android.places.fragments.PlaceActivityPDPFragment;

public class PlaceActivityPDPActivity extends AirActivity implements ResyControllerProvider {
    private static final String PLACE_ACTIVITY_DETAILS_FRAGMENT_TAG = "fragment_place_activity_pdp";
    private static final String PLACE_ACTIVITY_FEEDBACK_FRAGMENT_TAG = "fragment_place_activity_feedback";
    private static final String PLACE_ACTIVITY_HOURS_FRAGMENT_TAG = "fragment_place_activity_hours";
    private static final String PLACE_ACTIVITY_MAP_FRAGMENT_TAG = "fragment_place_activity_map";
    private final PlaceActivityPDPController activityController = new PlaceActivityPDPController() {
        public void showMap(Place place) {
            PlaceActivityPDPActivity.this.showModal(PlaceActivityMapFragment.newInstance(place), C7627R.C7629id.content_container, C7627R.C7629id.modal_container, true, PlaceActivityPDPActivity.PLACE_ACTIVITY_MAP_FRAGMENT_TAG);
        }

        public void showDirections(Place place) {
            PlaceActivityPDPActivity.this.startActivity(MapUtil.intentFor(PlaceActivityPDPActivity.this, place.getLat(), place.getLng(), place.getName()));
        }

        public void showHours(Place place) {
            PlaceActivityPDPActivity.this.showModal(PlaceActivityHoursFragment.newInstance(place), C7627R.C7629id.content_container, C7627R.C7629id.modal_container, true, PlaceActivityPDPActivity.PLACE_ACTIVITY_HOURS_FRAGMENT_TAG);
        }

        public void showFeedbackForm(PlaceActivity placeActivity) {
            PlaceActivityPDPActivity.this.startActivity(ReactNativeIntents.intentForGuidebookFeedback(PlaceActivityPDPActivity.this, (long) placeActivity.getId()));
        }
    };
    private ResyController resyController;

    public interface PlaceActivityPDPController {
        void showDirections(Place place);

        void showFeedbackForm(PlaceActivity placeActivity);

        void showHours(Place place);

        void showMap(Place place);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C7627R.layout.activity_place_activity_details);
        long activityId = getIntent().getLongExtra("activity_id", -1);
        boolean isMeetup = getIntent().getBooleanExtra(PlacesIntents.INTENT_EXTRA_IS_MEETUP, false);
        MeetupPDPArguments meetupPDPArguments = (MeetupPDPArguments) getIntent().getParcelableExtra(ReactExposedActivityParamsConstants.KEY_ARGUMENT);
        if (meetupPDPArguments != null) {
            activityId = meetupPDPArguments.mo9028id().longValue();
            isMeetup = true;
        }
        Check.validId(activityId);
        this.resyController = new ResyController(this, this.requestManager);
        this.resyController.onRestoreInstanceState(savedInstanceState, ResyState.builder().activityId(activityId).build());
        ForYouMetaData forYouMetaData = (ForYouMetaData) getIntent().getParcelableExtra(PlacesIntents.INTENT_EXTRA_FOR_YOU_METADATA);
        String searchId = getIntent().getStringExtra(PlacesIntents.INTENT_EXTRA_SEARCH_ID);
        String searchSessionId = getIntent().getStringExtra(PlacesIntents.INTENT_EXTRA_SEARCH_SESSION_ID);
        if (getSupportFragmentManager().findFragmentByTag(PLACE_ACTIVITY_DETAILS_FRAGMENT_TAG) == null) {
            showFragment(PlaceActivityPDPFragment.newInstance(activityId, isMeetup, forYouMetaData, searchId, searchSessionId), C7627R.C7629id.content_container, FragmentTransitionType.SlideInFromSide, true, PLACE_ACTIVITY_DETAILS_FRAGMENT_TAG);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.resyController.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.resyController.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public PlaceActivityPDPController getActivityController() {
        return this.activityController;
    }

    public ResyController getResyController() {
        return this.resyController;
    }
}
