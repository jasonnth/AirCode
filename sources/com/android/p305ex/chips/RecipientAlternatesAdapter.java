package com.android.p305ex.chips;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.ContactsContract.Contacts;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import com.android.p305ex.chips.BaseRecipientAdapter.DirectorySearchParams;
import com.android.p305ex.chips.DropdownChipLayouter.AdapterType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* renamed from: com.android.ex.chips.RecipientAlternatesAdapter */
public class RecipientAlternatesAdapter extends CursorAdapter {
    private static final Map<String, String> sCorrectedPhotoUris = new HashMap();
    private OnCheckedItemChangedListener mCheckedItemChangedListener;
    private int mCheckedItemPosition = -1;
    private final long mCurrentId;
    private final Long mDirectoryId;
    private DropdownChipLayouter mDropdownChipLayouter;

    /* renamed from: com.android.ex.chips.RecipientAlternatesAdapter$CaseInsensitiveMap */
    private static class CaseInsensitiveMap extends HashMap<String, RecipientEntry> {
        private CaseInsensitiveMap() {
        }

        public RecipientEntry put(String key, RecipientEntry value) {
            return (RecipientEntry) super.put(key.toLowerCase(), value);
        }

        public RecipientEntry get(Object key) {
            return (RecipientEntry) super.get(String.valueOf(key).toLowerCase());
        }
    }

    /* renamed from: com.android.ex.chips.RecipientAlternatesAdapter$OnCheckedItemChangedListener */
    interface OnCheckedItemChangedListener {
        void onCheckedItemChanged(int i);
    }

    /* renamed from: com.android.ex.chips.RecipientAlternatesAdapter$RecipientMatchCallback */
    public interface RecipientMatchCallback {
        void matchesFound(Map<String, RecipientEntry> map);

        void matchesNotFound(Set<String> set);
    }

    public static void getMatchingRecipients(Context context, BaseRecipientAdapter adapter, ArrayList<String> inAddresses, Account account, RecipientMatchCallback callback) {
        getMatchingRecipients(context, adapter, inAddresses, 0, account, callback);
    }

    public static void getMatchingRecipients(Context context, BaseRecipientAdapter adapter, ArrayList<String> inAddresses, int addressType, Account account, RecipientMatchCallback callback) {
        Query query;
        List<DirectorySearchParams> paramsList;
        if (addressType == 0) {
            query = Queries.EMAIL;
        } else {
            query = Queries.PHONE;
        }
        int addressesSize = Math.min(50, inAddresses.size());
        HashSet<String> addresses = new HashSet<>();
        StringBuilder bindString = new StringBuilder();
        for (int i = 0; i < addressesSize; i++) {
            Rfc822Token[] tokens = Rfc822Tokenizer.tokenize(((String) inAddresses.get(i)).toLowerCase());
            addresses.add(tokens.length > 0 ? tokens[0].getAddress() : (String) inAddresses.get(i));
            bindString.append("?");
            if (i < addressesSize - 1) {
                bindString.append(",");
            }
        }
        if (Log.isLoggable("RecipAlternates", 3)) {
            Log.d("RecipAlternates", "Doing reverse lookup for " + addresses.toString());
        }
        String[] addressArray = new String[addresses.size()];
        addresses.toArray(addressArray);
        Cursor c = null;
        try {
            c = context.getContentResolver().query(query.getContentUri(), query.getProjection(), query.getProjection()[1] + " IN (" + bindString.toString() + ")", addressArray, null);
            HashMap<String, RecipientEntry> recipientEntries = processContactEntries(c, null);
            callback.matchesFound(recipientEntries);
            if (c != null) {
                c.close();
            }
            HashSet hashSet = new HashSet();
            if (recipientEntries.size() < addresses.size()) {
                Cursor directoryCursor = null;
                try {
                    directoryCursor = context.getContentResolver().query(DirectoryListQuery.URI, DirectoryListQuery.PROJECTION, null, null, null);
                    if (directoryCursor == null) {
                        paramsList = null;
                    } else {
                        paramsList = BaseRecipientAdapter.setupOtherDirectories(context, directoryCursor, account);
                    }
                    HashSet<String> unresolvedAddresses = new HashSet<>();
                    Iterator it = addresses.iterator();
                    while (it.hasNext()) {
                        String address = (String) it.next();
                        if (!recipientEntries.containsKey(address)) {
                            unresolvedAddresses.add(address);
                        }
                    }
                    hashSet.addAll(unresolvedAddresses);
                    if (paramsList != null) {
                        Cursor directoryContactsCursor = null;
                        Iterator it2 = unresolvedAddresses.iterator();
                        while (it2.hasNext()) {
                            String unresolvedAddress = (String) it2.next();
                            Long directoryId = null;
                            int i2 = 0;
                            while (true) {
                                if (i2 >= paramsList.size()) {
                                    break;
                                }
                                try {
                                    directoryContactsCursor = doQuery(unresolvedAddress, 1, Long.valueOf(((DirectorySearchParams) paramsList.get(i2)).directoryId), account, context.getContentResolver(), query);
                                    if (directoryContactsCursor == null || directoryContactsCursor.getCount() != 0) {
                                        directoryId = Long.valueOf(((DirectorySearchParams) paramsList.get(i2)).directoryId);
                                    } else {
                                        directoryContactsCursor.close();
                                        directoryContactsCursor = null;
                                        i2++;
                                    }
                                } catch (Throwable th) {
                                    if (directoryContactsCursor == null || directoryContactsCursor.getCount() != 0) {
                                        directoryId = Long.valueOf(((DirectorySearchParams) paramsList.get(i2)).directoryId);
                                    } else {
                                        directoryContactsCursor.close();
                                        throw th;
                                    }
                                }
                            }
                            directoryId = Long.valueOf(((DirectorySearchParams) paramsList.get(i2)).directoryId);
                            if (directoryContactsCursor != null) {
                                try {
                                    HashMap processContactEntries = processContactEntries(directoryContactsCursor, directoryId);
                                    for (String address2 : processContactEntries.keySet()) {
                                        hashSet.remove(address2);
                                    }
                                    callback.matchesFound(processContactEntries);
                                } finally {
                                    directoryContactsCursor.close();
                                }
                            }
                        }
                    }
                } finally {
                    if (directoryCursor != null) {
                        directoryCursor.close();
                    }
                }
            }
            if (adapter != null) {
                Map<String, RecipientEntry> entries = adapter.getMatchingRecipients(hashSet);
                if (entries != null && entries.size() > 0) {
                    callback.matchesFound(entries);
                    for (String address3 : entries.keySet()) {
                        hashSet.remove(address3);
                    }
                }
            }
            callback.matchesNotFound(hashSet);
        } catch (Exception e) {
            if (c != null) {
                c.close();
            }
        } catch (Throwable th2) {
            if (c != null) {
                c.close();
            }
            throw th2;
        }
    }

    private static HashMap<String, RecipientEntry> processContactEntries(Cursor c, Long directoryId) {
        CaseInsensitiveMap caseInsensitiveMap = new CaseInsensitiveMap();
        if (c == null || !c.moveToFirst()) {
            return caseInsensitiveMap;
        }
        do {
            String address = c.getString(1);
            caseInsensitiveMap.put(address, getBetterRecipient((RecipientEntry) caseInsensitiveMap.get(address), RecipientEntry.constructTopLevelEntry(c.getString(0), c.getInt(7), c.getString(1), c.getInt(2), c.getString(3), c.getLong(4), directoryId, c.getLong(5), c.getString(6), true, c.getString(8))));
            if (Log.isLoggable("RecipAlternates", 3)) {
                Log.d("RecipAlternates", "Received reverse look up information for " + address + " RESULTS:  NAME : " + c.getString(0) + " CONTACT ID : " + c.getLong(4) + " ADDRESS :" + c.getString(1));
            }
        } while (c.moveToNext());
        return caseInsensitiveMap;
    }

    static RecipientEntry getBetterRecipient(RecipientEntry entry1, RecipientEntry entry2) {
        if (entry2 == null) {
            return entry1;
        }
        if (entry1 == null) {
            return entry2;
        }
        if (!TextUtils.isEmpty(entry1.getDisplayName()) && TextUtils.isEmpty(entry2.getDisplayName())) {
            return entry1;
        }
        if (!TextUtils.isEmpty(entry2.getDisplayName()) && TextUtils.isEmpty(entry1.getDisplayName())) {
            return entry2;
        }
        if (!TextUtils.equals(entry1.getDisplayName(), entry1.getDestination()) && TextUtils.equals(entry2.getDisplayName(), entry2.getDestination())) {
            return entry1;
        }
        if (!TextUtils.equals(entry2.getDisplayName(), entry2.getDestination()) && TextUtils.equals(entry1.getDisplayName(), entry1.getDestination())) {
            return entry2;
        }
        if ((entry1.getPhotoThumbnailUri() != null || entry1.getPhotoBytes() != null) && entry2.getPhotoThumbnailUri() == null && entry2.getPhotoBytes() == null) {
            return entry1;
        }
        if (!(entry2.getPhotoThumbnailUri() == null && entry2.getPhotoBytes() == null) && entry1.getPhotoThumbnailUri() == null && entry1.getPhotoBytes() == null) {
        }
        return entry2;
    }

    private static Cursor doQuery(CharSequence constraint, int limit, Long directoryId, Account account, ContentResolver resolver, Query query) {
        Builder builder = query.getContentFilterUri().buildUpon().appendPath(constraint.toString()).appendQueryParameter("limit", String.valueOf(limit + 5));
        if (directoryId != null) {
            builder.appendQueryParameter("directory", String.valueOf(directoryId));
        }
        if (account != null) {
            builder.appendQueryParameter("name_for_primary_account", account.name);
            builder.appendQueryParameter("type_for_primary_account", account.type);
        }
        return resolver.query(builder.build(), query.getProjection(), null, null, null);
    }

    public RecipientAlternatesAdapter(Context context, long contactId, Long directoryId, String lookupKey, long currentId, int queryMode, OnCheckedItemChangedListener listener, DropdownChipLayouter dropdownChipLayouter) {
        super(context, getCursorForConstruction(context, contactId, directoryId, lookupKey, queryMode), 0);
        this.mCurrentId = currentId;
        this.mDirectoryId = directoryId;
        this.mCheckedItemChangedListener = listener;
        this.mDropdownChipLayouter = dropdownChipLayouter;
    }

    private static Cursor getCursorForConstruction(Context context, long contactId, Long directoryId, String lookupKey, int queryType) {
        Uri uri;
        String desiredMimeType;
        String desiredMimeType2;
        Cursor cursor;
        Uri uri2;
        if (queryType == 0) {
            StringBuilder selection = new StringBuilder();
            selection.append(Queries.EMAIL.getProjection()[4]);
            selection.append(" = ?");
            if (directoryId == null || lookupKey == null) {
                uri2 = Queries.EMAIL.getContentUri();
                desiredMimeType2 = null;
            } else {
                Builder builder = Contacts.getLookupUri(contactId, lookupKey).buildUpon();
                builder.appendPath("entities").appendQueryParameter("directory", String.valueOf(directoryId));
                uri2 = builder.build();
                desiredMimeType2 = "vnd.android.cursor.item/email_v2";
            }
            cursor = context.getContentResolver().query(uri2, Queries.EMAIL.getProjection(), selection.toString(), new String[]{String.valueOf(contactId)}, null);
        } else {
            StringBuilder selection2 = new StringBuilder();
            selection2.append(Queries.PHONE.getProjection()[4]);
            selection2.append(" = ?");
            if (lookupKey == null) {
                uri = Queries.PHONE.getContentUri();
                desiredMimeType = null;
            } else {
                Builder builder2 = Contacts.getLookupUri(contactId, lookupKey).buildUpon();
                builder2.appendPath("entities").appendQueryParameter("directory", String.valueOf(directoryId));
                uri = builder2.build();
                desiredMimeType = "vnd.android.cursor.item/phone_v2";
            }
            cursor = context.getContentResolver().query(uri, Queries.PHONE.getProjection(), selection2.toString(), new String[]{String.valueOf(contactId)}, null);
        }
        Cursor resultCursor = removeUndesiredDestinations(cursor, desiredMimeType2, lookupKey);
        if (cursor != null) {
            cursor.close();
        }
        return resultCursor;
    }

    static Cursor removeUndesiredDestinations(Cursor original, String desiredMimeType, String lookupKey) {
        if (original == null) {
            return new MatrixCursor(Queries.PHONE.getProjection());
        }
        MatrixCursor result = new MatrixCursor(original.getColumnNames(), original.getCount());
        HashSet<String> destinationsSeen = new HashSet<>();
        String defaultDisplayName = null;
        String defaultPhotoThumbnailUri = null;
        int defaultDisplayNameSource = 0;
        original.moveToPosition(-1);
        while (true) {
            if (!original.moveToNext()) {
                break;
            }
            if ("vnd.android.cursor.item/name".equals(original.getString(9))) {
                defaultDisplayName = original.getString(0);
                defaultPhotoThumbnailUri = original.getString(6);
                defaultDisplayNameSource = original.getInt(7);
                break;
            }
        }
        original.moveToPosition(-1);
        while (original.moveToNext()) {
            if (desiredMimeType != null) {
                if (!desiredMimeType.equals(original.getString(9))) {
                }
            }
            String destination = original.getString(1);
            if (!destinationsSeen.contains(destination)) {
                destinationsSeen.add(destination);
                Object[] row = {original.getString(0), original.getString(1), Integer.valueOf(original.getInt(2)), original.getString(3), Long.valueOf(original.getLong(4)), Long.valueOf(original.getLong(5)), original.getString(6), Integer.valueOf(original.getInt(7)), original.getString(8), original.getString(9)};
                if (row[0] == null) {
                    row[0] = defaultDisplayName;
                }
                if (row[6] == null) {
                    row[6] = defaultPhotoThumbnailUri;
                }
                if (((Integer) row[7]).intValue() == 0) {
                    row[7] = Integer.valueOf(defaultDisplayNameSource);
                }
                if (row[8] == null) {
                    row[8] = lookupKey;
                }
                String photoThumbnailUri = (String) row[6];
                if (photoThumbnailUri != null) {
                    if (sCorrectedPhotoUris.containsKey(photoThumbnailUri)) {
                        row[6] = sCorrectedPhotoUris.get(photoThumbnailUri);
                    } else if (photoThumbnailUri.indexOf(63) != photoThumbnailUri.lastIndexOf(63)) {
                        String[] parts = photoThumbnailUri.split("\\?");
                        StringBuilder correctedUriBuilder = new StringBuilder();
                        for (int i = 0; i < parts.length; i++) {
                            if (i == 1) {
                                correctedUriBuilder.append("?");
                            } else if (i > 1) {
                                correctedUriBuilder.append("&");
                            }
                            correctedUriBuilder.append(parts[i]);
                        }
                        String correctedUri = correctedUriBuilder.toString();
                        sCorrectedPhotoUris.put(photoThumbnailUri, correctedUri);
                        row[6] = correctedUri;
                    }
                }
                result.addRow(row);
            }
        }
        return result;
    }

    public long getItemId(int position) {
        Cursor c = getCursor();
        if (c.moveToPosition(position)) {
            c.getLong(5);
        }
        return -1;
    }

    public RecipientEntry getRecipientEntry(int position) {
        Cursor c = getCursor();
        c.moveToPosition(position);
        return RecipientEntry.constructTopLevelEntry(c.getString(0), c.getInt(7), c.getString(1), c.getInt(2), c.getString(3), c.getLong(4), this.mDirectoryId, c.getLong(5), c.getString(6), true, c.getString(8));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Cursor cursor = getCursor();
        cursor.moveToPosition(position);
        if (convertView == null) {
            convertView = this.mDropdownChipLayouter.newView();
        }
        if (cursor.getLong(5) == this.mCurrentId) {
            this.mCheckedItemPosition = position;
            if (this.mCheckedItemChangedListener != null) {
                this.mCheckedItemChangedListener.onCheckedItemChanged(this.mCheckedItemPosition);
            }
        }
        bindView(convertView, convertView.getContext(), cursor);
        return convertView;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        int position = cursor.getPosition();
        View view2 = view;
        this.mDropdownChipLayouter.bindView(view2, null, getRecipientEntry(position), position, AdapterType.RECIPIENT_ALTERNATES, null);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return this.mDropdownChipLayouter.newView();
    }
}
