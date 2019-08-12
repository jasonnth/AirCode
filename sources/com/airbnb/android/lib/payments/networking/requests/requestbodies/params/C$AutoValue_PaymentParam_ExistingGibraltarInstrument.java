package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.$AutoValue_PaymentParam_ExistingGibraltarInstrument reason: invalid class name */
abstract class C$AutoValue_PaymentParam_ExistingGibraltarInstrument extends ExistingGibraltarInstrument {

    /* renamed from: id */
    private final Long f1245id;

    C$AutoValue_PaymentParam_ExistingGibraltarInstrument(Long id) {
        this.f1245id = id;
    }

    @JsonProperty("id")
    /* renamed from: id */
    public Long mo10855id() {
        return this.f1245id;
    }

    public String toString() {
        return "ExistingGibraltarInstrument{id=" + this.f1245id + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ExistingGibraltarInstrument)) {
            return false;
        }
        ExistingGibraltarInstrument that = (ExistingGibraltarInstrument) o;
        if (this.f1245id != null) {
            return this.f1245id.equals(that.mo10855id());
        }
        if (that.mo10855id() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.f1245id == null ? 0 : this.f1245id.hashCode());
    }
}
