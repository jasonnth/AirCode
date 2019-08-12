package com.paypal.android.sdk.onetouch.core;

import android.content.Context;
import android.content.Intent;
import com.paypal.android.sdk.data.collector.PayPalDataCollector;
import com.paypal.android.sdk.onetouch.core.base.ContextInspector;
import com.paypal.android.sdk.onetouch.core.config.ConfigManager;
import com.paypal.android.sdk.onetouch.core.config.OAuth2Recipe;
import com.paypal.android.sdk.onetouch.core.config.Recipe;
import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;
import com.paypal.android.sdk.onetouch.core.fpti.FptiManager;
import com.paypal.android.sdk.onetouch.core.fpti.TrackingPoint;
import com.paypal.android.sdk.onetouch.core.network.PayPalHttpClient;
import com.paypal.android.sdk.onetouch.core.sdk.AppSwitchHelper;
import com.paypal.android.sdk.onetouch.core.sdk.BrowserSwitchHelper;
import com.paypal.android.sdk.onetouch.core.sdk.PendingRequest;
import java.util.Collections;

public class PayPalOneTouchCore {
    private static ConfigManager sConfigManager;
    private static ContextInspector sContextInspector;
    private static FptiManager sFptiManager;

    public static boolean isWalletAppInstalled(Context context) {
        initService(context);
        for (OAuth2Recipe recipe : sConfigManager.getConfig().getOauth2Recipes()) {
            if (recipe.getTarget() != RequestTarget.wallet || !recipe.isValidAppTarget(context)) {
                sFptiManager.trackFpti(TrackingPoint.WalletIsAbsent, "", Collections.emptyMap(), recipe.getProtocol());
            } else {
                sFptiManager.trackFpti(TrackingPoint.WalletIsPresent, "", Collections.emptyMap(), recipe.getProtocol());
                return true;
            }
        }
        return false;
    }

    public static PendingRequest getStartIntent(Context context, Request request) {
        initService(context);
        isWalletAppInstalled(context);
        Recipe recipe = request.getRecipeToExecute(context, sConfigManager.getConfig());
        if (recipe == null) {
            return new PendingRequest(false, null, null, null);
        }
        if (RequestTarget.wallet == recipe.getTarget()) {
            request.trackFpti(context, TrackingPoint.SwitchToWallet, recipe.getProtocol());
            return new PendingRequest(true, RequestTarget.wallet, request.getClientMetadataId(), AppSwitchHelper.getAppSwitchIntent(sContextInspector, sConfigManager, request, recipe));
        }
        Intent intent = BrowserSwitchHelper.getBrowserSwitchIntent(sContextInspector, sConfigManager, request);
        if (intent != null) {
            return new PendingRequest(true, RequestTarget.browser, request.getClientMetadataId(), intent);
        }
        return new PendingRequest(false, RequestTarget.browser, request.getClientMetadataId(), null);
    }

    public static Result parseResponse(Context context, Request request, Intent data) {
        initService(context);
        if (data != null && data.getData() != null) {
            return BrowserSwitchHelper.parseBrowserSwitchResponse(sContextInspector, request, data.getData());
        }
        if (data != null && data.getExtras() != null && !data.getExtras().isEmpty()) {
            return AppSwitchHelper.parseAppSwitchResponse(sContextInspector, request, data);
        }
        request.trackFpti(context, TrackingPoint.Cancel, null);
        return new Result();
    }

    public static String getClientMetadataId(Context context) {
        return PayPalDataCollector.getClientMetadataId(context);
    }

    public static String getClientMetadataId(Context context, String pairingId) {
        return PayPalDataCollector.getClientMetadataId(context, pairingId);
    }

    public static FptiManager getFptiManager(Context context) {
        initService(context);
        return sFptiManager;
    }

    private static void initService(Context context) {
        if (sConfigManager == null || sFptiManager == null) {
            PayPalHttpClient httpClient = (PayPalHttpClient) new PayPalHttpClient().setBaseUrl("https://api-m.paypal.com/v1/");
            sConfigManager = new ConfigManager(getContextInspector(context), httpClient);
            sFptiManager = new FptiManager(getContextInspector(context), httpClient);
        }
        sConfigManager.refreshConfiguration();
    }

    private static ContextInspector getContextInspector(Context context) {
        if (sContextInspector == null) {
            sContextInspector = new ContextInspector(context);
        }
        return sContextInspector;
    }
}
