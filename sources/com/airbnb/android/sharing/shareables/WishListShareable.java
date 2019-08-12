package com.airbnb.android.sharing.shareables;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.models.WishList;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.WeChatHelper;
import com.airbnb.android.sharing.C0921R;
import com.airbnb.android.sharing.enums.CustomShareActivities;
import com.facebook.FacebookSdk;
import com.facebook.imageutils.JfifUtil;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareLinkContent.Builder;
import com.facebook.share.widget.MessageDialog;
import com.kakao.KakaoLink;
import com.kakao.KakaoParameterException;
import org.spongycastle.crypto.tls.CipherSuite;

public class WishListShareable extends Shareable {
    private final Context context;
    private final boolean publicShare;
    private final WishList wishList;

    public WishListShareable(Context context2, WishList wishList2, boolean publicShare2) {
        super(context2);
        this.context = context2;
        this.wishList = wishList2;
        this.publicShare = publicShare2;
    }

    /* access modifiers changed from: protected */
    public String getUrl() {
        String url;
        if (this.publicShare) {
            url = this.context.getString(C0921R.string.wishlists_base_url, new Object[]{Long.valueOf(this.wishList.getId())});
        } else {
            url = this.wishList.getInviteUrl();
        }
        Check.notEmpty(url, "The wish list must have an invite url");
        return url;
    }

    private Intent buildDefaultIntent(Intent baseIntent, String wishListUrl) {
        String subject;
        baseIntent.putExtra("android.intent.extra.TEXT", wishListUrl);
        if (this.publicShare) {
            subject = this.context.getString(C0921R.string.share_wishlist_msg, new Object[]{this.wishList.getName()});
        } else {
            subject = this.context.getString(C0921R.string.wishlist_invite_friends_modal_text, new Object[]{this.wishList.getName()});
        }
        baseIntent.putExtra("android.intent.extra.SUBJECT", subject);
        return baseIntent;
    }

    private Intent createFacebookIntent(Intent baseIntent, String wishListUrl) {
        FacebookSdk.sdkInitialize(this.context);
        ShareLinkContent linkContent = ((Builder) new Builder().setContentTitle(this.wishList.getName()).setContentDescription(this.context.getString(C0921R.string.p3_sharetext)).setContentUrl(Uri.parse(wishListUrl))).setImageUrl(Uri.parse(this.wishList.getImageUrl())).build();
        if (!MessageDialog.canShow(ShareLinkContent.class)) {
            return buildDefaultIntent(baseIntent, wishListUrl);
        }
        MessageDialog.show((Activity) (AirActivity) this.context, (ShareContent) linkContent);
        return null;
    }

    private Intent createKakaoTalkIntent(Intent baseIntent, String wishListUrl) {
        try {
            return new Intent("android.intent.action.SEND", Uri.parse(KakaoLink.getKakaoLink(this.context).createKakaoTalkLinkMessageBuilder().addText(this.wishList.getName()).addImage(this.wishList.getImageUrl(), JfifUtil.MARKER_SOI, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA).addWebButton(this.context.getString(C0921R.string.p3_sharetext_kakao), wishListUrl).build()));
        } catch (KakaoParameterException e) {
            return buildDefaultIntent(baseIntent, wishListUrl);
        }
    }

    private void shareToWechat(String wishListUrl) {
        WeChatHelper.shareWebPageToWechat(this.context, this.wishList.getName(), this.context.getString(C0921R.string.p3_sharetext), wishListUrl, this.wishList.getImageUrl());
    }

    public String getName() {
        return this.wishList.getName();
    }

    public String getImageUrl() {
        return this.wishList.getImageUrl();
    }

    public String getDeeplinkPath() {
        return null;
    }

    public Intent buildIntentForPackage(Intent baseIntent, CustomShareActivities csa, String packageName) {
        AirbnbEventLogger.event().name("sharing").mo8238kv(BaseAnalytics.OPERATION, "click").mo8238kv(BaseAnalytics.TARGET, "share_button").mo8238kv(ShareActivityIntents.ARG_ENTRY_POINT, "wishlist").mo8237kv("wishlist_id", this.wishList.getId()).mo8238kv("service", packageName).track();
        String wishListUrl = buildFullShareUri(csa);
        switch (csa) {
            case FB_MESSENGER:
                return createFacebookIntent(baseIntent, wishListUrl);
            case KAKAOTALK:
                return createKakaoTalkIntent(baseIntent, wishListUrl);
            case WECHAT:
                shareToWechat(wishListUrl);
                return null;
            default:
                return buildDefaultIntent(baseIntent, wishListUrl);
        }
    }
}
