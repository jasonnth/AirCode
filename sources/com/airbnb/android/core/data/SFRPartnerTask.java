package com.airbnb.android.core.data;

import android.content.Context;
import android.database.Cursor;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;

public class SFRPartnerTask extends PartnerTask<Integer> {
    public static final int SFR_AFFILIATE_ID = 27222538;
    public static final String SFR_COLUMN_NAME = "Result";
    public static final String SFR_CONTENT_URI = "content://com.sfr.android.console.partner.provider/package/0bc85d67";

    public SFRPartnerTask(Context context) {
        super(context, SFR_CONTENT_URI, SFR_COLUMN_NAME);
        ((CoreGraph) CoreApplication.instance(context).component()).inject(this);
    }

    public SFRPartnerTask(Context context, AffiliateInfo affiliateInfo) {
        super(context, SFR_CONTENT_URI, SFR_COLUMN_NAME);
        this.mAffiliateInfo = affiliateInfo;
    }

    /* access modifiers changed from: protected */
    public Integer getToken(Cursor cursor, int columnIndex) {
        return Integer.valueOf(cursor.getInt(columnIndex));
    }

    /* access modifiers changed from: protected */
    public boolean isValidToken(Integer token) {
        return token != null && token.intValue() == 1;
    }

    /* access modifiers changed from: protected */
    public void storeToken(Integer token) {
        this.mAffiliateInfo.storeAffiliateParams((int) SFR_AFFILIATE_ID, "SFR", (String) null);
    }
}
