package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.enums.CancellationPolicyLabel;
import com.airbnb.android.core.models.CancellationRefundBanner;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.models.FullRefundUpsellInfo;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.PriceFactor;
import com.airbnb.android.core.models.PricingQuote.RateType;
import com.airbnb.android.core.models.UrgencyMessageData;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenPricingQuote implements Parcelable {
    @JsonProperty("available")
    protected boolean mAvailable;
    @JsonProperty("cancellation_policy_label")
    protected CancellationPolicyLabel mCancellationPolicyLabel;
    @JsonProperty("cancellation_policy_link")
    protected String mCancellationPolicyLink;
    @JsonProperty("check_in")
    protected AirDate mCheckIn;
    @JsonProperty("check_out")
    protected AirDate mCheckOut;
    @JsonProperty("dateless_available")
    protected boolean mDatelessAvailable;
    @JsonProperty("guests")
    protected int mGuests;
    @JsonProperty("host_payout_breakdown")
    protected Price mHostPayoutBreakdown;
    @JsonProperty("host_subtotal_amount")
    protected CurrencyAmount mHostSubtotalAmount;
    @JsonProperty("installments")
    protected List<Price> mInstallments;
    @JsonProperty("can_instant_book")
    protected boolean mInstantBookable;
    @JsonProperty("localized_cancellation_policy_name")
    protected String mLocalizedCancellationPolicyName;
    @JsonProperty("localized_unavailability_message")
    protected String mLocalizedUnavailabilityMessage;
    @JsonProperty("monthly_price_factor")
    protected PriceFactor mMonthlyPriceFactor;
    @JsonProperty("p3_cancellation_refund_banner")
    protected CancellationRefundBanner mP3CancellationRefundBanner;
    @JsonProperty("p3_percentage_recommended")
    protected int mP3PercentageRecommended;
    @JsonProperty("p4_cancellation_refund_banner")
    protected CancellationRefundBanner mP4CancellationRefundBanner;
    @JsonProperty("p4_full_refund_upsell_info")
    protected FullRefundUpsellInfo mP4FullRefundUpsellInfo;
    @JsonProperty("price")
    protected Price mPrice;
    @JsonProperty("rate")
    protected CurrencyAmount mRate;
    @JsonProperty("rate_as_guest_canonical")
    protected CurrencyAmount mRateAsGuestCanonical;
    @JsonProperty("rate_type")
    protected RateType mRateType;
    @JsonProperty("rate_with_service_fee")
    protected CurrencyAmount mRateWithServiceFee;
    @JsonProperty("refund")
    protected CurrencyAmount mRefund;
    @JsonProperty("p3_message_data")
    protected UrgencyMessageData mUrgencyMessageData;
    @JsonProperty("weekly_price_factor")
    protected PriceFactor mWeeklyPriceFactor;

    protected GenPricingQuote(AirDate checkIn, AirDate checkOut, CancellationPolicyLabel cancellationPolicyLabel, CancellationRefundBanner p3CancellationRefundBanner, CancellationRefundBanner p4CancellationRefundBanner, CurrencyAmount rate, CurrencyAmount rateWithServiceFee, CurrencyAmount rateAsGuestCanonical, CurrencyAmount refund, CurrencyAmount hostSubtotalAmount, FullRefundUpsellInfo p4FullRefundUpsellInfo, List<Price> installments, Price price, Price hostPayoutBreakdown, PriceFactor monthlyPriceFactor, PriceFactor weeklyPriceFactor, RateType rateType, String cancellationPolicyLink, String localizedCancellationPolicyName, String localizedUnavailabilityMessage, UrgencyMessageData urgencyMessageData, boolean instantBookable, boolean available, boolean datelessAvailable, int guests, int p3PercentageRecommended) {
        this();
        this.mCheckIn = checkIn;
        this.mCheckOut = checkOut;
        this.mCancellationPolicyLabel = cancellationPolicyLabel;
        this.mP3CancellationRefundBanner = p3CancellationRefundBanner;
        this.mP4CancellationRefundBanner = p4CancellationRefundBanner;
        this.mRate = rate;
        this.mRateWithServiceFee = rateWithServiceFee;
        this.mRateAsGuestCanonical = rateAsGuestCanonical;
        this.mRefund = refund;
        this.mHostSubtotalAmount = hostSubtotalAmount;
        this.mP4FullRefundUpsellInfo = p4FullRefundUpsellInfo;
        this.mInstallments = installments;
        this.mPrice = price;
        this.mHostPayoutBreakdown = hostPayoutBreakdown;
        this.mMonthlyPriceFactor = monthlyPriceFactor;
        this.mWeeklyPriceFactor = weeklyPriceFactor;
        this.mRateType = rateType;
        this.mCancellationPolicyLink = cancellationPolicyLink;
        this.mLocalizedCancellationPolicyName = localizedCancellationPolicyName;
        this.mLocalizedUnavailabilityMessage = localizedUnavailabilityMessage;
        this.mUrgencyMessageData = urgencyMessageData;
        this.mInstantBookable = instantBookable;
        this.mAvailable = available;
        this.mDatelessAvailable = datelessAvailable;
        this.mGuests = guests;
        this.mP3PercentageRecommended = p3PercentageRecommended;
    }

    protected GenPricingQuote() {
    }

    public AirDate getCheckIn() {
        return this.mCheckIn;
    }

    @JsonProperty("check_in")
    public void setCheckIn(AirDate value) {
        this.mCheckIn = value;
    }

    public AirDate getCheckOut() {
        return this.mCheckOut;
    }

    @JsonProperty("check_out")
    public void setCheckOut(AirDate value) {
        this.mCheckOut = value;
    }

    public CancellationPolicyLabel getCancellationPolicyLabel() {
        return this.mCancellationPolicyLabel;
    }

    public CancellationRefundBanner getP3CancellationRefundBanner() {
        return this.mP3CancellationRefundBanner;
    }

    @JsonProperty("p3_cancellation_refund_banner")
    public void setP3CancellationRefundBanner(CancellationRefundBanner value) {
        this.mP3CancellationRefundBanner = value;
    }

    public CancellationRefundBanner getP4CancellationRefundBanner() {
        return this.mP4CancellationRefundBanner;
    }

    @JsonProperty("p4_cancellation_refund_banner")
    public void setP4CancellationRefundBanner(CancellationRefundBanner value) {
        this.mP4CancellationRefundBanner = value;
    }

    public CurrencyAmount getRate() {
        return this.mRate;
    }

    @JsonProperty("rate")
    public void setRate(CurrencyAmount value) {
        this.mRate = value;
    }

    public CurrencyAmount getRateWithServiceFee() {
        return this.mRateWithServiceFee;
    }

    @JsonProperty("rate_with_service_fee")
    public void setRateWithServiceFee(CurrencyAmount value) {
        this.mRateWithServiceFee = value;
    }

    public CurrencyAmount getRateAsGuestCanonical() {
        return this.mRateAsGuestCanonical;
    }

    @JsonProperty("rate_as_guest_canonical")
    public void setRateAsGuestCanonical(CurrencyAmount value) {
        this.mRateAsGuestCanonical = value;
    }

    public CurrencyAmount getRefund() {
        return this.mRefund;
    }

    @JsonProperty("refund")
    public void setRefund(CurrencyAmount value) {
        this.mRefund = value;
    }

    public CurrencyAmount getHostSubtotalAmount() {
        return this.mHostSubtotalAmount;
    }

    @JsonProperty("host_subtotal_amount")
    public void setHostSubtotalAmount(CurrencyAmount value) {
        this.mHostSubtotalAmount = value;
    }

    public FullRefundUpsellInfo getP4FullRefundUpsellInfo() {
        return this.mP4FullRefundUpsellInfo;
    }

    @JsonProperty("p4_full_refund_upsell_info")
    public void setP4FullRefundUpsellInfo(FullRefundUpsellInfo value) {
        this.mP4FullRefundUpsellInfo = value;
    }

    public List<Price> getInstallments() {
        return this.mInstallments;
    }

    @JsonProperty("installments")
    public void setInstallments(List<Price> value) {
        this.mInstallments = value;
    }

    public Price getPrice() {
        return this.mPrice;
    }

    @JsonProperty("price")
    public void setPrice(Price value) {
        this.mPrice = value;
    }

    public Price getHostPayoutBreakdown() {
        return this.mHostPayoutBreakdown;
    }

    @JsonProperty("host_payout_breakdown")
    public void setHostPayoutBreakdown(Price value) {
        this.mHostPayoutBreakdown = value;
    }

    public PriceFactor getMonthlyPriceFactor() {
        return this.mMonthlyPriceFactor;
    }

    @JsonProperty("monthly_price_factor")
    public void setMonthlyPriceFactor(PriceFactor value) {
        this.mMonthlyPriceFactor = value;
    }

    public PriceFactor getWeeklyPriceFactor() {
        return this.mWeeklyPriceFactor;
    }

    @JsonProperty("weekly_price_factor")
    public void setWeeklyPriceFactor(PriceFactor value) {
        this.mWeeklyPriceFactor = value;
    }

    public RateType getRateType() {
        return this.mRateType;
    }

    public String getCancellationPolicyLink() {
        return this.mCancellationPolicyLink;
    }

    @JsonProperty("cancellation_policy_link")
    public void setCancellationPolicyLink(String value) {
        this.mCancellationPolicyLink = value;
    }

    public String getLocalizedCancellationPolicyName() {
        return this.mLocalizedCancellationPolicyName;
    }

    @JsonProperty("localized_cancellation_policy_name")
    public void setLocalizedCancellationPolicyName(String value) {
        this.mLocalizedCancellationPolicyName = value;
    }

    public String getLocalizedUnavailabilityMessage() {
        return this.mLocalizedUnavailabilityMessage;
    }

    @JsonProperty("localized_unavailability_message")
    public void setLocalizedUnavailabilityMessage(String value) {
        this.mLocalizedUnavailabilityMessage = value;
    }

    public UrgencyMessageData getUrgencyMessageData() {
        return this.mUrgencyMessageData;
    }

    @JsonProperty("p3_message_data")
    public void setUrgencyMessageData(UrgencyMessageData value) {
        this.mUrgencyMessageData = value;
    }

    public boolean isInstantBookable() {
        return this.mInstantBookable;
    }

    @JsonProperty("can_instant_book")
    public void setInstantBookable(boolean value) {
        this.mInstantBookable = value;
    }

    public boolean isAvailable() {
        return this.mAvailable;
    }

    @JsonProperty("available")
    public void setAvailable(boolean value) {
        this.mAvailable = value;
    }

    public boolean isDatelessAvailable() {
        return this.mDatelessAvailable;
    }

    @JsonProperty("dateless_available")
    public void setDatelessAvailable(boolean value) {
        this.mDatelessAvailable = value;
    }

    public int getGuests() {
        return this.mGuests;
    }

    @JsonProperty("guests")
    public void setGuests(int value) {
        this.mGuests = value;
    }

    public int getP3PercentageRecommended() {
        return this.mP3PercentageRecommended;
    }

    @JsonProperty("p3_percentage_recommended")
    public void setP3PercentageRecommended(int value) {
        this.mP3PercentageRecommended = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mCheckIn, 0);
        parcel.writeParcelable(this.mCheckOut, 0);
        parcel.writeSerializable(this.mCancellationPolicyLabel);
        parcel.writeParcelable(this.mP3CancellationRefundBanner, 0);
        parcel.writeParcelable(this.mP4CancellationRefundBanner, 0);
        parcel.writeParcelable(this.mRate, 0);
        parcel.writeParcelable(this.mRateWithServiceFee, 0);
        parcel.writeParcelable(this.mRateAsGuestCanonical, 0);
        parcel.writeParcelable(this.mRefund, 0);
        parcel.writeParcelable(this.mHostSubtotalAmount, 0);
        parcel.writeParcelable(this.mP4FullRefundUpsellInfo, 0);
        parcel.writeTypedList(this.mInstallments);
        parcel.writeParcelable(this.mPrice, 0);
        parcel.writeParcelable(this.mHostPayoutBreakdown, 0);
        parcel.writeParcelable(this.mMonthlyPriceFactor, 0);
        parcel.writeParcelable(this.mWeeklyPriceFactor, 0);
        parcel.writeSerializable(this.mRateType);
        parcel.writeString(this.mCancellationPolicyLink);
        parcel.writeString(this.mLocalizedCancellationPolicyName);
        parcel.writeString(this.mLocalizedUnavailabilityMessage);
        parcel.writeParcelable(this.mUrgencyMessageData, 0);
        parcel.writeBooleanArray(new boolean[]{this.mInstantBookable, this.mAvailable, this.mDatelessAvailable});
        parcel.writeInt(this.mGuests);
        parcel.writeInt(this.mP3PercentageRecommended);
    }

    public void readFromParcel(Parcel source) {
        this.mCheckIn = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCheckOut = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mCancellationPolicyLabel = (CancellationPolicyLabel) source.readSerializable();
        this.mP3CancellationRefundBanner = (CancellationRefundBanner) source.readParcelable(CancellationRefundBanner.class.getClassLoader());
        this.mP4CancellationRefundBanner = (CancellationRefundBanner) source.readParcelable(CancellationRefundBanner.class.getClassLoader());
        this.mRate = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mRateWithServiceFee = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mRateAsGuestCanonical = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mRefund = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mHostSubtotalAmount = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mP4FullRefundUpsellInfo = (FullRefundUpsellInfo) source.readParcelable(FullRefundUpsellInfo.class.getClassLoader());
        this.mInstallments = source.createTypedArrayList(Price.CREATOR);
        this.mPrice = (Price) source.readParcelable(Price.class.getClassLoader());
        this.mHostPayoutBreakdown = (Price) source.readParcelable(Price.class.getClassLoader());
        this.mMonthlyPriceFactor = (PriceFactor) source.readParcelable(PriceFactor.class.getClassLoader());
        this.mWeeklyPriceFactor = (PriceFactor) source.readParcelable(PriceFactor.class.getClassLoader());
        this.mRateType = (RateType) source.readSerializable();
        this.mCancellationPolicyLink = source.readString();
        this.mLocalizedCancellationPolicyName = source.readString();
        this.mLocalizedUnavailabilityMessage = source.readString();
        this.mUrgencyMessageData = (UrgencyMessageData) source.readParcelable(UrgencyMessageData.class.getClassLoader());
        boolean[] bools = source.createBooleanArray();
        this.mInstantBookable = bools[0];
        this.mAvailable = bools[1];
        this.mDatelessAvailable = bools[2];
        this.mGuests = source.readInt();
        this.mP3PercentageRecommended = source.readInt();
    }
}
