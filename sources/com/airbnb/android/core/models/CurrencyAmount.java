package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCurrencyAmount;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.utils.CurrencyUtils;
import java.math.BigDecimal;

public class CurrencyAmount extends GenCurrencyAmount {
    public static final Creator<CurrencyAmount> CREATOR = new Creator<CurrencyAmount>() {
        public CurrencyAmount[] newArray(int size) {
            return new CurrencyAmount[size];
        }

        public CurrencyAmount createFromParcel(Parcel source) {
            CurrencyAmount object = new CurrencyAmount();
            object.readFromParcel(source);
            return object;
        }
    };

    public String formattedForDisplay() {
        if (!Trebuchet.launch(TrebuchetKeys.DOLLARS_TO_CENTS, false) || getAmountFormatted() == null) {
            return CurrencyUtils.formatCurrencyAmount(getAmount().doubleValue(), getCurrency());
        }
        return getAmountFormatted();
    }

    public String formattedForFXCopyDisplay() {
        return CurrencyUtils.formatCurrencyAmountForFXCopy(getAmount().doubleValue(), getCurrency());
    }

    public boolean isPositive() {
        return this.mAmount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isNegative() {
        return this.mAmount.compareTo(BigDecimal.ZERO) < 0;
    }

    public boolean isZero() {
        return this.mAmount.compareTo(BigDecimal.ZERO) == 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CurrencyAmount)) {
            return false;
        }
        CurrencyAmount that = (CurrencyAmount) o;
        if (this.mAmount.equals(that.mAmount)) {
            return this.mCurrency.equals(that.mCurrency);
        }
        return false;
    }

    public int hashCode() {
        return (this.mAmount.hashCode() * 31) + this.mCurrency.hashCode();
    }
}
