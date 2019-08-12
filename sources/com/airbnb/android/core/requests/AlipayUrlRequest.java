package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.responses.AlipayUrlResponse;
import com.airbnb.android.core.utils.BuildHelper;
import java.lang.reflect.Type;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
import p032rx.Observer;

public class AlipayUrlRequest extends BaseRequestV2<AlipayUrlResponse> {
    private static final String CLIENT_ANDROID = "android";
    private static final String PAYMENT_TYPE_ALIPAY = "alipay_wap";
    private final Reservation reservation;

    public AlipayUrlRequest(Reservation reservation2, BaseRequestListener<AlipayUrlResponse> listener) {
        withListener((Observer) listener);
        this.reservation = reservation2;
    }

    public String getPath() {
        return "reservation_payment_redirects";
    }

    public String getBody() {
        JSONObject json = new JSONObject();
        try {
            json.put("payment_method", PAYMENT_TYPE_ALIPAY);
            json.put("device_type", "android");
            json.put("reservation_id", this.reservation.getId());
            json.put("country", Locale.getDefault().getCountry());
        } catch (JSONException e) {
            if (BuildHelper.isDevelopmentBuild()) {
                throw new IllegalStateException("Error building alipay json");
            }
        }
        return json.toString();
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return AlipayUrlResponse.class;
    }
}
