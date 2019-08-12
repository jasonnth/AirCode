package com.airbnb.android.explore.requests;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.intents.PlacesIntents;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.mparticle.commerce.Product;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

public class ExploreSubtabClickLoggingRequest extends BaseRequestV2<BaseResponse> {
    private final int currSubtabIndex;
    private final TopLevelSearchParams data;
    private final boolean isNewQuery;
    private final String newLocation;
    private final int prevSubtabIndex;
    private final String searchId;
    private final String searchSessionId;
    private final String target;

    public ExploreSubtabClickLoggingRequest(TopLevelSearchParams data2, String searchSessionId2, String searchId2, C2139ExploreSubtab currSubtab, C2139ExploreSubtab prevSubtab, String target2, boolean isNewQuery2, String newLocation2) {
        this.data = data2;
        this.searchId = searchId2;
        this.searchSessionId = searchSessionId2;
        this.currSubtabIndex = currSubtab.value;
        this.prevSubtabIndex = prevSubtab.value;
        this.target = target2;
        this.isNewQuery = isNewQuery2;
        this.newLocation = newLocation2;
    }

    public String getPath() {
        return "trips_subtab_click_events";
    }

    public String getBody() {
        JSONObject params = new JSONObject();
        try {
            if (this.data.hasSearchTerm()) {
                params.put("location", this.data.searchTerm());
            }
            AirDate checkInDate = this.data.checkInDate();
            if (checkInDate != null) {
                params.put(UpdateReviewRequest.KEY_CHECKIN, checkInDate.getIsoDateString());
            }
            AirDate checkOutDate = this.data.checkOutDate();
            if (checkOutDate != null) {
                params.put(Product.CHECKOUT, checkOutDate.getIsoDateString());
            }
            GuestDetails guestDetails = this.data.guestDetails();
            params.put(FindTweenAnalytics.GUESTS, (guestDetails == null || guestDetails.totalGuestCount() <= 0) ? 0 : guestDetails.totalGuestCount());
            JSONObject searchContext = new JSONObject();
            if (!TextUtils.isEmpty(this.searchId)) {
                searchContext.put(PlacesIntents.INTENT_EXTRA_SEARCH_ID, this.searchId);
            }
            if (!TextUtils.isEmpty(this.searchSessionId)) {
                searchContext.put("mobile_search_session_id", this.searchSessionId);
            }
            params.put("search_context", searchContext);
            params.put("subtab", this.currSubtabIndex);
            params.put("subtab_previous", this.prevSubtabIndex);
            params.put(BaseAnalytics.TARGET, this.target);
            params.put("new_query", this.isNewQuery);
            params.put("location_next", this.newLocation);
            return params.toString();
        } catch (JSONException e) {
            throw new IllegalStateException("Unable to create params json.");
        }
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
