package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.requests.SupportPhoneNumbersRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.Strap;
import java.util.UUID;

public class HelpCenterIntents {
    private static final String APP_MODE = "app_mode";
    private static final String ARTICLE = "article";
    private static final String CURRENCY = "currency";
    private static final String FEEDBACK = "feedback";
    private static final String GENERAL = "general";
    private static final String GUEST = "guest";
    private static final String HOST = "host";
    private static final String IMPRESSION = "impression";
    private static final String MOBILE_EVENT_ID = "mobile_event_id";
    private static final String MOBILE_HELP = "mobile_help";
    private static final String OPERATION = "operation";
    private static final String PAGE = "page";
    private static final String SUPPORTS_OFFLINE = "supports_offline";
    private static final String SUPPORTS_TRIPASSISTANT = "supports_tripassistant";
    private static final String TOPIC = "topic";
    private static final String TRUE_STRING = "true";

    private HelpCenterIntents() {
    }

    public static Intent intentForHelpCenter(Context context) {
        AirbnbEventLogger.track(MOBILE_HELP, Strap.make().mo11639kv("page", "general").mo11639kv(SUPPORTS_OFFLINE, "true").mo11639kv("operation", "impression"));
        prefetchForOfflineMode();
        if (!NetworkUtil.isConnected(context)) {
            return new Intent(context, Activities.helpCenter());
        }
        return intentForHelpCenterWebView(context);
    }

    public static Intent intentForHelpCenterWithTripAssistant(Context context) {
        AirbnbEventLogger.track(MOBILE_HELP, Strap.make().mo11639kv("page", "general").mo11639kv(SUPPORTS_OFFLINE, "true").mo11639kv(SUPPORTS_TRIPASSISTANT, "true").mo11639kv("operation", "impression"));
        if (!NetworkUtil.isConnected(context)) {
            return new Intent(context, Activities.helpCenter());
        }
        return intentForHelpThread(context);
    }

    private static Intent intentForHelpCenterWebView(Context context) {
        return intentForHelpCenterUrl(context, getBaseHelpCenterUrl(context)).backupIntent(new Intent(context, Activities.helpCenter())).toIntent();
    }

    public static WebViewIntentBuilder intentForHelpCenterArticle(Context context, int article) {
        return intentForHelpCenterUrl(context, getHelpCenterArticleUrl(context, article));
    }

    public static Intent intentForKoreanCancellationPolicy(Context context) {
        return intentForHelpCenterUrl(context, "https://www.airbnb.co.kr/home/cancellation_policies#strict").toIntent();
    }

    public static String getHelpCenterArticleUrl(Context context, int article) {
        return getBaseHelpCenterUrl(context) + "/" + ARTICLE + "/" + article;
    }

    public static WebViewIntentBuilder intentForHelpCenterTopic(Context context, int topic) {
        return intentForHelpCenterUrl(context, getHelpCenterTopicUrl(context, topic));
    }

    public static String getHelpCenterTopicUrl(Context context, int topic) {
        return getBaseHelpCenterUrl(context) + "/" + TOPIC + "/" + topic;
    }

    public static WebViewIntentBuilder intentForFeedback(Context context, boolean guestMode, String currency) {
        String uuid = UUID.randomUUID().toString();
        AirbnbEventLogger.track(MOBILE_HELP, Strap.make().mo11639kv("page", FEEDBACK).mo11639kv("operation", "impression").mo11639kv(MOBILE_EVENT_ID, uuid).mo11639kv(APP_MODE, guestMode ? "guest" : "host").mo11639kv("currency", currency));
        return intentForHelpCenterUrl(context, getBaseHelpCenterUrl(context) + "/" + FEEDBACK + "?" + MOBILE_EVENT_ID + "=" + uuid);
    }

    public static WebViewIntentBuilder intentForHelpCenterUrl(Context context, String url) {
        return WebViewIntentBuilder.newBuilder(context, url).title(C0716R.string.airbnb_help).authenticate();
    }

    public static String getBaseHelpCenterUrl(Context context) {
        return context.getString(C0716R.string.help_center_base_url);
    }

    public static Intent intentForHelpThread(Context context) {
        return new Intent(context, Activities.helpThread());
    }

    public static void prefetchForOfflineMode() {
        new SupportPhoneNumbersRequest().execute(NetworkUtil.singleFireExecutor());
    }
}
