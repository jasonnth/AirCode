package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.$AutoValue_HomesGuestIdentifications reason: invalid class name */
abstract class C$AutoValue_HomesGuestIdentifications extends HomesGuestIdentifications {
    private final List<HomesGuestIdentity> identifications;

    /* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.$AutoValue_HomesGuestIdentifications$Builder */
    static final class Builder extends com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.HomesGuestIdentifications.Builder {
        private List<HomesGuestIdentity> identifications;

        Builder() {
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.HomesGuestIdentifications.Builder identifications(List<HomesGuestIdentity> identifications2) {
            this.identifications = identifications2;
            return this;
        }

        public HomesGuestIdentifications build() {
            return new AutoValue_HomesGuestIdentifications(this.identifications);
        }
    }

    C$AutoValue_HomesGuestIdentifications(List<HomesGuestIdentity> identifications2) {
        this.identifications = identifications2;
    }

    @JsonProperty("identifications")
    public List<HomesGuestIdentity> identifications() {
        return this.identifications;
    }

    public String toString() {
        return "HomesGuestIdentifications{identifications=" + this.identifications + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomesGuestIdentifications)) {
            return false;
        }
        HomesGuestIdentifications that = (HomesGuestIdentifications) o;
        if (this.identifications != null) {
            return this.identifications.equals(that.identifications());
        }
        if (that.identifications() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.identifications == null ? 0 : this.identifications.hashCode());
    }
}
