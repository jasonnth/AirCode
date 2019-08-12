package com.airbnb.android.core.payments.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.payments.models.Bill.ProductMetadata;

final class AutoValue_Bill_BillItem extends C$AutoValue_Bill_BillItem {
    public static final Creator<AutoValue_Bill_BillItem> CREATOR = new Creator<AutoValue_Bill_BillItem>() {
        public AutoValue_Bill_BillItem createFromParcel(Parcel in) {
            return new AutoValue_Bill_BillItem(in.readLong(), in.readLong(), (ProductMetadata) in.readParcelable(ProductMetadata.class.getClassLoader()), in.readString(), in.readLong(), in.readString());
        }

        public AutoValue_Bill_BillItem[] newArray(int size) {
            return new AutoValue_Bill_BillItem[size];
        }
    };

    AutoValue_Bill_BillItem(long billId, long productId, ProductMetadata productMetadata, String productType, long status, String token) {
        super(billId, productId, productMetadata, productType, status, token);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(billId());
        dest.writeLong(productId());
        dest.writeParcelable(productMetadata(), flags);
        dest.writeString(productType());
        dest.writeLong(status());
        dest.writeString(token());
    }

    public int describeContents() {
        return 0;
    }
}
