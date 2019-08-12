package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPaymentMethod;

public class PaymentMethod extends GenPaymentMethod {
    public static final Creator<PaymentMethod> CREATOR = new Creator<PaymentMethod>() {
        public PaymentMethod[] newArray(int size) {
            return new PaymentMethod[size];
        }

        public PaymentMethod createFromParcel(Parcel source) {
            PaymentMethod object = new PaymentMethod();
            object.readFromParcel(source);
            return object;
        }
    };

    public static PaymentMethod paymentMethodWithType(String type) {
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setType(type);
        return paymentMethod;
    }
}
