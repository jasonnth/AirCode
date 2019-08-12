package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.responses.CreateInquiryResponse;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import p032rx.Observer;
import retrofit2.Query;

public class CreateInquiryRequest extends BaseRequestV2<CreateInquiryResponse> {
    private final AirDate checkinDate;
    private final AirDate checkoutDate;
    private final GuestDetails guestDetails;
    private final long listingId;
    private final String message;
    private final long toUserId;

    public CreateInquiryRequest(String message2, long listingId2, AirDate checkinDate2, AirDate checkoutDate2, GuestDetails guestDetails2, long toUserId2, BaseRequestListener<CreateInquiryResponse> listener) {
        withListener((Observer) listener);
        this.message = message2;
        this.listingId = listingId2;
        this.checkinDate = checkinDate2;
        this.checkoutDate = checkoutDate2;
        this.guestDetails = guestDetails2;
        this.toUserId = toUserId2;
    }

    public String getPath() {
        return "threads";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv(TimelineRequest.ARG_FORMAT, "for_mobile_reservation_object").mo7895kv("_intents", "guest_inquiry");
    }

    public String getBody() {
        SimpleDateFormat sdf = DateHelper.YEAR_MONTH_DAY_FORMATTER_US;
        try {
            JSONObject body = new JSONObject().put("message", this.message).put("checkin_date", this.checkinDate.formatDate((DateFormat) sdf)).put("checkout_date", this.checkoutDate.formatDate((DateFormat) sdf)).put("number_of_adults", this.guestDetails.adultsCount()).put("number_of_children", this.guestDetails.childrenCount()).put("number_of_infants", this.guestDetails.infantsCount()).put("user_id", this.toUserId);
            if (this.listingId > 0) {
                body.put("listing_id", String.valueOf(this.listingId));
            }
            return body.toString();
        } catch (JSONException e) {
            BugsnagWrapper.notify((Throwable) e);
            return "";
        }
    }

    public Type successResponseType() {
        return CreateInquiryResponse.class;
    }
}
