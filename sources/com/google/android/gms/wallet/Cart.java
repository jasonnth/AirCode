package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;

public final class Cart extends zza implements ReflectedParcelable {
    public static final Creator<Cart> CREATOR = new zzc();
    String zzbPO;
    String zzbPP;
    ArrayList<LineItem> zzbPQ;

    public final class Builder {
        private Builder() {
        }

        public Cart build() {
            return Cart.this;
        }

        public Builder setCurrencyCode(String str) {
            Cart.this.zzbPP = str;
            return this;
        }

        public Builder setTotalPrice(String str) {
            Cart.this.zzbPO = str;
            return this;
        }
    }

    Cart() {
        this.zzbPQ = new ArrayList<>();
    }

    Cart(String str, String str2, ArrayList<LineItem> arrayList) {
        this.zzbPO = str;
        this.zzbPP = str2;
        this.zzbPQ = arrayList;
    }

    public static Builder newBuilder() {
        Cart cart = new Cart();
        cart.getClass();
        return new Builder();
    }

    public String getCurrencyCode() {
        return this.zzbPP;
    }

    public ArrayList<LineItem> getLineItems() {
        return this.zzbPQ;
    }

    public String getTotalPrice() {
        return this.zzbPO;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }
}
