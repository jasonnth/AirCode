package com.airbnb.android.core.payments.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.Price;

final class AutoValue_QuickPayArguments extends C$AutoValue_QuickPayArguments {
    public static final Creator<AutoValue_QuickPayArguments> CREATOR = new Creator<AutoValue_QuickPayArguments>() {
        public AutoValue_QuickPayArguments createFromParcel(Parcel in) {
            return new AutoValue_QuickPayArguments((CartItem) in.readParcelable(CartItem.class.getClassLoader()), (Price) in.readParcelable(Price.class.getClassLoader()), QuickPayClientType.valueOf(in.readString()));
        }

        public AutoValue_QuickPayArguments[] newArray(int size) {
            return new AutoValue_QuickPayArguments[size];
        }
    };

    AutoValue_QuickPayArguments(CartItem cartItem, Price price, QuickPayClientType clientType) {
        super(cartItem, price, clientType);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(getCartItem(), flags);
        dest.writeParcelable(getPrice(), flags);
        dest.writeString(getClientType().name());
    }

    public int describeContents() {
        return 0;
    }
}
