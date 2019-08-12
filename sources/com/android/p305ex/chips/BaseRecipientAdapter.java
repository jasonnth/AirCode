package com.android.p305ex.chips;

import android.accounts.Account;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.Filterable;
import com.android.p305ex.chips.DropdownChipLayouter.AdapterType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import p005cn.jpush.android.data.DbHelper;

/* renamed from: com.android.ex.chips.BaseRecipientAdapter */
public class BaseRecipientAdapter extends BaseAdapter implements Filterable {
    /* access modifiers changed from: private */
    public static LruCache<Uri, byte[]> mPhotoCacheMap;
    /* access modifiers changed from: private */
    public Account mAccount;
    /* access modifiers changed from: private */
    public final ContentResolver mContentResolver;
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public CharSequence mCurrentConstraint;
    /* access modifiers changed from: private */
    public final DelayedMessageHandler mDelayedMessageHandler;
    private DropdownChipLayouter mDropdownChipLayouter;
    private List<RecipientEntry> mEntries;
    private EntriesUpdatedObserver mEntriesUpdatedObserver;
    /* access modifiers changed from: private */
    public LinkedHashMap<Long, List<RecipientEntry>> mEntryMap;
    /* access modifiers changed from: private */
    public Set<String> mExistingDestinations;
    private final LayoutInflater mInflater;
    /* access modifiers changed from: private */
    public List<RecipientEntry> mNonAggregatedEntries;
    /* access modifiers changed from: private */
    public final int mPreferredMaxResultCount;
    private final Query mQuery;
    private final int mQueryType;
    /* access modifiers changed from: private */
    public int mRemainingDirectoryCount;
    private List<RecipientEntry> mTempEntries;
    private boolean showMobileOnly;

    /* renamed from: com.android.ex.chips.BaseRecipientAdapter$DefaultFilter */
    private final class DefaultFilter extends Filter {
        private DefaultFilter() {
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: protected */
        public FilterResults performFiltering(CharSequence constraint) {
            List<DirectorySearchParams> paramsList;
            Log.d("BaseRecipientAdapter", "start filtering. constraint: " + constraint + ", thread:" + Thread.currentThread());
            if (constraint == null) {
                return new FilterResults();
            }
            FilterResults results = new FilterResults();
            Cursor defaultDirectoryCursor = null;
            Cursor directoryCursor = null;
            boolean limitResults = true;
            if (TextUtils.isEmpty(constraint)) {
                limitResults = false;
            }
            try {
                defaultDirectoryCursor = BaseRecipientAdapter.this.doQuery(constraint, limitResults ? BaseRecipientAdapter.this.mPreferredMaxResultCount : -1, null);
                if (defaultDirectoryCursor == null) {
                    Log.w("BaseRecipientAdapter", "null cursor returned for default Email filter query.");
                } else {
                    LinkedHashMap<Long, List<RecipientEntry>> entryMap = new LinkedHashMap<>();
                    ArrayList arrayList = new ArrayList();
                    HashSet hashSet = new HashSet();
                    while (defaultDirectoryCursor.moveToNext()) {
                        BaseRecipientAdapter.putOneEntry(new TemporaryEntry(defaultDirectoryCursor, null), true, entryMap, arrayList, hashSet);
                    }
                    List<RecipientEntry> entries = BaseRecipientAdapter.this.constructEntryList(entryMap, arrayList);
                    int limit = BaseRecipientAdapter.this.mPreferredMaxResultCount - hashSet.size();
                    if (limit <= 0 || !limitResults) {
                        paramsList = null;
                    } else {
                        Log.d("BaseRecipientAdapter", "More entries should be needed (current: " + hashSet.size() + ", remaining limit: " + limit + ") ");
                        directoryCursor = BaseRecipientAdapter.this.mContentResolver.query(DirectoryListQuery.URI, DirectoryListQuery.PROJECTION, null, null, null);
                        paramsList = BaseRecipientAdapter.setupOtherDirectories(BaseRecipientAdapter.this.mContext, directoryCursor, BaseRecipientAdapter.this.mAccount);
                    }
                    results.values = new DefaultFilterResult(entries, entryMap, arrayList, hashSet, paramsList);
                    results.count = 1;
                }
                if (defaultDirectoryCursor != null) {
                    defaultDirectoryCursor.close();
                }
                if (directoryCursor == null) {
                    return results;
                }
                directoryCursor.close();
                return results;
            } catch (Throwable th) {
                if (defaultDirectoryCursor != null) {
                    defaultDirectoryCursor.close();
                }
                if (directoryCursor != null) {
                    directoryCursor.close();
                }
                throw th;
            }
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence constraint, FilterResults results) {
            BaseRecipientAdapter.this.mCurrentConstraint = constraint;
            BaseRecipientAdapter.this.clearTempEntries();
            if (results.values != null) {
                DefaultFilterResult defaultFilterResult = (DefaultFilterResult) results.values;
                BaseRecipientAdapter.this.mEntryMap = defaultFilterResult.entryMap;
                BaseRecipientAdapter.this.mNonAggregatedEntries = defaultFilterResult.nonAggregatedEntries;
                BaseRecipientAdapter.this.mExistingDestinations = defaultFilterResult.existingDestinations;
                if (defaultFilterResult.entries.size() == 0 && defaultFilterResult.paramsList != null) {
                    BaseRecipientAdapter.this.cacheCurrentEntries();
                }
                BaseRecipientAdapter.this.updateEntries(defaultFilterResult.entries);
                if (defaultFilterResult.paramsList != null) {
                    BaseRecipientAdapter.this.startSearchOtherDirectories(constraint, defaultFilterResult.paramsList, BaseRecipientAdapter.this.mPreferredMaxResultCount - defaultFilterResult.existingDestinations.size());
                }
            }
        }

        public CharSequence convertResultToString(Object resultValue) {
            RecipientEntry entry = (RecipientEntry) resultValue;
            String displayName = entry.getDisplayName();
            String emailAddress = entry.getDestination();
            return (TextUtils.isEmpty(displayName) || TextUtils.equals(displayName, emailAddress)) ? emailAddress : new Rfc822Token(displayName, emailAddress, null).toString();
        }
    }

    /* renamed from: com.android.ex.chips.BaseRecipientAdapter$DefaultFilterResult */
    private static class DefaultFilterResult {
        public final List<RecipientEntry> entries;
        public final LinkedHashMap<Long, List<RecipientEntry>> entryMap;
        public final Set<String> existingDestinations;
        public final List<RecipientEntry> nonAggregatedEntries;
        public final List<DirectorySearchParams> paramsList;

        public DefaultFilterResult(List<RecipientEntry> entries2, LinkedHashMap<Long, List<RecipientEntry>> entryMap2, List<RecipientEntry> nonAggregatedEntries2, Set<String> existingDestinations2, List<DirectorySearchParams> paramsList2) {
            this.entries = entries2;
            this.entryMap = entryMap2;
            this.nonAggregatedEntries = nonAggregatedEntries2;
            this.existingDestinations = existingDestinations2;
            this.paramsList = paramsList2;
        }
    }

    /* renamed from: com.android.ex.chips.BaseRecipientAdapter$DelayedMessageHandler */
    private final class DelayedMessageHandler extends Handler {
        private DelayedMessageHandler() {
        }

        public void handleMessage(Message msg) {
            if (BaseRecipientAdapter.this.mRemainingDirectoryCount > 0) {
                BaseRecipientAdapter.this.updateEntries(BaseRecipientAdapter.this.constructEntryList(BaseRecipientAdapter.this.mEntryMap, BaseRecipientAdapter.this.mNonAggregatedEntries));
            }
        }

        public void sendDelayedLoadMessage() {
            sendMessageDelayed(obtainMessage(1, 0, 0, null), 1000);
        }

        public void removeDelayedLoadMessage() {
            removeMessages(1);
        }
    }

    /* renamed from: com.android.ex.chips.BaseRecipientAdapter$DirectoryFilter */
    protected class DirectoryFilter extends Filter {
        private int mLimit;
        private final DirectorySearchParams mParams;

        public DirectoryFilter(DirectorySearchParams params) {
            this.mParams = params;
        }

        public synchronized void setLimit(int limit) {
            this.mLimit = limit;
        }

        public synchronized int getLimit() {
            return this.mLimit;
        }

        /* JADX INFO: finally extract failed */
        /* access modifiers changed from: protected */
        public FilterResults performFiltering(CharSequence constraint) {
            Log.d("BaseRecipientAdapter", "DirectoryFilter#performFiltering. directoryId: " + this.mParams.directoryId + ", constraint: " + constraint + ", thread: " + Thread.currentThread());
            FilterResults results = new FilterResults();
            results.values = null;
            results.count = 0;
            if (!TextUtils.isEmpty(constraint)) {
                ArrayList<TemporaryEntry> tempEntries = new ArrayList<>();
                Cursor cursor = null;
                try {
                    Cursor cursor2 = BaseRecipientAdapter.this.doQuery(constraint, getLimit(), Long.valueOf(this.mParams.directoryId));
                    if (cursor2 != null) {
                        while (cursor2.moveToNext()) {
                            tempEntries.add(new TemporaryEntry(cursor2, Long.valueOf(this.mParams.directoryId)));
                        }
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (!tempEntries.isEmpty()) {
                        results.values = tempEntries;
                        results.count = 1;
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            Log.v("BaseRecipientAdapter", "finished loading directory \"" + this.mParams.displayName + "\" with query " + constraint);
            return results;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence constraint, FilterResults results) {
            Log.d("BaseRecipientAdapter", "DirectoryFilter#publishResult. constraint: " + constraint + ", mCurrentConstraint: " + BaseRecipientAdapter.this.mCurrentConstraint);
            BaseRecipientAdapter.this.mDelayedMessageHandler.removeDelayedLoadMessage();
            if (TextUtils.equals(constraint, BaseRecipientAdapter.this.mCurrentConstraint)) {
                if (results.count > 0) {
                    Iterator it = ((ArrayList) results.values).iterator();
                    while (it.hasNext()) {
                        BaseRecipientAdapter.putOneEntry((TemporaryEntry) it.next(), this.mParams.directoryId == 0, BaseRecipientAdapter.this.mEntryMap, BaseRecipientAdapter.this.mNonAggregatedEntries, BaseRecipientAdapter.this.mExistingDestinations);
                    }
                }
                BaseRecipientAdapter.this.mRemainingDirectoryCount = BaseRecipientAdapter.this.mRemainingDirectoryCount - 1;
                if (BaseRecipientAdapter.this.mRemainingDirectoryCount > 0) {
                    Log.d("BaseRecipientAdapter", "Resend delayed load message. Current mRemainingDirectoryLoad: " + BaseRecipientAdapter.this.mRemainingDirectoryCount);
                    BaseRecipientAdapter.this.mDelayedMessageHandler.sendDelayedLoadMessage();
                }
                if (results.count > 0 || BaseRecipientAdapter.this.mRemainingDirectoryCount == 0) {
                    BaseRecipientAdapter.this.clearTempEntries();
                }
            }
            BaseRecipientAdapter.this.updateEntries(BaseRecipientAdapter.this.constructEntryList(BaseRecipientAdapter.this.mEntryMap, BaseRecipientAdapter.this.mNonAggregatedEntries));
        }
    }

    /* renamed from: com.android.ex.chips.BaseRecipientAdapter$DirectoryListQuery */
    protected static class DirectoryListQuery {
        public static final String[] PROJECTION = {DbHelper.TABLE_ID, "accountName", "accountType", "displayName", "packageName", "typeResourceId"};
        public static final Uri URI = Uri.withAppendedPath(ContactsContract.AUTHORITY_URI, "directories");
    }

    /* renamed from: com.android.ex.chips.BaseRecipientAdapter$DirectorySearchParams */
    public static final class DirectorySearchParams {
        public String accountName;
        public String accountType;
        public CharSequence constraint;
        public long directoryId;
        public String directoryType;
        public String displayName;
        public DirectoryFilter filter;
    }

    /* renamed from: com.android.ex.chips.BaseRecipientAdapter$EntriesUpdatedObserver */
    public interface EntriesUpdatedObserver {
        void onChanged(List<RecipientEntry> list);
    }

    /* renamed from: com.android.ex.chips.BaseRecipientAdapter$PhotoQuery */
    private static class PhotoQuery {
        public static final String[] PROJECTION = {"data15"};
    }

    /* renamed from: com.android.ex.chips.BaseRecipientAdapter$TemporaryEntry */
    protected static class TemporaryEntry {
        public final long contactId;
        public final long dataId;
        public final String destination;
        public final String destinationLabel;
        public final int destinationType;
        public final Long directoryId;
        public final String displayName;
        public final int displayNameSource;
        public final String lookupKey;
        public final String thumbnailUriString;

        public TemporaryEntry(Cursor cursor, Long directoryId2) {
            this.displayName = cursor.getString(0);
            this.destination = cursor.getString(1);
            this.destinationType = cursor.getInt(2);
            this.destinationLabel = cursor.getString(3);
            this.contactId = cursor.getLong(4);
            this.directoryId = directoryId2;
            this.dataId = cursor.getLong(5);
            this.thumbnailUriString = cursor.getString(6);
            this.displayNameSource = cursor.getInt(7);
            this.lookupKey = cursor.getString(8);
        }
    }

    public BaseRecipientAdapter(Context context) {
        this(context, 10, 0);
    }

    public BaseRecipientAdapter(Context context, int preferredMaxResultCount, int queryMode) {
        this.showMobileOnly = true;
        this.mDelayedMessageHandler = new DelayedMessageHandler();
        this.mContext = context;
        this.mContentResolver = context.getContentResolver();
        this.mInflater = LayoutInflater.from(context);
        this.mPreferredMaxResultCount = preferredMaxResultCount;
        if (mPhotoCacheMap == null) {
            mPhotoCacheMap = new LruCache<>(200);
        }
        this.mQueryType = queryMode;
        if (queryMode == 0) {
            this.mQuery = Queries.EMAIL;
        } else if (queryMode == 1) {
            this.mQuery = Queries.PHONE;
        } else {
            this.mQuery = Queries.EMAIL;
            Log.e("BaseRecipientAdapter", "Unsupported query type: " + queryMode);
        }
    }

    public int getQueryType() {
        return this.mQueryType;
    }

    public void setDropdownChipLayouter(DropdownChipLayouter dropdownChipLayouter) {
        this.mDropdownChipLayouter = dropdownChipLayouter;
        this.mDropdownChipLayouter.setQuery(this.mQuery);
    }

    public Filter getFilter() {
        return new DefaultFilter();
    }

    public Map<String, RecipientEntry> getMatchingRecipients(Set<String> set) {
        return null;
    }

    public static List<DirectorySearchParams> setupOtherDirectories(Context context, Cursor directoryCursor, Account account) {
        PackageManager packageManager = context.getPackageManager();
        List<DirectorySearchParams> paramsList = new ArrayList<>();
        DirectorySearchParams preferredDirectory = null;
        while (directoryCursor.moveToNext()) {
            long id = directoryCursor.getLong(0);
            if (id != 1) {
                DirectorySearchParams params = new DirectorySearchParams();
                String packageName = directoryCursor.getString(4);
                int resourceId = directoryCursor.getInt(5);
                params.directoryId = id;
                params.displayName = directoryCursor.getString(3);
                params.accountName = directoryCursor.getString(1);
                params.accountType = directoryCursor.getString(2);
                if (!(packageName == null || resourceId == 0)) {
                    try {
                        params.directoryType = packageManager.getResourcesForApplication(packageName).getString(resourceId);
                        if (params.directoryType == null) {
                            Log.e("BaseRecipientAdapter", "Cannot resolve directory name: " + resourceId + "@" + packageName);
                        }
                    } catch (NameNotFoundException e) {
                        Log.e("BaseRecipientAdapter", "Cannot resolve directory name: " + resourceId + "@" + packageName, e);
                    }
                }
                if (account == null || !account.name.equals(params.accountName) || !account.type.equals(params.accountType)) {
                    paramsList.add(params);
                } else {
                    preferredDirectory = params;
                }
            }
        }
        if (preferredDirectory != null) {
            paramsList.add(1, preferredDirectory);
        }
        return paramsList;
    }

    /* access modifiers changed from: protected */
    public void startSearchOtherDirectories(CharSequence constraint, List<DirectorySearchParams> paramsList, int limit) {
        int count = paramsList.size();
        for (int i = 1; i < count; i++) {
            DirectorySearchParams params = (DirectorySearchParams) paramsList.get(i);
            params.constraint = constraint;
            if (params.filter == null) {
                params.filter = new DirectoryFilter(params);
            }
            params.filter.setLimit(limit);
            params.filter.filter(constraint);
        }
        this.mRemainingDirectoryCount = count - 1;
        this.mDelayedMessageHandler.sendDelayedLoadMessage();
    }

    /* access modifiers changed from: private */
    public static void putOneEntry(TemporaryEntry entry, boolean isAggregatedEntry, LinkedHashMap<Long, List<RecipientEntry>> entryMap, List<RecipientEntry> nonAggregatedEntries, Set<String> existingDestinations) {
        if (!existingDestinations.contains(entry.destination)) {
            existingDestinations.add(entry.destination);
            if (!isAggregatedEntry) {
                nonAggregatedEntries.add(RecipientEntry.constructTopLevelEntry(entry.displayName, entry.displayNameSource, entry.destination, entry.destinationType, entry.destinationLabel, entry.contactId, entry.directoryId, entry.dataId, entry.thumbnailUriString, true, entry.lookupKey));
                return;
            }
            if (entryMap.containsKey(Long.valueOf(entry.contactId))) {
                ((List) entryMap.get(Long.valueOf(entry.contactId))).add(RecipientEntry.constructSecondLevelEntry(entry.displayName, entry.displayNameSource, entry.destination, entry.destinationType, entry.destinationLabel, entry.contactId, entry.directoryId, entry.dataId, entry.thumbnailUriString, true, entry.lookupKey));
                return;
            }
            List<RecipientEntry> entryList = new ArrayList<>();
            entryList.add(RecipientEntry.constructTopLevelEntry(entry.displayName, entry.displayNameSource, entry.destination, entry.destinationType, entry.destinationLabel, entry.contactId, entry.directoryId, entry.dataId, entry.thumbnailUriString, true, entry.lookupKey));
            entryMap.put(Long.valueOf(entry.contactId), entryList);
        }
    }

    /* access modifiers changed from: private */
    public List<RecipientEntry> constructEntryList(LinkedHashMap<Long, List<RecipientEntry>> entryMap, List<RecipientEntry> nonAggregatedEntries) {
        List<RecipientEntry> entries = new ArrayList<>();
        int validEntryCount = 0;
        for (Entry<Long, List<RecipientEntry>> mapEntry : entryMap.entrySet()) {
            List<RecipientEntry> entryList = (List) mapEntry.getValue();
            int size = entryList.size();
            for (int i = 0; i < size; i++) {
                RecipientEntry entry = (RecipientEntry) entryList.get(i);
                entries.add(entry);
                tryFetchPhoto(entry, this.mContentResolver, this, false, i);
                validEntryCount++;
            }
        }
        if (validEntryCount <= this.mPreferredMaxResultCount) {
            for (int i2 = 0; i2 < nonAggregatedEntries.size(); i2++) {
                RecipientEntry entry2 = (RecipientEntry) nonAggregatedEntries.get(i2);
                entries.add(entry2);
                tryFetchPhoto(entry2, this.mContentResolver, this, false, i2);
                validEntryCount++;
            }
        }
        return entries;
    }

    public void registerUpdateObserver(EntriesUpdatedObserver observer) {
        this.mEntriesUpdatedObserver = observer;
    }

    /* access modifiers changed from: private */
    public void updateEntries(List<RecipientEntry> newEntries) {
        this.mEntries = newEntries;
        this.mEntriesUpdatedObserver.onChanged(newEntries);
        notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void cacheCurrentEntries() {
        this.mTempEntries = this.mEntries;
    }

    /* access modifiers changed from: private */
    public void clearTempEntries() {
        this.mTempEntries = null;
    }

    /* access modifiers changed from: protected */
    public List<RecipientEntry> getEntries() {
        return this.mTempEntries != null ? this.mTempEntries : this.mEntries;
    }

    public static void tryFetchPhoto(RecipientEntry entry, ContentResolver mContentResolver2, BaseAdapter adapter, boolean forceLoad, int position) {
        if (forceLoad || position <= 20) {
            Uri photoThumbnailUri = entry.getPhotoThumbnailUri();
            if (photoThumbnailUri != null) {
                byte[] photoBytes = (byte[]) mPhotoCacheMap.get(photoThumbnailUri);
                if (photoBytes != null) {
                    entry.setPhotoBytes(photoBytes);
                    return;
                }
                Log.d("BaseRecipientAdapter", "No photo cache for " + entry.getDisplayName() + ". Fetch one asynchronously");
                fetchPhotoAsync(entry, photoThumbnailUri, adapter, mContentResolver2);
            }
        }
    }

    private static void fetchPhotoAsync(final RecipientEntry entry, final Uri photoThumbnailUri, final BaseAdapter adapter, final ContentResolver mContentResolver2) {
        new AsyncTask<Void, Void, byte[]>() {
            /* access modifiers changed from: protected */
            public byte[] doInBackground(Void... params) {
                InputStream is;
                Cursor photoCursor = mContentResolver2.query(photoThumbnailUri, PhotoQuery.PROJECTION, null, null, null);
                if (photoCursor != null) {
                    try {
                        if (photoCursor.moveToFirst()) {
                            return photoCursor.getBlob(0);
                        }
                        photoCursor.close();
                        return null;
                    } finally {
                        photoCursor.close();
                    }
                } else {
                    try {
                        is = mContentResolver2.openInputStream(photoThumbnailUri);
                        if (is == null) {
                            return null;
                        }
                        byte[] buffer = new byte[16384];
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        while (true) {
                            int size = is.read(buffer);
                            if (size != -1) {
                                baos.write(buffer, 0, size);
                            } else {
                                is.close();
                                return baos.toByteArray();
                            }
                        }
                    } catch (IOException e) {
                        return null;
                    } catch (Throwable th) {
                        is.close();
                        throw th;
                    }
                }
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(byte[] photoBytes) {
                entry.setPhotoBytes(photoBytes);
                if (photoBytes != null) {
                    BaseRecipientAdapter.mPhotoCacheMap.put(photoThumbnailUri, photoBytes);
                    if (adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x0089 A[SYNTHETIC, Splitter:B:36:0x0089] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x008e A[SYNTHETIC, Splitter:B:39:0x008e] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00ad A[SYNTHETIC, Splitter:B:47:0x00ad] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00b2 A[SYNTHETIC, Splitter:B:50:0x00b2] */
    /* JADX WARNING: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void fetchPhoto(com.android.p305ex.chips.RecipientEntry r13, android.net.Uri r14, android.content.ContentResolver r15) {
        /*
            r3 = 0
            android.util.LruCache<android.net.Uri, byte[]> r0 = mPhotoCacheMap
            java.lang.Object r11 = r0.get(r14)
            byte[] r11 = (byte[]) r11
            if (r11 == 0) goto L_0x000f
            r13.setPhotoBytes(r11)
        L_0x000e:
            return
        L_0x000f:
            java.lang.String[] r2 = com.android.p305ex.chips.BaseRecipientAdapter.PhotoQuery.PROJECTION
            r0 = r15
            r1 = r14
            r4 = r3
            r5 = r3
            android.database.Cursor r12 = r0.query(r1, r2, r3, r4, r5)
            if (r12 == 0) goto L_0x0037
            boolean r0 = r12.moveToFirst()     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x002e
            r0 = 0
            byte[] r11 = r12.getBlob(r0)     // Catch:{ all -> 0x0032 }
            r13.setPhotoBytes(r11)     // Catch:{ all -> 0x0032 }
            android.util.LruCache<android.net.Uri, byte[]> r0 = mPhotoCacheMap     // Catch:{ all -> 0x0032 }
            r0.put(r14, r11)     // Catch:{ all -> 0x0032 }
        L_0x002e:
            r12.close()
            goto L_0x000e
        L_0x0032:
            r0 = move-exception
            r12.close()
            throw r0
        L_0x0037:
            r8 = 0
            r9 = 0
            java.io.InputStream r8 = r15.openInputStream(r14)     // Catch:{ FileNotFoundException -> 0x007d }
            android.graphics.Bitmap r6 = android.graphics.BitmapFactory.decodeStream(r8)     // Catch:{ FileNotFoundException -> 0x007d }
            if (r6 == 0) goto L_0x005c
            java.io.ByteArrayOutputStream r10 = new java.io.ByteArrayOutputStream     // Catch:{ FileNotFoundException -> 0x007d }
            r10.<init>()     // Catch:{ FileNotFoundException -> 0x007d }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ FileNotFoundException -> 0x00cf, all -> 0x00cc }
            r1 = 100
            r6.compress(r0, r1, r10)     // Catch:{ FileNotFoundException -> 0x00cf, all -> 0x00cc }
            byte[] r11 = r10.toByteArray()     // Catch:{ FileNotFoundException -> 0x00cf, all -> 0x00cc }
            r13.setPhotoBytes(r11)     // Catch:{ FileNotFoundException -> 0x00cf, all -> 0x00cc }
            android.util.LruCache<android.net.Uri, byte[]> r0 = mPhotoCacheMap     // Catch:{ FileNotFoundException -> 0x00cf, all -> 0x00cc }
            r0.put(r14, r11)     // Catch:{ FileNotFoundException -> 0x00cf, all -> 0x00cc }
            r9 = r10
        L_0x005c:
            if (r8 == 0) goto L_0x0061
            r8.close()     // Catch:{ IOException -> 0x0072 }
        L_0x0061:
            if (r9 == 0) goto L_0x000e
            r9.close()     // Catch:{ IOException -> 0x0067 }
            goto L_0x000e
        L_0x0067:
            r7 = move-exception
            java.lang.String r0 = "BaseRecipientAdapter"
            java.lang.String r1 = "Error closing photo output stream"
            android.util.Log.e(r0, r1, r7)
            goto L_0x000e
        L_0x0072:
            r7 = move-exception
            java.lang.String r0 = "BaseRecipientAdapter"
            java.lang.String r1 = "Error closing photo input stream"
            android.util.Log.e(r0, r1, r7)
            goto L_0x0061
        L_0x007d:
            r7 = move-exception
        L_0x007e:
            java.lang.String r0 = "BaseRecipientAdapter"
            java.lang.String r1 = "Error opening InputStream for photo"
            android.util.Log.w(r0, r1, r7)     // Catch:{ all -> 0x00aa }
            if (r8 == 0) goto L_0x008c
            r8.close()     // Catch:{ IOException -> 0x009f }
        L_0x008c:
            if (r9 == 0) goto L_0x000e
            r9.close()     // Catch:{ IOException -> 0x0093 }
            goto L_0x000e
        L_0x0093:
            r7 = move-exception
            java.lang.String r0 = "BaseRecipientAdapter"
            java.lang.String r1 = "Error closing photo output stream"
            android.util.Log.e(r0, r1, r7)
            goto L_0x000e
        L_0x009f:
            r7 = move-exception
            java.lang.String r0 = "BaseRecipientAdapter"
            java.lang.String r1 = "Error closing photo input stream"
            android.util.Log.e(r0, r1, r7)
            goto L_0x008c
        L_0x00aa:
            r0 = move-exception
        L_0x00ab:
            if (r8 == 0) goto L_0x00b0
            r8.close()     // Catch:{ IOException -> 0x00b6 }
        L_0x00b0:
            if (r9 == 0) goto L_0x00b5
            r9.close()     // Catch:{ IOException -> 0x00c1 }
        L_0x00b5:
            throw r0
        L_0x00b6:
            r7 = move-exception
            java.lang.String r1 = "BaseRecipientAdapter"
            java.lang.String r2 = "Error closing photo input stream"
            android.util.Log.e(r1, r2, r7)
            goto L_0x00b0
        L_0x00c1:
            r7 = move-exception
            java.lang.String r1 = "BaseRecipientAdapter"
            java.lang.String r2 = "Error closing photo output stream"
            android.util.Log.e(r1, r2, r7)
            goto L_0x00b5
        L_0x00cc:
            r0 = move-exception
            r9 = r10
            goto L_0x00ab
        L_0x00cf:
            r7 = move-exception
            r9 = r10
            goto L_0x007e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.p305ex.chips.BaseRecipientAdapter.fetchPhoto(com.android.ex.chips.RecipientEntry, android.net.Uri, android.content.ContentResolver):void");
    }

    /* access modifiers changed from: private */
    public Cursor doQuery(CharSequence constraint, int limit, Long directoryId) {
        String where;
        Builder builder = this.mQuery.getContentFilterUri().buildUpon();
        builder.appendPath(constraint.toString());
        builder.appendQueryParameter("limit", String.valueOf(limit + 5));
        if (directoryId != null) {
            builder.appendQueryParameter("directory", String.valueOf(directoryId));
        }
        if (this.mAccount != null) {
            builder.appendQueryParameter("name_for_primary_account", this.mAccount.name);
            builder.appendQueryParameter("type_for_primary_account", this.mAccount.type);
        }
        if (!this.showMobileOnly || this.mQueryType != 1) {
            where = null;
        } else {
            where = "data2=2";
        }
        long start = System.currentTimeMillis();
        Cursor cursor = this.mContentResolver.query(limit == -1 ? this.mQuery.getContentUri() : builder.build(), this.mQuery.getProjection(), where, null, limit == -1 ? "display_name ASC" : null);
        Log.d("BaseRecipientAdapter", "Time for autocomplete (query: " + constraint + ", directoryId: " + directoryId + ", num_of_results: " + (cursor != null ? Integer.valueOf(cursor.getCount()) : "null") + "): " + (System.currentTimeMillis() - start) + " ms");
        return cursor;
    }

    public int getCount() {
        List<RecipientEntry> entries = getEntries();
        if (entries != null) {
            return entries.size();
        }
        return 0;
    }

    public RecipientEntry getItem(int position) {
        return (RecipientEntry) getEntries().get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public int getViewTypeCount() {
        return 1;
    }

    public int getItemViewType(int position) {
        return ((RecipientEntry) getEntries().get(position)).getEntryType();
    }

    public boolean isEnabled(int position) {
        if (getEntries().isEmpty()) {
            return false;
        }
        return ((RecipientEntry) getEntries().get(position)).isSelectable();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        String constraint;
        RecipientEntry entry = (RecipientEntry) getEntries().get(position);
        if (this.mCurrentConstraint == null) {
            constraint = null;
        } else {
            constraint = this.mCurrentConstraint.toString();
        }
        return this.mDropdownChipLayouter.bindView(convertView, parent, entry, position, AdapterType.BASE_RECIPIENT, constraint);
    }

    public Account getAccount() {
        return this.mAccount;
    }
}
