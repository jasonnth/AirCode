package com.airbnb.android.core.models.payments;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.common.collect.FluentIterable;
import com.miteksystems.misnap.params.MiSnapApiConstants;

/* renamed from: com.airbnb.android.core.models.payments.PaymentInstrumentType */
public enum C6200PaymentInstrumentType implements Parcelable {
    ACH(MiSnapApiConstants.PARAMETER_DOCTYPE_AUTOMATIC_CHECK_HANDLING),
    Alipay("Alipay"),
    AndroidPay("Android Pay"),
    BankTransfer("Bank Transfer"),
    BraintreePaypal("Braintree PayPal"),
    CreditCard("Credit Card"),
    DigitalRiverCreditCard("Digital River Credit Card"),
    BusinessTravelInvoice("Business Travel Invoice"),
    PayoneerAPI("Payoneer API"),
    PayoneerBankTransfer("Payoneer Bank Transfer"),
    PayPal("PayPal"),
    TaxGarnishment("Tax Garnishment"),
    VaCuba("VaCuba"),
    WesternUnion("Western Union"),
    Unknown("");
    
    public static final Creator<C6200PaymentInstrumentType> CREATOR = null;
    private final String serverKey;

    static {
        CREATOR = new Creator<C6200PaymentInstrumentType>() {
            public C6200PaymentInstrumentType createFromParcel(Parcel source) {
                return C6200PaymentInstrumentType.values()[source.readInt()];
            }

            public C6200PaymentInstrumentType[] newArray(int size) {
                return new C6200PaymentInstrumentType[size];
            }
        };
    }

    private C6200PaymentInstrumentType(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public static C6200PaymentInstrumentType findByServerKey(String serverKey2) {
        return (C6200PaymentInstrumentType) FluentIterable.m1283of(values()).firstMatch(PaymentInstrumentType$$Lambda$1.lambdaFactory$(serverKey2)).mo41059or(Unknown);
    }

    public String getServerKey() {
        return this.serverKey;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
