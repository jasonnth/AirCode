package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class CardBuilder extends BaseCardBuilder<CardBuilder> implements Parcelable {
    public static final Creator<CardBuilder> CREATOR = new Creator<CardBuilder>() {
        public CardBuilder createFromParcel(Parcel in) {
            return new CardBuilder(in);
        }

        public CardBuilder[] newArray(int size) {
            return new CardBuilder[size];
        }
    };

    public CardBuilder() {
    }

    protected CardBuilder(Parcel in) {
        super(in);
    }
}
