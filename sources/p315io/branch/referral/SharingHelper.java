package p315io.branch.referral;

import com.airbnb.android.core.utils.ExternalAppUtils;
import com.facebook.messenger.MessengerUtils;

/* renamed from: io.branch.referral.SharingHelper */
public class SharingHelper {

    /* renamed from: io.branch.referral.SharingHelper$SHARE_WITH */
    public enum SHARE_WITH {
        FACEBOOK(ExternalAppUtils.FACEBOOK_APP_ID),
        FACEBOOK_MESSENGER(MessengerUtils.PACKAGE_NAME),
        TWITTER("com.twitter.android"),
        MESSAGE(".mms"),
        EMAIL("com.google.android.email"),
        FLICKR("com.yahoo.mobile.client.android.flickr"),
        GOOGLE_DOC("com.google.android.apps.docs"),
        WHATS_APP("com.whatsapp"),
        PINTEREST("com.pinterest"),
        HANGOUT("com.google.android.talk"),
        INSTAGRAM("com.instagram.android"),
        WECHAT("jom.tencent.mm"),
        GMAIL("com.google.android.gm");
        
        private String name;

        private SHARE_WITH(String key) {
            this.name = "";
            this.name = key;
        }

        public String toString() {
            return this.name;
        }
    }
}
