package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.responses.ClaimGiftCardResponse;
import java.lang.reflect.Type;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import p032rx.Observer;
import retrofit2.Query;

public class ClaimGiftCardRequest extends BaseRequestV2<ClaimGiftCardResponse> {
    private static final String FORMAT_FOR_STATUS_UPDATE = "for_status_update";
    private static final String PARAM_FORMAT = "_format";
    private static final String PARAM_STATUS = "status";
    private static final String STATUS_CLAIMED = "Claimed";
    private final String code;

    public ClaimGiftCardRequest(String code2, BaseRequestListener<ClaimGiftCardResponse> listener) {
        this.code = code2;
        withListener((Observer) listener);
    }

    public String getPath() {
        return "gift_credits/" + this.code;
    }

    public String getBody() {
        try {
            return new JSONObject().put("status", STATUS_CLAIMED).toString();
        } catch (JSONException e) {
            BugsnagWrapper.notify((Throwable) e);
            return "";
        }
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mo7895kv("_format", FORMAT_FOR_STATUS_UPDATE);
    }

    public Type successResponseType() {
        return ClaimGiftCardResponse.class;
    }
}
