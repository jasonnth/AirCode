package com.airbnb.android.sharing.shareables;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.utils.WeChatHelper;
import com.airbnb.android.sharing.C0921R;
import com.airbnb.android.sharing.enums.CustomShareActivities;
import com.airbnb.android.sharing.utils.ShareChannelsHelper;

public class ActivityShareable extends Shareable {
    private final String baseUrl;

    /* renamed from: id */
    private final long f1850id;
    private final boolean isMeetup;
    private final String location;
    private final Photo photo;
    private final String title;

    public ActivityShareable(Context context, long id, String baseUrl2, Photo photo2, String title2, String location2, boolean isMeetup2) {
        super(context);
        this.f1850id = id;
        this.baseUrl = baseUrl2;
        this.photo = photo2;
        this.title = title2;
        this.location = location2;
        this.isMeetup = isMeetup2;
    }

    public String getName() {
        return this.title;
    }

    public String getImageUrl() {
        return this.photo.getLargeUrl();
    }

    public String getDeeplinkPath() {
        if (this.isMeetup) {
            return "d/meetup_activity?id=" + this.f1850id;
        }
        return "d/activity?id=" + this.f1850id;
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        return this.baseUrl;
    }

    public Intent buildIntentForPackage(Intent baseIntent, CustomShareActivities csa, String packageName) {
        AirbnbEventLogger.event().name("sharing").mo8238kv(BaseAnalytics.OPERATION, "click").mo8238kv(BaseAnalytics.TARGET, "share_button").mo8238kv(ShareActivityIntents.ARG_ENTRY_POINT, this.isMeetup ? "meetup" : "activity").mo8237kv("activity_id", this.f1850id).mo8238kv("service", packageName).track();
        String url = buildShareUriString(csa);
        switch (csa) {
            case SMS:
            case GOOGLE_HANGOUTS:
            case WHATSAPP:
                return baseIntent.putExtra("android.intent.extra.TEXT", this.context.getString(C0921R.string.activity_sms_sharetext, new Object[]{this.title, this.location, url}));
            case GMAIL:
            case EMAIL:
                return baseIntent.putExtra("android.intent.extra.TEXT", this.context.getString(C0921R.string.activity_email_sharetext_body, new Object[]{this.title, this.location, url})).putExtra("android.intent.extra.SUBJECT", this.context.getString(C0921R.string.activity_email_sharetext_subject, new Object[]{this.location}));
            case FACEBOOK:
                if (ShareChannelsHelper.shareToFacebook((Activity) this.context, Uri.parse(url), Uri.parse(getImageUrl()), this.title, this.context.getString(C0921R.string.activity_facebook_and_weibo_sharetext, new Object[]{this.title, this.location}))) {
                    return null;
                }
                return buildDefaultIntent(baseIntent, csa);
            case FB_MESSENGER:
                if (ShareChannelsHelper.shareToFacebookMessenger((Activity) this.context, Uri.parse(url), Uri.parse(getImageUrl()), this.title, this.context.getString(C0921R.string.activity_wechat_and_messenger_sharetext, new Object[]{this.title, this.location}))) {
                    return null;
                }
                return buildDefaultIntent(baseIntent, csa);
            case WECHAT:
                WeChatHelper.shareWebPageToWechat(this.context, this.title, this.context.getString(C0921R.string.activity_wechat_and_messenger_sharetext, new Object[]{this.title, this.location}), url, getImageUrl());
                return null;
            case TWITTER:
                return baseIntent.putExtra("android.intent.extra.TEXT", this.context.getString(C0921R.string.activity_twitter_sharetext, new Object[]{this.title, this.location, url}));
            default:
                return buildDefaultIntent(baseIntent, csa);
        }
    }
}
