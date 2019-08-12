package com.airbnb.android.core.payments.models.clientparameters;

import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.payments.models.BillProductType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/* renamed from: com.airbnb.android.core.payments.models.clientparameters.$AutoValue_HomesClientParameters reason: invalid class name */
abstract class C$AutoValue_HomesClientParameters extends HomesClientParameters {
    private final String businessTripNotes;
    private final List<GuestIdentity> guestIdentities;
    private final Boolean isBusinessTrip;
    private final String messageToHost;
    private final String p4Steps;
    private final BillProductType productType;
    private final String reservationConfirmationCode;
    private final String searchRankingId;

    /* renamed from: com.airbnb.android.core.payments.models.clientparameters.$AutoValue_HomesClientParameters$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters.Builder {
        private String businessTripNotes;
        private List<GuestIdentity> guestIdentities;
        private Boolean isBusinessTrip;
        private String messageToHost;
        private String p4Steps;
        private BillProductType productType;
        private String reservationConfirmationCode;
        private String searchRankingId;

        Builder() {
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters.Builder productType(BillProductType productType2) {
            if (productType2 == null) {
                throw new NullPointerException("Null productType");
            }
            this.productType = productType2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters.Builder reservationConfirmationCode(String reservationConfirmationCode2) {
            if (reservationConfirmationCode2 == null) {
                throw new NullPointerException("Null reservationConfirmationCode");
            }
            this.reservationConfirmationCode = reservationConfirmationCode2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters.Builder messageToHost(String messageToHost2) {
            this.messageToHost = messageToHost2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters.Builder searchRankingId(String searchRankingId2) {
            this.searchRankingId = searchRankingId2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters.Builder isBusinessTrip(Boolean isBusinessTrip2) {
            this.isBusinessTrip = isBusinessTrip2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters.Builder businessTripNotes(String businessTripNotes2) {
            this.businessTripNotes = businessTripNotes2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters.Builder guestIdentities(List<GuestIdentity> guestIdentities2) {
            this.guestIdentities = guestIdentities2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.HomesClientParameters.Builder p4Steps(String p4Steps2) {
            this.p4Steps = p4Steps2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public HomesClientParameters autoBuild() {
            String missing = "";
            if (this.productType == null) {
                missing = missing + " productType";
            }
            if (this.reservationConfirmationCode == null) {
                missing = missing + " reservationConfirmationCode";
            }
            if (missing.isEmpty()) {
                return new AutoValue_HomesClientParameters(this.productType, this.reservationConfirmationCode, this.messageToHost, this.searchRankingId, this.isBusinessTrip, this.businessTripNotes, this.guestIdentities, this.p4Steps);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_HomesClientParameters(BillProductType productType2, String reservationConfirmationCode2, String messageToHost2, String searchRankingId2, Boolean isBusinessTrip2, String businessTripNotes2, List<GuestIdentity> guestIdentities2, String p4Steps2) {
        if (productType2 == null) {
            throw new NullPointerException("Null productType");
        }
        this.productType = productType2;
        if (reservationConfirmationCode2 == null) {
            throw new NullPointerException("Null reservationConfirmationCode");
        }
        this.reservationConfirmationCode = reservationConfirmationCode2;
        this.messageToHost = messageToHost2;
        this.searchRankingId = searchRankingId2;
        this.isBusinessTrip = isBusinessTrip2;
        this.businessTripNotes = businessTripNotes2;
        this.guestIdentities = guestIdentities2;
        this.p4Steps = p4Steps2;
    }

    public BillProductType productType() {
        return this.productType;
    }

    @JsonProperty("reservation_confirmation_code")
    public String reservationConfirmationCode() {
        return this.reservationConfirmationCode;
    }

    @JsonProperty("message_to_host")
    public String messageToHost() {
        return this.messageToHost;
    }

    @JsonProperty("search_ranking_id")
    public String searchRankingId() {
        return this.searchRankingId;
    }

    @JsonProperty("is_business_trip")
    public Boolean isBusinessTrip() {
        return this.isBusinessTrip;
    }

    @JsonProperty("business_trip_notes")
    public String businessTripNotes() {
        return this.businessTripNotes;
    }

    @JsonProperty("guest_identities")
    public List<GuestIdentity> guestIdentities() {
        return this.guestIdentities;
    }

    @JsonProperty("p4_steps")
    public String p4Steps() {
        return this.p4Steps;
    }

    public String toString() {
        return "HomesClientParameters{productType=" + this.productType + ", reservationConfirmationCode=" + this.reservationConfirmationCode + ", messageToHost=" + this.messageToHost + ", searchRankingId=" + this.searchRankingId + ", isBusinessTrip=" + this.isBusinessTrip + ", businessTripNotes=" + this.businessTripNotes + ", guestIdentities=" + this.guestIdentities + ", p4Steps=" + this.p4Steps + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomesClientParameters)) {
            return false;
        }
        HomesClientParameters that = (HomesClientParameters) o;
        if (this.productType.equals(that.productType()) && this.reservationConfirmationCode.equals(that.reservationConfirmationCode()) && (this.messageToHost != null ? this.messageToHost.equals(that.messageToHost()) : that.messageToHost() == null) && (this.searchRankingId != null ? this.searchRankingId.equals(that.searchRankingId()) : that.searchRankingId() == null) && (this.isBusinessTrip != null ? this.isBusinessTrip.equals(that.isBusinessTrip()) : that.isBusinessTrip() == null) && (this.businessTripNotes != null ? this.businessTripNotes.equals(that.businessTripNotes()) : that.businessTripNotes() == null) && (this.guestIdentities != null ? this.guestIdentities.equals(that.guestIdentities()) : that.guestIdentities() == null)) {
            if (this.p4Steps == null) {
                if (that.p4Steps() == null) {
                    return true;
                }
            } else if (this.p4Steps.equals(that.p4Steps())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((1 * 1000003) ^ this.productType.hashCode()) * 1000003) ^ this.reservationConfirmationCode.hashCode()) * 1000003) ^ (this.messageToHost == null ? 0 : this.messageToHost.hashCode())) * 1000003) ^ (this.searchRankingId == null ? 0 : this.searchRankingId.hashCode())) * 1000003) ^ (this.isBusinessTrip == null ? 0 : this.isBusinessTrip.hashCode())) * 1000003) ^ (this.businessTripNotes == null ? 0 : this.businessTripNotes.hashCode())) * 1000003) ^ (this.guestIdentities == null ? 0 : this.guestIdentities.hashCode())) * 1000003;
        if (this.p4Steps != null) {
            i = this.p4Steps.hashCode();
        }
        return h ^ i;
    }
}
