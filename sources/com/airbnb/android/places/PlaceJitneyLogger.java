package com.airbnb.android.places;

import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.android.core.models.RestaurantAvailability;
import com.airbnb.android.places.adapters.TimeOfDayController.TimeOfDay;
import com.airbnb.jitney.event.logging.ActivityPDP.p035v1.ActivityPDPClickMakeAReservationEvent;
import com.airbnb.jitney.event.logging.ActivityPDP.p035v1.ActivityPDPClickResyChangeEvent;
import com.airbnb.jitney.event.logging.ActivityPDP.p035v1.ActivityPDPClickTimeSlotEvent;
import com.airbnb.jitney.event.logging.ActivityPDP.p035v1.ActivityPDPLoadResyTimeSlotsEvent;
import com.airbnb.jitney.event.logging.AddRemoveMethod.p036v1.C1796AddRemoveMethod;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreClickMtPdpElementEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreViewMtPdpEvent;
import com.airbnb.jitney.event.logging.Guidebook.p109v1.GuidebookClickActivityPdpAddItineraryEvent;
import com.airbnb.jitney.event.logging.Guidebook.p109v1.GuidebookClickPlacePdpMeetupJoinEvent;
import com.airbnb.jitney.event.logging.Guidebook.p109v1.GuidebookViewActivityPdpEvent.Builder;
import com.airbnb.jitney.event.logging.MtPdpReferrer.p157v1.C2443MtPdpReferrer;
import com.airbnb.jitney.event.logging.MtProduct.p158v1.C2444MtProduct;
import com.airbnb.jitney.event.logging.PlacePdpType.p201v2.C2560PlacePdpType;
import com.airbnb.jitney.event.logging.Resy.p228v1.ResyChangeGuestCountEvent;
import com.airbnb.jitney.event.logging.Resy.p228v1.ResyClickChangeDateEvent;
import com.airbnb.jitney.event.logging.Resy.p228v1.ResyClickTimeSlotEvent;
import com.airbnb.jitney.event.logging.Resy.p228v1.ResyViewResyPageEvent;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.TimeSlot.p266v1.C2755TimeSlot;
import com.airbnb.jitney.event.logging.TimeSlotInfo.p267v1.C2757TimeSlotInfo;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceJitneyLogger extends BaseLogger {
    private final ResyController resyController;

    public PlaceJitneyLogger(LoggingContextFactory loggingContextFactory, ResyController resyController2) {
        super(loggingContextFactory);
        this.resyController = resyController2;
    }

    public PlaceJitneyLogger(LoggingContextFactory loggingContextFactory) {
        this(loggingContextFactory, null);
    }

    public void activityPDPView(long activityId, C2731SearchContext searchContext) {
        publish(new Builder(context(), Long.valueOf(activityId)));
        ExploreViewMtPdpEvent.Builder builder2 = new ExploreViewMtPdpEvent.Builder(context(), Long.valueOf(activityId), C2444MtProduct.Activity, C2443MtPdpReferrer.Unknown);
        builder2.search_context(searchContext);
        publish(builder2);
    }

    public void clickAddToPlansCTA(long activityId) {
        ExploreClickMtPdpElementEvent.Builder builder = new ExploreClickMtPdpElementEvent.Builder(context(), "mt-pdp", Long.valueOf(activityId), C2444MtProduct.Activity, "activityPdp.addToPlans");
        builder.section("cta");
        publish(builder);
    }

    public void addToPlansGoNowClick(long activityId, int positionInCarousel) {
        activityPDPAddToPlansClick(activityId, "activityPdp.goNow", Long.valueOf((long) positionInCarousel), null);
    }

    public void addToPlansCustomDateClick(long activityId, int positionInCarousel) {
        activityPDPAddToPlansClick(activityId, "activityPdp.pickCustomDate", Long.valueOf((long) positionInCarousel), null);
    }

    public void addToPlansDateClick(long activityId, int positionInCarousel, AirDate date) {
        Map<String, String> pdpContext = new HashMap<>();
        pdpContext.put("date", date.getIsoDateString());
        activityPDPAddToPlansClick(activityId, "activityPdp.providedDate", Long.valueOf((long) positionInCarousel), pdpContext);
    }

    public void addToPlansTimeClick(long activityId, int positionInCarousel, TimeOfDay timeOfDay) {
        Map<String, String> pdpContext = new HashMap<>();
        pdpContext.put("timeOfDay", timeOfDay.name());
        pdpContext.put("hourOffset", String.valueOf(timeOfDay.hour));
        activityPDPAddToPlansClick(activityId, "activityPdp.providedTime", Long.valueOf((long) positionInCarousel), pdpContext);
    }

    public void addToPlansCancel(long activityId) {
        activityPDPAddToPlansClick(activityId, "activityPdp.cancelAddToPlans", null, null);
    }

    public void addToPlansConfirm(long activityId, String dateTimeString) {
        Map<String, String> pdpContext = new HashMap<>();
        pdpContext.put("dateTime", dateTimeString);
        activityPDPAddToPlansClick(activityId, "activityPdp.confirmAddToPlans", null, pdpContext);
    }

    private void activityPDPAddToPlansClick(long activityId, String target, Long position, Map<String, String> pdpContext) {
        ExploreClickMtPdpElementEvent.Builder builder = new ExploreClickMtPdpElementEvent.Builder(context(), "mt-pdp", Long.valueOf(activityId), C2444MtProduct.Activity, target);
        builder.section("addToPlans");
        builder.position(position);
        builder.pdp_context(pdpContext);
        publish(builder);
    }

    public void carouselRecommendedItemClick(long activityId, RecommendationItem item, int recommendedItemsCount, int position) {
        C2444MtProduct loggingType = item.getItemType().loggingType;
        Map<String, String> pdpContext = new HashMap<>();
        pdpContext.put("relatedItemsCount", String.valueOf(recommendedItemsCount));
        pdpContext.put("mtPdpTargetType", loggingType != null ? loggingType.toString() : "");
        pdpContext.put("mtPdpTargetId", String.valueOf(item.getId()));
        ExploreClickMtPdpElementEvent.Builder builder = new ExploreClickMtPdpElementEvent.Builder(context(), "mt-pdp", Long.valueOf(activityId), C2444MtProduct.Activity, "activityPdp.relatedActivities");
        builder.section("relatedActivities");
        builder.position(Long.valueOf((long) position));
        builder.pdp_context(pdpContext);
        publish(builder);
    }

    public void meetupPDPView(long meetupId, C2731SearchContext searchContext) {
        ExploreViewMtPdpEvent.Builder builder = new ExploreViewMtPdpEvent.Builder(context(), Long.valueOf(meetupId), C2444MtProduct.Meetup, C2443MtPdpReferrer.Unknown);
        builder.search_context(searchContext);
        publish(builder);
    }

    public void meetupPDPClickJoin(long placeId) {
        publish(new GuidebookClickPlacePdpMeetupJoinEvent.Builder(context(), Long.valueOf(placeId), C2560PlacePdpType.General));
    }

    public void activityPDPClickAddItinerary(long activityId) {
        publish(new GuidebookClickActivityPdpAddItineraryEvent.Builder(context(), Long.valueOf(activityId)));
    }

    public void activityPDPLoadResy() {
        ResyState resyState = this.resyController.getResyState();
        long activityId = resyState.activityId();
        List<RestaurantAvailability> timeSlots = resyState.timeSlots();
        AirDate date = resyState.date();
        long guests = (long) resyState.guests();
        publish(new ActivityPDPLoadResyTimeSlotsEvent.Builder(context(), Long.valueOf(activityId), FluentIterable.from((Iterable<E>) timeSlots).transform(PlaceJitneyLogger$$Lambda$1.lambdaFactory$(this)).toList(), date.getIsoDateString(), Long.valueOf(guests)));
    }

    public void activityPDPClickResyChange() {
        publish(new ActivityPDPClickResyChangeEvent.Builder(context(), Long.valueOf(this.resyController.getResyState().activityId())));
    }

    public void activityPDPClickMakeReservation(long activityId) {
        publish(new ActivityPDPClickMakeAReservationEvent.Builder(context(), Long.valueOf(activityId)));
    }

    public void activityPDPClickResyTimeSlot() {
        ResyState resyState = this.resyController.getResyState();
        long activityId = resyState.activityId();
        publish(new ActivityPDPClickTimeSlotEvent.Builder(context(), Long.valueOf(activityId), getTimeSlotInfo(resyState)));
    }

    public void resyPageView() {
        ResyState resyState = this.resyController.getResyState();
        long activityId = resyState.activityId();
        List<RestaurantAvailability> timeSlots = resyState.timeSlots();
        AirDate date = resyState.date();
        long guests = (long) resyState.guests();
        publish(new ResyViewResyPageEvent.Builder(context(), Long.valueOf(activityId), FluentIterable.from((Iterable<E>) timeSlots).transform(PlaceJitneyLogger$$Lambda$2.lambdaFactory$(this)).toList(), date.getIsoDateString(), Long.valueOf(guests)));
    }

    public void resyPageClickChangeDates(long activityId) {
        publish(new ResyClickChangeDateEvent.Builder(context(), Long.valueOf(activityId)));
    }

    public void resyPageChangeGuestCount(long activityId, boolean add) {
        publish(new ResyChangeGuestCountEvent.Builder(context(), add ? C1796AddRemoveMethod.Add : C1796AddRemoveMethod.Remove, Long.valueOf(activityId)));
    }

    public void resyPageClickTimeSlot() {
        ResyState resyState = this.resyController.getResyState();
        long activityId = resyState.activityId();
        AirDate date = resyState.date();
        long guests = (long) resyState.guests();
        publish(new ResyClickTimeSlotEvent.Builder(context(), Long.valueOf(activityId), getTimeSlotInfo(resyState), date.getIsoDateString(), Long.valueOf(guests)));
    }

    /* access modifiers changed from: private */
    public C2755TimeSlot jitneyfy(RestaurantAvailability timeSlot) {
        return new C2755TimeSlot.Builder(timeSlot.getStartsAtInRestaurantTimezone().getIsoDateString(), Boolean.valueOf(timeSlot.hasConfirmationMessages())).build();
    }

    private C2757TimeSlotInfo getTimeSlotInfo(ResyState resyState) {
        List<RestaurantAvailability> timeSlots = resyState.timeSlots();
        RestaurantAvailability selectedTimeSlot = resyState.selectedTime();
        ArrayList<C2755TimeSlot> jitneyTimeSlots = new ArrayList<>();
        long selectedIndex = -1;
        for (int i = 0; i < timeSlots.size(); i++) {
            RestaurantAvailability timeSlot = (RestaurantAvailability) timeSlots.get(i);
            if (timeSlot.equals(selectedTimeSlot)) {
                selectedIndex = (long) i;
            }
            jitneyTimeSlots.add(jitneyfy(timeSlot));
        }
        return new C2757TimeSlotInfo.Builder(jitneyTimeSlots, jitneyfy(selectedTimeSlot), Long.valueOf(selectedIndex)).build();
    }
}
