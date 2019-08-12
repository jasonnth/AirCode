package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.deserializers.WrappedDeserializer;
import com.airbnb.android.core.deserializers.WrappedObject;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.Verification;
import com.airbnb.android.core.models.Verification.Type;
import com.airbnb.android.core.models.VerificationRequirements;
import com.airbnb.android.core.models.verifications.VerificationsState;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

public class VerificationsResponse extends BaseResponse {
    @JsonProperty("account_activation_verifications")
    public VerificationRequirements accountActivationVerifications;
    @JsonProperty("verifications")
    public VerificationRequirements checkpointVerifications;
    @WrappedObject("reservation")
    @JsonProperty("reservation")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public Reservation reservation;

    public static VerificationsState toVerificationsState(VerificationsResponse response) {
        return VerificationsState.initialize(response.getVerifications());
    }

    public Verification getVerification(Type type) {
        for (Verification verification : getVerifications()) {
            if (verification.getType() == type) {
                return verification;
            }
        }
        return null;
    }

    private List<Verification> getVerifications() {
        return this.accountActivationVerifications.getVerificationGroups().getAccountActivation().getItems();
    }
}
