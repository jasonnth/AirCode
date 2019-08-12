package com.airbnb.android.itinerary.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View.OnClickListener;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.TimelineMetadata;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.controllers.ItineraryNavigationController;
import com.airbnb.android.itinerary.data.models.BaseItineraryItem;
import com.airbnb.android.itinerary.data.models.FreeTimeItem;
import com.airbnb.android.itinerary.data.models.Suggestion;
import com.airbnb.android.itinerary.data.models.TimelineTrip;
import com.airbnb.android.itinerary.data.models.TripEvent;
import com.airbnb.android.itinerary.data.models.TripEventCardType;
import com.airbnb.android.itinerary.data.models.TripEventSecondaryAction;
import com.airbnb.android.itinerary.fragments.ItineraryParentFragment;
import com.airbnb.android.itinerary.views.ItineraryItemView.HeaderPaddingType;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.RecommendationRow.CardType;
import com.airbnb.p027n2.components.RecommendationRow.Recommendation;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItineraryUtils {
    private static final String GOOGLE_MAPS_URL = "http://maps.google.com/maps?q=";
    public static final int INVALID_RES_ID = -1;
    private static final String LAT_LNG_DELIMITER = ",";
    public static final int MAX_NUM_TOTAL_SUGGESTIONS = 4;
    private static final int MIN_NUM_SUGGESTIONS = 2;
    private static final String PENDING_IS_VERIFICATION_PENDING_PARAM = "isVerificationPending";
    private static final String PENDING_NEED_VERIFICATION_PARAM = "needVerification";
    private static final String PENDING_SOONEST_EXPIRES_AT_PARAM = "soonestExpiresAt";
    private static final String PENDING_SOONEST_EXPIRES_AT_TIMEZONE_PARAM = "soonestExpiresAtTimeZone";
    private static final String PENDING_SOONEST_EXPIRES_TITLE_PARAM = "soonestExpiresTitle";
    private static final String PENDING_TOS_PARAM = "needMtTOS";
    private static final String TIME_FROM_NOW_AND_DATE_RANGE_SEPARATOR = " · ";

    public static boolean isSingleHome(List<TripEvent> tripEvents) {
        return tripEvents != null && tripEvents.size() == 2 && ((TripEvent) tripEvents.get(0)).isHomeCard() && ((TripEvent) tripEvents.get(1)).isHomeCard();
    }

    public static List<BaseItineraryItem> getBaseItineraryItemList(List<? extends BaseItineraryItem> list) {
        return FluentIterable.from((Iterable<E>) list).filter(BaseItineraryItem.class).toList();
    }

    public static Intent getItineraryIntent(Context context) {
        if (FeatureToggles.showNativeItinerary()) {
            return TransparentActionBarActivity.intentForFragment(context, ItineraryParentFragment.instance(ItineraryParentFragment.PARAM_TIMELINE));
        }
        return ReactNativeIntents.intentForItinerary(context);
    }

    public static double[] getLatLng(String latLng) {
        String[] splitLatLng = latLng.split(LAT_LNG_DELIMITER);
        if (splitLatLng.length != 2 || TextUtils.isEmpty(splitLatLng[0]) || TextUtils.isEmpty(splitLatLng[1])) {
            return null;
        }
        return new double[]{Double.parseDouble(splitLatLng[0]), Double.parseDouble(splitLatLng[1])};
    }

    public static int getTimelineColor(Context context, BaseItineraryItem itineraryItem, boolean isTimeline) {
        return ContextCompat.getColor(context, isDuringOrUpcoming(itineraryItem, isTimeline) ? C5755R.color.n2_divider_color : C5755R.color.n2_babu);
    }

    public static int getTimelineColor(Context context, boolean isDuringOrUpcoming) {
        return ContextCompat.getColor(context, isDuringOrUpcoming ? C5755R.color.n2_divider_color : C5755R.color.n2_babu);
    }

    public static boolean isDuringOrUpcoming(BaseItineraryItem itineraryItem, boolean isTimeline) {
        AirDateTime now = AirDateTime.now();
        if (itineraryItem.getStartsAt().isAfter(now)) {
            return true;
        }
        if (!isTimeline && (itineraryItem instanceof TripEvent) && ((TripEvent) itineraryItem).isCheckinCard()) {
            return false;
        }
        return itineraryItem.getEndsAt() != null && itineraryItem.getEndsAt().isAfter(now);
    }

    public static Bundle getVerificationBundleForPendingScreen(TimelineMetadata timelineMetadata) {
        return ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putBoolean(PENDING_IS_VERIFICATION_PENDING_PARAM, timelineMetadata.isVerificationPending().booleanValue())).putBoolean(PENDING_TOS_PARAM, timelineMetadata.isNeedMtTos().booleanValue())).putBoolean(PENDING_NEED_VERIFICATION_PARAM, timelineMetadata.isNeedVerification().booleanValue())).putString(PENDING_SOONEST_EXPIRES_AT_PARAM, timelineMetadata.getSoonestExpiresAt() == null ? null : timelineMetadata.getSoonestExpiresAt().getIsoDateStringUTC())).putString(PENDING_SOONEST_EXPIRES_AT_TIMEZONE_PARAM, timelineMetadata.getSoonestExpiresAtTimeZone())).putString(PENDING_SOONEST_EXPIRES_TITLE_PARAM, timelineMetadata.getSoonestExpiresTitle())).toBundle();
    }

    public static String getHeader(BaseItineraryItem item, boolean isTimeline) {
        if (item instanceof TimelineTrip) {
            return ((TimelineTrip) item).title();
        }
        if (!(item instanceof TripEvent)) {
            throw new IllegalStateException("ItineraryItem was neither TimelineTrip or TripEvent");
        } else if (isTimeline) {
            return ((TripEvent) item).header();
        } else {
            return null;
        }
    }

    public static String getSubheader(Context context, BaseItineraryItem item, boolean isTimeline) {
        String subheaderText;
        AirDateTime startsAt;
        boolean showWeekday;
        boolean showTime;
        if (item instanceof TripEvent) {
            subheaderText = getSubheaderText(context, (TripEvent) item, isTimeline);
        } else {
            subheaderText = null;
        }
        if (item instanceof TripEvent) {
            startsAt = getHomeCheckinOrCheckoutTime((TripEvent) item);
        } else {
            startsAt = null;
        }
        if (startsAt == null) {
            startsAt = item.getStartsAt();
        }
        boolean showRelativeTime = isTimeline;
        if (!isTimeline || !TextUtils.isEmpty(subheaderText)) {
            showWeekday = true;
        } else {
            showWeekday = false;
        }
        if (!TextUtils.isEmpty(subheaderText) || (isTimeline && (!(item instanceof TripEvent) || TripEventCardType.Checkin.equals(((TripEvent) item).card_type())))) {
            showTime = false;
        } else {
            showTime = true;
        }
        String timestamp = getTimestamp(context, startsAt, item.getEndsAt(), showRelativeTime, showWeekday, showTime);
        if (!TextUtils.isEmpty(subheaderText)) {
            return timestamp + TIME_FROM_NOW_AND_DATE_RANGE_SEPARATOR + subheaderText;
        }
        return timestamp;
    }

    public static String getCardTitle(BaseItineraryItem item) {
        if (item instanceof TimelineTrip) {
            TimelineTrip timelineTrip = (TimelineTrip) item;
            return !TextUtils.isEmpty(timelineTrip.bundle_title()) ? timelineTrip.bundle_title() : timelineTrip.title();
        } else if (item instanceof TripEvent) {
            return ((TripEvent) item).card_title();
        } else {
            throw new IllegalStateException("ItineraryItem was neither TimelineTrip or TripEvent");
        }
    }

    public static String getCardSubtitle(BaseItineraryItem item) {
        if (item instanceof TimelineTrip) {
            return ((TimelineTrip) item).bundle_subtitle();
        }
        if (item instanceof TripEvent) {
            return ((TripEvent) item).card_subtitle();
        }
        throw new IllegalStateException("ItineraryItem was neither TimelineTrip or TripEvent");
    }

    public static int getCardSubtitleCopyToast(BaseItineraryItem item) {
        if (!(item instanceof TripEvent) || !((TripEvent) item).isHomeCard()) {
            return -1;
        }
        return C5755R.string.itinerary_address_copied_text;
    }

    public static String getSecondaryActionTitle(TripEventSecondaryAction secondaryAction) {
        if (secondaryAction == null) {
            return null;
        }
        return secondaryAction.title();
    }

    public static String getPendingHeaderTitle(Context context, TimelineMetadata timelineMetadata, List<TimelineTrip> timelineTrips) {
        if (timelineMetadata.isVerificationPending().booleanValue()) {
            return context.getString(C5755R.string.itinerary_verification_pending);
        }
        if (timelineMetadata.isNeedVerification().booleanValue()) {
            return context.getString(C5755R.string.itinerary_need_verification, new Object[]{getPendingTimeLeft(context, timelineMetadata), timelineMetadata.getSoonestExpiresTitle()});
        } else if (timelineMetadata.isNeedMtTos().booleanValue()) {
            return context.getString(C5755R.string.itinerary_tos_pending, new Object[]{getPendingTimeLeft(context, timelineMetadata), timelineMetadata.getSoonestExpiresTitle()});
        } else if (ListUtils.isEmpty((Collection<?>) timelineTrips)) {
            return null;
        } else {
            return context.getResources().getQuantityString(C5755R.plurals.itinerary_x_homes_pending, timelineTrips.size(), new Object[]{Integer.valueOf(timelineTrips.size())});
        }
    }

    public static String getPendingHeaderButtonText(Context context, TimelineMetadata timelineMetadata, List<TimelineTrip> timelineTrips) {
        if (timelineMetadata.isVerificationPending().booleanValue()) {
            return context.getString(C5755R.string.itinerary_view_button);
        }
        if (timelineMetadata.isNeedVerification().booleanValue()) {
            return context.getString(C5755R.string.itinerary_complete_button);
        }
        if (timelineMetadata.isNeedMtTos().booleanValue()) {
            return context.getString(C5755R.string.itinerary_complete_button);
        }
        if (!ListUtils.isEmpty((Collection<?>) timelineTrips)) {
            return context.getString(C5755R.string.itinerary_view_button);
        }
        return null;
    }

    public static String getPendingTimeLeft(Context context, TimelineMetadata timelineMetadata) {
        if (timelineMetadata.getSoonestExpiresAt() == null) {
            return null;
        }
        return timelineMetadata.getSoonestExpiresAt().getTimeRemaining(context);
    }

    public static List<List<Recommendation>> getRecommendationLists(FreeTimeItem freeTimeItem, ItineraryNavigationController navigationController) {
        List<Suggestion> suggestions = freeTimeItem.suggestions();
        List<List<Recommendation>> recommendations = new ArrayList<>();
        List<Recommendation> topRecommendations = new ArrayList<>(2);
        List<Recommendation> bottomRecommendations = new ArrayList<>(2);
        int numSuggestions = suggestions.size();
        if (!ListUtils.isEmpty((Collection<?>) suggestions)) {
            if (numSuggestions >= 4) {
                for (int i = 0; i < 2; i++) {
                    Suggestion suggestion = (Suggestion) suggestions.get(i);
                    topRecommendations.add(new Recommendation(suggestion.title(), suggestion.pictureUrl(), null, null, 0, i, getSuggestionClickListener(navigationController, suggestion, freeTimeItem.confirmationCode()), CardType.Small));
                }
                for (int i2 = 2; i2 < 4; i2++) {
                    Suggestion suggestion2 = (Suggestion) suggestions.get(i2);
                    bottomRecommendations.add(new Recommendation(suggestion2.title(), suggestion2.pictureUrl(), null, null, 0, i2, getSuggestionClickListener(navigationController, suggestion2, freeTimeItem.confirmationCode()), CardType.Small));
                }
                recommendations.add(topRecommendations);
                recommendations.add(bottomRecommendations);
            } else if (numSuggestions >= 2) {
                for (int i3 = 0; i3 < numSuggestions; i3++) {
                    Suggestion suggestion3 = (Suggestion) suggestions.get(i3);
                    topRecommendations.add(new Recommendation(suggestion3.title(), suggestion3.pictureUrl(), null, null, 0, i3, getSuggestionClickListener(navigationController, suggestion3, freeTimeItem.confirmationCode()), CardType.Small));
                }
                recommendations.add(topRecommendations);
            }
        }
        return recommendations;
    }

    public static HeaderPaddingType getHeaderPaddingType(boolean showHeaderPadding, boolean showExtraHeaderPadding) {
        if (showExtraHeaderPadding) {
            return HeaderPaddingType.EXTRA_PADDING;
        }
        if (showHeaderPadding) {
            return HeaderPaddingType.NORMAL_PADDING;
        }
        return HeaderPaddingType.NO_PADDING;
    }

    private static String getTimestamp(Context context, AirDateTime startsAt, AirDateTime endsAt, boolean showRelativeTime, boolean showWeekday, boolean showTime) {
        if (startsAt == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (showRelativeTime) {
            stringBuilder.append(getTimeFromNow(context, startsAt)).append(TIME_FROM_NOW_AND_DATE_RANGE_SEPARATOR);
        }
        if (showWeekday) {
            stringBuilder.append(startsAt.getDateStringWithWeekday(context));
        } else {
            stringBuilder.append(getDateRange(context, startsAt, endsAt));
        }
        if (showTime) {
            stringBuilder.append(TIME_FROM_NOW_AND_DATE_RANGE_SEPARATOR).append(startsAt.getTimeString(context));
        }
        return stringBuilder.toString();
    }

    private static String getDateRange(Context context, AirDateTime startsAt, AirDateTime endsAt) {
        if (endsAt == null) {
            return startsAt.getDateString(context);
        }
        return startsAt.getDateRangeString(context, endsAt);
    }

    private static String getTimeFromNow(Context context, AirDateTime startsAt) {
        return startsAt.getTimeFromNow(context);
    }

    private static boolean isWithinCheckinRange(AirDateTime checkinTime) {
        AirDateTime now = AirDateTime.now();
        if (!now.isAfter(checkinTime.plusDays(-1)) || !now.isBefore(checkinTime.plusDays(1))) {
            return false;
        }
        return true;
    }

    private static boolean isWithinCheckoutRange(AirDateTime checkinTime, AirDateTime checkoutTime) {
        boolean z = true;
        if (checkoutTime == null) {
            return false;
        }
        AirDateTime now = AirDateTime.now();
        if (!now.isAfter(checkinTime.plusDays(1)) || !now.isBefore(checkoutTime.plusDays(1))) {
            z = false;
        }
        return z;
    }

    private static String getSubheaderText(Context context, TripEvent tripEvent, boolean isTimeline) {
        if (tripEvent.card_type() == null) {
            return null;
        }
        Resources resources = context.getResources();
        switch (tripEvent.card_type()) {
            case Checkin:
                if (!isTimeline) {
                    return resources.getString(C5755R.string.itinerary_check_in_subheader, new Object[]{tripEvent.getStartsAt().getTimeString(context)});
                } else if (isWithinCheckinRange(tripEvent.getStartsAt())) {
                    return resources.getString(C5755R.string.itinerary_check_in_subheader, new Object[]{tripEvent.getStartsAt().getTimeString(context)});
                } else if (!isWithinCheckoutRange(tripEvent.getStartsAt(), tripEvent.getEndsAt())) {
                    return null;
                } else {
                    return resources.getString(C5755R.string.itinerary_check_out_subheader, new Object[]{tripEvent.getEndsAt().getTimeString(context)});
                }
            case Checkout:
                return resources.getString(C5755R.string.itinerary_check_out_subheader, new Object[]{tripEvent.getStartsAt().getTimeString(context)});
            default:
                return null;
        }
    }

    private static AirDateTime getHomeCheckinOrCheckoutTime(TripEvent tripEvent) {
        if (TripEventCardType.Checkin.equals(tripEvent.card_type())) {
            if (isWithinCheckinRange(tripEvent.getStartsAt())) {
                return tripEvent.getStartsAt();
            }
            if (isWithinCheckoutRange(tripEvent.getStartsAt(), tripEvent.getEndsAt())) {
                return tripEvent.getEndsAt();
            }
        }
        return null;
    }

    private static String getMapQuery(String latLng, String label) {
        StringBuilder stringBuilder = new StringBuilder().append(GOOGLE_MAPS_URL);
        stringBuilder.append(latLng).append("(");
        if (!TextUtils.isEmpty(label)) {
            stringBuilder.append(label);
        } else {
            stringBuilder.append(latLng);
        }
        return stringBuilder.append(")").toString();
    }

    private static OnClickListener getSuggestionClickListener(ItineraryNavigationController navigationController, Suggestion suggestion, String confirmationCode) {
        return ItineraryUtils$$Lambda$1.lambdaFactory$(navigationController, suggestion, confirmationCode);
    }

    public static boolean shouldHideSecondaryAction(TripEvent tripEvent) {
        AirDateTime now = AirDateTime.now();
        if (tripEvent.getEndsAt() == null) {
            return now.isAfter(tripEvent.getStartsAt().plusHours(1));
        }
        return now.isAfter(tripEvent.getEndsAt());
    }
}
