package com.airbnb.android.core.payments.models.clientparameters;

import com.airbnb.android.core.payments.models.BillProductType;

/* renamed from: com.airbnb.android.core.payments.models.clientparameters.$AutoValue_PaidAmenityClientParameters reason: invalid class name */
abstract class C$AutoValue_PaidAmenityClientParameters extends PaidAmenityClientParameters {
    private final int numberOfUnits;
    private final long paidAmenityId;
    private final BillProductType productType;
    private final String reservationConfirmationCode;

    /* renamed from: com.airbnb.android.core.payments.models.clientparameters.$AutoValue_PaidAmenityClientParameters$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.clientparameters.PaidAmenityClientParameters.Builder {
        private Integer numberOfUnits;
        private Long paidAmenityId;
        private BillProductType productType;
        private String reservationConfirmationCode;

        Builder() {
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.core.payments.models.clientparameters.PaidAmenityClientParameters.Builder productType(BillProductType productType2) {
            if (productType2 == null) {
                throw new NullPointerException("Null productType");
            }
            this.productType = productType2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.PaidAmenityClientParameters.Builder paidAmenityId(long paidAmenityId2) {
            this.paidAmenityId = Long.valueOf(paidAmenityId2);
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.PaidAmenityClientParameters.Builder numberOfUnits(int numberOfUnits2) {
            this.numberOfUnits = Integer.valueOf(numberOfUnits2);
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.PaidAmenityClientParameters.Builder reservationConfirmationCode(String reservationConfirmationCode2) {
            if (reservationConfirmationCode2 == null) {
                throw new NullPointerException("Null reservationConfirmationCode");
            }
            this.reservationConfirmationCode = reservationConfirmationCode2;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public PaidAmenityClientParameters autoBuild() {
            String missing = "";
            if (this.productType == null) {
                missing = missing + " productType";
            }
            if (this.paidAmenityId == null) {
                missing = missing + " paidAmenityId";
            }
            if (this.numberOfUnits == null) {
                missing = missing + " numberOfUnits";
            }
            if (this.reservationConfirmationCode == null) {
                missing = missing + " reservationConfirmationCode";
            }
            if (missing.isEmpty()) {
                return new AutoValue_PaidAmenityClientParameters(this.productType, this.paidAmenityId.longValue(), this.numberOfUnits.intValue(), this.reservationConfirmationCode);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_PaidAmenityClientParameters(BillProductType productType2, long paidAmenityId2, int numberOfUnits2, String reservationConfirmationCode2) {
        if (productType2 == null) {
            throw new NullPointerException("Null productType");
        }
        this.productType = productType2;
        this.paidAmenityId = paidAmenityId2;
        this.numberOfUnits = numberOfUnits2;
        if (reservationConfirmationCode2 == null) {
            throw new NullPointerException("Null reservationConfirmationCode");
        }
        this.reservationConfirmationCode = reservationConfirmationCode2;
    }

    public BillProductType productType() {
        return this.productType;
    }

    public long paidAmenityId() {
        return this.paidAmenityId;
    }

    public int numberOfUnits() {
        return this.numberOfUnits;
    }

    public String reservationConfirmationCode() {
        return this.reservationConfirmationCode;
    }

    public String toString() {
        return "PaidAmenityClientParameters{productType=" + this.productType + ", paidAmenityId=" + this.paidAmenityId + ", numberOfUnits=" + this.numberOfUnits + ", reservationConfirmationCode=" + this.reservationConfirmationCode + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PaidAmenityClientParameters)) {
            return false;
        }
        PaidAmenityClientParameters that = (PaidAmenityClientParameters) o;
        if (!this.productType.equals(that.productType()) || this.paidAmenityId != that.paidAmenityId() || this.numberOfUnits != that.numberOfUnits() || !this.reservationConfirmationCode.equals(that.reservationConfirmationCode())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((int) (((long) (((1 * 1000003) ^ this.productType.hashCode()) * 1000003)) ^ ((this.paidAmenityId >>> 32) ^ this.paidAmenityId))) * 1000003) ^ this.numberOfUnits) * 1000003) ^ this.reservationConfirmationCode.hashCode();
    }
}
