package com.airbnb.android.superhero;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.PushNotificationType;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.services.PushNotificationBuilder;
import com.airbnb.android.core.utils.PushNotificationUtil;

public class SuperHeroLocalPushNotificationUtil {
    private SuperHeroLocalPushNotificationUtil() {
    }

    public static void show(Context context, Bundle messageBundle) {
        long messageId = SuperHeroBundleUtil.m1449id(messageBundle);
        PushNotificationBuilder builder = new PushNotificationBuilder(context).setTitleAndContent(context.getString(C0716R.string.superhero_name), SuperHeroBundleUtil.firstMessage(messageBundle)).setLaunchIntent(HomeActivityIntents.intentForSuperHero(context, messageBundle));
        PushNotificationType type = PushNotificationType.SuperHeroLocal;
        builder.buildAndNotify(PushNotificationUtil.getClientTag(type), PushNotificationUtil.getClientPushId(type, messageId));
    }
}
