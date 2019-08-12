package com.paypal.android.sdk.onetouch.core.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.braintreepayments.api.internal.SignatureVerification;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.paypal.android.sdk.data.collector.InstallationIdentifier;
import com.paypal.android.sdk.onetouch.core.AuthorizationRequest;
import com.paypal.android.sdk.onetouch.core.CheckoutRequest;
import com.paypal.android.sdk.onetouch.core.Request;
import com.paypal.android.sdk.onetouch.core.Result;
import com.paypal.android.sdk.onetouch.core.base.ContextInspector;
import com.paypal.android.sdk.onetouch.core.base.DeviceInspector;
import com.paypal.android.sdk.onetouch.core.config.ConfigManager;
import com.paypal.android.sdk.onetouch.core.config.Recipe;
import com.paypal.android.sdk.onetouch.core.enums.ResponseType;
import com.paypal.android.sdk.onetouch.core.exception.ResponseParsingException;
import com.paypal.android.sdk.onetouch.core.exception.WalletSwitchException;
import com.paypal.android.sdk.onetouch.core.fpti.TrackingPoint;
import com.paypal.android.sdk.onetouch.core.network.EnvironmentManager;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class AppSwitchHelper {
    public static boolean isSignatureValid(Context context, String packageName) {
        return SignatureVerification.isSignatureValid(context, packageName, "O=Paypal", "O=Paypal", 34172764);
    }

    public static Intent createBaseIntent(String action, String packageName) {
        return new Intent(action).setPackage(packageName);
    }

    public static Intent getAppSwitchIntent(ContextInspector contextInspector, ConfigManager configManager, Request request, Recipe recipe) {
        Intent intent = createBaseIntent(recipe.getTargetIntentAction(), "com.paypal.android.p2pmobile").putExtra("version", recipe.getProtocol().getVersion()).putExtra("app_guid", InstallationIdentifier.getInstallationGUID(contextInspector.getContext())).putExtra("client_metadata_id", request.getClientMetadataId()).putExtra("client_id", request.getClientId()).putExtra(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, DeviceInspector.getApplicationInfoName(contextInspector.getContext())).putExtra("environment", request.getEnvironment()).putExtra("environment_url", EnvironmentManager.getEnvironmentUrl(request.getEnvironment()));
        if (request instanceof AuthorizationRequest) {
            AuthorizationRequest authorizationRequest = (AuthorizationRequest) request;
            intent.putExtra("scope", authorizationRequest.getScopeString()).putExtra(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, "code").putExtra("privacy_url", authorizationRequest.getPrivacyUrl()).putExtra("agreement_url", authorizationRequest.getUserAgreementUrl());
        } else {
            intent.putExtra(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_WEB).putExtra("webURL", ((CheckoutRequest) request).getBrowserSwitchUrl(contextInspector.getContext(), configManager.getConfig()));
        }
        return intent;
    }

    public static Result parseAppSwitchResponse(ContextInspector contextInspector, Request request, Intent data) {
        Bundle bundle = data.getExtras();
        if (request.validateV1V2Response(contextInspector, bundle)) {
            request.trackFpti(contextInspector.getContext(), TrackingPoint.Return, null);
            return processResponseIntent(bundle);
        } else if (bundle.containsKey("error")) {
            request.trackFpti(contextInspector.getContext(), TrackingPoint.Error, null);
            return new Result((Throwable) new WalletSwitchException(bundle.getString("error")));
        } else {
            request.trackFpti(contextInspector.getContext(), TrackingPoint.Error, null);
            return new Result((Throwable) new ResponseParsingException("invalid wallet response"));
        }
    }

    private static Result processResponseIntent(Bundle bundle) {
        ResponseType response_type;
        String error = bundle.getString("error");
        if (!TextUtils.isEmpty(error)) {
            return new Result((Throwable) new WalletSwitchException(error));
        }
        String environment = bundle.getString("environment");
        if ("code".equals(bundle.getString(ServerProtocol.DIALOG_PARAM_RESPONSE_TYPE).toLowerCase(Locale.US))) {
            response_type = ResponseType.authorization_code;
        } else {
            response_type = ResponseType.web;
        }
        try {
            if (ResponseType.web == response_type) {
                return new Result(environment, response_type, new JSONObject().put("webURL", bundle.getString("webURL")), null);
            }
            String authorization_code = bundle.getString("authorization_code");
            return new Result(environment, response_type, new JSONObject().put("code", authorization_code), bundle.getString("email"));
        } catch (JSONException e) {
            return new Result((Throwable) new ResponseParsingException((Throwable) e));
        }
    }
}
