package com.airbnb.android.sharing.enums;

import android.content.Context;
import android.content.pm.ResolveInfo;
import com.airbnb.android.core.utils.ExternalAppUtils;
import com.facebook.messenger.MessengerUtils;
import java.util.Comparator;

@Deprecated
public enum CustomShareActivities {
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
    OTHER("");
    
    private static CustomShareActivities[] sAll;
    public final String packageName;
    public final Integer trackingCode;

    private CustomShareActivities(String packageName2) {
        this.packageName = packageName2;
        this.trackingCode = null;
    }

    private CustomShareActivities(String packageName2, int trackingCode2) {
        this.packageName = packageName2;
        this.trackingCode = Integer.valueOf(trackingCode2);
    }

    public static CustomShareActivities getEnum(String packageName2) {
        CustomShareActivities[] customShareActivitiesArr;
        if (sAll == null) {
            sAll = values();
        }
        for (CustomShareActivities csa : sAll) {
            if (csa.packageName.equalsIgnoreCase(packageName2)) {
                return csa;
            }
        }
        return OTHER;
    }

    public static Comparator<ResolveInfo> getComparator(Context context) {
        return CustomShareActivities$$Lambda$1.lambdaFactory$(context);
    }
}
