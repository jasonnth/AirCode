package com.airbnb.android.sharing.shareables;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.utils.WeChatHelper;
import com.airbnb.android.sharing.C0921R;
import com.airbnb.android.sharing.enums.CustomShareActivities;
import com.airbnb.android.sharing.models.ShareArguments;
import com.airbnb.android.sharing.utils.ShareChannelsHelper;

public class DetourGuidebookShareable extends Shareable {
    private final String about;
    private final String baseUrl;

    /* renamed from: id */
    private final long f1852id;
    private final String imageUrl;
    private final String title;

    private DetourGuidebookShareable(Context context, long id, String baseUrl2, String imageUrl2, String title2, String location, String about2, String description, String tips) {
        super(context);
        this.f1852id = id;
        this.baseUrl = baseUrl2;
        this.imageUrl = imageUrl2;
        this.title = title2;
        this.about = about2;
    }

    public DetourGuidebookShareable(Context context, ShareArguments shareArguments) {
        this(context, shareArguments.mo11468id().longValue(), shareArguments.url(), shareArguments.primaryImageUrl(), shareArguments.detourTitle(), shareArguments.location(), shareArguments.detourAbout(), shareArguments.detourDescription(), shareArguments.detourTips());
    }

    public String getName() {
        return this.title;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getDeeplinkPath() {
        return "d/Guidebook/detour?id=" + this.f1852id;
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        return this.baseUrl;
    }

    public Intent buildIntentForPackage(Intent baseIntent, CustomShareActivities csa, String packageName) {
        AirbnbEventLogger.event().name("sharing").mo8238kv(BaseAnalytics.OPERATION, "click").mo8238kv(BaseAnalytics.TARGET, "share_button").mo8238kv(ShareActivityIntents.ARG_ENTRY_POINT, ShareActivityIntents.ENTRY_POINT_GUIDEBOOK_DETOUR).mo8237kv("guidebook_id", this.f1852id).mo8238kv("service", packageName).track();
        String url = buildShareUriString(csa);
        switch (csa) {
            case SMS:
            case GOOGLE_HANGOUTS:
            case WHATSAPP:
                return baseIntent.putExtra("android.intent.extra.TEXT", this.context.getString(C0921R.string.detour_sms_sharetext, new Object[]{this.title, url}));
            case GMAIL:
            case EMAIL:
                return baseIntent.putExtra("android.intent.extra.TEXT", this.context.getString(C0921R.string.detour_email_sharetext_body, new Object[]{this.title, this.about, url})).putExtra("android.intent.extra.SUBJECT", this.context.getString(C0921R.string.detour_email_sharetext_subject));
            case FACEBOOK:
                return ShareChannelsHelper.shareToFacebook((Activity) this.context, Uri.parse(url), Uri.parse(this.imageUrl), this.title, this.context.getString(C0921R.string.detour_facebook_sharetext)) ? null : buildDefaultIntent(baseIntent, csa);
            case FB_MESSENGER:
                if (!ShareChannelsHelper.shareToFacebookMessenger((Activity) this.context, Uri.parse(url), Uri.parse(this.imageUrl), this.title, this.context.getString(C0921R.string.detour_facebook_sharetext))) {
                    return buildDefaultIntent(baseIntent, csa);
                }
                return null;
            case WECHAT:
                WeChatHelper.shareWebPageToWechat(this.context, this.title, this.context.getString(C0921R.string.detour_wechat_sharetext), url, this.imageUrl);
                return null;
            case TWITTER:
                return baseIntent.putExtra("android.intent.extra.TEXT", this.context.getString(C0921R.string.detour_twitter_no_handle_sharetext, new Object[]{this.context.getString(C0921R.string.airbnb_twitter_handle), url}));
            default:
                return buildDefaultIntent(baseIntent, csa);
        }
    }
}
