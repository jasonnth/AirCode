package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.data.AddressParts;
import com.airbnb.android.core.models.payments.C6200PaymentInstrumentType;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.lib.contentproviders.PlacesSearchSuggestionProvider;
import com.airbnb.android.utils.Strap;
import com.braintreepayments.api.models.PostalAddress;
import java.lang.reflect.Type;

public class CreatePaymentInstrumentRequest extends BaseRequestV2<PaymentInstrumentResponse> {
    private final Object requestBody;

    public static CreatePaymentInstrumentRequest forAch(Strap trustStrap, String personName, String accountType, String routingNumber, String accountNumber) {
        return new CreatePaymentInstrumentRequest(trustStrap.mo11639kv("type", C6200PaymentInstrumentType.ACH.getServerKey()).mo11639kv("account_name", personName).mo11639kv("account_number", accountNumber).mo11639kv("account_type", accountType).mo11639kv("routing_number", routingNumber));
    }

    public static CreatePaymentInstrumentRequest forPayPal(Strap trustStrap, String paypalEmail, String currencyCode) {
        return new CreatePaymentInstrumentRequest(trustStrap.mo11639kv("type", C6200PaymentInstrumentType.PayPal.getServerKey()).mo11639kv("paypal_email", paypalEmail).mo11639kv("target_currency", currencyCode));
    }

    private CreatePaymentInstrumentRequest(Object requestBody2) {
        this.requestBody = requestBody2;
    }

    public String getPath() {
        return "payment_instruments";
    }

    public Object getBody() {
        return this.requestBody;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return PaymentInstrumentResponse.class;
    }

    public static final Strap generateTrustStrap(AddressParts address) {
        return Strap.make().mo11639kv("street_address1", address.street1()).mo11639kv("street_address2", address.street2()).mo11639kv("locality", address.city()).mo11639kv(PlacesSearchSuggestionProvider.DISPLAY_REGION, address.state()).mo11639kv(PostalAddress.POSTAL_CODE_UNDERSCORE_KEY, address.zipCode()).mo11639kv("country", address.countryCode());
    }
}
