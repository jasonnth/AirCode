package com.airbnb.android.core.deeplinks;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.airbnb.android.core.ButtonPartnership;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.DeepLinkUtils;
import p005cn.jpush.android.JPushConstants.PushService;

public class DeepLinkReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String deepLinkUri = intent.getStringExtra("com.airbnb.deeplinkdispatch.EXTRA_URI");
        ButtonPartnership.get().onOpenDeepLink(deepLinkUri);
        C0715L.m1194i(DeepLinkReceiver.class.getSimpleName(), "Open deeplink: " + deepLinkUri);
        if (intent.getBooleanExtra("com.airbnb.deeplinkdispatch.EXTRA_SUCCESSFUL", false)) {
            AirbnbEventLogger.event().name("mobile_deeplink").mo8238kv("uri", deepLinkUri).mo8238kv(PushService.PARAM_RESULT, "success").track();
            return;
        }
        context.startActivity(HomeActivityIntents.intentForUnhandledDeeplink(context, deepLinkUri).addFlags(268435456));
        String errorMessage = intent.getStringExtra("com.airbnb.deeplinkdispatch.EXTRA_ERROR_MESSAGE");
        if (BuildHelper.isDebugFeaturesEnabled()) {
            Toast.makeText(context, "Deeplink error: " + errorMessage, 0).show();
        }
        AirbnbEventLogger.event().name("mobile_deeplink").mo8238kv("uri", deepLinkUri).mo8238kv(PushService.PARAM_RESULT, "error").mo8238kv("message", errorMessage).track();
        DeepLinkUtils.notifyUnhandledDeepLink(deepLinkUri, errorMessage);
    }
}
