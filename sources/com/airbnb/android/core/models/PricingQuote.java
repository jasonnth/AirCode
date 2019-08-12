package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.enums.CancellationPolicyLabel;
import com.airbnb.android.core.models.Price.Type;
import com.airbnb.android.core.models.generated.GenPricingQuote;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import p005cn.jpush.android.JPushConstants.JPushReportInterface;

public class PricingQuote extends GenPricingQuote {
    public static final Creator<PricingQuote> CREATOR = new Creator<PricingQuote>() {
        public PricingQuote[] newArray(int size) {
            return new PricingQuote[size];
        }

        public PricingQuote createFromParcel(Parcel source) {
            PricingQuote object = new PricingQuote();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum RateType {
        Nightly("nightly"),
        Monthly("monthly"),
        Total(JPushReportInterface.TOTAL);
        
        private final String serverKey;

        private RateType(String serverKey2) {
            this.serverKey = serverKey2;
        }

        public static RateType fromServerKey(String serverKey2) {
            RateType[] values;
            for (RateType type : values()) {
                if (type.serverKey.equals(serverKey2)) {
                    return type;
                }
            }
            return null;
        }
    }

    @JsonProperty("rate_type")
    public void setRateType(String serverKey) {
        setRateType(RateType.fromServerKey(serverKey));
    }

    public void setRateType(RateType type) {
        this.mRateType = type;
    }

    public RateType getRateType() {
        return super.getRateType();
    }

    @JsonProperty("cancellation_policy_label")
    public void setCancellationPolicyLabel(String serverKey) {
        this.mCancellationPolicyLabel = CancellationPolicyLabel.fromServerKey(serverKey);
    }

    public boolean hasLineItemType(Type type) {
        return FluentIterable.from((Iterable<E>) this.mPrice.getPriceItems()).anyMatch(PricingQuote$$Lambda$1.lambdaFactory$(type));
    }

    static /* synthetic */ boolean lambda$hasLineItemType$0(Type type, Price price) {
        return price.getType() == type;
    }

    public boolean hasTotalPrice() {
        return this.mPrice != null;
    }

    public boolean hasRateWithServiceFee() {
        return this.mRateWithServiceFee != null;
    }

    public boolean hasCanonicalRate() {
        return this.mRateAsGuestCanonical != null;
    }

    public CurrencyAmount getTotalPrice() {
        return this.mPrice.getTotal();
    }

    static /* synthetic */ boolean lambda$getCouponCurrencyAmount$1(Price price) {
        return price.getType() == Type.Coupon;
    }

    public CurrencyAmount getCouponCurrencyAmount() {
        Optional<Price> coupon = FluentIterable.from((Iterable<E>) this.mPrice.getPriceItems()).firstMatch(PricingQuote$$Lambda$2.lambdaFactory$());
        if (coupon.isPresent()) {
            return ((Price) coupon.get()).getTotal();
        }
        return null;
    }

    public boolean hasCouponApplied() {
        return hasLineItemType(Type.Coupon);
    }

    public boolean hasDates() {
        return (this.mCheckIn == null || this.mCheckOut == null) ? false : true;
    }

    public CancellationPolicyLabel getCancellationPolicyLabel() {
        return super.getCancellationPolicyLabel();
    }

    public boolean isAvailableForWishList() {
        if (hasDates()) {
            return isAvailable();
        }
        return isDatelessAvailable();
    }
}
