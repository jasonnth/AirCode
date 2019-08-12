package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity;

import com.airbnb.android.core.enums.HelpCenterArticle;
import com.fasterxml.jackson.annotation.JsonProperty;

/* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.$AutoValue_HomesGuestIdentity reason: invalid class name */
abstract class C$AutoValue_HomesGuestIdentity extends HomesGuestIdentity {

    /* renamed from: id */
    private final Integer f1246id;
    private final String idType;
    private final boolean isBooker;

    /* renamed from: com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.$AutoValue_HomesGuestIdentity$Builder */
    static final class Builder extends com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.HomesGuestIdentity.Builder {

        /* renamed from: id */
        private Integer f1247id;
        private String idType;
        private Boolean isBooker;

        Builder() {
        }

        /* renamed from: id */
        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.HomesGuestIdentity.Builder mo10936id(Integer id) {
            if (id == null) {
                throw new NullPointerException("Null id");
            }
            this.f1247id = id;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.HomesGuestIdentity.Builder idType(String idType2) {
            if (idType2 == null) {
                throw new NullPointerException("Null idType");
            }
            this.idType = idType2;
            return this;
        }

        public com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.HomesGuestIdentity.Builder isBooker(boolean isBooker2) {
            this.isBooker = Boolean.valueOf(isBooker2);
            return this;
        }

        public HomesGuestIdentity build() {
            String missing = "";
            if (this.f1247id == null) {
                missing = missing + " id";
            }
            if (this.idType == null) {
                missing = missing + " idType";
            }
            if (this.isBooker == null) {
                missing = missing + " isBooker";
            }
            if (missing.isEmpty()) {
                return new AutoValue_HomesGuestIdentity(this.f1247id, this.idType, this.isBooker.booleanValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_HomesGuestIdentity(Integer id, String idType2, boolean isBooker2) {
        if (id == null) {
            throw new NullPointerException("Null id");
        }
        this.f1246id = id;
        if (idType2 == null) {
            throw new NullPointerException("Null idType");
        }
        this.idType = idType2;
        this.isBooker = isBooker2;
    }

    @JsonProperty("id")
    /* renamed from: id */
    public Integer mo10931id() {
        return this.f1246id;
    }

    @JsonProperty("id_type")
    public String idType() {
        return this.idType;
    }

    @JsonProperty("is_booker")
    public boolean isBooker() {
        return this.isBooker;
    }

    public String toString() {
        return "HomesGuestIdentity{id=" + this.f1246id + ", idType=" + this.idType + ", isBooker=" + this.isBooker + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomesGuestIdentity)) {
            return false;
        }
        HomesGuestIdentity that = (HomesGuestIdentity) o;
        if (!this.f1246id.equals(that.mo10931id()) || !this.idType.equals(that.idType()) || this.isBooker != that.isBooker()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((1 * 1000003) ^ this.f1246id.hashCode()) * 1000003) ^ this.idType.hashCode()) * 1000003) ^ (this.isBooker ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE);
    }
}
