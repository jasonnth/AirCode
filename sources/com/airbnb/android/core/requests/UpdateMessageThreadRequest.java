package com.airbnb.android.core.requests;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.FormUrlRequest;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Map;

public class UpdateMessageThreadRequest extends FormUrlRequest<BaseResponse> {
    private static final String ENDPOINT_THREAD_CREATE = "threads/create";
    public static final int INVALID_ID = -1;
    private Strap params;
    private final String path;

    public static UpdateMessageThreadRequest forPreApproveOrDecline(long threadId, long listingId, String message, long recipientId, boolean forDecline, BaseRequestListener<BaseResponse> listener) {
        return getRequestWithPostParams(threadId, getMessageParams(listingId, recipientId, message, forDecline ? ReservationStatus.Denied : ReservationStatus.Preapproved, false), listener);
    }

    public static UpdateMessageThreadRequest forPreApproveOrDecline(long threadId, long listingId, String message, long recipientId, boolean forDecline, BaseRequestListener<BaseResponse> listener, boolean hostAgreedSouthKoreanPreapproval) {
        return getRequestWithPostParams(threadId, getMessageParams(listingId, recipientId, message, forDecline ? ReservationStatus.Denied : ReservationStatus.Preapproved, hostAgreedSouthKoreanPreapproval), listener);
    }

    private static UpdateMessageThreadRequest getRequestWithPostParams(long threadId, Strap postParams, BaseRequestListener<BaseResponse> listener) {
        UpdateMessageThreadRequest request = new UpdateMessageThreadRequest(threadId > -1 ? "threads/" + Long.toString(threadId) + "/update" : ENDPOINT_THREAD_CREATE, null, listener);
        request.params = postParams;
        return request;
    }

    private UpdateMessageThreadRequest(String path2, Strap params2, BaseRequestListener<BaseResponse> listener) {
        withListener(listener);
        this.path = path2;
        this.params = params2;
    }

    @SuppressLint({"SimpleDateFormat"})
    private static Strap getMessageParams(long listingId, long recipientId, String message, ReservationStatus status, boolean hostAgreedSouthKoreanPreapproval) {
        Strap strap = Strap.make();
        if (listingId > -1) {
            strap.mo11639kv("listing_id", String.valueOf(listingId));
        } else if (recipientId > -1) {
            strap.mo11639kv("user_id", String.valueOf(recipientId));
        }
        if (status == ReservationStatus.Preapproved || status == ReservationStatus.Denied) {
            strap.mo11639kv("status", status.key);
        }
        String str = "message";
        if (TextUtils.isEmpty(message)) {
            message = "";
        }
        strap.mo11639kv(str, message);
        if (hostAgreedSouthKoreanPreapproval) {
            strap.mo11640kv("host_agreed_korean_booking", hostAgreedSouthKoreanPreapproval);
        }
        return strap;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public QueryStrap getFields() {
        return QueryStrap.make().mix((Map<String, String>) this.params);
    }

    public String getPath() {
        return this.path;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }
}
