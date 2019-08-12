package com.android.p305ex.chips;

import android.content.res.Resources;
import android.net.Uri;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import p005cn.jpush.android.data.DbHelper;

/* renamed from: com.android.ex.chips.Queries */
class Queries {
    public static final Query EMAIL = new Query(new String[]{"display_name", "data1", "data2", "data3", "contact_id", DbHelper.TABLE_ID, "photo_thumb_uri", "display_name_source", "lookup", "mimetype"}, Email.CONTENT_FILTER_URI, Email.CONTENT_URI) {
        public CharSequence getTypeLabel(Resources res, int type, CharSequence label) {
            return Email.getTypeLabel(res, type, label);
        }
    };
    public static final Query PHONE = new Query(new String[]{"display_name", "data1", "data2", "data3", "contact_id", DbHelper.TABLE_ID, "photo_thumb_uri", "display_name_source", "lookup", "mimetype"}, Phone.CONTENT_FILTER_URI, Phone.CONTENT_URI) {
        public CharSequence getTypeLabel(Resources res, int type, CharSequence label) {
            return Phone.getTypeLabel(res, type, label);
        }
    };

    /* renamed from: com.android.ex.chips.Queries$Query */
    static abstract class Query {
        private final Uri mContentFilterUri;
        private final Uri mContentUri;
        private final String[] mProjection;

        public abstract CharSequence getTypeLabel(Resources resources, int i, CharSequence charSequence);

        public Query(String[] projection, Uri contentFilter, Uri content) {
            this.mProjection = projection;
            this.mContentFilterUri = contentFilter;
            this.mContentUri = content;
        }

        public String[] getProjection() {
            return this.mProjection;
        }

        public Uri getContentFilterUri() {
            return this.mContentFilterUri;
        }

        public Uri getContentUri() {
            return this.mContentUri;
        }
    }
}
