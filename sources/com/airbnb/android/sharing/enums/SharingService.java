package com.airbnb.android.sharing.enums;

import android.text.TextUtils;
import com.airbnb.android.core.utils.ExternalAppUtils;
import com.facebook.messenger.MessengerUtils;

public enum SharingService {
    WHATSAPP("com.whatsapp", 12),
    FACEBOOK(ExternalAppUtils.FACEBOOK_APP_ID, 40),
    GMAIL("com.google.android.gm", 39),
    KAKAOTALK("com.kakao.talk", 14),
    WECHAT(ExternalAppUtils.WECHAT_APP_ID, 42),
    FB_MESSENGER(MessengerUtils.PACKAGE_NAME, 26),
    SMS("com.android.mms", 23),
    COPY_TO_CLIPBOARD("com.google.android.apps.docs", 41),
    TWITTER("com.twitter.android", 4),
    EMAIL("com.android.email", 39),
    LINE("jp.naver.line.android", 27),
    GOOGLE_HANGOUTS("com.google.android.talk", 17),
    VIBER("com.viber.voip", 18),
    GOOGLE_PLUS("com.google.android.apps.plus", 19),
    BLACKBERRY("com.bbm", 20),
    QQ("com.tencent.mobileqq", 21),
    WEIBO("com.sina.weibo", 43),
    OTHER("", 0);
    
    public final String packageName;
    public final Integer trackingCode;

    private SharingService(String packageName2, int trackingCode2) {
        this.packageName = packageName2;
        this.trackingCode = Integer.valueOf(trackingCode2);
    }

    public static SharingService findServiceByPackageName(String packageName2) {
        SharingService[] values;
        for (SharingService service : values()) {
            if (TextUtils.equals(packageName2, service.packageName)) {
                return service;
            }
        }
        return OTHER;
    }
}
