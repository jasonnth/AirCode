package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.SeasonalMinNightsCalendarSetting;
import com.airbnb.android.core.requests.constants.CalendarRulesRequestConstants;
import com.airbnb.android.core.responses.CalendarRulesResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CalendarRulesRequest extends BaseRequestV2<CalendarRulesResponse> {
    private final Object body;
    private final long listingId;
    private final RequestMethod method;

    static class SeasonalCalendarRequestBody {
        @JsonProperty("seasonal_min_nights")
        List<SeasonalMinNightsCalendarSetting> seasonalSettings;

        public SeasonalCalendarRequestBody(List<SeasonalMinNightsCalendarSetting> seasonalSettings2) {
            this.seasonalSettings = seasonalSettings2;
        }
    }

    public static CalendarRulesRequest forListingId(long listingId2) {
        return new CalendarRulesRequest(listingId2, null, RequestMethod.GET);
    }

    public static CalendarRulesRequest updateWeekendMinNights(long listingId2, int weekendMinNights) {
        JSONObject hash = new JSONObject();
        try {
            hash.put(CalendarRulesRequestConstants.WEEKEND_MIN_NIGHTS, getWeekendMinNightsHash(weekendMinNights));
            return new CalendarRulesRequest(listingId2, hash.toString(), RequestMethod.PUT);
        } catch (JSONException e) {
            throw new RuntimeException("Error constructing JSON for weekend_min_nights update", e);
        }
    }

    public static CalendarRulesRequest updateSeasonalCalendarSettings(long listingId2, List<SeasonalMinNightsCalendarSetting> seasonalSettings) {
        return new CalendarRulesRequest(listingId2, new SeasonalCalendarRequestBody(seasonalSettings), RequestMethod.PUT);
    }

    public static CalendarRulesRequest updateForFields(long listingId2, int bookingLeadTimeHours, int allowRequestToBook, int turnoverDays, int futureReservationsDays) {
        JSONObject requestHash = new JSONObject();
        try {
            requestHash.put(CalendarRulesRequestConstants.BOOKING_LEAD_TIME, getAdvanceNoticeHash(bookingLeadTimeHours, allowRequestToBook));
            requestHash.put("turnover_days", getPreparationTimeHash(turnoverDays));
            requestHash.put(CalendarRulesRequestConstants.MAX_DAYS_NOTICE, getFutureReservationsHash(futureReservationsDays));
        } catch (JSONException e) {
            BugsnagWrapper.throwOrNotify(new RuntimeException("Error constructing JSON for calendar_rules update", e));
        }
        return new CalendarRulesRequest(listingId2, requestHash.toString(), RequestMethod.PUT);
    }

    public static JSONObject getWeekendMinNightsHash(int weekendMInNights) throws JSONException {
        return new JSONObject().put("min_nights", weekendMInNights);
    }

    public static JSONObject getAdvanceNoticeHash(int bookingLeadTimeHours, int allowRequestToBook) throws JSONException {
        return new JSONObject().put("hours", bookingLeadTimeHours).put(CalendarRulesRequestConstants.ALLOW_REQUEST_TO_BOOK, allowRequestToBook);
    }

    public static JSONObject getPreparationTimeHash(int turnoverDays) throws JSONException {
        return new JSONObject().put(CalendarRulesRequestConstants.DAYS, turnoverDays);
    }

    public static JSONObject getFutureReservationsHash(int futureReservationsDays) throws JSONException {
        return new JSONObject().put(CalendarRulesRequestConstants.DAYS, futureReservationsDays);
    }

    public static CalendarRulesRequest forFields(long listingId2, JSONObject requestHash) {
        return new CalendarRulesRequest(listingId2, requestHash.toString(), RequestMethod.PUT);
    }

    private CalendarRulesRequest(long listingId2, Object body2, RequestMethod method2) {
        this.listingId = listingId2;
        this.body = body2;
        this.method = method2;
    }

    public String getPath() {
        return "calendar_rules/" + this.listingId;
    }

    public Object getBody() {
        return this.body;
    }

    public RequestMethod getMethod() {
        return this.method;
    }

    public Type successResponseType() {
        return CalendarRulesResponse.class;
    }
}
