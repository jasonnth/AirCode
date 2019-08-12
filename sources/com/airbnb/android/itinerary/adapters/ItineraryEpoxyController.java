package com.airbnb.android.itinerary.adapters;

import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import com.airbnb.android.core.viewcomponents.models.EpoxyControllerLoadingModel_;
import com.airbnb.android.itinerary.controllers.ItineraryDataController;
import com.airbnb.android.itinerary.controllers.ItineraryJitneyLogger;
import com.airbnb.android.itinerary.controllers.ItineraryNavigationController;
import com.airbnb.android.itinerary.controllers.ItineraryPerformanceAnalytics;
import com.airbnb.android.itinerary.data.models.BaseItineraryItem;
import com.airbnb.android.itinerary.data.models.FlightEntryPointItem;
import com.airbnb.android.itinerary.data.models.FreeTimeItem;
import com.airbnb.android.itinerary.data.models.TimelineTrip;
import com.airbnb.android.itinerary.data.models.TripEvent;
import com.airbnb.android.itinerary.utils.ItineraryUtils;
import com.airbnb.android.itinerary.viewmodels.FlightEntryPointEpoxyModel_;
import com.airbnb.android.itinerary.viewmodels.SuggestionsEpoxyModel_;
import com.airbnb.android.itinerary.viewmodels.TimelineTripEpoxyModel_;
import com.airbnb.android.itinerary.viewmodels.TripEventEpoxyModel_;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.p027n2.components.RecommendationRow.Recommendation;
import com.airbnb.p027n2.epoxy.Typed2AirEpoxyController;
import java.util.Collection;
import java.util.List;

public class ItineraryEpoxyController extends Typed2AirEpoxyController<List<BaseItineraryItem>, Boolean> {
    private static final int NUMBER_OF_ITEMS_FOR_EXTRA_PADDING = 2;
    private ItineraryDataController dataController;
    private String fragmentTag;
    private boolean isTimeline;
    private ItineraryJitneyLogger jitneyLogger;
    private ItineraryNavigationController navigationController;
    EpoxyControllerLoadingModel_ paginationLoadingModel;
    private ItineraryPerformanceAnalytics performanceAnalytics;
    private boolean showLoadingIndicator;
    private boolean showNowIndicator;
    private EpoxyModel<?> upcomingEpoxyModel;

    public ItineraryEpoxyController(String fragmentTag2, ItineraryDataController dataController2, ItineraryNavigationController navigationController2, ItineraryPerformanceAnalytics performanceAnalytics2, ItineraryJitneyLogger jitneyLogger2, boolean showLoadingIndicator2, boolean showNowIndicator2) {
        this.fragmentTag = fragmentTag2;
        this.dataController = dataController2;
        this.navigationController = navigationController2;
        this.performanceAnalytics = performanceAnalytics2;
        this.jitneyLogger = jitneyLogger2;
        this.showLoadingIndicator = showLoadingIndicator2;
        this.showNowIndicator = showNowIndicator2;
        this.isTimeline = fragmentTag2.equals(ItineraryNavigationController.FRAGMENT_TIMELINE_TAG);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            ((LinearLayoutManager) recyclerView.getLayoutManager()).setRecycleChildrenOnDetach(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> boundModel, int position, EpoxyModel<?> previouslyBoundModel) {
        super.onModelBound(holder, boundModel, position, previouslyBoundModel);
        if (boundModel.equals(this.paginationLoadingModel) && !this.dataController.isFetchingNextPageForTimelineTrips()) {
            this.dataController.fetchTimelineTripsFromNetwork(this.dataController.getTimelineTripCount());
            this.jitneyLogger.trackPaginationEvent(this.isTimeline, ItineraryJitneyLogger.PAGINATION_UP_DIRECTION);
        } else if (boundModel instanceof SuggestionsEpoxyModel_) {
            this.jitneyLogger.trackItineraryRecommendationImpressionItemEvent(this.isTimeline, ((SuggestionsEpoxyModel_) boundModel).freeTimeItem());
        }
    }

    public int getUpcomingItemPosition() {
        if (this.upcomingEpoxyModel != null) {
            return getAdapter().getModelPosition(this.upcomingEpoxyModel);
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void buildModels(List<BaseItineraryItem> list, Boolean hasNewContent) {
        BaseItineraryItem upcomingItem = this.showNowIndicator ? this.dataController.getUpcomingItem(list, this.isTimeline) : null;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            EpoxyModel<?> epoxyModel = buildItineraryItemModel((BaseItineraryItem) list.get(i), this.navigationController, isBottomItem(i, size), isTopItem(i, size), upcomingItem, isTopItemWithExtraPadding(i, size));
            if (epoxyModel != null) {
                add(epoxyModel);
            }
        }
        if (hasNewContent.booleanValue() && this.showLoadingIndicator) {
            add((EpoxyModel<?>) this.paginationLoadingModel);
        }
        this.performanceAnalytics.trackItineraryLoadSuccess(this.fragmentTag);
    }

    private boolean isBottomItem(int index, int size) {
        return (this.isTimeline && index == 0) || (!this.isTimeline && index == size + -1);
    }

    private boolean isTopItem(int index, int size) {
        return (this.isTimeline && index == size + -1) || (!this.isTimeline && index == 0);
    }

    private boolean isTopItemWithExtraPadding(int index, int size) {
        return isTopItem(index, size) && size <= 2;
    }

    private EpoxyModel<?> buildItineraryItemModel(BaseItineraryItem itineraryItem, ItineraryNavigationController navigationController2, boolean isBottomItem, boolean isTopItem, BaseItineraryItem upcomingItem, boolean showExtraHeaderPadding) {
        EpoxyModel<?> epoxyModel = null;
        boolean isUpcomingItem = itineraryItem.equals(upcomingItem);
        if (itineraryItem instanceof TimelineTrip) {
            epoxyModel = buildTimelineTripModel((TimelineTrip) itineraryItem, navigationController2, isBottomItem, isTopItem, isUpcomingItem, showExtraHeaderPadding);
        } else if (itineraryItem instanceof TripEvent) {
            epoxyModel = buildTripEventModel((TripEvent) itineraryItem, navigationController2, isBottomItem, isTopItem, isUpcomingItem, showExtraHeaderPadding);
        } else if (itineraryItem instanceof FreeTimeItem) {
            epoxyModel = buildSuggestionsModel((FreeTimeItem) itineraryItem);
        } else if (itineraryItem instanceof FlightEntryPointItem) {
            epoxyModel = buildFlightEntryPointModel((FlightEntryPointItem) itineraryItem);
        }
        if (itineraryItem.equals(upcomingItem)) {
            this.upcomingEpoxyModel = epoxyModel;
        }
        return epoxyModel;
    }

    private TimelineTripEpoxyModel_ buildTimelineTripModel(TimelineTrip timelineTrip, ItineraryNavigationController navigationController2, boolean isBottomItem, boolean isTopItem, boolean isUpcomingItem, boolean showExtraHeaderPadding) {
        return new TimelineTripEpoxyModel_().m4272id((CharSequence) timelineTrip.confirmation_code()).timelineTrip(timelineTrip).isBottomItem(isBottomItem).isUpcomingItem(isUpcomingItem).showHeaderPadding(isTopItem).showExtraHeaderPadding(showExtraHeaderPadding).clickListener(ItineraryEpoxyController$$Lambda$1.lambdaFactory$(navigationController2, timelineTrip, isUpcomingItem));
    }

    private TripEventEpoxyModel_ buildTripEventModel(TripEvent tripEvent, ItineraryNavigationController navigationController2, boolean isBottomItem, boolean isTopItem, boolean isUpcomingItem, boolean showExtraHeaderPadding) {
        return new TripEventEpoxyModel_().m4284id((CharSequence) tripEvent.primary_key()).tripEvent(tripEvent).isBottomItem(isBottomItem).isTimeline(this.isTimeline).isUpcomingItem(isUpcomingItem).showHeaderPadding(isTopItem).showExtraHeaderPadding(showExtraHeaderPadding).cardClickListener(ItineraryEpoxyController$$Lambda$2.lambdaFactory$(this, navigationController2, tripEvent)).actionClickListener(ItineraryEpoxyController$$Lambda$3.lambdaFactory$(this, navigationController2, tripEvent));
    }

    private SuggestionsEpoxyModel_ buildSuggestionsModel(FreeTimeItem freeTimeItem) {
        List<List<Recommendation>> recommendations = ItineraryUtils.getRecommendationLists(freeTimeItem, this.navigationController);
        if (ListUtils.isEmpty((Collection<?>) recommendations)) {
            return null;
        }
        return new SuggestionsEpoxyModel_().m4260id((CharSequence) freeTimeItem.confirmationCode()).freeTimeItem(freeTimeItem).recommendations(recommendations);
    }

    private FlightEntryPointEpoxyModel_ buildFlightEntryPointModel(FlightEntryPointItem flightEntryPointItem) {
        return new FlightEntryPointEpoxyModel_().m4248id((CharSequence) flightEntryPointItem.mo57128id()).flightEntryPointItem(flightEntryPointItem).acceptClickListener(ItineraryEpoxyController$$Lambda$4.lambdaFactory$(this)).dismissClickListener(ItineraryEpoxyController$$Lambda$5.lambdaFactory$(this, flightEntryPointItem));
    }
}
