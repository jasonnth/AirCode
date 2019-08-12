package com.rt2zz.reactnativecontacts;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.os.AsyncTask;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import java.util.ArrayList;

public class ContactsManager extends ReactContextBaseJavaModule {
    public ContactsManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @ReactMethod
    public void getAll(Callback callback) {
        getAllContacts(callback);
    }

    @ReactMethod
    public void getAllWithoutPhotos(Callback callback) {
        getAllContacts(callback);
    }

    private void getAllContacts(final Callback callback) {
        AsyncTask.execute(new Runnable() {
            public void run() {
                WritableArray contacts = new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getContacts();
                callback.invoke(null, contacts);
            }
        });
    }

    @ReactMethod
    public void getPhotoForId(final String contactId, final Callback callback) {
        AsyncTask.execute(new Runnable() {
            public void run() {
                String photoUri = new ContactsProvider(ContactsManager.this.getReactApplicationContext().getContentResolver()).getPhotoUriFromContactId(contactId);
                callback.invoke(null, photoUri);
            }
        });
    }

    @ReactMethod
    public void addContact(ReadableMap contact, Callback callback) {
        String givenName = contact.hasKey("givenName") ? contact.getString("givenName") : null;
        String middleName = contact.hasKey("middleName") ? contact.getString("middleName") : null;
        String familyName = contact.hasKey("familyName") ? contact.getString("familyName") : null;
        String prefix = contact.hasKey("prefix") ? contact.getString("prefix") : null;
        String suffix = contact.hasKey("suffix") ? contact.getString("suffix") : null;
        String company = contact.hasKey("company") ? contact.getString("company") : null;
        String jobTitle = contact.hasKey("jobTitle") ? contact.getString("jobTitle") : null;
        String department = contact.hasKey("department") ? contact.getString("department") : null;
        ReadableArray phoneNumbers = contact.hasKey("phoneNumbers") ? contact.getArray("phoneNumbers") : null;
        int numOfPhones = 0;
        String[] phones = null;
        Integer[] phonesLabels = null;
        if (phoneNumbers != null) {
            numOfPhones = phoneNumbers.size();
            phones = new String[numOfPhones];
            phonesLabels = new Integer[numOfPhones];
            for (int i = 0; i < numOfPhones; i++) {
                phones[i] = phoneNumbers.getMap(i).getString("number");
                phonesLabels[i] = Integer.valueOf(mapStringToPhoneType(phoneNumbers.getMap(i).getString("label")));
            }
        }
        ReadableArray emailAddresses = contact.hasKey("emailAddresses") ? contact.getArray("emailAddresses") : null;
        int numOfEmails = 0;
        String[] emails = null;
        Integer[] emailsLabels = null;
        if (emailAddresses != null) {
            numOfEmails = emailAddresses.size();
            emails = new String[numOfEmails];
            emailsLabels = new Integer[numOfEmails];
            for (int i2 = 0; i2 < numOfEmails; i2++) {
                emails[i2] = emailAddresses.getMap(i2).getString("email");
                emailsLabels[i2] = Integer.valueOf(mapStringToEmailType(emailAddresses.getMap(i2).getString("label")));
            }
        }
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        ops.add(ContentProviderOperation.newInsert(RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
        ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", givenName).withValue("data5", middleName).withValue("data3", familyName).withValue("data4", prefix).withValue("data6", suffix).build());
        Builder op = ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/organization").withValue("data1", company).withValue("data4", jobTitle).withValue("data5", department);
        ops.add(op.build());
        op.withYieldAllowed(true);
        for (int i3 = 0; i3 < numOfPhones; i3++) {
            ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", phones[i3]).withValue("data2", phonesLabels[i3]).build());
        }
        for (int i4 = 0; i4 < numOfEmails; i4++) {
            ops.add(ContentProviderOperation.newInsert(Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", emails[i4]).withValue("data2", emailsLabels[i4]).build());
        }
        try {
            getReactApplicationContext().getContentResolver().applyBatch("com.android.contacts", ops);
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(e.toString());
        }
    }

    @ReactMethod
    public void updateContact(ReadableMap contact, Callback callback) {
        String recordID = contact.hasKey("recordID") ? contact.getString("recordID") : null;
        String givenName = contact.hasKey("givenName") ? contact.getString("givenName") : null;
        String middleName = contact.hasKey("middleName") ? contact.getString("middleName") : null;
        String familyName = contact.hasKey("familyName") ? contact.getString("familyName") : null;
        String prefix = contact.hasKey("prefix") ? contact.getString("prefix") : null;
        String suffix = contact.hasKey("suffix") ? contact.getString("suffix") : null;
        String company = contact.hasKey("company") ? contact.getString("company") : null;
        String jobTitle = contact.hasKey("jobTitle") ? contact.getString("jobTitle") : null;
        String department = contact.hasKey("department") ? contact.getString("department") : null;
        ReadableArray phoneNumbers = contact.hasKey("phoneNumbers") ? contact.getArray("phoneNumbers") : null;
        int numOfPhones = 0;
        String[] phones = null;
        Integer[] phonesLabels = null;
        if (phoneNumbers != null) {
            numOfPhones = phoneNumbers.size();
            phones = new String[numOfPhones];
            phonesLabels = new Integer[numOfPhones];
            for (int i = 0; i < numOfPhones; i++) {
                ReadableMap phoneMap = phoneNumbers.getMap(i);
                String phoneNumber = phoneMap.getString("number");
                String phoneLabel = phoneMap.getString("label");
                phones[i] = phoneNumber;
                phonesLabels[i] = Integer.valueOf(mapStringToPhoneType(phoneLabel));
            }
        }
        ReadableArray emailAddresses = contact.hasKey("emailAddresses") ? contact.getArray("emailAddresses") : null;
        int numOfEmails = 0;
        String[] emails = null;
        Integer[] emailsLabels = null;
        if (emailAddresses != null) {
            numOfEmails = emailAddresses.size();
            emails = new String[numOfEmails];
            emailsLabels = new Integer[numOfEmails];
            for (int i2 = 0; i2 < numOfEmails; i2++) {
                ReadableMap emailMap = emailAddresses.getMap(i2);
                emails[i2] = emailMap.getString("email");
                emailsLabels[i2] = Integer.valueOf(mapStringToEmailType(emailMap.getString("label")));
            }
        }
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        ops.add(ContentProviderOperation.newUpdate(RawContacts.CONTENT_URI).withSelection("contact_id=?", new String[]{String.valueOf(recordID)}).withValue("account_type", null).withValue("account_name", null).build());
        ops.add(ContentProviderOperation.newUpdate(Data.CONTENT_URI).withSelection("contact_id=?", new String[]{String.valueOf(recordID)}).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data2", givenName).withValue("data5", middleName).withValue("data3", familyName).withValue("data4", prefix).withValue("data6", suffix).build());
        Builder op = ContentProviderOperation.newUpdate(Data.CONTENT_URI).withSelection("contact_id=? AND mimetype = ?", new String[]{String.valueOf(recordID), "vnd.android.cursor.item/organization"}).withValue("data1", company).withValue("data4", jobTitle).withValue("data5", department);
        ops.add(op.build());
        op.withYieldAllowed(true);
        for (int i3 = 0; i3 < numOfPhones; i3++) {
            ops.add(ContentProviderOperation.newUpdate(Data.CONTENT_URI).withSelection("contact_id=? AND mimetype = ?", new String[]{String.valueOf(recordID), "vnd.android.cursor.item/phone_v2"}).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", phones[i3]).withValue("data2", phonesLabels[i3]).build());
        }
        for (int i4 = 0; i4 < numOfEmails; i4++) {
            ops.add(ContentProviderOperation.newUpdate(Data.CONTENT_URI).withSelection("raw_contact_id=? AND mimetype = ?", new String[]{String.valueOf(recordID), "vnd.android.cursor.item/email_v2"}).withValue("mimetype", "vnd.android.cursor.item/email_v2").withValue("data1", emails[i4]).withValue("data2", emailsLabels[i4]).build());
        }
        try {
            getReactApplicationContext().getContentResolver().applyBatch("com.android.contacts", ops);
            callback.invoke(new Object[0]);
        } catch (Exception e) {
            callback.invoke(e.toString());
        }
    }

    @ReactMethod
    public void checkPermission(Callback callback) {
        callback.invoke(null, isPermissionGranted());
    }

    @ReactMethod
    public void requestPermission(Callback callback) {
        callback.invoke(null, isPermissionGranted());
    }

    private String isPermissionGranted() {
        return getReactApplicationContext().checkCallingOrSelfPermission("android.permission.READ_CONTACTS") == 0 ? "authorized" : "denied";
    }

    private int mapStringToPhoneType(String label) {
        char c = 65535;
        switch (label.hashCode()) {
            case -1068855134:
                if (label.equals("mobile")) {
                    c = 2;
                    break;
                }
                break;
            case 3208415:
                if (label.equals("home")) {
                    c = 0;
                    break;
                }
                break;
            case 3655441:
                if (label.equals("work")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 1;
            case 1:
                return 3;
            case 2:
                return 2;
            default:
                return 7;
        }
    }

    private int mapStringToEmailType(String label) {
        char c = 65535;
        switch (label.hashCode()) {
            case -1068855134:
                if (label.equals("mobile")) {
                    c = 2;
                    break;
                }
                break;
            case 3208415:
                if (label.equals("home")) {
                    c = 0;
                    break;
                }
                break;
            case 3655441:
                if (label.equals("work")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            default:
                return 3;
        }
    }

    public String getName() {
        return "Contacts";
    }
}
