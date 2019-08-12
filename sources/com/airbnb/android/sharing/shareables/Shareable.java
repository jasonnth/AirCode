package com.airbnb.android.sharing.shareables;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.sharing.SharingGraph;
import com.airbnb.android.sharing.enums.CustomShareActivities;
import p315io.branch.indexing.BranchUniversalObject;
import p315io.branch.referral.util.LinkProperties;

public abstract class Shareable {
    AirbnbAccountManager accountManager;
    AirbnbApi airbnbApi;
    protected final Context context;

    public abstract Intent buildIntentForPackage(Intent intent, CustomShareActivities customShareActivities, String str);

    public abstract String getDeeplinkPath();

    public abstract String getImageUrl();

    public abstract String getName();

    /* access modifiers changed from: protected */
    public abstract String getUrl();

    public Shareable(Context context2) {
        this.context = context2;
        ((SharingGraph) CoreApplication.instance(context2).component()).inject(this);
    }

    public String getImagePath() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String buildShareUriString(CustomShareActivities csa) {
        String uri = buildFullShareUri(csa);
        if (csa == CustomShareActivities.WECHAT) {
            return uri;
        }
        BranchUniversalObject branchUri = new BranchUniversalObject().setCanonicalIdentifier(uri).setTitle(getName()).setContentImageUrl(getImageUrl());
        LinkProperties linkProperties = new LinkProperties().setFeature("sharing").setChannel(csa.packageName).addControlParameter("$desktop_url", uri).addControlParameter("$ios_url", uri).addControlParameter("$android_url", uri);
        String deepLinkPath = getDeeplinkPath();
        if (TextUtils.isEmpty(deepLinkPath)) {
            linkProperties.addControlParameter("$web_only", "true");
        } else {
            linkProperties.addControlParameter("$deeplink_path", deepLinkPath);
        }
        String shortUri = branchUri.getShortUrl(this.context, linkProperties);
        if (shortUri == null) {
            shortUri = uri;
        }
        return shortUri;
    }

    /* access modifiers changed from: protected */
    public String buildFullShareUri(CustomShareActivities csa) {
        Builder uri = Uri.parse(getUrl()).buildUpon();
        if (csa.trackingCode != null) {
            uri.appendQueryParameter("s", Integer.toString(csa.trackingCode.intValue()));
        }
        uri.appendQueryParameter("user_id", Long.toString(this.accountManager.getCurrentUser().getId()));
        uri.appendQueryParameter("ref_device_id", this.airbnbApi.getAndroidId());
        return uri.build().toString();
    }

    public Intent buildDefaultIntent(Intent baseIntent, CustomShareActivities csa) {
        return baseIntent.putExtra("android.intent.extra.TEXT", buildShareUriString(csa));
    }
}
