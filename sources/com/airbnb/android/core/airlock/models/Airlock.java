package com.airbnb.android.core.airlock.models;

import android.os.Parcelable;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.erf.Experiments;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@JsonDeserialize(builder = Builder.class)
public abstract class Airlock implements Parcelable {
    private static Set<AirlockFrictionType> aovFrictions = ImmutableSet.builder().add((Object) AirlockFrictionType.EmailCodeVerification).add((Object) AirlockFrictionType.PhoneVerificationViaCall).add((Object) AirlockFrictionType.PhoneVerificationViaText).add((Object) AirlockFrictionType.ContactKBA).add((Object) AirlockFrictionType.ContactTicket).build();
    private static Map<AirlockFrictionType, Double> supportedFrictionVersions = ImmutableMap.builder().put(AirlockFrictionType.ChargebackAppeal, Double.valueOf(1.0d)).put(AirlockFrictionType.EmailCodeVerification, Double.valueOf(1.0d)).put(AirlockFrictionType.MicroAuthorization, Double.valueOf(1.0d)).put(AirlockFrictionType.PhoneVerificationViaCall, Double.valueOf(1.0d)).put(AirlockFrictionType.PhoneVerificationViaText, Double.valueOf(1.0d)).put(AirlockFrictionType.ContactKBA, Double.valueOf(1.0d)).put(AirlockFrictionType.ContactTicket, Double.valueOf(1.0d)).build();

    public static abstract class Builder {
        @JsonProperty("action_name")
        public abstract Builder actionName(String str);

        public abstract Airlock build();

        @JsonProperty("first_name")
        public abstract Builder firstName(String str);

        @JsonUnwrapped
        public abstract Builder frictionInitData(FrictionInitData frictionInitData);

        @JsonProperty("frictions")
        public abstract Builder frictions(List<List<AirlockFrictionType>> list);

        @JsonProperty("id")
        /* renamed from: id */
        public abstract Builder mo8260id(long j);

        @JsonProperty("user_id")
        public abstract Builder userId(long j);
    }

    public abstract String actionName();

    public abstract String firstName();

    public abstract FrictionInitData frictionInitData();

    public abstract List<List<AirlockFrictionType>> frictions();

    /* renamed from: id */
    public abstract long mo8253id();

    public abstract long userId();

    public static Builder builder() {
        return new Builder();
    }

    public boolean hasOnlyChargebackAppeal() {
        return frictions().size() == 1 && ((List) frictions().get(0)).size() == 1 && frictionInitData().hasChargebackAppeal();
    }

    public boolean areAllFrictionsSupportedByRN() {
        return FluentIterable.concat(frictions()).allMatch(Airlock$$Lambda$1.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public boolean isFrictionSupported(AirlockFrictionType airlockFrictionType) {
        double version = 0.0d;
        BaseAirlockFriction baseAirlockFriction = frictionInitData().get(airlockFrictionType);
        if (baseAirlockFriction != null) {
            version = baseAirlockFriction.version();
        }
        if (airlockFrictionType == AirlockFrictionType.Unknown) {
            return false;
        }
        if ((aovFrictions.contains(airlockFrictionType) && (!Trebuchet.launch(TrebuchetKeys.AIRLOCK_AOV, false) || !Experiments.isUsingRNAOVAndKBA())) || !supportedFrictionVersions.containsKey(airlockFrictionType)) {
            return false;
        }
        return ((Double) supportedFrictionVersions.get(airlockFrictionType)).doubleValue() >= version;
    }
}
