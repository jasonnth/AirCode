package com.airbnb.android.sharing.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.airbnb.android.sharing.C0921R;
import com.facebook.FacebookSdk;
import com.facebook.imageutils.JfifUtil;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareLinkContent.Builder;
import com.facebook.share.widget.MessageDialog;
import com.facebook.share.widget.ShareDialog;
import com.kakao.KakaoLink;
import com.kakao.KakaoParameterException;
import org.spongycastle.crypto.tls.CipherSuite;

public class ShareChannelsHelper {
    public static boolean shareToFacebook(Activity context, Uri shareUri, Uri imageUri, String title, String description) {
        FacebookSdk.sdkInitialize(context);
        ShareLinkContent linkContent = ((Builder) new Builder().setContentTitle(title).setContentDescription(description).setContentUrl(shareUri)).setImageUrl(imageUri).build();
        if (!ShareDialog.canShow(ShareLinkContent.class)) {
            return false;
        }
        ShareDialog.show(context, (ShareContent) linkContent);
        return true;
    }

    public static boolean shareToFacebookMessenger(Activity context, Uri shareUri, Uri imageUri, String title, String description) {
        FacebookSdk.sdkInitialize(context);
        ShareLinkContent linkContent = ((Builder) new Builder().setContentTitle(title).setContentDescription(description).setContentUrl(shareUri)).setImageUrl(imageUri).build();
        if (!MessageDialog.canShow(ShareLinkContent.class)) {
            return false;
        }
        MessageDialog.show(context, (ShareContent) linkContent);
        return true;
    }

    public static Intent shareToKakaoTalk(Context context, String shareUrl, String imageUrl, String name) {
        try {
            return new Intent("android.intent.action.SEND", Uri.parse(KakaoLink.getKakaoLink(context).createKakaoTalkLinkMessageBuilder().addText(name).addImage(imageUrl, JfifUtil.MARKER_SOI, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA).addWebButton(context.getString(C0921R.string.p3_sharetext_kakao), shareUrl).build()));
        } catch (KakaoParameterException e) {
            return null;
        }
    }
}
