package com.airbnb.android.places.fragments;

import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.beta.models.guidebook.Place;
import com.airbnb.android.core.intents.ExploreIntents;
import com.airbnb.android.core.intents.PlacesIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.ForYouMetaData;
import com.airbnb.android.core.models.Meetup;
import com.airbnb.android.core.models.PlaceActivity;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.android.core.models.Restaurant;
import com.airbnb.android.core.models.RestaurantAvailability;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.SearchJitneyUtils;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.views.WishListIcon;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.PlaceJitneyLogger;
import com.airbnb.android.places.adapters.PlaceActivityPDPController;
import com.airbnb.android.places.adapters.PlaceActivityPDPController.PlaceActivityPDPNavigationController;
import com.airbnb.android.places.requests.ActivityReservationRequest;
import com.airbnb.android.places.requests.MeetupActivityRequest;
import com.airbnb.android.places.requests.PlaceActivityRequest;
import com.airbnb.android.places.requests.PlaceReservationRequest;
import com.airbnb.android.places.responses.ActivityReservationResponse;
import com.airbnb.android.places.responses.MeetupActivityResponse;
import com.airbnb.android.places.responses.PlaceActivityResponse;
import com.airbnb.android.places.responses.PlaceReservationResponse;
import com.airbnb.android.utils.CallHelper;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.MtPdpReferrer.p157v1.C2443MtPdpReferrer;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionFooter;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import java.util.List;
import p032rx.Observer;

public class PlaceActivityPDPFragment extends BasePlaceActivityFragment implements OnDateSetListener, OnTimeSetListener {
    private static final int ADD_TO_PLANS_REQUEST_CODE = 1115;
    private static final String ARG_ACTIVITY_ID = "activity_id";
    private static final String ARG_FOR_YOU_METADATA = "for_you_meta_data";
    private static final String ARG_IS_MEETUP = "is_meetup";
    private static final String ARG_SEARCH_ID = "search_id";
    private static final String ARG_SEARCH_SESSION_ID = "search_session_id";
    private static final String DATE_PICKER_FRAGMENT_TAG = "fragment_date_picker";
    private static final String TIME_PICKER_FRAGMENT_TAG = "fragment_time_picker";
    @BindView
    FixedActionFooter actionFooter;
    @State
    PlaceActivity activityModel;
    @State
    AirDateTime addToItineraryDateTime;
    private PlaceActivityPDPController controller;
    @BindView
    CoordinatorLayout coordinatorLayout;
    @BindView
    FixedFlowActionFooter flowActionFooter;
    ForYouMetaData forYouMetaData;
    final RequestListener<PlaceReservationResponse> joinMeetupRequestListener = new C0699RL().onResponse(PlaceActivityPDPFragment$$Lambda$7.lambdaFactory$(this)).onError(PlaceActivityPDPFragment$$Lambda$8.lambdaFactory$(this)).build();
    final RequestListener<MeetupActivityResponse> meetupActivityRequestListener = new C0699RL().onResponse(PlaceActivityPDPFragment$$Lambda$3.lambdaFactory$(this)).onError(PlaceActivityPDPFragment$$Lambda$4.lambdaFactory$(this)).build();
    private final PlaceActivityPDPNavigationController navigationController = new PlaceActivityPDPNavigationController() {
        public void goToCallPlace(String phoneNumber) {
            CallHelper.dial(PlaceActivityPDPFragment.this.getContext(), phoneNumber);
        }

        public void goToHours(Place place) {
            PlaceActivityPDPFragment.this.activityController.showHours(place);
        }

        public void goToFeedback(PlaceActivity placeActivity) {
            PlaceActivityPDPFragment.this.activityController.showFeedbackForm(placeActivity);
        }

        public void goToPlaceWebsite(String placeName, String website) {
            PlaceActivityPDPFragment.this.getAirActivity().startActivity(WebViewIntentBuilder.newBuilder(PlaceActivityPDPFragment.this.getContext(), website).title(placeName).toIntent());
        }

        public void goToMap(Place place) {
            PlaceActivityPDPFragment.this.activityController.showMap(place);
        }

        public void goToMapApp(Place place) {
            PlaceActivityPDPFragment.this.activityController.showDirections(place);
        }

        public void onTapRecommendationItem(RecommendationItem item, int carouselPositionForLogging) {
            AirActivity activity = PlaceActivityPDPFragment.this.getAirActivity();
            Intent intent = ExploreIntents.forType(activity, item, PlaceActivityPDPFragment.this.searchContext, C2443MtPdpReferrer.SimilarActivities, null, PlaceActivityPDPFragment.this.forYouMetaData);
            if (intent != null) {
                activity.startActivity(intent);
            }
            List<RecommendationItem> recommendationItems = PlaceActivityPDPFragment.this.activityModel.getRecommendationSection().getItems();
            PlaceActivityPDPFragment.this.placeJitneyLogger.carouselRecommendedItemClick((long) PlaceActivityPDPFragment.this.activityModel.getId(), item, recommendationItems != null ? recommendationItems.size() : 0, carouselPositionForLogging);
        }

        public void onTapResyChange() {
            PlaceActivityPDPFragment.this.placeJitneyLogger.activityPDPClickResyChange();
            PlaceActivityPDPFragment.this.resyController.goToResyPage();
        }

        public void onTapTimeSlot(RestaurantAvailability timeSlot) {
            PlaceActivityPDPFragment.this.resyController.setSelectedTimeSlot(timeSlot);
            if (timeSlot.hasConfirmationMessages()) {
                PlaceActivityPDPFragment.this.resyController.goToResyPage();
            } else {
                PlaceActivityPDPFragment.this.resyController.goToQuickPay();
            }
            PlaceActivityPDPFragment.this.placeJitneyLogger.activityPDPClickResyTimeSlot();
        }
    };
    final RequestListener<PlaceActivityResponse> placeActivityRequestListener = new C0699RL().onResponse(PlaceActivityPDPFragment$$Lambda$1.lambdaFactory$(this)).onError(PlaceActivityPDPFragment$$Lambda$2.lambdaFactory$(this)).build();
    /* access modifiers changed from: private */
    public PlaceJitneyLogger placeJitneyLogger;
    @BindView
    RecyclerView recyclerView;
    final RequestListener<ActivityReservationResponse> reservationRequestListener = new C0699RL().onResponse(PlaceActivityPDPFragment$$Lambda$5.lambdaFactory$(this)).onError(PlaceActivityPDPFragment$$Lambda$6.lambdaFactory$(this)).build();
    C2731SearchContext searchContext;
    private final SnackbarWrapper snackbarWrapper = new SnackbarWrapper().duration(-2);
    private boolean startLegacyAddToPlansOnResume = false;
    @BindView
    AirToolbar toolbar;

    public static PlaceActivityPDPFragment newInstance(long activityId, boolean isMeetup, ForYouMetaData forYouMetaData2, String searchId, String searchSessionId) {
        return (PlaceActivityPDPFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new PlaceActivityPDPFragment()).putLong("activity_id", activityId)).putBoolean("is_meetup", isMeetup)).putParcelable(ARG_FOR_YOU_METADATA, forYouMetaData2)).putString("search_id", searchId)).putString("search_session_id", searchSessionId)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7627R.layout.fragment_place_activity_pdp, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (Trebuchet.launch(TrebuchetKeys.PLACE_ACTIVITY_SHARING)) {
            setHasOptionsMenu(true);
        }
        this.snackbarWrapper.view(this.coordinatorLayout).action(C7627R.string.dismiss, PlaceActivityPDPFragment$$Lambda$9.lambdaFactory$(this));
        this.controller = new PlaceActivityPDPController(this.navigationController, getContext());
        this.recyclerView.setAdapter(this.controller.getAdapter());
        this.recyclerView.setHasFixedSize(true);
        this.forYouMetaData = (ForYouMetaData) getArguments().getParcelable(ARG_FOR_YOU_METADATA);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (Trebuchet.launch(TrebuchetKeys.PLACE_RESY_SUPPORT)) {
            this.resyController.attachResySticky(PlaceActivityPDPFragment$$Lambda$10.lambdaFactory$(this));
        }
        this.placeJitneyLogger = new PlaceJitneyLogger(this.loggingContextFactory, this.resyController);
        long activityId = getArguments().getLong("activity_id", -1);
        boolean isMeetup = getArguments().getBoolean("is_meetup");
        String searchId = getArguments().getString("search_id");
        String searchSessionId = getArguments().getString("search_session_id");
        if (!TextUtils.isEmpty(searchId)) {
            this.searchContext = SearchJitneyUtils.searchContext(searchId, searchSessionId);
        }
        if (isMeetup) {
            this.placeJitneyLogger.meetupPDPView(activityId, this.searchContext);
        } else {
            this.placeJitneyLogger.activityPDPView(activityId, this.searchContext);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.activityModel == null || this.activityModel.getMeetup() != null) {
            fetchPlaceActivity();
        } else {
            onPlaceActivityReceived();
        }
        if (this.startLegacyAddToPlansOnResume) {
            startLegacyAddToPlansFlow();
        }
        setPickerFragmentListenerIfNeeded();
    }

    public void onDestroyView() {
        this.resyController.unbindListener();
        super.onDestroyView();
    }

    static /* synthetic */ void lambda$new$2(PlaceActivityPDPFragment placeActivityPDPFragment, PlaceActivityResponse response) {
        placeActivityPDPFragment.activityModel = response.getPlaceActivity();
        placeActivityPDPFragment.onPlaceActivityReceived();
    }

    static /* synthetic */ void lambda$new$5(PlaceActivityPDPFragment placeActivityPDPFragment, MeetupActivityResponse response) {
        placeActivityPDPFragment.activityModel = response.getMeetupActivity();
        placeActivityPDPFragment.onPlaceActivityReceived();
    }

    /* access modifiers changed from: private */
    public void fetchPlaceActivity() {
        long activityId = getArguments().getLong("activity_id", -1);
        boolean isMeetup = getArguments().getBoolean("is_meetup");
        Check.validId(activityId);
        if (isMeetup) {
            MeetupActivityRequest.forId(activityId).withListener((Observer) this.meetupActivityRequestListener).doubleResponse().execute(this.requestManager);
        } else {
            PlaceActivityRequest.forId(activityId).withListener((Observer) this.placeActivityRequestListener).doubleResponse().execute(this.requestManager);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != 1115) {
            return;
        }
        if (resultCode != -1) {
            this.placeJitneyLogger.addToPlansCancel((long) this.activityModel.getId());
        } else if (data.getBooleanExtra(PickAddToPlansFragment.EXTRA_PLANS_CUSTOM, false)) {
            this.startLegacyAddToPlansOnResume = true;
        } else if (data.getBooleanExtra(PickAddToPlansFragment.EXTRA_PLANS_GO_NOW, false)) {
            this.navigationController.goToMapApp(this.activityModel.getPlace());
        } else {
            this.addToItineraryDateTime = (AirDateTime) data.getParcelableExtra(PickAddToPlansFragment.EXTRA_PLANS_DATE_TIME);
            makeActivityReservation();
            this.placeJitneyLogger.addToPlansConfirm((long) this.activityModel.getId(), this.addToItineraryDateTime.getIsoDateString());
        }
    }

    @OnClick
    public void onAddToItineraryClick() {
        if (!Trebuchet.launch(TrebuchetKeys.PLACES_ADD_TO_PLANS) || !Experiments.enableAndroidAddToPlans()) {
            this.placeJitneyLogger.activityPDPClickAddItinerary((long) this.activityModel.getId());
            startLegacyAddToPlansFlow();
            return;
        }
        this.placeJitneyLogger.clickAddToPlansCTA((long) this.activityModel.getId());
        AirActivity activity = getAirActivity();
        activity.startActivityForResult(PlacesIntents.intentForPickAddToPlans(activity, this.activityModel), 1115);
    }

    private void startLegacyAddToPlansFlow() {
        NativeDatePickerFragment fragment = NativeDatePickerFragment.newInstance();
        fragment.setListener(this);
        fragment.show(getFragmentManager(), DATE_PICKER_FRAGMENT_TAG);
        this.startLegacyAddToPlansOnResume = false;
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        this.addToItineraryDateTime = new AirDateTime(year, month + 1, dayOfMonth, 0, 0, 0);
        NativeTimePickerFragment fragment = NativeTimePickerFragment.newInstance();
        fragment.setListener(this);
        fragment.show(getFragmentManager(), TIME_PICKER_FRAGMENT_TAG);
    }

    private void setPickerFragmentListenerIfNeeded() {
        NativeTimePickerFragment timePickerFragment = (NativeTimePickerFragment) getFragmentManager().findFragmentByTag(TIME_PICKER_FRAGMENT_TAG);
        if (timePickerFragment != null) {
            timePickerFragment.setListener(this);
        }
        NativeDatePickerFragment datePickerFragment = (NativeDatePickerFragment) getFragmentManager().findFragmentByTag(DATE_PICKER_FRAGMENT_TAG);
        if (datePickerFragment != null) {
            datePickerFragment.setListener(this);
        }
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.addToItineraryDateTime = this.addToItineraryDateTime.plusHours(hourOfDay).plusMinutes(minute);
        makeActivityReservation();
    }

    /* access modifiers changed from: private */
    public void makeActivityReservation() {
        String time = this.addToItineraryDateTime.getIsoDateString();
        this.actionFooter.setButtonLoading(true);
        this.flowActionFooter.setButtonLoading(true);
        ActivityReservationRequest.forReservation(this.activityModel.getPlace().getId(), (long) this.activityModel.getId(), time, time).withListener((Observer) this.reservationRequestListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$8(PlaceActivityPDPFragment placeActivityPDPFragment, ActivityReservationResponse response) {
        placeActivityPDPFragment.actionFooter.setButtonLoading(false);
        placeActivityPDPFragment.flowActionFooter.setButtonLoading(false);
        placeActivityPDPFragment.showSnackbar(C7627R.string.add_to_itinerary_success_title, C7627R.string.add_to_itinerary_success);
    }

    static /* synthetic */ void lambda$new$10(PlaceActivityPDPFragment placeActivityPDPFragment, AirRequestNetworkException e) {
        placeActivityPDPFragment.actionFooter.setButtonLoading(false);
        placeActivityPDPFragment.flowActionFooter.setButtonLoading(false);
        placeActivityPDPFragment.showErrorSnackbar(C7627R.string.error, PlaceActivityPDPFragment$$Lambda$16.lambdaFactory$(placeActivityPDPFragment));
    }

    /* access modifiers changed from: private */
    public void joinMeetup() {
        this.flowActionFooter.setButtonLoading(true);
        PlaceReservationRequest.forJoinMeetup((long) this.activityModel.getId()).withListener((Observer) this.joinMeetupRequestListener).execute(this.requestManager);
        this.placeJitneyLogger.meetupPDPClickJoin(this.activityModel.getPlace().getId());
    }

    static /* synthetic */ void lambda$new$11(PlaceActivityPDPFragment placeActivityPDPFragment, PlaceReservationResponse response) {
        placeActivityPDPFragment.flowActionFooter.setButtonLoading(false);
        placeActivityPDPFragment.formatButtonForMeetupReservation(response.getPlaceReservation().getId());
    }

    static /* synthetic */ void lambda$new$13(PlaceActivityPDPFragment placeActivityPDPFragment, AirRequestNetworkException e) {
        placeActivityPDPFragment.flowActionFooter.setButtonLoading(false);
        placeActivityPDPFragment.showErrorSnackbar(C7627R.string.error, PlaceActivityPDPFragment$$Lambda$15.lambdaFactory$(placeActivityPDPFragment));
    }

    /* access modifiers changed from: private */
    public void viewMeetupReservation(long scheduledMeetupId) {
        startActivity(ReactNativeIntents.intentForItineraryPlaceCard(getActivity(), scheduledMeetupId, "", ""));
    }

    private void showSnackbar(int messageTitle, int messageBody) {
        this.snackbarWrapper.title(getResources().getString(messageTitle), false).body(messageBody).buildAndShow();
    }

    /* access modifiers changed from: private */
    public void showErrorSnackbar(int messageTitle, OnClickListener retryAction) {
        this.snackbarWrapper.title(getResources().getString(messageTitle), true).body("").action(C7627R.string.retry, retryAction).buildAndShow();
    }

    /* access modifiers changed from: private */
    public void safeDismissSnackbar() {
        if (this.snackbarWrapper.isShown()) {
            this.snackbarWrapper.dismiss();
        }
    }

    private void onPlaceActivityReceived() {
        initButtons();
        this.controller.setData(this.activityModel, this.resyController.getResyState());
        Restaurant restaurant = this.activityModel.getRestaurant();
        if (Trebuchet.launch(TrebuchetKeys.PLACE_RESY_SUPPORT) && restaurant != null) {
            this.resyController.setPlaceDetails(restaurant, this.activityModel);
            formatFooterForRestaurant(restaurant);
        } else if (this.activityModel.getMeetup() != null) {
            formatFooterForMeetup();
        } else {
            formatFooter();
        }
        getActivity().supportInvalidateOptionsMenu();
    }

    private void formatFooter() {
        this.flowActionFooter.setTitle((CharSequence) this.activityModel.getActionRowTitle());
        this.flowActionFooter.setSubtitle((CharSequence) this.activityModel.getActionRowSubtitle());
        this.flowActionFooter.setButtonText(C7627R.string.add_to_itinerary);
        this.flowActionFooter.setButtonOnClickListener(PlaceActivityPDPFragment$$Lambda$11.lambdaFactory$(this));
    }

    private void formatFooterForRestaurant(Restaurant restaurant) {
        this.flowActionFooter.setTitle((CharSequence) restaurant.getName());
        this.flowActionFooter.setSubtitle(restaurant.getStarsString(getContext()));
        this.flowActionFooter.setButtonOnClickListener(PlaceActivityPDPFragment$$Lambda$12.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$formatFooterForRestaurant$15(PlaceActivityPDPFragment placeActivityPDPFragment, View v) {
        placeActivityPDPFragment.placeJitneyLogger.activityPDPClickMakeReservation((long) placeActivityPDPFragment.activityModel.getId());
        placeActivityPDPFragment.resyController.goToResyPage();
    }

    private void formatFooterForMeetup() {
        Meetup meetup = this.activityModel.getMeetup();
        this.flowActionFooter.setTitle((CharSequence) meetup.getTimeForDisplay());
        if (!meetup.isFuture()) {
            this.flowActionFooter.setSubtitle(C7627R.string.places_meetup_already_happened);
        } else if (meetup.hasReservation()) {
            this.flowActionFooter.setSubtitle(C7627R.string.places_meetup_joined_message);
        } else if (meetup.getGuestsAttending() > 0) {
            this.flowActionFooter.setSubtitle((CharSequence) getResources().getQuantityString(C7627R.plurals.places_meetup_rsvps, meetup.getGuestsAttending(), new Object[]{Integer.valueOf(meetup.getGuestsAttending())}));
        } else {
            this.flowActionFooter.setSubtitle((CharSequence) "");
        }
        if (meetup.hasReservation()) {
            formatButtonForMeetupReservation(meetup.getReservationId());
            return;
        }
        this.flowActionFooter.setButtonText(C7627R.string.places_meetup_join);
        this.flowActionFooter.setButtonOnClickListener(PlaceActivityPDPFragment$$Lambda$13.lambdaFactory$(this));
        this.flowActionFooter.setButtonEnabled(meetup.isFuture());
    }

    private void formatButtonForMeetupReservation(long scheduledMeetupId) {
        this.flowActionFooter.setButtonText(C7627R.string.places_meetup_details);
        this.flowActionFooter.setButtonOnClickListener(PlaceActivityPDPFragment$$Lambda$14.lambdaFactory$(this, scheduledMeetupId));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7627R.C7629id.menu_share) {
            return super.onOptionsItemSelected(item);
        }
        startActivity(ShareActivityIntents.newIntentForPlaceActivity(getContext(), this.activityModel, this.activityModel.getMeetup() != null));
        return true;
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem wishListItem = menu.findItem(C7627R.C7629id.menu_wish_list);
        if (wishListItem != null) {
            boolean canWishlistActivity = this.activityModel != null && this.activityModel.getMeetup() == null && Trebuchet.launch(TrebuchetKeys.PLACES_WISHLIST_ACTIVITY);
            wishListItem.setVisible(canWishlistActivity);
            if (canWishlistActivity) {
                ((WishListIcon) wishListItem.getActionView()).initIfNeeded(WishListableData.forPlaceActivity(this.activityModel).source(C2813WishlistSource.ActivityDetail).build());
            }
        }
    }

    private void initButtons() {
        getActiveButton().setVisibility(0);
    }

    private View getActiveButton() {
        Check.notNull(this.activityModel);
        if (showFlowActionFooter()) {
            return this.flowActionFooter;
        }
        if (Trebuchet.launch(TrebuchetKeys.PLACES_SPLIT_CTA)) {
            return this.flowActionFooter;
        }
        return this.actionFooter;
    }

    private boolean showFlowActionFooter() {
        return (Trebuchet.launch(TrebuchetKeys.PLACE_RESY_SUPPORT) && this.activityModel.getRestaurant() != null) || this.activityModel.getMeetup() != null;
    }
}
