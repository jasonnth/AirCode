package com.airbnb.android.core.payments.models.clientparameters;

import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.payments.models.BillProductType;

/* renamed from: com.airbnb.android.core.payments.models.clientparameters.$AutoValue_GiftCardClientParameters reason: invalid class name */
abstract class C$AutoValue_GiftCardClientParameters extends GiftCardClientParameters {
    private final String categoryType;
    private final CurrencyAmount giftCreditCurrencyAmount;
    private final String locale;
    private final long overlayId;
    private final BillProductType productType;
    private final String recipientEmail;
    private final String recipientMessage;
    private final String recipientName;
    private final long videoId;

    /* renamed from: com.airbnb.android.core.payments.models.clientparameters.$AutoValue_GiftCardClientParameters$Builder */
    static final class Builder extends com.airbnb.android.core.payments.models.clientparameters.GiftCardClientParameters.Builder {
        private String categoryType;
        private CurrencyAmount giftCreditCurrencyAmount;
        private String locale;
        private Long overlayId;
        private BillProductType productType;
        private String recipientEmail;
        private String recipientMessage;
        private String recipientName;
        private Long videoId;

        Builder() {
        }

        /* access modifiers changed from: 0000 */
        public com.airbnb.android.core.payments.models.clientparameters.GiftCardClientParameters.Builder productType(BillProductType productType2) {
            if (productType2 == null) {
                throw new NullPointerException("Null productType");
            }
            this.productType = productType2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.GiftCardClientParameters.Builder recipientMessage(String recipientMessage2) {
            if (recipientMessage2 == null) {
                throw new NullPointerException("Null recipientMessage");
            }
            this.recipientMessage = recipientMessage2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.GiftCardClientParameters.Builder recipientEmail(String recipientEmail2) {
            if (recipientEmail2 == null) {
                throw new NullPointerException("Null recipientEmail");
            }
            this.recipientEmail = recipientEmail2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.GiftCardClientParameters.Builder recipientName(String recipientName2) {
            if (recipientName2 == null) {
                throw new NullPointerException("Null recipientName");
            }
            this.recipientName = recipientName2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.GiftCardClientParameters.Builder giftCreditCurrencyAmount(CurrencyAmount giftCreditCurrencyAmount2) {
            if (giftCreditCurrencyAmount2 == null) {
                throw new NullPointerException("Null giftCreditCurrencyAmount");
            }
            this.giftCreditCurrencyAmount = giftCreditCurrencyAmount2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.GiftCardClientParameters.Builder categoryType(String categoryType2) {
            if (categoryType2 == null) {
                throw new NullPointerException("Null categoryType");
            }
            this.categoryType = categoryType2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.GiftCardClientParameters.Builder locale(String locale2) {
            if (locale2 == null) {
                throw new NullPointerException("Null locale");
            }
            this.locale = locale2;
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.GiftCardClientParameters.Builder overlayId(long overlayId2) {
            this.overlayId = Long.valueOf(overlayId2);
            return this;
        }

        public com.airbnb.android.core.payments.models.clientparameters.GiftCardClientParameters.Builder videoId(long videoId2) {
            this.videoId = Long.valueOf(videoId2);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public GiftCardClientParameters autoBuild() {
            String missing = "";
            if (this.productType == null) {
                missing = missing + " productType";
            }
            if (this.recipientMessage == null) {
                missing = missing + " recipientMessage";
            }
            if (this.recipientEmail == null) {
                missing = missing + " recipientEmail";
            }
            if (this.recipientName == null) {
                missing = missing + " recipientName";
            }
            if (this.giftCreditCurrencyAmount == null) {
                missing = missing + " giftCreditCurrencyAmount";
            }
            if (this.categoryType == null) {
                missing = missing + " categoryType";
            }
            if (this.locale == null) {
                missing = missing + " locale";
            }
            if (this.overlayId == null) {
                missing = missing + " overlayId";
            }
            if (this.videoId == null) {
                missing = missing + " videoId";
            }
            if (missing.isEmpty()) {
                return new AutoValue_GiftCardClientParameters(this.productType, this.recipientMessage, this.recipientEmail, this.recipientName, this.giftCreditCurrencyAmount, this.categoryType, this.locale, this.overlayId.longValue(), this.videoId.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_GiftCardClientParameters(BillProductType productType2, String recipientMessage2, String recipientEmail2, String recipientName2, CurrencyAmount giftCreditCurrencyAmount2, String categoryType2, String locale2, long overlayId2, long videoId2) {
        if (productType2 == null) {
            throw new NullPointerException("Null productType");
        }
        this.productType = productType2;
        if (recipientMessage2 == null) {
            throw new NullPointerException("Null recipientMessage");
        }
        this.recipientMessage = recipientMessage2;
        if (recipientEmail2 == null) {
            throw new NullPointerException("Null recipientEmail");
        }
        this.recipientEmail = recipientEmail2;
        if (recipientName2 == null) {
            throw new NullPointerException("Null recipientName");
        }
        this.recipientName = recipientName2;
        if (giftCreditCurrencyAmount2 == null) {
            throw new NullPointerException("Null giftCreditCurrencyAmount");
        }
        this.giftCreditCurrencyAmount = giftCreditCurrencyAmount2;
        if (categoryType2 == null) {
            throw new NullPointerException("Null categoryType");
        }
        this.categoryType = categoryType2;
        if (locale2 == null) {
            throw new NullPointerException("Null locale");
        }
        this.locale = locale2;
        this.overlayId = overlayId2;
        this.videoId = videoId2;
    }

    public BillProductType productType() {
        return this.productType;
    }

    public String recipientMessage() {
        return this.recipientMessage;
    }

    public String recipientEmail() {
        return this.recipientEmail;
    }

    public String recipientName() {
        return this.recipientName;
    }

    public CurrencyAmount giftCreditCurrencyAmount() {
        return this.giftCreditCurrencyAmount;
    }

    public String categoryType() {
        return this.categoryType;
    }

    public String locale() {
        return this.locale;
    }

    public long overlayId() {
        return this.overlayId;
    }

    public long videoId() {
        return this.videoId;
    }

    public String toString() {
        return "GiftCardClientParameters{productType=" + this.productType + ", recipientMessage=" + this.recipientMessage + ", recipientEmail=" + this.recipientEmail + ", recipientName=" + this.recipientName + ", giftCreditCurrencyAmount=" + this.giftCreditCurrencyAmount + ", categoryType=" + this.categoryType + ", locale=" + this.locale + ", overlayId=" + this.overlayId + ", videoId=" + this.videoId + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof GiftCardClientParameters)) {
            return false;
        }
        GiftCardClientParameters that = (GiftCardClientParameters) o;
        if (!this.productType.equals(that.productType()) || !this.recipientMessage.equals(that.recipientMessage()) || !this.recipientEmail.equals(that.recipientEmail()) || !this.recipientName.equals(that.recipientName()) || !this.giftCreditCurrencyAmount.equals(that.giftCreditCurrencyAmount()) || !this.categoryType.equals(that.categoryType()) || !this.locale.equals(that.locale()) || this.overlayId != that.overlayId() || this.videoId != that.videoId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (((long) (((int) (((long) (((((((((((((((1 * 1000003) ^ this.productType.hashCode()) * 1000003) ^ this.recipientMessage.hashCode()) * 1000003) ^ this.recipientEmail.hashCode()) * 1000003) ^ this.recipientName.hashCode()) * 1000003) ^ this.giftCreditCurrencyAmount.hashCode()) * 1000003) ^ this.categoryType.hashCode()) * 1000003) ^ this.locale.hashCode()) * 1000003)) ^ ((this.overlayId >>> 32) ^ this.overlayId))) * 1000003)) ^ ((this.videoId >>> 32) ^ this.videoId));
    }
}
