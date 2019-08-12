package com.airbnb.android.core.data;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;

public class DTKPartnerTask extends PartnerTask<String> {
    public static final int DTK_AFFILIATE_ID = 20640851;
    public static final String DTK_COLUMN_NAME = "token";
    public static final String DTK_CONTENT_URI = "content://de.telekom.tsc.tokenprovider/token";

    public DTKPartnerTask(Context context) {
        super(context, DTK_CONTENT_URI, "token");
        ((CoreGraph) CoreApplication.instance(context).component()).inject(this);
    }

    public DTKPartnerTask(Context context, AffiliateInfo affiliateInfo) {
        super(context, DTK_CONTENT_URI, "token");
        this.mAffiliateInfo = affiliateInfo;
    }

    /* access modifiers changed from: protected */
    public String getToken(Cursor cursor, int columnIndex) {
        return cursor.getString(columnIndex);
    }

    /* access modifiers changed from: protected */
    public boolean isValidToken(String token) {
        return !TextUtils.isEmpty(token);
    }

    /* access modifiers changed from: protected */
    public void storeToken(String token) {
        this.mAffiliateInfo.storeAffiliateParams((int) DTK_AFFILIATE_ID, token, (String) null);
    }
}
