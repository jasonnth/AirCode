package com.airbnb.android.lib.postbooking;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.core.viewcomponents.models.PosterCardEpoxyModel_;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.explore.adapters.MTExperiencesCarouselAdapter;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.react.ReactNativeUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.collections.Carousel.OnSnapToPositionListener;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.transitions.AutoSharedElementCallback;
import java.util.ArrayList;
import java.util.List;

public class PostBookConfirmAndUpsellFragment extends PostBookingBaseFragment {
    @BindView
    DocumentMarquee headerMarquee;
    /* access modifiers changed from: private */
    public PostBookLogger logger;
    private final OnSnapToPositionListener onSnapToPositionListener = new OnSnapToPositionListener() {
        public void onSnappedToPosition(int position, boolean userSwipedLeft, boolean autoScroll) {
            PostBookConfirmAndUpsellFragment.this.logger.trackUpsellScroll(userSwipedLeft);
        }
    };
    @BindView
    Carousel tripsCarousel;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.logger = new PostBookLogger(this.postBookingFlowController);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_post_booking_confirm_upsell, container, false);
        bindViews(view);
        updateHeader();
        updateCarousel();
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.P5TripsUpsellPage;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mix(this.logger.tripsUpsellParams());
    }

    @OnClick
    public void onSkip() {
        this.postBookingFlowController.onCurrentStateFinished();
    }

    private void updateHeader() {
        this.headerMarquee.setTitle((CharSequence) getString(C0880R.string.post_booking_trips_upsell_title, this.postBookingFlowController.getReservation().getListing().getCity()));
        this.headerMarquee.setCaption((CharSequence) getString(this.postBookingFlowController.getTripSuggestions().size() > 1 ? C0880R.string.post_booking_trips_upsell_subtitle_multi : C0880R.string.post_booking_trips_upsell_subtitle_single));
    }

    private void updateCarousel() {
        MTExperiencesCarouselAdapter adapter = new MTExperiencesCarouselAdapter(new PosterCardEpoxyModel_().loading(false));
        adapter.setModels(getTripPostCardModels(this.postBookingFlowController.getTripSuggestions()));
        this.tripsCarousel.setAdapter(adapter);
        this.tripsCarousel.setSnapToPositionListener(this.onSnapToPositionListener);
    }

    private List<EpoxyModel<?>> getTripPostCardModels(List<TripTemplate> trips) {
        List<EpoxyModel<?>> tripModels = new ArrayList<>(trips.size());
        for (TripTemplate tripTemplate : trips) {
            tripModels.add(new PosterCardEpoxyModel_().tripTemplate(tripTemplate).wishListableData(WishListableData.forTrip(tripTemplate).source(C2813WishlistSource.P5).checkIn(this.postBookingFlowController.getReservation().getCheckinDate()).checkOut(this.postBookingFlowController.getReservation().getCheckoutDate()).guestDetails(this.postBookingFlowController.getReservation().getGuestDetails()).build()).clickListener(PostBookConfirmAndUpsellFragment$$Lambda$1.lambdaFactory$(this, tripTemplate, trips)).displayOptions(DisplayOptions.forPosterCard(getActivity(), DisplayType.Horizontal)).m5305id((CharSequence) "trip", tripTemplate.getId()));
        }
        return tripModels;
    }

    static /* synthetic */ void lambda$getTripPostCardModels$0(PostBookConfirmAndUpsellFragment postBookConfirmAndUpsellFragment, TripTemplate tripTemplate, List trips, View v) {
        postBookConfirmAndUpsellFragment.logger.trackTapOnAnUpsell(tripTemplate, trips.indexOf(tripTemplate));
        postBookConfirmAndUpsellFragment.launchTripPDP(tripTemplate);
    }

    private void launchTripPDP(TripTemplate tripTemplate) {
        Intent intent = ReactNativeIntents.intentForExperiencePDP((Context) getActivity(), tripTemplate.isImmersion(), tripTemplate.getId());
        ReactNativeUtils.setHasSharedElementTransition(intent, true);
        getActivity().startActivity(intent, AutoSharedElementCallback.getActivityOptionsBundle(getActivity(), getView()));
    }
}
