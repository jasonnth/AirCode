package com.airbnb.android.itinerary.controllers;

import android.text.TextUtils;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.android.itinerary.data.models.FreeTimeItem;
import com.airbnb.android.itinerary.data.models.Suggestion;
import com.airbnb.android.itinerary.data.models.SuggestionType;
import com.airbnb.android.itinerary.data.models.TripEvent;
import com.airbnb.android.itinerary.data.models.TripEventCardType;
import com.airbnb.jitney.event.logging.Direction.p012v1.C0940Direction;
import com.airbnb.jitney.event.logging.Itinerary.p018v1.ItineraryClickStartExploringEvent.Builder;
import com.airbnb.jitney.event.logging.Itinerary.p018v1.ItineraryImpressionItemEvent;
import com.airbnb.jitney.event.logging.Itinerary.p018v1.ItineraryImpressionOverviewEvent;
import com.airbnb.jitney.event.logging.Itinerary.p018v1.ItineraryPaginationEvent;
import com.airbnb.jitney.event.logging.Itinerary.p019v2.ItineraryClickActionButtonEvent;
import com.airbnb.jitney.event.logging.Itinerary.p019v2.ItineraryClickCardItemEvent;
import com.airbnb.jitney.event.logging.Itinerary.p019v2.ItineraryClickItineraryRecommendationsCarouselEvent;
import com.airbnb.jitney.event.logging.Itinerary.p019v2.ItineraryClickToReviewEvent;
import com.airbnb.jitney.event.logging.Itinerary.p020v4.ItineraryClickItineraryEvent;
import com.airbnb.jitney.event.logging.SchedulableInfo.p023v2.C0973SchedulableInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ItineraryJitneyLogger extends BaseLogger {
    public static final String CLICK_ACTION_BUTTON_TARGET_SECONDARY_ACTION_DIRECTIONS = "secondary_action_directions";
    private static final String CLICK_CARD_ITEM_TARGET_CARD = "Card";
    public static final int PAGINATION_UP_DIRECTION = C0940Direction.Up.value;
    private static final String RESERVATION_GROUP_PAGE = "t1";
    private static final String SUGGESTION_IMMERSION_OR_EXPERIENCE_TYPE = "mt_template";
    private static final String SUGGESTION_PLACE_CARD_TYPE = "place";
    private static final String TIMELINE_PAGE = "t0";
    private static final String TIMELINE_TRIP_TRIP_SCHEDULE_TYPE = "mt_trip_schedule";
    private static final String TRIP_EVENT_EXPERIENCE_CARD_TYPE = "experience_reservation";
    private static final String TRIP_EVENT_HOME_CARD_TYPE = "reservation2";
    private static final String TRIP_EVENT_PLACE_CARD_TYPE = "place_reservation";

    @Retention(RetentionPolicy.SOURCE)
    public @interface ItineraryPageName {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SuggestionSchedulableType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TripEventSchedulableType {
    }

    public ItineraryJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void trackClickStartExploringEvent() {
        publish(new Builder(context()));
    }

    public void trackItineraryImpressionOverviewEvent() {
        publish(new ItineraryImpressionOverviewEvent.Builder(context()));
    }

    public void trackClickItineraryEvent(String confirmationCode) {
        publish(new ItineraryClickItineraryEvent.Builder(context(), getTimelineTripSchedulableInfo(confirmationCode)));
    }

    public void trackClickCardItem(boolean isTimeline, TripEvent tripEvent) {
        publish(new ItineraryClickCardItemEvent.Builder(context(), getItineraryPage(isTimeline), CLICK_CARD_ITEM_TARGET_CARD, getTripEventSchedulableInfo(tripEvent), getTimelineTripSchedulableInfo(tripEvent.schedule_confirmation_code())));
    }

    public void trackClickToReviewEvent(boolean isTimeline, TripEvent tripEvent, Long reviewId) {
        publish(new ItineraryClickToReviewEvent.Builder(context(), getItineraryPage(isTimeline), getTripEventSchedulableInfo(tripEvent), reviewId));
    }

    public void trackClickActionButton(boolean isTimeline, TripEvent tripEvent, String target) {
        publish(new ItineraryClickActionButtonEvent.Builder(context(), getItineraryPage(isTimeline), target, getTripEventSchedulableInfo(tripEvent)));
    }

    public void trackPaginationEvent(boolean isTimeline, int direction) {
        publish(new ItineraryPaginationEvent.Builder(context(), getItineraryPage(isTimeline)).direction(getPaginationDirection(direction)));
    }

    public void trackItineraryRecommendationImpressionItemEvent(boolean isTimeline, FreeTimeItem freeTimeItem) {
        if (freeTimeItem != null && freeTimeItem.suggestions() != null) {
            for (int i = 0; i < Math.min(4, freeTimeItem.suggestions().size()); i++) {
                trackItineraryRecommendationImpressionItemEvent(isTimeline, (Suggestion) freeTimeItem.suggestions().get(i), freeTimeItem.confirmationCode());
            }
        }
    }

    public void trackItineraryRecommendationImpressionItemEvent(boolean isTimeline, Suggestion suggestion, String confirmationCode) {
        publish(new ItineraryImpressionItemEvent.Builder(context(), getItineraryPage(isTimeline), getSuggestionSchedulableInfo(suggestion)).parent_schedulable_info(getTimelineTripSchedulableInfo(confirmationCode)));
    }

    public void trackClickItineraryRecommendationsEvent(Suggestion suggestion, String confirmationCode) {
        publish(new ItineraryClickItineraryRecommendationsCarouselEvent.Builder(context(), getSuggestionSchedulableInfo(suggestion), getTimelineTripSchedulableInfo(confirmationCode)));
    }

    public String getItineraryPage(boolean isTimeline) {
        return isTimeline ? TIMELINE_PAGE : RESERVATION_GROUP_PAGE;
    }

    private String getLoggingCardType(TripEventCardType cardType) {
        switch (cardType) {
            case Checkin:
            case Checkout:
                return TRIP_EVENT_HOME_CARD_TYPE;
            case Experience:
                return TRIP_EVENT_EXPERIENCE_CARD_TYPE;
            case Place:
                return TRIP_EVENT_PLACE_CARD_TYPE;
            default:
                return "";
        }
    }

    private String getLoggingCardType(SuggestionType suggestionType) {
        switch (suggestionType) {
            case Immersion:
            case Experience:
                return SUGGESTION_IMMERSION_OR_EXPERIENCE_TYPE;
            case Place:
                return SUGGESTION_PLACE_CARD_TYPE;
            default:
                return "";
        }
    }

    private C0973SchedulableInfo getSuggestionSchedulableInfo(Suggestion suggestion) {
        return new C0973SchedulableInfo.Builder(getLoggingCardType(suggestion.type()), Long.toString(suggestion.mo10321id())).build();
    }

    private C0973SchedulableInfo getTripEventSchedulableInfo(TripEvent tripEvent) {
        String id = TextUtils.isEmpty(tripEvent.confirmation_code()) ? tripEvent.mo10245id() == null ? "" : tripEvent.mo10245id().toString() : tripEvent.confirmation_code();
        return new C0973SchedulableInfo.Builder(getLoggingCardType(tripEvent.card_type()), id).build();
    }

    private C0973SchedulableInfo getTimelineTripSchedulableInfo(String confirmationCode) {
        return new C0973SchedulableInfo.Builder(TIMELINE_TRIP_TRIP_SCHEDULE_TYPE, confirmationCode).build();
    }

    private C0940Direction getPaginationDirection(int direction) {
        return C0940Direction.findByValue(direction);
    }
}
