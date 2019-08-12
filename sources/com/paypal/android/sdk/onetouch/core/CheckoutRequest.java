package com.paypal.android.sdk.onetouch.core;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.paypal.android.sdk.onetouch.core.base.ContextInspector;
import com.paypal.android.sdk.onetouch.core.config.CheckoutRecipe;
import com.paypal.android.sdk.onetouch.core.config.OtcConfiguration;
import com.paypal.android.sdk.onetouch.core.config.Recipe;
import com.paypal.android.sdk.onetouch.core.enums.Protocol;
import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;
import com.paypal.android.sdk.onetouch.core.enums.ResponseType;
import com.paypal.android.sdk.onetouch.core.exception.BrowserSwitchException;
import com.paypal.android.sdk.onetouch.core.exception.ResponseParsingException;
import com.paypal.android.sdk.onetouch.core.fpti.TrackingPoint;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckoutRequest extends Request<CheckoutRequest> implements Parcelable {
    public static final Creator<CheckoutRequest> CREATOR = new Creator<CheckoutRequest>() {
        public CheckoutRequest[] newArray(int size) {
            return new CheckoutRequest[size];
        }

        public CheckoutRequest createFromParcel(Parcel source) {
            return new CheckoutRequest(source);
        }
    };
    private static final String TOKEN_QUERY_PARAM_KEY_TOKEN = "token";
    protected String mApprovalUrl;
    private String mPairingId;
    protected String mTokenQueryParamKey;

    public CheckoutRequest() {
        this.mTokenQueryParamKey = "token";
    }

    public String getPairingId() {
        return this.mPairingId;
    }

    @Deprecated
    public CheckoutRequest pairingId(String pairingId) {
        return pairingId(null, pairingId);
    }

    public CheckoutRequest pairingId(Context context, String pairingId) {
        this.mPairingId = pairingId;
        clientMetadataId(PayPalOneTouchCore.getClientMetadataId(context, pairingId));
        return this;
    }

    public CheckoutRequest approvalURL(String approvalURL) {
        this.mApprovalUrl = approvalURL;
        this.mTokenQueryParamKey = "token";
        return this;
    }

    public String getBrowserSwitchUrl(Context context, OtcConfiguration config) {
        return this.mApprovalUrl;
    }

    public Recipe getBrowserSwitchRecipe(OtcConfiguration config) {
        return config.getBrowserCheckoutConfig();
    }

    public Result parseBrowserResponse(ContextInspector contextInspector, Uri uri) {
        if (!Uri.parse(getSuccessUrl()).getLastPathSegment().equals(uri.getLastPathSegment())) {
            return new Result();
        }
        String requestXoToken = Uri.parse(this.mApprovalUrl).getQueryParameter(this.mTokenQueryParamKey);
        String responseXoToken = uri.getQueryParameter(this.mTokenQueryParamKey);
        if (responseXoToken == null || !TextUtils.equals(requestXoToken, responseXoToken)) {
            return new Result((Throwable) new BrowserSwitchException("The response contained inconsistent data."));
        }
        try {
            JSONObject response = new JSONObject();
            response.put("webURL", uri.toString());
            return new Result(null, ResponseType.web, response, null);
        } catch (JSONException e) {
            return new Result((Throwable) new ResponseParsingException((Throwable) e));
        }
    }

    public boolean validateV1V2Response(ContextInspector contextInspector, Bundle extras) {
        String requestXoToken = Uri.parse(this.mApprovalUrl).getQueryParameter(this.mTokenQueryParamKey);
        String webUrl = extras.getString("webURL");
        if (webUrl != null) {
            String responseXoToken = Uri.parse(webUrl).getQueryParameter(this.mTokenQueryParamKey);
            if (responseXoToken != null && TextUtils.equals(requestXoToken, responseXoToken)) {
                return true;
            }
        }
        return false;
    }

    public Recipe getRecipeToExecute(Context context, OtcConfiguration config) {
        for (CheckoutRecipe recipe : config.getCheckoutRecipes()) {
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

    public void trackFpti(Context context, TrackingPoint trackingPoint, Protocol protocol) {
        String ecToken = Uri.parse(this.mApprovalUrl).getQueryParameter(this.mTokenQueryParamKey);
        Map<String, String> fptiDataBundle = new HashMap<>();
        fptiDataBundle.put("fltk", ecToken);
        fptiDataBundle.put("clid", getClientId());
        PayPalOneTouchCore.getFptiManager(context).trackFpti(trackingPoint, getEnvironment(), fptiDataBundle, protocol);
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mApprovalUrl);
        dest.writeString(this.mTokenQueryParamKey);
        dest.writeString(this.mPairingId);
    }

    protected CheckoutRequest(Parcel source) {
        super(source);
        this.mApprovalUrl = source.readString();
        this.mTokenQueryParamKey = source.readString();
        this.mPairingId = source.readString();
    }
}
