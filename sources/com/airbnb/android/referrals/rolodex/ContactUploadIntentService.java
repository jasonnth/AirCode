package com.airbnb.android.referrals.rolodex;

import android.app.IntentService;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.text.TextUtils;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.ReferralContact;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.referrals.ReferralsBindings;
import com.airbnb.android.referrals.ReferralsComponent.Builder;
import com.airbnb.jitney.event.logging.Contact.p072v1.C1969Contact;
import com.airbnb.jitney.event.logging.ContactBookImport.p073v1.C1971ContactBookImport;
import com.airbnb.jitney.event.logging.ContactBookImportType.p074v1.C1973ContactBookImportType;
import com.airbnb.jitney.event.logging.Rolodex.p238v3.RolodexMobileContactBatchUploadEvent;
import com.bugsnag.android.Severity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import p005cn.jpush.android.data.DbHelper;

public class ContactUploadIntentService extends IntentService {
    private static final int MAX_RECOMMENDED_CONTACTS = 15;
    private static final int ROLODEX_UPLOAD_MAX_BATCH_SIZE = 500;
    private static final String TAG = ContactUploadIntentService.class.getSimpleName();
    AirbnbAccountManager accountManager;
    protected ContentResolver contentResolver;
    LoggingContextFactory loggingContextFactory;
    SharedPrefsHelper sharedPrefsHelper;

    public static Intent getIntent(Context context) {
        return new Intent(context, ContactUploadIntentService.class);
    }

    public ContactUploadIntentService() {
        super(TAG);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        ((Builder) ((ReferralsBindings) CoreApplication.instance(this).componentProvider()).referralsComponentProvider().get()).build().inject(this);
        if (Trebuchet.launch(TrebuchetKeys.ENABLE_ANDROID_ROLODEX_UPLOADING) && this.sharedPrefsHelper.getLastContactUploadTime() + 86400000 < System.currentTimeMillis()) {
            this.contentResolver = getContentResolver();
            ArrayList<ReferralContact> result = null;
            try {
                ArrayList<ReferralContact> result2 = fetchAllContacts();
                if (result2 == null) {
                    BugsnagWrapper.notify((Throwable) new RuntimeException("Rolodex upload failed: read contacts permission is likely blocked"), Severity.INFO);
                } else {
                    uploadContactsForRolodex(result2);
                }
            } catch (RuntimeException e) {
                BugsnagWrapper.notify((Throwable) e, Severity.INFO);
                if (result == null) {
                    BugsnagWrapper.notify((Throwable) new RuntimeException("Rolodex upload failed: read contacts permission is likely blocked"), Severity.INFO);
                }
            } catch (Throwable th) {
                if (result == null) {
                    BugsnagWrapper.notify((Throwable) new RuntimeException("Rolodex upload failed: read contacts permission is likely blocked"), Severity.INFO);
                    return;
                }
                throw th;
            }
        }
    }

    private void uploadContactsForRolodex(ArrayList<ReferralContact> result) {
        String uploadID = UUID.randomUUID().toString();
        int contactsProcessed = 0;
        List<C1969Contact> contacts = new ArrayList<>(500);
        Iterator it = result.iterator();
        while (it.hasNext()) {
            ReferralContact contact = (ReferralContact) it.next();
            contacts.add(new C1969Contact.Builder().first_name(contact.getName()).phone_numbers(contact.getPhonesAsStrings()).emails(contact.getEmailsAsStrings()).has_photo(Boolean.valueOf(!TextUtils.isEmpty(contact.getPictureUri()))).favorite(Boolean.valueOf(contact.isRecommended())).build());
            contactsProcessed++;
            if (contactsProcessed == 500) {
                batchUploadContactSet(contacts, uploadID);
                contacts = new ArrayList<>(500);
            }
        }
        batchUploadContactSet(contacts, uploadID);
        this.sharedPrefsHelper.setLastContactUploadTime(System.currentTimeMillis());
    }

    private com.airbnb.jitney.event.logging.core.context.p025v2.Context getLoggingContext() {
        return this.loggingContextFactory.newInstance();
    }

    private void batchUploadContactSet(List<C1969Contact> contacts, String uploadID) {
        boolean z;
        if (!contacts.isEmpty()) {
            com.airbnb.jitney.event.logging.core.context.p025v2.Context loggingContext = getLoggingContext();
            Long valueOf = Long.valueOf(this.accountManager.getCurrentUserId());
            Long valueOf2 = Long.valueOf(this.accountManager.getCurrentUserId());
            C1973ContactBookImportType contactBookImportType = C1973ContactBookImportType.Android;
            if (this.sharedPrefsHelper.getLastContactUploadTime() == -1) {
                z = true;
            } else {
                z = false;
            }
            com.airbnb.jitney.event.logging.core.context.p025v2.Context context = loggingContext;
            Long l = valueOf;
            List<C1969Contact> list = contacts;
            RolodexMobileContactBatchUploadEvent.Builder builder = new RolodexMobileContactBatchUploadEvent.Builder(context, l, new C1971ContactBookImport.Builder(valueOf2, contactBookImportType, uploadID, Boolean.valueOf(z), getLoggingContext().mobile.device_id).is_delta(Boolean.valueOf(false)).build(), list, Long.valueOf((long) contacts.size()));
            JitneyPublisher.publish(builder);
        }
    }

    /* access modifiers changed from: protected */
    public ArrayList<ReferralContact> fetchAllContacts() {
        HashSet<Integer> recommendedContactIds = getRecommendedContactIds();
        if (recommendedContactIds == null) {
            return null;
        }
        return getContactsWithRecommended(getAllContactsCursor(), recommendedContactIds);
    }

    private HashSet<Integer> getRecommendedContactIds() {
        HashSet<Integer> recommendedContactIds = new HashSet<>();
        if (!MiscUtils.canDoRecommendedContacts()) {
            return recommendedContactIds;
        }
        try {
            Cursor recommendedContactsCursor = getStrequentContactsCursor();
            if (recommendedContactsCursor == null) {
                return recommendedContactIds;
            }
            int columnIndex = recommendedContactsCursor.getColumnIndex(DbHelper.TABLE_ID);
            int numRecommended = 0;
            while (recommendedContactsCursor.moveToNext() && numRecommended < 15) {
                recommendedContactIds.add(Integer.valueOf(recommendedContactsCursor.getInt(columnIndex)));
                numRecommended++;
            }
            return recommendedContactIds;
        } catch (SQLiteException e) {
            return recommendedContactIds;
        } catch (SecurityException e2) {
            return null;
        }
    }

    protected static ArrayList<ReferralContact> getFilteredContactsFromCursor(Cursor cursor) {
        LinkedHashMap<Integer, ReferralContact> distinctContacts = new LinkedHashMap<>();
        int nameIndex = cursor.getColumnIndexOrThrow("display_name");
        int idIndex = cursor.getColumnIndexOrThrow("contact_id");
        int photoIndex = cursor.getColumnIndexOrThrow("photo_thumb_uri");
        int dataIndex = cursor.getColumnIndexOrThrow("data1");
        int typeIndex = cursor.getColumnIndexOrThrow("mimetype");
        while (cursor.moveToNext()) {
            String contactName = cursor.getString(nameIndex);
            int contactId = cursor.getInt(idIndex);
            String contactPhoto = cursor.getString(photoIndex);
            String contactEmailPhoneData = cursor.getString(dataIndex);
            String contactDataMimetype = cursor.getString(typeIndex);
            ReferralContact contact = (ReferralContact) distinctContacts.get(Integer.valueOf(contactId));
            if (contact == null) {
                contact = new ReferralContact(contactId, contactName, contactPhoto);
            }
            if ("vnd.android.cursor.item/email_v2".equals(contactDataMimetype)) {
                contact.addEmail(contactEmailPhoneData, false);
            } else if ("vnd.android.cursor.item/phone_v2".equals(contactDataMimetype)) {
                contact.addPhoneNum(contactEmailPhoneData, false);
            } else {
                BugsnagWrapper.notify((Throwable) new RuntimeException("Rolodex upload problem: unexpected mime type \"" + contactDataMimetype + "\" for contacts cursor"), Severity.INFO);
            }
            distinctContacts.put(Integer.valueOf(contactId), contact);
        }
        ArrayList<ReferralContact> output = new ArrayList<>(distinctContacts.size());
        output.addAll(distinctContacts.values());
        return output;
    }

    protected static ArrayList<ReferralContact> getContactsWithRecommended(Cursor cursor, Set<Integer> recommendedIds) {
        ArrayList<ReferralContact> result = getFilteredContactsFromCursor(cursor);
        if (recommendedIds != null && !recommendedIds.isEmpty()) {
            Iterator it = result.iterator();
            while (it.hasNext()) {
                ReferralContact contact = (ReferralContact) it.next();
                if (recommendedIds.contains(Integer.valueOf(contact.getId()))) {
                    contact.setIsRecommended();
                }
            }
        }
        return result;
    }

    private Cursor getAllContactsCursor() {
        return this.contentResolver.query(Data.CONTENT_URI, new String[]{"display_name", "contact_id", "photo_thumb_uri", "data1", "mimetype"}, "(mimetype=? OR mimetype=?) AND contact_id IN (SELECT _id FROM contacts WHERE has_phone_number=1)", new String[]{"vnd.android.cursor.item/email_v2", "vnd.android.cursor.item/phone_v2"}, "display_name");
    }

    private Cursor getStrequentContactsCursor() {
        return this.contentResolver.query(Contacts.CONTENT_STREQUENT_URI, new String[]{DbHelper.TABLE_ID}, null, null, null);
    }
}
