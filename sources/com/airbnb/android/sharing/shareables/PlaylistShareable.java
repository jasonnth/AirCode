package com.airbnb.android.sharing.shareables;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.utils.WeChatHelper;
import com.airbnb.android.sharing.C0921R;
import com.airbnb.android.sharing.enums.CustomShareActivities;
import com.airbnb.android.sharing.utils.ShareChannelsHelper;

public class PlaylistShareable extends Shareable {
    private final String baseUrl;

    /* renamed from: id */
    private final long f1861id;
    private final String location;
    private final Photo photo;
    private final String title;

    public PlaylistShareable(Context context, long id, String baseUrl2, Photo photo2, String title2, String location2) {
        super(context);
        this.f1861id = id;
        this.baseUrl = baseUrl2;
        this.photo = photo2;
        this.title = title2;
        this.location = location2;
    }

    public String getName() {
        return this.title;
    }

    public String getImageUrl() {
        return this.photo.getLargeUrl();
    }

    public String getDeeplinkPath() {
        return "d/playlist?id=" + this.f1861id;
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        return this.baseUrl;
    }

    public Intent buildIntentForPackage(Intent baseIntent, CustomShareActivities csa, String packageName) {
        switch (csa) {
            case SMS:
            case GOOGLE_HANGOUTS:
            case WHATSAPP:
                return baseIntent.putExtra("android.intent.extra.TEXT", this.context.getString(C0921R.string.playlist_sms_sharetext, new Object[]{this.location, this.baseUrl}));
            case GMAIL:
            case EMAIL:
                return baseIntent.putExtra("android.intent.extra.TEXT", this.context.getString(C0921R.string.playlist_email_sharetext_body, new Object[]{this.location, this.title, this.baseUrl})).putExtra("android.intent.extra.SUBJECT", this.context.getString(C0921R.string.playlist_email_sharetext_subject, new Object[]{this.location}));
            case FACEBOOK:
                return ShareChannelsHelper.shareToFacebook((Activity) this.context, Uri.parse(this.baseUrl), Uri.parse(getImageUrl()), this.title, this.context.getString(C0921R.string.playlist_wechat_and_messenger_sharetext, new Object[]{this.location})) ? null : buildDefaultIntent(baseIntent, csa);
            case FB_MESSENGER:
                if (!ShareChannelsHelper.shareToFacebookMessenger((Activity) this.context, Uri.parse(this.baseUrl), Uri.parse(getImageUrl()), this.title, this.context.getString(C0921R.string.playlist_wechat_and_messenger_sharetext, new Object[]{this.location}))) {
                    return buildDefaultIntent(baseIntent, csa);
                }
                return null;
            case WECHAT:
                WeChatHelper.shareWebPageToWechat(this.context, this.title, this.context.getString(C0921R.string.playlist_wechat_and_messenger_sharetext, new Object[]{this.location}), this.baseUrl, getImageUrl());
                return null;
            case TWITTER:
                return baseIntent.putExtra("android.intent.extra.TEXT", this.context.getString(C0921R.string.playlist_twitter_sharetext, new Object[]{this.location, this.title, this.baseUrl}));
            default:
                return buildDefaultIntent(baseIntent, csa);
        }
    }
}
