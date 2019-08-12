package com.airbnb.android.core.airlock.models;

import com.airbnb.android.core.airlock.enums.AirlockStatus;
import java.util.List;

/* renamed from: com.airbnb.android.core.airlock.models.$AutoValue_ChargebackAppealFriction reason: invalid class name */
abstract class C$AutoValue_ChargebackAppealFriction extends ChargebackAppealFriction {
    private final AirlockStatus airlockStatus;
    private final String ccLastFourDigits;
    private final String ccType;
    private final List<String> emails;
    private final long hostingId;
    private final String hostingName;
    private final long paymentInstrumentId;
    private final long reservationId;
    private final double version;

    /* renamed from: com.airbnb.android.core.airlock.models.$AutoValue_ChargebackAppealFriction$Builder */
    static final class Builder extends com.airbnb.android.core.airlock.models.ChargebackAppealFriction.Builder {
        private AirlockStatus airlockStatus;
        private String ccLastFourDigits;
        private String ccType;
        private List<String> emails;
        private Long hostingId;
        private String hostingName;
        private Long paymentInstrumentId;
        private Long reservationId;
        private Double version;

        Builder() {
        }

        public com.airbnb.android.core.airlock.models.ChargebackAppealFriction.Builder version(double version2) {
            this.version = Double.valueOf(version2);
            return this;
        }

        public com.airbnb.android.core.airlock.models.ChargebackAppealFriction.Builder airlockStatus(AirlockStatus airlockStatus2) {
            if (airlockStatus2 == null) {
                throw new NullPointerException("Null airlockStatus");
            }
            this.airlockStatus = airlockStatus2;
            return this;
        }

        public com.airbnb.android.core.airlock.models.ChargebackAppealFriction.Builder ccLastFourDigits(String ccLastFourDigits2) {
            if (ccLastFourDigits2 == null) {
                throw new NullPointerException("Null ccLastFourDigits");
            }
            this.ccLastFourDigits = ccLastFourDigits2;
            return this;
        }

        public com.airbnb.android.core.airlock.models.ChargebackAppealFriction.Builder ccType(String ccType2) {
            if (ccType2 == null) {
                throw new NullPointerException("Null ccType");
            }
            this.ccType = ccType2;
            return this;
        }

        public com.airbnb.android.core.airlock.models.ChargebackAppealFriction.Builder emails(List<String> emails2) {
            if (emails2 == null) {
                throw new NullPointerException("Null emails");
            }
            this.emails = emails2;
            return this;
        }

        public com.airbnb.android.core.airlock.models.ChargebackAppealFriction.Builder hostingId(long hostingId2) {
            this.hostingId = Long.valueOf(hostingId2);
            return this;
        }

        public com.airbnb.android.core.airlock.models.ChargebackAppealFriction.Builder hostingName(String hostingName2) {
            if (hostingName2 == null) {
                throw new NullPointerException("Null hostingName");
            }
            this.hostingName = hostingName2;
            return this;
        }

        public com.airbnb.android.core.airlock.models.ChargebackAppealFriction.Builder paymentInstrumentId(long paymentInstrumentId2) {
            this.paymentInstrumentId = Long.valueOf(paymentInstrumentId2);
            return this;
        }

        public com.airbnb.android.core.airlock.models.ChargebackAppealFriction.Builder reservationId(long reservationId2) {
            this.reservationId = Long.valueOf(reservationId2);
            return this;
        }

        public ChargebackAppealFriction build() {
            String missing = "";
            if (this.version == null) {
                missing = missing + " version";
            }
            if (this.airlockStatus == null) {
                missing = missing + " airlockStatus";
            }
            if (this.ccLastFourDigits == null) {
                missing = missing + " ccLastFourDigits";
            }
            if (this.ccType == null) {
                missing = missing + " ccType";
            }
            if (this.emails == null) {
                missing = missing + " emails";
            }
            if (this.hostingId == null) {
                missing = missing + " hostingId";
            }
            if (this.hostingName == null) {
                missing = missing + " hostingName";
            }
            if (this.paymentInstrumentId == null) {
                missing = missing + " paymentInstrumentId";
            }
            if (this.reservationId == null) {
                missing = missing + " reservationId";
            }
            if (missing.isEmpty()) {
                return new AutoValue_ChargebackAppealFriction(this.version.doubleValue(), this.airlockStatus, this.ccLastFourDigits, this.ccType, this.emails, this.hostingId.longValue(), this.hostingName, this.paymentInstrumentId.longValue(), this.reservationId.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_ChargebackAppealFriction(double version2, AirlockStatus airlockStatus2, String ccLastFourDigits2, String ccType2, List<String> emails2, long hostingId2, String hostingName2, long paymentInstrumentId2, long reservationId2) {
        this.version = version2;
        if (airlockStatus2 == null) {
            throw new NullPointerException("Null airlockStatus");
        }
        this.airlockStatus = airlockStatus2;
        if (ccLastFourDigits2 == null) {
            throw new NullPointerException("Null ccLastFourDigits");
        }
        this.ccLastFourDigits = ccLastFourDigits2;
        if (ccType2 == null) {
            throw new NullPointerException("Null ccType");
        }
        this.ccType = ccType2;
        if (emails2 == null) {
            throw new NullPointerException("Null emails");
        }
        this.emails = emails2;
        this.hostingId = hostingId2;
        if (hostingName2 == null) {
            throw new NullPointerException("Null hostingName");
        }
        this.hostingName = hostingName2;
        this.paymentInstrumentId = paymentInstrumentId2;
        this.reservationId = reservationId2;
    }

    public double version() {
        return this.version;
    }

    public AirlockStatus airlockStatus() {
        return this.airlockStatus;
    }

    public String ccLastFourDigits() {
        return this.ccLastFourDigits;
    }

    public String ccType() {
        return this.ccType;
    }

    public List<String> emails() {
        return this.emails;
    }

    public long hostingId() {
        return this.hostingId;
    }

    public String hostingName() {
        return this.hostingName;
    }

    public long paymentInstrumentId() {
        return this.paymentInstrumentId;
    }

    public long reservationId() {
        return this.reservationId;
    }

    public String toString() {
        return "ChargebackAppealFriction{version=" + this.version + ", airlockStatus=" + this.airlockStatus + ", ccLastFourDigits=" + this.ccLastFourDigits + ", ccType=" + this.ccType + ", emails=" + this.emails + ", hostingId=" + this.hostingId + ", hostingName=" + this.hostingName + ", paymentInstrumentId=" + this.paymentInstrumentId + ", reservationId=" + this.reservationId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ChargebackAppealFriction)) {
            return false;
        }
        ChargebackAppealFriction that = (ChargebackAppealFriction) o;
        if (Double.doubleToLongBits(this.version) != Double.doubleToLongBits(that.version()) || !this.airlockStatus.equals(that.airlockStatus()) || !this.ccLastFourDigits.equals(that.ccLastFourDigits()) || !this.ccType.equals(that.ccType()) || !this.emails.equals(that.emails()) || this.hostingId != that.hostingId() || !this.hostingName.equals(that.hostingName()) || this.paymentInstrumentId != that.paymentInstrumentId() || this.reservationId != that.reservationId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (((long) (((int) (((long) (((((int) (((long) (((((((((((int) (((long) (1 * 1000003)) ^ ((Double.doubleToLongBits(this.version) >>> 32) ^ Double.doubleToLongBits(this.version)))) * 1000003) ^ this.airlockStatus.hashCode()) * 1000003) ^ this.ccLastFourDigits.hashCode()) * 1000003) ^ this.ccType.hashCode()) * 1000003) ^ this.emails.hashCode()) * 1000003)) ^ ((this.hostingId >>> 32) ^ this.hostingId))) * 1000003) ^ this.hostingName.hashCode()) * 1000003)) ^ ((this.paymentInstrumentId >>> 32) ^ this.paymentInstrumentId))) * 1000003)) ^ ((this.reservationId >>> 32) ^ this.reservationId));
    }
}
