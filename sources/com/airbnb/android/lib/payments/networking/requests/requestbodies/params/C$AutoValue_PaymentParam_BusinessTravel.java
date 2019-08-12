package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.$AutoValue_PaymentParam_BusinessTravel reason: invalid class name */
abstract class C$AutoValue_PaymentParam_BusinessTravel extends C0895BusinessTravel {
    private final Long businessEntityGroupId;

    C$AutoValue_PaymentParam_BusinessTravel(Long businessEntityGroupId2) {
        this.businessEntityGroupId = businessEntityGroupId2;
    }

    @JsonProperty("business_entity_group_id")
    public Long businessEntityGroupId() {
        return this.businessEntityGroupId;
    }

    public String toString() {
        return "BusinessTravel{businessEntityGroupId=" + this.businessEntityGroupId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof C0895BusinessTravel)) {
            return false;
        }
        C0895BusinessTravel that = (C0895BusinessTravel) o;
        if (this.businessEntityGroupId != null) {
            return this.businessEntityGroupId.equals(that.businessEntityGroupId());
        }
        if (that.businessEntityGroupId() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.businessEntityGroupId == null ? 0 : this.businessEntityGroupId.hashCode());
    }
}
