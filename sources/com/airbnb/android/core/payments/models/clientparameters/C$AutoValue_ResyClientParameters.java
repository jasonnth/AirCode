package com.airbnb.android.core.payments.models.clientparameters;

import com.airbnb.android.core.payments.models.BillProductType;

/* renamed from: com.airbnb.android.core.payments.models.clientparameters.$AutoValue_ResyClientParameters reason: invalid class name */
abstract class C$AutoValue_ResyClientParameters extends ResyClientParameters {
    private final long activityId;
    private final int numberOfGuests;
    private final BillProductType productType;
    private final long reservationId;

    /* renamed from: com.airbnb.android.core.payments.models.clientparameters.$AutoValue_ResyClientParameters$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.clientparameters.ResyClientParameters.Builder {
        private Long activityId;
        private Integer numberOfGuests;
        private BillProductType productType;
        private Long reservationId;

        Builder() {
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.core.payments.models.clientparameters.ResyClientParameters.Builder productType(BillProductType productType2) {
            if (productType2 == null) {
                throw new NullPointerException("Null productType");
            }
            this.productType = productType2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.ResyClientParameters.Builder reservationId(long reservationId2) {
            this.reservationId = Long.valueOf(reservationId2);
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.ResyClientParameters.Builder numberOfGuests(int numberOfGuests2) {
            this.numberOfGuests = Integer.valueOf(numberOfGuests2);
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.ResyClientParameters.Builder activityId(long activityId2) {
            this.activityId = Long.valueOf(activityId2);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public ResyClientParameters autoBuild() {
            String missing = "";
            if (this.productType == null) {
                missing = missing + " productType";
            }
            if (this.reservationId == null) {
                missing = missing + " reservationId";
            }
            if (this.numberOfGuests == null) {
                missing = missing + " numberOfGuests";
            }
            if (this.activityId == null) {
                missing = missing + " activityId";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ResyClientParameters(this.productType, this.reservationId.longValue(), this.numberOfGuests.intValue(), this.activityId.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_ResyClientParameters(BillProductType productType2, long reservationId2, int numberOfGuests2, long activityId2) {
        if (productType2 == null) {
            throw new NullPointerException("Null productType");
        }
        this.productType = productType2;
        this.reservationId = reservationId2;
        this.numberOfGuests = numberOfGuests2;
        this.activityId = activityId2;
    }

    public BillProductType productType() {
        return this.productType;
    }

    public long reservationId() {
        return this.reservationId;
    }

    public int numberOfGuests() {
        return this.numberOfGuests;
    }

    public long activityId() {
        return this.activityId;
    }

    public String toString() {
        return "ResyClientParameters{productType=" + this.productType + ", reservationId=" + this.reservationId + ", numberOfGuests=" + this.numberOfGuests + ", activityId=" + this.activityId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ResyClientParameters)) {
            return false;
        }
        ResyClientParameters that = (ResyClientParameters) o;
        if (this.productType.equals(that.productType()) && this.reservationId == that.reservationId() && this.numberOfGuests == that.numberOfGuests() && this.activityId == that.activityId()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) (((long) (((((int) (((long) (((1 * 1000003) ^ this.productType.hashCode()) * 1000003)) ^ ((this.reservationId >>> 32) ^ this.reservationId))) * 1000003) ^ this.numberOfGuests) * 1000003)) ^ ((this.activityId >>> 32) ^ this.activityId));
    }
}
