package com.airbnb.android.lib.payments.networking.requests.requestbodies.params;

import com.airbnb.android.core.enums.HelpCenterArticle;
import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.$AutoValue_PaymentParam_AirbnbCredit reason: invalid class name */
abstract class C$AutoValue_PaymentParam_AirbnbCredit extends AirbnbCredit {
    private final boolean includeAirbnbCredit;

    C$AutoValue_PaymentParam_AirbnbCredit(boolean includeAirbnbCredit2) {
        this.includeAirbnbCredit = includeAirbnbCredit2;
    }

    @JsonProperty("include_airbnb_credit")
    public boolean includeAirbnbCredit() {
        return this.includeAirbnbCredit;
    }

    public String toString() {
        return "AirbnbCredit{includeAirbnbCredit=" + this.includeAirbnbCredit + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AirbnbCredit)) {
            return false;
        }
        if (this.includeAirbnbCredit != ((AirbnbCredit) o).includeAirbnbCredit()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.includeAirbnbCredit ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE);
    }
}
