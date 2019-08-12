package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.payments.C6200PaymentInstrumentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenPayoutInfoType implements Parcelable {
    @JsonProperty("additional_fees_text")
    protected String mAdditionalFeesText;
    @JsonProperty("currencies")
    protected List<String> mCurrencies;
    @JsonProperty("details_text")
    protected String mDetailsText;
    @JsonProperty("info_type")
    protected C6200PaymentInstrumentType mInfoType;
    @JsonProperty("payout_method_text")
    protected String mPayoutMethodText;
    @JsonProperty("processing_time_text")
    protected String mProcessingTimeText;

    protected GenPayoutInfoType(List<String> currencies, C6200PaymentInstrumentType infoType, String additionalFeesText, String detailsText, String payoutMethodText, String processingTimeText) {
        this();
        this.mCurrencies = currencies;
        this.mInfoType = infoType;
        this.mAdditionalFeesText = additionalFeesText;
        this.mDetailsText = detailsText;
        this.mPayoutMethodText = payoutMethodText;
        this.mProcessingTimeText = processingTimeText;
    }

    protected GenPayoutInfoType() {
    }

    public List<String> getCurrencies() {
        return this.mCurrencies;
    }

    @JsonProperty("currencies")
    public void setCurrencies(List<String> value) {
        this.mCurrencies = value;
    }

    public C6200PaymentInstrumentType getInfoType() {
        return this.mInfoType;
    }

    public String getAdditionalFeesText() {
        return this.mAdditionalFeesText;
    }

    @JsonProperty("additional_fees_text")
    public void setAdditionalFeesText(String value) {
        this.mAdditionalFeesText = value;
    }

    public String getDetailsText() {
        return this.mDetailsText;
    }

    @JsonProperty("details_text")
    public void setDetailsText(String value) {
        this.mDetailsText = value;
    }

    public String getPayoutMethodText() {
        return this.mPayoutMethodText;
    }

    @JsonProperty("payout_method_text")
    public void setPayoutMethodText(String value) {
        this.mPayoutMethodText = value;
    }

    public String getProcessingTimeText() {
        return this.mProcessingTimeText;
    }

    @JsonProperty("processing_time_text")
    public void setProcessingTimeText(String value) {
        this.mProcessingTimeText = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeStringList(this.mCurrencies);
        parcel.writeParcelable(this.mInfoType, 0);
        parcel.writeString(this.mAdditionalFeesText);
        parcel.writeString(this.mDetailsText);
        parcel.writeString(this.mPayoutMethodText);
        parcel.writeString(this.mProcessingTimeText);
    }

    public void readFromParcel(Parcel source) {
        this.mCurrencies = source.createStringArrayList();
        this.mInfoType = (C6200PaymentInstrumentType) source.readParcelable(C6200PaymentInstrumentType.class.getClassLoader());
        this.mAdditionalFeesText = source.readString();
        this.mDetailsText = source.readString();
        this.mPayoutMethodText = source.readString();
        this.mProcessingTimeText = source.readString();
    }
}
