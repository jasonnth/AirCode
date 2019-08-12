package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.identity.intents.model.UserAddress;
import com.google.android.gms.wallet.Cart;
import com.google.android.gms.wallet.FullWallet;
import org.json.JSONException;
import org.json.JSONObject;

public class AndroidPayCardNonce extends PaymentMethodNonce implements Parcelable {
    protected static final String API_RESOURCE_KEY = "androidPayCards";
    private static final String CARD_DETAILS_KEY = "details";
    private static final String CARD_TYPE_KEY = "cardType";
    public static final Creator<AndroidPayCardNonce> CREATOR = new Creator<AndroidPayCardNonce>() {
        public AndroidPayCardNonce createFromParcel(Parcel source) {
            return new AndroidPayCardNonce(source);
        }

        public AndroidPayCardNonce[] newArray(int size) {
            return new AndroidPayCardNonce[size];
        }
    };
    private static final String LAST_TWO_KEY = "lastTwo";
    protected static final String TYPE = "AndroidPayCard";
    private UserAddress mBillingAddress;
    private String mCardType;
    private Cart mCart;
    private String mEmail;
    private String mGoogleTransactionId;
    private String mLastTwo;
    private UserAddress mShippingAddress;

    @Deprecated
    public static AndroidPayCardNonce fromFullWallet(FullWallet wallet) throws JSONException {
        return fromFullWallet(wallet, null);
    }

    public static AndroidPayCardNonce fromFullWallet(FullWallet wallet, Cart cart) throws JSONException {
        AndroidPayCardNonce androidPayCardNonce = fromJson(wallet.getPaymentMethodToken().getToken());
        androidPayCardNonce.mDescription = wallet.getPaymentDescriptions()[0];
        androidPayCardNonce.mEmail = wallet.getEmail();
        androidPayCardNonce.mBillingAddress = wallet.getBuyerBillingAddress();
        androidPayCardNonce.mShippingAddress = wallet.getBuyerShippingAddress();
        androidPayCardNonce.mGoogleTransactionId = wallet.getGoogleTransactionId();
        androidPayCardNonce.mCart = cart;
        return androidPayCardNonce;
    }

    public static AndroidPayCardNonce fromJson(String json) throws JSONException {
        AndroidPayCardNonce androidPayCardNonce = new AndroidPayCardNonce();
        androidPayCardNonce.fromJson(getJsonObjectForType(API_RESOURCE_KEY, json));
        return androidPayCardNonce;
    }

    /* access modifiers changed from: protected */
    public void fromJson(JSONObject json) throws JSONException {
        super.fromJson(json);
        JSONObject details = json.getJSONObject("details");
        this.mLastTwo = details.getString(LAST_TWO_KEY);
        this.mCardType = details.getString(CARD_TYPE_KEY);
    }

    public String getTypeLabel() {
        return "Android Pay";
    }

    public String getCardType() {
        return this.mCardType;
    }

    public String getLastTwo() {
        return this.mLastTwo;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public UserAddress getBillingAddress() {
        return this.mBillingAddress;
    }

    public UserAddress getShippingAddress() {
        return this.mShippingAddress;
    }

    public String getGoogleTransactionId() {
        return this.mGoogleTransactionId;
    }

    public Cart getCart() {
        return this.mCart;
    }

    public AndroidPayCardNonce() {
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mCardType);
        dest.writeString(this.mLastTwo);
        dest.writeString(this.mEmail);
        dest.writeParcelable(this.mBillingAddress, flags);
        dest.writeParcelable(this.mShippingAddress, flags);
        dest.writeString(this.mGoogleTransactionId);
        dest.writeParcelable(this.mCart, flags);
    }

    private AndroidPayCardNonce(Parcel in) {
        super(in);
        this.mCardType = in.readString();
        this.mLastTwo = in.readString();
        this.mEmail = in.readString();
        this.mBillingAddress = (UserAddress) in.readParcelable(UserAddress.class.getClassLoader());
        this.mShippingAddress = (UserAddress) in.readParcelable(UserAddress.class.getClassLoader());
        this.mGoogleTransactionId = in.readString();
        this.mCart = (Cart) in.readParcelable(Cart.class.getClassLoader());
    }
}
