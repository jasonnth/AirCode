package com.airbnb.android.core.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.bumptech.glide.Glide;
import com.google.common.collect.ImmutableList;
import com.tencent.p313mm.sdk.modelmsg.SendAuth.Req;
import com.tencent.p313mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.p313mm.sdk.modelmsg.WXImageObject;
import com.tencent.p313mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.p313mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.p313mm.sdk.openapi.IWXAPI;
import com.tencent.p313mm.sdk.openapi.WXAPIFactory;
import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WeChatHelper {
    public static int DESCRIPTION_LENGTH_LIMIT = 256;
    private static final String OFFICIAL_DOMAIN = "www.airbnbchina.cn";
    private static final List<String> SHARE_DOMAINS_NEED_REPLACE = ImmutableList.m1286of("www.airbnb.com", "zh.airbnb.com");
    private static final int THUMBNAIL_SIZE = 300;
    private static final String WECHAT_FAVORITE_SHARE = "com.tencent.mm.ui.tools.AddFavoriteUI";
    public static String WECHAT_LOGIN = "WECHAT_LOGIN";
    public static String WECHAT_LOGIN_AUTH_KEY = "snsapi_userinfo";
    public static String WECHAT_LOGIN_IDENTIFIER = "AIRBNB";
    private static final String WECHAT_MOMENTS_SHARE = "com.tencent.mm.ui.tools.ShareToTimeLineUI";
    public static String WECHAT_SHARE = "WECHAT_SHARE";
    public static String WECHAT_SHARE_TRIP = "WECHAT_SHARE_TRIP";
    public static final int WECHAT_SHARE_TYPE_FAVORITE = 2;
    public static final int WECHAT_SHARE_TYPE_MESSAGE = 0;
    public static final int WECHAT_SHARE_TYPE_MOMENTS = 1;
    private static final String WECHAT_TALK_SHARE = "com.tencent.mm.ui.tools.ShareImgUI";

    public static void loginWithWeChat(Context context) {
        String wechatAppID = getWeChatID(context);
        IWXAPI wechatAPI = WXAPIFactory.createWXAPI(context, wechatAppID, true);
        wechatAPI.registerApp(wechatAppID);
        Req req = new Req();
        req.scope = WECHAT_LOGIN_AUTH_KEY;
        req.state = WECHAT_LOGIN_IDENTIFIER;
        req.transaction = WECHAT_LOGIN;
        wechatAPI.sendReq(req);
    }

    public static Bitmap fetchThumbnailForWeChatSharing(Context context, String imageUrl) {
        try {
            return (Bitmap) Glide.with(context).load(imageUrl).asBitmap().into(300, 300).get();
        } catch (InterruptedException | ExecutionException e) {
            return null;
        }
    }

    public static String getWeChatID(Context context) {
        if (BuildHelper.isDevelopmentBuild()) {
            return context.getString(C0716R.string.wechat_weixin_app_dev_id);
        }
        return context.getString(C0716R.string.wechat_weixin_app_id);
    }

    public static void shareWebPageToWechat(Context context, String title, String description, String url) {
        shareWebPageToWechat(context, title, description, url, null);
    }

    public static void shareWebPageToWechat(Context context, String title, String description, String url, String imageUrl) {
        shareWebPageToWechat(context, title, description, url, AirImageView.getBitmapFromCacheOrDrawable(context, imageUrl, C0716R.C0717drawable.airbnb_logo_white_bg), "");
    }

    public static void shareWebPageToWechat(Context context, String title, String description, String url, Bitmap thumbnail, String className) {
        IWXAPI wechatAPI = WXAPIFactory.createWXAPI(context, getWeChatID(context), true);
        WXMediaMessage msg = new WXMediaMessage(new WXWebpageObject(replaceWithWeChatOfficialDomain(url)));
        msg.title = title;
        msg.description = description;
        msg.setThumbImage(thumbnail);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.message = msg;
        req.transaction = WECHAT_SHARE;
        req.scene = findShareTypeByClassName(className);
        wechatAPI.sendReq(req);
    }

    private static String replaceWithWeChatOfficialDomain(String originalUrl) {
        try {
            URI uri = new URI(originalUrl);
            if (SHARE_DOMAINS_NEED_REPLACE.contains(uri.getAuthority())) {
                return new URI(uri.getScheme(), uri.getUserInfo(), OFFICIAL_DOMAIN, uri.getPort(), uri.getPath(), uri.getQuery(), uri.getFragment()).toString();
            }
            return originalUrl;
        } catch (URISyntaxException e) {
            BugsnagWrapper.throwOrNotify(new RuntimeException(e));
            return originalUrl;
        }
    }

    private static int findShareTypeByClassName(String className) {
        if (TextUtils.isEmpty(className)) {
            return 0;
        }
        char c = 65535;
        switch (className.hashCode()) {
            case -1707757395:
                if (className.equals(WECHAT_TALK_SHARE)) {
                    c = 0;
                    break;
                }
                break;
            case -615488292:
                if (className.equals(WECHAT_FAVORITE_SHARE)) {
                    c = 2;
                    break;
                }
                break;
            case 1722520506:
                if (className.equals(WECHAT_MOMENTS_SHARE)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Coudn't find the expected wechat class " + className));
                return 0;
        }
    }

    public static void sharePhotoToWechatMoments(Context context, String filePath) {
        WXImageObject imgObj = new WXImageObject();
        imgObj.setImagePath(filePath);
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
        Bitmap bmp = BitmapFactory.decodeFile(filePath);
        Bitmap thumbBmp = Bitmap.createScaledBitmap(bmp, 50, (bmp.getHeight() * 50) / bmp.getWidth(), true);
        bmp.recycle();
        msg.thumbData = bmpToByteArray(thumbBmp);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = WECHAT_SHARE_TRIP;
        req.message = msg;
        req.scene = 1;
        WXAPIFactory.createWXAPI(context, getWeChatID(context), true).sendReq(req);
    }

    private static byte[] bmpToByteArray(Bitmap bmp) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(CompressFormat.PNG, 10, output);
        bmp.recycle();
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void prefetchThumbnailForWechat(Context context, String thumbnailURL) {
        if (ExternalAppUtils.isWechatInstalled(context) && !TextUtils.isEmpty(thumbnailURL)) {
            AirImageView.getImageBackground(context, thumbnailURL);
        }
    }
}
