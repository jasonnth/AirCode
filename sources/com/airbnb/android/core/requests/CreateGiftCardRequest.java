package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.responses.CreateGiftCardResponse;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;
import p032rx.Observer;

public class CreateGiftCardRequest extends BaseRequestV2<CreateGiftCardResponse> {
    private static final String PARAM_AMOUNT = "amount";
    private static final String PARAM_CURRENCY = "currency";
    private static final String PARAM_RECIPIENT_EMAIL = "recipient_email";
    private static final String PARAM_RECIPIENT_MESSAGE = "recipient_message";
    private static final String PARAM_RECIPIENT_NAME = "recipient_name";
    private static final String PARAM_TEMPLATE_ID = "gift_credit_template_id";
    private static final String PATH_GIFT_CREDIT_CHECKOUT = "gift_credit_checkouts/";
    private final int amount;
    private final String currency;
    private String message;
    private final String recipientEmail;
    private final String recipientName;
    private final long templateId;

    public CreateGiftCardRequest(String recipientName2, String recipientEmail2, String currency2, long templateId2, int amount2, BaseRequestListener<CreateGiftCardResponse> listener) {
        withListener((Observer) listener);
        this.recipientEmail = recipientEmail2;
        this.recipientName = recipientName2;
        this.currency = currency2;
        this.templateId = templateId2;
        this.amount = amount2;
    }

    public CreateGiftCardRequest(String recipientName2, String recipientEmail2, String currency2, String message2, long templateId2, int amount2, BaseRequestListener<CreateGiftCardResponse> createRequestListener) {
        this(recipientName2, recipientEmail2, currency2, templateId2, amount2, createRequestListener);
        this.message = message2;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public String getBody() {
        try {
            JSONObject body = new JSONObject().put("amount", this.amount).put("currency", this.currency).put(PARAM_RECIPIENT_EMAIL, this.recipientEmail).put("recipient_name", this.recipientName).put(PARAM_TEMPLATE_ID, this.templateId);
            if (this.message != null) {
                body.put(PARAM_RECIPIENT_MESSAGE, this.message);
            }
            return body.toString();
        } catch (JSONException e) {
            BugsnagWrapper.notify((Throwable) e);
            return "";
        }
    }

    public String getPath() {
        return PATH_GIFT_CREDIT_CHECKOUT;
    }

    public Type successResponseType() {
        return CreateGiftCardResponse.class;
    }
}
