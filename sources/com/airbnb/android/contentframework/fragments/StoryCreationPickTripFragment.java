package com.airbnb.android.contentframework.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.contentframework.ContentFrameworkBindings;
import com.airbnb.android.contentframework.ContentFrameworkComponent.Builder;
import com.airbnb.android.contentframework.adapters.StoryCreationPickTripController;
import com.airbnb.android.contentframework.adapters.StoryCreationPickTripController.Delegate;
import com.airbnb.android.contentframework.data.StoryCreationListingAppendix;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.GuestReservationsRequest;
import com.airbnb.android.core.responses.GuestReservationsResponse;
import com.airbnb.p027n2.components.AirToolbar;
import p032rx.Observer;

public class StoryCreationPickTripFragment extends AirFragment implements Delegate {
    private static final int RC_COMPOSER = 131;
    private static final int TRIPS_ON_FIRST_LOAD = 3;
    private static final int TRIPS_ON_SUBSEQUENT_LOAD = 20;
    @BindView
    View emptyView;
    PerformanceLogger performanceLogger;
    private StoryCreationPickTripController pickTripController;
    @BindView
    RecyclerView recyclerView;
    final RequestListener<GuestReservationsResponse> reservationsResponseRequestListener = new C0699RL().onResponse(StoryCreationPickTripFragment$$Lambda$1.lambdaFactory$(this)).onError(StoryCreationPickTripFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirToolbar toolbar;

    public static Intent newIntent(Context context) {
        return AutoFragmentActivity.create(context, StoryCreationPickTripFragment.class).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((ContentFrameworkBindings) CoreApplication.instance(getContext()).componentProvider()).contentFrameworkComponentProvider().get()).build().inject(this);
        this.pickTripController = new StoryCreationPickTripController(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5709R.layout.fragment_story_creation_pick_trip, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.pickTripController.requestModelBuild();
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.pickTripController.getAdapter());
        GuestReservationsRequest.forStoriesCreationTripPicker(this.mAirbnbApi, 0, 3, this.mAccountManager.getCurrentUserId()).withListener((Observer) this.reservationsResponseRequestListener).execute(this.requestManager);
        return view;
    }

    static /* synthetic */ void lambda$new$0(StoryCreationPickTripFragment storyCreationPickTripFragment, GuestReservationsResponse guestReservationsResponse) {
        if (guestReservationsResponse.reservations.size() == 0 && storyCreationPickTripFragment.pickTripController.getReservationCount() == 0) {
            ContentFrameworkAnalytics.trackEmptyReservationViewOnTripPicker();
            storyCreationPickTripFragment.recyclerView.setVisibility(8);
            storyCreationPickTripFragment.emptyView.setVisibility(0);
            return;
        }
        storyCreationPickTripFragment.pickTripController.appendReservations(guestReservationsResponse.reservations, guestReservationsResponse.metadata.hasNextPage());
    }

    static /* synthetic */ void lambda$new$1(StoryCreationPickTripFragment storyCreationPickTripFragment, AirRequestNetworkException e) {
        Toast.makeText(storyCreationPickTripFragment.getContext(), C5709R.string.error_request, 1).show();
        storyCreationPickTripFragment.getActivity().finish();
    }

    public void onReservationSelected(Reservation reservation) {
        ContentFrameworkAnalytics.trackReservationSelectedOnTripPicker(reservation);
        startActivityForResult(StoryCreationComposerFragment.newIntent(getContext(), StoryCreationListingAppendix.fromReservation(reservation)), 131);
    }

    public void onShowMoreReservationsClicked() {
        ContentFrameworkAnalytics.trackSeeMoreClickedOnTripPicker();
        this.pickTripController.setLoading(true);
        GuestReservationsRequest.forStoriesCreationTripPicker(this.mAirbnbApi, this.pickTripController.getReservationCount(), 20, this.mAccountManager.getCurrentUserId()).withListener((Observer) this.reservationsResponseRequestListener).execute(this.requestManager);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 131) {
            getActivity().finish();
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.StoryTripPicker;
    }
}
