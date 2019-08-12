package com.airbnb.android.core.airlock.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.FluentIterable;

public enum AirlockFrictionType {
    ChargebackAppeal("chargeback_appeal", ChargebackAppealFriction.class),
    EmailVerification("email_verification", GenericAirlockFriction.class),
    EmailCodeVerification("email_code_verification", GenericAirlockFriction.class),
    PhoneVerificationViaText("phone_verification_via_text", GenericAirlockFriction.class),
    PhoneVerificationViaCall("phone_verification_via_call", GenericAirlockFriction.class),
    ContactKBA("contact_kba", GenericAirlockFriction.class),
    ContactTicket("contact_ticket", GenericAirlockFriction.class),
    CVVVerification("cvv_verification", GenericAirlockFriction.class),
    MicroAuthorization("micro_authorization", GenericAirlockFriction.class),
    Unknown("", GenericAirlockFriction.class);
    
    private final Class<? extends BaseAirlockFriction> frictionClass;
    private final String serverKey;

    private AirlockFrictionType(String serverKey2, Class<? extends BaseAirlockFriction> frictionClass2) {
        this.serverKey = serverKey2;
        this.frictionClass = frictionClass2;
    }

    @JsonCreator
    public static AirlockFrictionType fromString(String airlockType) {
        return (AirlockFrictionType) FluentIterable.from((E[]) values()).firstMatch(AirlockFrictionType$$Lambda$1.lambdaFactory$(airlockType)).mo41059or(Unknown);
    }

    @JsonValue
    public String getServerKey() {
        return this.serverKey;
    }

    public Class<? extends BaseAirlockFriction> getFrictionClass() {
        return this.frictionClass;
    }
}
