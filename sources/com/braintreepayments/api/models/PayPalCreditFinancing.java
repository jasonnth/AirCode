package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.json.JSONException;
import org.json.JSONObject;

public class PayPalCreditFinancing implements Parcelable {
    private static final String CARD_AMOUNT_IMMUTABLE_KEY = "cardAmountImmutable";
    public static final Creator<PayPalCreditFinancing> CREATOR = new Creator<PayPalCreditFinancing>() {
        public PayPalCreditFinancing createFromParcel(Parcel source) {
            return new PayPalCreditFinancing(source);
        }

        public PayPalCreditFinancing[] newArray(int size) {
            return new PayPalCreditFinancing[size];
        }
    };
    private static final String MONTHLY_PAYMENT_KEY = "monthlyPayment";
    private static final String PAYER_ACCEPTANCE_KEY = "payerAcceptance";
    private static final String TERM_KEY = "term";
    private static final String TOTAL_COST_KEY = "totalCost";
    private static final String TOTAL_INTEREST_KEY = "totalInterest";
    private boolean mCardAmountImmutable;
    private PayPalCreditFinancingAmount mMonthlyPayment;
    private boolean mPayerAcceptance;
    private int mTerm;
    private PayPalCreditFinancingAmount mTotalCost;
    private PayPalCreditFinancingAmount mTotalInterest;

    private PayPalCreditFinancing() {
    }

    public static PayPalCreditFinancing fromJson(JSONObject creditFinancing) throws JSONException {
        PayPalCreditFinancing result = new PayPalCreditFinancing();
        if (creditFinancing != null) {
            result.mCardAmountImmutable = creditFinancing.optBoolean(CARD_AMOUNT_IMMUTABLE_KEY, false);
            result.mMonthlyPayment = PayPalCreditFinancingAmount.fromJson(creditFinancing.getJSONObject(MONTHLY_PAYMENT_KEY));
            result.mPayerAcceptance = creditFinancing.optBoolean(PAYER_ACCEPTANCE_KEY, false);
            result.mTerm = creditFinancing.optInt(TERM_KEY, 0);
            result.mTotalCost = PayPalCreditFinancingAmount.fromJson(creditFinancing.getJSONObject(TOTAL_COST_KEY));
            result.mTotalInterest = PayPalCreditFinancingAmount.fromJson(creditFinancing.getJSONObject(TOTAL_INTEREST_KEY));
        }
        return result;
    }

    public int getTerm() {
        return this.mTerm;
    }

    public boolean isCardAmountImmutable() {
        return this.mCardAmountImmutable;
    }

    public PayPalCreditFinancingAmount getMonthlyPayment() {
        return this.mMonthlyPayment;
    }

    public boolean hasPayerAcceptance() {
        return this.mPayerAcceptance;
    }

    public PayPalCreditFinancingAmount getTotalCost() {
        return this.mTotalCost;
    }

    public PayPalCreditFinancingAmount getTotalInterest() {
        return this.mTotalInterest;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        byte b = 1;
        dest.writeByte(this.mCardAmountImmutable ? (byte) 1 : 0);
        dest.writeParcelable(this.mMonthlyPayment, flags);
        if (!this.mPayerAcceptance) {
            b = 0;
        }
        dest.writeByte(b);
        dest.writeInt(this.mTerm);
        dest.writeParcelable(this.mTotalCost, flags);
        dest.writeParcelable(this.mTotalInterest, flags);
    }

    private PayPalCreditFinancing(Parcel in) {
        boolean z = true;
        this.mCardAmountImmutable = in.readByte() != 0;
        this.mMonthlyPayment = (PayPalCreditFinancingAmount) in.readParcelable(PayPalCreditFinancingAmount.class.getClassLoader());
        if (in.readByte() == 0) {
            z = false;
        }
        this.mPayerAcceptance = z;
        this.mTerm = in.readInt();
        this.mTotalCost = (PayPalCreditFinancingAmount) in.readParcelable(PayPalCreditFinancingAmount.class.getClassLoader());
        this.mTotalInterest = (PayPalCreditFinancingAmount) in.readParcelable(PayPalCreditFinancingAmount.class.getClassLoader());
    }
}
