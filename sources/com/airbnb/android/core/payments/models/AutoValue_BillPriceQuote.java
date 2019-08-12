package com.airbnb.android.core.payments.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.payments.models.BillPriceQuote.CancellationInfo;
import com.airbnb.android.core.payments.models.BillPriceQuote.LinkableLegalText;
import java.util.List;

final class AutoValue_BillPriceQuote extends C$AutoValue_BillPriceQuote {
    public static final Creator<AutoValue_BillPriceQuote> CREATOR = new Creator<AutoValue_BillPriceQuote>() {
        public AutoValue_BillPriceQuote createFromParcel(Parcel in) {
            return new AutoValue_BillPriceQuote((CurrencyAmount) in.readParcelable(CurrencyAmount.class.getClassLoader()), (Price) in.readParcelable(Price.class.getClassLoader()), in.readString(), in.readArrayList(Price.class.getClassLoader()), in.readInt() == 0 ? in.readString() : null, (CancellationInfo) in.readParcelable(CancellationInfo.class.getClassLoader()), (LinkableLegalText) in.readParcelable(LinkableLegalText.class.getClassLoader()), (LinkableLegalText) in.readParcelable(LinkableLegalText.class.getClassLoader()));
        }

        public AutoValue_BillPriceQuote[] newArray(int size) {
            return new AutoValue_BillPriceQuote[size];
        }
    };

    AutoValue_BillPriceQuote(CurrencyAmount applicableAirbnbCredit, Price price, String quoteKey, List<Price> installments, String fxMessage, CancellationInfo cancellationInfo, LinkableLegalText termsAndConditions, LinkableLegalText cancellationRefundPolicy) {
        super(applicableAirbnbCredit, price, quoteKey, installments, fxMessage, cancellationInfo, termsAndConditions, cancellationRefundPolicy);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(getApplicableAirbnbCredit(), flags);
        dest.writeParcelable(getPrice(), flags);
        dest.writeString(getQuoteKey());
        dest.writeList(getInstallments());
        if (getFxMessage() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(getFxMessage());
        }
        dest.writeParcelable(getCancellationInfo(), flags);
        dest.writeParcelable(getTermsAndConditions(), flags);
        dest.writeParcelable(getCancellationRefundPolicy(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
