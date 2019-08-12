package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.generated.GenPrice;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.math.BigDecimal;
import java.util.List;

public class Price extends GenPrice {
    public static final Creator<Price> CREATOR = new Creator<Price>() {
        public Price[] newArray(int size) {
            return new Price[size];
        }

        public Price createFromParcel(Parcel source) {
            Price object = new Price();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum Type {
        Accommodation("ACCOMMODATION"),
        AirbnbCredit("AIRBNB_CREDIT"),
        GiftCredit("AIRBNB_GIFT_CREDIT"),
        GuestFee("AIRBNB_GUEST_FEE"),
        CleaningFee("CLEANING_FEE"),
        Coupon("COUPON"),
        Discount("DISCOUNT"),
        Installment("INSTALLMENT"),
        InstallmentFee("INSTALLMENT_FEE"),
        PaidAmenity("PAID_AMENITY"),
        Taxes("TAXES"),
        TaxesAndAirbnbGuestFee("TAXES_AND_AIRBNB_GUEST_FEE"),
        Total("TOTAL"),
        Unknown("");
        
        private final String serverKey;

        private Type(String serverKey2) {
            this.serverKey = serverKey2;
        }

        public static Type fromServerKey(String serverKey2) {
            Type[] values;
            for (Type type : values()) {
                if (type.serverKey.equals(serverKey2)) {
                    return type;
                }
            }
            return Unknown;
        }

        public String getServerKey() {
            return this.serverKey;
        }
    }

    @JsonProperty("type")
    public void setType(String priceType) {
        this.mType = Type.fromServerKey(priceType);
    }

    public Type getType() {
        return super.getType();
    }

    public boolean hasExplanation() {
        return !TextUtils.isEmpty(getLocalizedExplanation());
    }

    public Price getGiftCredit() {
        return (Price) FluentIterable.from((Iterable<E>) getPriceItems()).firstMatch(Price$$Lambda$1.lambdaFactory$()).orNull();
    }

    static /* synthetic */ boolean lambda$getGiftCredit$0(Price p) {
        return p.getType() == Type.AirbnbCredit;
    }

    public Price getCouponCode() {
        return (Price) FluentIterable.from((Iterable<E>) getPriceItems()).firstMatch(Price$$Lambda$2.lambdaFactory$()).orNull();
    }

    static /* synthetic */ boolean lambda$getCouponCode$1(Price p) {
        return p.getType() == Type.Coupon;
    }

    public boolean hasGiftCredit() {
        Price giftCredit = getGiftCredit();
        return giftCredit != null && BigDecimal.ZERO.compareTo(giftCredit.getTotal().getAmount()) > 0;
    }

    public boolean hasCouponCode() {
        return getCouponCode() != null;
    }

    public static String getAccomodationText(List<Price> prices) {
        for (Price p : prices) {
            if (p.getType() == Type.Accommodation) {
                return p.getLocalizedTitle();
            }
        }
        BugsnagWrapper.notify((Throwable) new IllegalStateException("Reservation host payout breakdown did not include accomodation string."));
        return null;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Price price = (Price) o;
        if (this.mTotal != null) {
            if (!this.mTotal.equals(price.mTotal)) {
                return false;
            }
        } else if (price.mTotal != null) {
            return false;
        }
        if (this.mPriceItems != null) {
            if (!this.mPriceItems.equals(price.mPriceItems)) {
                return false;
            }
        } else if (price.mPriceItems != null) {
            return false;
        }
        if (this.mLocalizedExplanation != null) {
            if (!this.mLocalizedExplanation.equals(price.mLocalizedExplanation)) {
                return false;
            }
        } else if (price.mLocalizedExplanation != null) {
            return false;
        }
        if (this.mLocalizedTitle != null) {
            if (!this.mLocalizedTitle.equals(price.mLocalizedTitle)) {
                return false;
            }
        } else if (price.mLocalizedTitle != null) {
            return false;
        }
        if (this.mType != price.mType) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int result;
        int i;
        int i2;
        int i3;
        int i4 = 0;
        if (this.mTotal != null) {
            result = this.mTotal.hashCode();
        } else {
            result = 0;
        }
        int i5 = result * 31;
        if (this.mPriceItems != null) {
            i = this.mPriceItems.hashCode();
        } else {
            i = 0;
        }
        int i6 = (i5 + i) * 31;
        if (this.mLocalizedExplanation != null) {
            i2 = this.mLocalizedExplanation.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.mLocalizedTitle != null) {
            i3 = this.mLocalizedTitle.hashCode();
        } else {
            i3 = 0;
        }
        int i8 = (i7 + i3) * 31;
        if (this.mType != null) {
            i4 = this.mType.hashCode();
        }
        return i8 + i4;
    }
}
