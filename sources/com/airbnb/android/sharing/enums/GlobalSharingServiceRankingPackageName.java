package com.airbnb.android.sharing.enums;

import android.text.TextUtils;
import com.airbnb.android.core.utils.ExternalAppUtils;
import com.facebook.messenger.MessengerUtils;

public enum GlobalSharingServiceRankingPackageName {
    COPY_TO_CLIPBOARD("com.google.android.apps.docs", 1),
    SMS("com.android.mms", 2),
    GMAIL("com.google.android.gm", 3),
    EMAIL("com.android.email", 3),
    WHATSAPP("com.whatsapp", 4),
    WECHAT(ExternalAppUtils.WECHAT_APP_ID, 5),
    FACEBOOK(ExternalAppUtils.FACEBOOK_APP_ID, 6),
    FB_MESSENGER(MessengerUtils.PACKAGE_NAME, 7),
    KAKAOTALK("com.kakao.talk", 8),
    LINE("jp.naver.line.android", 9),
    QQ("com.tencent.mobileqq", 10),
    GOOGLE_HANGOUTS("com.google.android.talk", 11),
    TWITTER("com.twitter.android", 12),
    VIBER("com.viber.voip", 13),
    GOOGLE_PLUS("com.google.android.apps.plus", 14),
    BLUETOOTH("com.android.bluetooth", 15),
    Verizon("com.verizon.messaging.vzmsgs", 16),
    SAMSUNG_EMAIL("com.samsung.android.email.provider", 17),
    VK("com.vkontakte.android", 18),
    YAHOO("com.yahoo.mobile.client.android.mail", 19),
    TELEGRAM("org.telegram.messenger", 20),
    WEIBO("com.sina.weibo", 21),
    QQMAIL("com.tencent.androidqqmail", 22),
    GMESSAGE("com.google.android.apps.messaging", 23),
    OUTLOOK("com.microsoft.office.outlook", 24),
    SKYPE("com.skype.raider", 25),
    SNAPCHAT("com.snapchat.android", 26),
    OTHER("", 27);
    
    private static final String OTHER_GOOGLE_DRIVE_CLASS = "com.google.android.apps.docs.shareitem.UploadSharedItemActivity";
    public final String packageName;
    public final Integer ranking;

    private GlobalSharingServiceRankingPackageName(String packageName2, int ranking2) {
        this.packageName = packageName2;
        this.ranking = Integer.valueOf(ranking2);
    }

    public static int findRankingByPackageName(String packageName2, String className) {
        GlobalSharingServiceRankingPackageName[] values;
        if (TextUtils.equals(className, OTHER_GOOGLE_DRIVE_CLASS)) {
            return OTHER.ranking.intValue();
        }
        for (GlobalSharingServiceRankingPackageName serviceRanking : values()) {
            if (TextUtils.equals(packageName2, serviceRanking.packageName)) {
                return serviceRanking.ranking.intValue();
            }
        }
        return OTHER.ranking.intValue();
    }
}
