package com.airbnb.android.sharing.shareables;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.p008mt.models.C6213ProductType;
import com.airbnb.android.core.utils.WeChatHelper;
import com.airbnb.android.sharing.C0921R;
import com.airbnb.android.sharing.enums.CustomShareActivities;
import com.airbnb.android.sharing.models.ShareArguments;
import com.airbnb.android.sharing.utils.ShareChannelsHelper;

public class ExperienceShareable extends Shareable {
    private final String baseUrl;
    private final String description;
    private final String fbShareUrl;
    private final String hostName;

    /* renamed from: id */
    private final long f1854id;
    private final String imageUrl;
    private final String location;
    private final String name;
    private final C6213ProductType productType;

    private ExperienceShareable(Context context, C6213ProductType productType2, long id, String baseUrl2, String fbShareUrl2, String name2, String imageUrl2, String description2, String hostName2, String location2) {
        super(context);
        this.productType = productType2;
        this.f1854id = id;
        this.name = name2;
        this.imageUrl = imageUrl2;
        this.description = description2;
        this.hostName = hostName2;
        this.location = location2;
        this.baseUrl = baseUrl2;
        this.fbShareUrl = fbShareUrl2;
        WeChatHelper.prefetchThumbnailForWechat(context, imageUrl2);
    }

    public ExperienceShareable(Context context, ShareArguments shareArguments) {
        this(context, C6213ProductType.from(shareArguments.productType().intValue()), shareArguments.mo11468id().longValue(), shareArguments.url(), shareArguments.fbShareUrl(), shareArguments.experienceTitle(), shareArguments.primaryImageUrl(), shareArguments.experienceDescription(), shareArguments.hostName(), shareArguments.location());
    }

    public String getUrl() {
        return this.baseUrl;
    }

    public String getName() {
        if (isImmersion()) {
            return null;
        }
        return this.name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getDeeplinkPath() {
        if (this.productType == C6213ProductType.EXPERIENCE) {
            return "d/CityHostsGuest/single_experience_template?tripTemplateId=" + this.f1854id;
        }
        return "d/CityHostsGuest/template?tripTemplateId=" + this.f1854id;
    }

    public Intent buildIntentForPackage(Intent baseIntent, CustomShareActivities csa, String packageName) {
        AirbnbEventLogger.event().name("sharing").mo8238kv(BaseAnalytics.OPERATION, "click").mo8238kv(BaseAnalytics.TARGET, "share_button").mo8238kv(ShareActivityIntents.ARG_ENTRY_POINT, isImmersion() ? "immersion" : ShareActivityIntents.ENTRY_POINT_EXPERIENCE).mo8237kv("trip_id", this.f1854id).mo8238kv("service", packageName).track();
        String url = buildShareUriString(csa);
        switch (csa) {
            case SMS:
            case GOOGLE_HANGOUTS:
                return baseIntent.putExtra("android.intent.extra.TEXT", this.context.getString(C0921R.string.experience_sms_sharetext, new Object[]{this.location, this.name, url}));
            case FACEBOOK:
                Activity activity = (Activity) this.context;
                if (!TextUtils.isEmpty(this.fbShareUrl)) {
                    url = this.fbShareUrl;
                }
                if (ShareChannelsHelper.shareToFacebook(activity, Uri.parse(url), Uri.parse(this.imageUrl), this.context.getString(C0921R.string.experience_facebook_sharetext, new Object[]{this.location, this.name, this.hostName}), this.description)) {
                    return null;
                }
                return buildDefaultIntent(baseIntent, csa);
            case WECHAT:
                WeChatHelper.shareWebPageToWechat(this.context, this.name, this.context.getString(C0921R.string.experience_default_sharetext, new Object[]{this.location}), url, this.imageUrl);
                return null;
            case TWITTER:
                return baseIntent.putExtra("android.intent.extra.TEXT", this.context.getString(C0921R.string.experience_twitter_no_handle_sharetext, new Object[]{this.location, this.context.getString(C0921R.string.airbnb_twitter_handle), url}));
            default:
                return buildDefaultIntent(baseIntent, csa);
        }
    }

    private boolean isImmersion() {
        return this.productType == C6213ProductType.IMMERSION;
    }
}
