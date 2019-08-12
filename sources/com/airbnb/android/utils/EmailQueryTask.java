package com.airbnb.android.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import p005cn.jpush.android.data.DbHelper;

public abstract class EmailQueryTask extends AsyncTask<Void, Void, List<String>> {
    private static final int MAX_RESULTS = 20;
    private final ContentResolver contentResolver;
    protected final String query;

    /* access modifiers changed from: protected */
    public abstract void onPostExecute(List<String> list);

    public EmailQueryTask(ContentResolver contentResolver2, String query2) {
        this.contentResolver = contentResolver2;
        this.query = query2;
    }

    /* access modifiers changed from: protected */
    public List<String> doInBackground(Void... params) {
        HashSet<Integer> filterIds = new HashSet<>();
        Cursor filterContactsCursor = getSearchFilterCursor(this.query);
        int columnIndex = filterContactsCursor.getColumnIndex(DbHelper.TABLE_ID);
        while (filterContactsCursor.moveToNext()) {
            filterIds.add(Integer.valueOf(filterContactsCursor.getInt(columnIndex)));
        }
        filterContactsCursor.close();
        return getFilteredContactsFromCursor(getRawAllContactsCursor(), filterIds);
    }

    private static List<String> getFilteredContactsFromCursor(Cursor cursor, HashSet<Integer> filterContactIds) {
        ArrayList<String> emails = new ArrayList<>();
        int idIndex = cursor.getColumnIndexOrThrow("contact_id");
        int dataIndex = cursor.getColumnIndexOrThrow("data1");
        while (cursor.moveToNext()) {
            int contactId = cursor.getInt(idIndex);
            String contactEmailPhoneData = cursor.getString(dataIndex);
            if (filterContactIds == null || filterContactIds.contains(Integer.valueOf(contactId))) {
                emails.add(contactEmailPhoneData);
                if (emails.size() >= 20) {
                    break;
                }
            }
        }
        cursor.close();
        return emails;
    }

    private Cursor getRawAllContactsCursor() {
        return this.contentResolver.query(Data.CONTENT_URI, new String[]{"display_name", "contact_id", "data1"}, "mimetype=?", new String[]{"vnd.android.cursor.item/email_v2"}, "display_name");
    }

    private Cursor getSearchFilterCursor(String filterString) {
        Uri contentUri = Uri.withAppendedPath(Contacts.CONTENT_FILTER_URI, Uri.encode(filterString));
        return this.contentResolver.query(contentUri, new String[]{DbHelper.TABLE_ID}, null, null, null);
    }
}
