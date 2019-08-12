package com.airbnb.android.payout.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import com.airbnb.android.payout.models.PayoutFormFieldInputWrapper;
import com.airbnb.android.payout.models.PayoutInfoFormType;
import java.lang.reflect.Type;
import java.util.List;

public class CreatePayoutMethodRequest extends BaseRequestV2<PaymentInstrumentResponse> {
    private final List<PayoutFormFieldInputWrapper> inputFields;
    private final AirAddress payoutAddress;
    private final PayoutInfoFormType payoutInfoFormType;
    private final String targetCurrency;

    public static CreatePayoutMethodRequest forPayoneerAPIRedirect(String targetCurrency2, PayoutInfoFormType payoutInfoFormType2, AirAddress address) {
        return new CreatePayoutMethodRequest(targetCurrency2, payoutInfoFormType2, address, null);
    }

    public static CreatePayoutMethodRequest forCreatePayoutMethod(String targetCurrency2, PayoutInfoFormType payoutInfoFormType2, AirAddress address, List<PayoutFormFieldInputWrapper> inputFields2) {
        return new CreatePayoutMethodRequest(targetCurrency2, payoutInfoFormType2, address, inputFields2);
    }

    private CreatePayoutMethodRequest(String targetCurrency2, PayoutInfoFormType payoutInfoFormType2, AirAddress payoutAddress2, List<PayoutFormFieldInputWrapper> inputFields2) {
        this.targetCurrency = targetCurrency2;
        this.payoutInfoFormType = payoutInfoFormType2;
        this.payoutAddress = payoutAddress2;
        this.inputFields = inputFields2;
    }

    public Object getBody() {
        return CreatePayoutMethodRequestBody.builder().accountInputs(this.inputFields != null ? CreatePayoutMethodRequestBody.getAccountInputs(this.inputFields) : null).accountAddress(this.payoutAddress).targetCurrency(this.targetCurrency).payoutInfoFormType(this.payoutInfoFormType.getServerKey()).build();
    }

    public Type successResponseType() {
        return PaymentInstrumentResponse.class;
    }

    public String getPath() {
        return "payment_instruments";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }
}
