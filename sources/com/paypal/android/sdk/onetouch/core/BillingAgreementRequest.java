package com.paypal.android.sdk.onetouch.core;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.paypal.android.sdk.onetouch.core.config.BillingAgreementRecipe;
import com.paypal.android.sdk.onetouch.core.config.OtcConfiguration;
import com.paypal.android.sdk.onetouch.core.config.Recipe;
import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;

public class BillingAgreementRequest extends CheckoutRequest {
    public static final Creator<BillingAgreementRequest> CREATOR = new Creator<BillingAgreementRequest>() {
        public BillingAgreementRequest[] newArray(int size) {
            return new BillingAgreementRequest[size];
        }

        public BillingAgreementRequest createFromParcel(Parcel source) {
            return new BillingAgreementRequest(source);
        }
    };
    private static final String TOKEN_QUERY_PARAM_KEY_BA_TOKEN = "ba_token";

    public BillingAgreementRequest() {
    }

    @Deprecated
    public BillingAgreementRequest pairingId(String pairingId) {
        return pairingId((Context) null, pairingId);
    }

    public BillingAgreementRequest pairingId(Context context, String pairingId) {
        super.pairingId(context, pairingId);
        return this;
    }

    public BillingAgreementRequest approvalURL(String approvalURL) {
        super.approvalURL(approvalURL);
        this.mTokenQueryParamKey = TOKEN_QUERY_PARAM_KEY_BA_TOKEN;
        return this;
    }

    public Recipe getRecipeToExecute(Context context, OtcConfiguration config) {
        for (BillingAgreementRecipe recipe : config.getBillingAgreementRecipes()) {
            if (RequestTarget.wallet == recipe.getTarget()) {
                if (recipe.isValidAppTarget(context)) {
                    return recipe;
                }
            } else if (RequestTarget.browser == recipe.getTarget() && recipe.isValidBrowserTarget(context, getBrowserSwitchUrl(context, config))) {
                return recipe;
            }
        }
        return null;
    }

    protected BillingAgreementRequest(Parcel source) {
        super(source);
    }
}
