package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.businesstravel.models.BusinessEntityGroup;
import com.airbnb.android.core.models.PaymentSettlementQuote;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPaymentOption implements Parcelable {
    @JsonProperty("business_entity_group")
    protected BusinessEntityGroup mBusinessEntityGroup;
    @JsonProperty("credit_card_last_four")
    protected String mCreditCardLastFour;
    @JsonProperty("credit_card_type")
    protected String mCreditCardType;
    @JsonProperty("details_text")
    protected String mDetailsText;
    @JsonProperty("expire_date")
    protected String mExpireDate;
    @JsonProperty("gibraltar_instrument_id")
    protected long mGibraltarInstrumentId;
    @JsonProperty("is_debit")
    protected boolean mIsDebit;
    @JsonProperty("is_default")
    protected boolean mIsDefault;
    @JsonProperty("is_existing_instrument")
    protected boolean mIsExistingInstrument;
    @JsonProperty("instrument_id")
    protected long mLegacyInstrumentId;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("owner_name")
    protected String mOwnerName;
    @JsonProperty("payment_method_type")
    protected String mPaymentMethodType;
    @JsonProperty("provider_name")
    protected String mProviderName;
    @JsonProperty("settlement_currency")
    protected String mSettlementCurrency;
    @JsonProperty("settlement_quote")
    protected PaymentSettlementQuote mSettlementQuote;

    protected GenPaymentOption(BusinessEntityGroup businessEntityGroup, PaymentSettlementQuote settlementQuote, String creditCardType, String creditCardLastFour, String name, String ownerName, String paymentMethodType, String providerName, String settlementCurrency, String expireDate, String detailsText, boolean isDebit, boolean isDefault, boolean isExistingInstrument, long legacyInstrumentId, long gibraltarInstrumentId) {
        this();
        this.mBusinessEntityGroup = businessEntityGroup;
        this.mSettlementQuote = settlementQuote;
        this.mCreditCardType = creditCardType;
        this.mCreditCardLastFour = creditCardLastFour;
        this.mName = name;
        this.mOwnerName = ownerName;
        this.mPaymentMethodType = paymentMethodType;
        this.mProviderName = providerName;
        this.mSettlementCurrency = settlementCurrency;
        this.mExpireDate = expireDate;
        this.mDetailsText = detailsText;
        this.mIsDebit = isDebit;
        this.mIsDefault = isDefault;
        this.mIsExistingInstrument = isExistingInstrument;
        this.mLegacyInstrumentId = legacyInstrumentId;
        this.mGibraltarInstrumentId = gibraltarInstrumentId;
    }

    protected GenPaymentOption() {
    }

    public BusinessEntityGroup getBusinessEntityGroup() {
        return this.mBusinessEntityGroup;
    }

    @JsonProperty("business_entity_group")
    public void setBusinessEntityGroup(BusinessEntityGroup value) {
        this.mBusinessEntityGroup = value;
    }

    public PaymentSettlementQuote getSettlementQuote() {
        return this.mSettlementQuote;
    }

    @JsonProperty("settlement_quote")
    public void setSettlementQuote(PaymentSettlementQuote value) {
        this.mSettlementQuote = value;
    }

    public String getCreditCardType() {
        return this.mCreditCardType;
    }

    @JsonProperty("credit_card_type")
    public void setCreditCardType(String value) {
        this.mCreditCardType = value;
    }

    public String getCreditCardLastFour() {
        return this.mCreditCardLastFour;
    }

    @JsonProperty("credit_card_last_four")
    public void setCreditCardLastFour(String value) {
        this.mCreditCardLastFour = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getOwnerName() {
        return this.mOwnerName;
    }

    @JsonProperty("owner_name")
    public void setOwnerName(String value) {
        this.mOwnerName = value;
    }

    public String getPaymentMethodType() {
        return this.mPaymentMethodType;
    }

    @JsonProperty("payment_method_type")
    public void setPaymentMethodType(String value) {
        this.mPaymentMethodType = value;
    }

    public String getProviderName() {
        return this.mProviderName;
    }

    @JsonProperty("provider_name")
    public void setProviderName(String value) {
        this.mProviderName = value;
    }

    public String getSettlementCurrency() {
        return this.mSettlementCurrency;
    }

    @JsonProperty("settlement_currency")
    public void setSettlementCurrency(String value) {
        this.mSettlementCurrency = value;
    }

    public String getExpireDate() {
        return this.mExpireDate;
    }

    @JsonProperty("expire_date")
    public void setExpireDate(String value) {
        this.mExpireDate = value;
    }

    public String getDetailsText() {
        return this.mDetailsText;
    }

    @JsonProperty("details_text")
    public void setDetailsText(String value) {
        this.mDetailsText = value;
    }

    public boolean isDebit() {
        return this.mIsDebit;
    }

    @JsonProperty("is_debit")
    public void setIsDebit(boolean value) {
        this.mIsDebit = value;
    }

    public boolean isDefault() {
        return this.mIsDefault;
    }

    @JsonProperty("is_default")
    public void setIsDefault(boolean value) {
        this.mIsDefault = value;
    }

    public boolean isExistingInstrument() {
        return this.mIsExistingInstrument;
    }

    @JsonProperty("is_existing_instrument")
    public void setIsExistingInstrument(boolean value) {
        this.mIsExistingInstrument = value;
    }

    public long getLegacyInstrumentId() {
        return this.mLegacyInstrumentId;
    }

    @JsonProperty("instrument_id")
    public void setLegacyInstrumentId(long value) {
        this.mLegacyInstrumentId = value;
    }

    public long getGibraltarInstrumentId() {
        return this.mGibraltarInstrumentId;
    }

    @JsonProperty("gibraltar_instrument_id")
    public void setGibraltarInstrumentId(long value) {
        this.mGibraltarInstrumentId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mBusinessEntityGroup, 0);
        parcel.writeParcelable(this.mSettlementQuote, 0);
        parcel.writeString(this.mCreditCardType);
        parcel.writeString(this.mCreditCardLastFour);
        parcel.writeString(this.mName);
        parcel.writeString(this.mOwnerName);
        parcel.writeString(this.mPaymentMethodType);
        parcel.writeString(this.mProviderName);
        parcel.writeString(this.mSettlementCurrency);
        parcel.writeString(this.mExpireDate);
        parcel.writeString(this.mDetailsText);
        parcel.writeBooleanArray(new boolean[]{this.mIsDebit, this.mIsDefault, this.mIsExistingInstrument});
        parcel.writeLong(this.mLegacyInstrumentId);
        parcel.writeLong(this.mGibraltarInstrumentId);
    }

    public void readFromParcel(Parcel source) {
        this.mBusinessEntityGroup = (BusinessEntityGroup) source.readParcelable(BusinessEntityGroup.class.getClassLoader());
        this.mSettlementQuote = (PaymentSettlementQuote) source.readParcelable(PaymentSettlementQuote.class.getClassLoader());
        this.mCreditCardType = source.readString();
        this.mCreditCardLastFour = source.readString();
        this.mName = source.readString();
        this.mOwnerName = source.readString();
        this.mPaymentMethodType = source.readString();
        this.mProviderName = source.readString();
        this.mSettlementCurrency = source.readString();
        this.mExpireDate = source.readString();
        this.mDetailsText = source.readString();
        boolean[] bools = source.createBooleanArray();
        this.mIsDebit = bools[0];
        this.mIsDefault = bools[1];
        this.mIsExistingInstrument = bools[2];
        this.mLegacyInstrumentId = source.readLong();
        this.mGibraltarInstrumentId = source.readLong();
    }
}
