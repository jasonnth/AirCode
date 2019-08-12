package com.airbnb.android.core.payments.models.clientparameters;

import com.airbnb.android.core.models.TripSecondaryGuest;
import com.airbnb.android.core.payments.models.BillProductType;
import java.util.ArrayList;
import java.util.Map;

/* renamed from: com.airbnb.android.core.payments.models.clientparameters.$AutoValue_MagicalTripsClientParameters reason: invalid class name */
abstract class C$AutoValue_MagicalTripsClientParameters extends MagicalTripsClientParameters {
    private final Map<String, String> guestAddress;
    private final int guestCount;
    private final BillProductType productType;
    private final ArrayList<TripSecondaryGuest> secondaryGuests;
    private final long templateId;
    private final Long travelPurpose;

    /* renamed from: com.airbnb.android.core.payments.models.clientparameters.$AutoValue_MagicalTripsClientParameters$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.clientparameters.MagicalTripsClientParameters.Builder {
        private Map<String, String> guestAddress;
        private Integer guestCount;
        private BillProductType productType;
        private ArrayList<TripSecondaryGuest> secondaryGuests;
        private Long templateId;
        private Long travelPurpose;

        Builder() {
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.core.payments.models.clientparameters.MagicalTripsClientParameters.Builder productType(BillProductType productType2) {
            if (productType2 == null) {
                throw new NullPointerException("Null productType");
            }
            this.productType = productType2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.MagicalTripsClientParameters.Builder templateId(long templateId2) {
            this.templateId = Long.valueOf(templateId2);
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.MagicalTripsClientParameters.Builder guestCount(int guestCount2) {
            this.guestCount = Integer.valueOf(guestCount2);
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.MagicalTripsClientParameters.Builder secondaryGuests(ArrayList<TripSecondaryGuest> secondaryGuests2) {
            if (secondaryGuests2 == null) {
                throw new NullPointerException("Null secondaryGuests");
            }
            this.secondaryGuests = secondaryGuests2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.MagicalTripsClientParameters.Builder guestAddress(Map<String, String> guestAddress2) {
            this.guestAddress = guestAddress2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.MagicalTripsClientParameters.Builder travelPurpose(Long travelPurpose2) {
            this.travelPurpose = travelPurpose2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public MagicalTripsClientParameters autoBuild() {
            String missing = "";
            if (this.productType == null) {
                missing = missing + " productType";
            }
            if (this.templateId == null) {
                missing = missing + " templateId";
            }
            if (this.guestCount == null) {
                missing = missing + " guestCount";
            }
            if (this.secondaryGuests == null) {
                missing = missing + " secondaryGuests";
            }
            if (missing.isEmpty()) {
                return new AutoValue_MagicalTripsClientParameters(this.productType, this.templateId.longValue(), this.guestCount.intValue(), this.secondaryGuests, this.guestAddress, this.travelPurpose);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_MagicalTripsClientParameters(BillProductType productType2, long templateId2, int guestCount2, ArrayList<TripSecondaryGuest> secondaryGuests2, Map<String, String> guestAddress2, Long travelPurpose2) {
        if (productType2 == null) {
            throw new NullPointerException("Null productType");
        }
        this.productType = productType2;
        this.templateId = templateId2;
        this.guestCount = guestCount2;
        if (secondaryGuests2 == null) {
            throw new NullPointerException("Null secondaryGuests");
        }
        this.secondaryGuests = secondaryGuests2;
        this.guestAddress = guestAddress2;
        this.travelPurpose = travelPurpose2;
    }

    public BillProductType productType() {
        return this.productType;
    }

    public long templateId() {
        return this.templateId;
    }

    public int guestCount() {
        return this.guestCount;
    }

    public ArrayList<TripSecondaryGuest> secondaryGuests() {
        return this.secondaryGuests;
    }

    public Map<String, String> guestAddress() {
        return this.guestAddress;
    }

    public Long travelPurpose() {
        return this.travelPurpose;
    }

    public String toString() {
        return "MagicalTripsClientParameters{productType=" + this.productType + ", templateId=" + this.templateId + ", guestCount=" + this.guestCount + ", secondaryGuests=" + this.secondaryGuests + ", guestAddress=" + this.guestAddress + ", travelPurpose=" + this.travelPurpose + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof MagicalTripsClientParameters)) {
            return false;
        }
        MagicalTripsClientParameters that = (MagicalTripsClientParameters) o;
        if (this.productType.equals(that.productType()) && this.templateId == that.templateId() && this.guestCount == that.guestCount() && this.secondaryGuests.equals(that.secondaryGuests()) && (this.guestAddress != null ? this.guestAddress.equals(that.guestAddress()) : that.guestAddress() == null)) {
            if (this.travelPurpose == null) {
                if (that.travelPurpose() == null) {
                    return true;
                }
            } else if (this.travelPurpose.equals(that.travelPurpose())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((int) (((long) (((1 * 1000003) ^ this.productType.hashCode()) * 1000003)) ^ ((this.templateId >>> 32) ^ this.templateId))) * 1000003) ^ this.guestCount) * 1000003) ^ this.secondaryGuests.hashCode()) * 1000003) ^ (this.guestAddress == null ? 0 : this.guestAddress.hashCode())) * 1000003;
        if (this.travelPurpose != null) {
            i = this.travelPurpose.hashCode();
        }
        return h ^ i;
    }
}
