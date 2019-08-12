package com.rt2zz.reactnativecontacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.Profile;
import android.text.TextUtils;
import com.airbnb.android.lib.contentproviders.PlacesSearchSuggestionProvider;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ContactsProvider {
    private static final List<String> FULL_PROJECTION = new ArrayList<String>() {
        {
            addAll(ContactsProvider.JUST_ME_PROJECTION);
        }
    };
    /* access modifiers changed from: private */
    public static final List<String> JUST_ME_PROJECTION = new ArrayList<String>() {
        {
            add("contact_id");
            add("lookup");
            add("mimetype");
            add("display_name");
            add("photo_uri");
            add("data1");
            add("data2");
            add("data5");
            add("data3");
            add("data4");
            add("data6");
            add("data1");
            add("data2");
            add("data3");
            add("data1");
            add("data1");
            add("data2");
            add("data3");
            add("data1");
            add("data4");
            add("data5");
            add("data1");
            add("data2");
            add("data3");
            add("data4");
            add("data5");
            add("data6");
            add("data7");
            add("data8");
            add("data9");
            add("data10");
        }
    };
    private static final List<String> PHOTO_PROJECTION = new ArrayList<String>() {
        {
            add("photo_uri");
        }
    };
    private final ContentResolver contentResolver;

    /* renamed from: com.rt2zz.reactnativecontacts.ContactsProvider$Contact */
    private static class C4714Contact {
        /* access modifiers changed from: private */
        public String company = "";
        private String contactId;
        /* access modifiers changed from: private */
        public String department = "";
        /* access modifiers changed from: private */
        public String displayName;
        /* access modifiers changed from: private */
        public List<Item> emails = new ArrayList();
        /* access modifiers changed from: private */
        public String familyName = "";
        /* access modifiers changed from: private */
        public String givenName = "";
        /* access modifiers changed from: private */
        public boolean hasPhoto = false;
        /* access modifiers changed from: private */
        public String jobTitle = "";
        /* access modifiers changed from: private */
        public String middleName = "";
        /* access modifiers changed from: private */
        public List<Item> phones = new ArrayList();
        /* access modifiers changed from: private */
        public String photoUri;
        /* access modifiers changed from: private */
        public List<PostalAddressItem> postalAddresses = new ArrayList();
        /* access modifiers changed from: private */
        public String prefix = "";
        /* access modifiers changed from: private */
        public String suffix = "";

        /* renamed from: com.rt2zz.reactnativecontacts.ContactsProvider$Contact$Item */
        public static class Item {
            public String label;
            public String value;

            public Item(String label2, String value2) {
                this.label = label2;
                this.value = value2;
            }
        }

        /* renamed from: com.rt2zz.reactnativecontacts.ContactsProvider$Contact$PostalAddressItem */
        public static class PostalAddressItem {
            public final WritableMap map = Arguments.createMap();

            public PostalAddressItem(Cursor cursor) {
                this.map.putString("label", getLabel(cursor));
                putString(cursor, "formattedAddress", "data1");
                putString(cursor, "street", "data4");
                putString(cursor, "pobox", "data5");
                putString(cursor, "neighborhood", "data6");
                putString(cursor, "city", "data7");
                putString(cursor, PlacesSearchSuggestionProvider.DISPLAY_REGION, "data8");
                putString(cursor, "postCode", "data9");
                putString(cursor, "country", "data10");
            }

            private void putString(Cursor cursor, String key, String androidKey) {
                String value = cursor.getString(cursor.getColumnIndex(androidKey));
                if (!TextUtils.isEmpty(value)) {
                    this.map.putString(key, value);
                }
            }

            static String getLabel(Cursor cursor) {
                switch (cursor.getInt(cursor.getColumnIndex("data2"))) {
                    case 0:
                        String label = cursor.getString(cursor.getColumnIndex("data3"));
                        if (label == null) {
                            return "";
                        }
                        return label;
                    case 1:
                        return "home";
                    case 2:
                        return "work";
                    default:
                        return "other";
                }
            }
        }

        public C4714Contact(String contactId2) {
            this.contactId = contactId2;
        }

        public WritableMap toMap() {
            WritableMap contact = Arguments.createMap();
            contact.putString("recordID", this.contactId);
            contact.putString("givenName", TextUtils.isEmpty(this.givenName) ? this.displayName : this.givenName);
            contact.putString("middleName", this.middleName);
            contact.putString("familyName", this.familyName);
            contact.putString("prefix", this.prefix);
            contact.putString("suffix", this.suffix);
            contact.putString("company", this.company);
            contact.putString("jobTitle", this.jobTitle);
            contact.putString("department", this.department);
            contact.putBoolean("hasThumbnail", this.hasPhoto);
            contact.putString("thumbnailPath", this.photoUri == null ? "" : this.photoUri);
            WritableArray phoneNumbers = Arguments.createArray();
            for (Item item : this.phones) {
                WritableMap map = Arguments.createMap();
                map.putString("number", item.value);
                map.putString("label", item.label);
                phoneNumbers.pushMap(map);
            }
            contact.putArray("phoneNumbers", phoneNumbers);
            WritableArray emailAddresses = Arguments.createArray();
            for (Item item2 : this.emails) {
                WritableMap map2 = Arguments.createMap();
                map2.putString("email", item2.value);
                map2.putString("label", item2.label);
                emailAddresses.pushMap(map2);
            }
            contact.putArray("emailAddresses", emailAddresses);
            WritableArray postalAddresses2 = Arguments.createArray();
            for (PostalAddressItem item3 : this.postalAddresses) {
                postalAddresses2.pushMap(item3.map);
            }
            contact.putArray("postalAddresses", postalAddresses2);
            return contact;
        }
    }

    public ContactsProvider(ContentResolver contentResolver2) {
        this.contentResolver = contentResolver2;
    }

    public WritableArray getContacts() {
        Cursor cursor = this.contentResolver.query(Uri.withAppendedPath(Profile.CONTENT_URI, "data"), (String[]) JUST_ME_PROJECTION.toArray(new String[JUST_ME_PROJECTION.size()]), null, null, null);
        try {
            Map<String, C4714Contact> justMe = loadContactsFrom(cursor);
            Cursor cursor2 = this.contentResolver.query(Data.CONTENT_URI, (String[]) FULL_PROJECTION.toArray(new String[FULL_PROJECTION.size()]), "mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR mimetype=?", new String[]{"vnd.android.cursor.item/email_v2", "vnd.android.cursor.item/phone_v2", "vnd.android.cursor.item/name", "vnd.android.cursor.item/organization", "vnd.android.cursor.item/postal-address_v2"}, null);
            try {
                Map<String, C4714Contact> everyoneElse = loadContactsFrom(cursor2);
                WritableArray contacts = Arguments.createArray();
                for (C4714Contact contact : justMe.values()) {
                    contacts.pushMap(contact.toMap());
                }
                for (C4714Contact contact2 : everyoneElse.values()) {
                    contacts.pushMap(contact2.toMap());
                }
                return contacts;
            } finally {
                if (cursor2 != null) {
                    cursor2.close();
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private Map<String, C4714Contact> loadContactsFrom(Cursor cursor) {
        String contactId;
        String label;
        String label2;
        Map<String, C4714Contact> map = new LinkedHashMap<>();
        while (cursor != null && cursor.moveToNext()) {
            int columnIndex = cursor.getColumnIndex("contact_id");
            if (columnIndex != -1) {
                contactId = cursor.getString(columnIndex);
            } else {
                contactId = String.valueOf(-1);
            }
            if (!map.containsKey(contactId)) {
                map.put(contactId, new C4714Contact(contactId));
            }
            C4714Contact contact = (C4714Contact) map.get(contactId);
            String mimeType = cursor.getString(cursor.getColumnIndex("mimetype"));
            String name = cursor.getString(cursor.getColumnIndex("display_name"));
            if (!TextUtils.isEmpty(name) && TextUtils.isEmpty(contact.displayName)) {
                contact.displayName = name;
            }
            if (TextUtils.isEmpty(contact.photoUri)) {
                String rawPhotoURI = cursor.getString(cursor.getColumnIndex("photo_uri"));
                if (!TextUtils.isEmpty(rawPhotoURI)) {
                    contact.photoUri = rawPhotoURI;
                    contact.hasPhoto = true;
                }
            }
            if (mimeType.equals("vnd.android.cursor.item/name")) {
                contact.givenName = cursor.getString(cursor.getColumnIndex("data2"));
                contact.middleName = cursor.getString(cursor.getColumnIndex("data5"));
                contact.familyName = cursor.getString(cursor.getColumnIndex("data3"));
                contact.prefix = cursor.getString(cursor.getColumnIndex("data4"));
                contact.suffix = cursor.getString(cursor.getColumnIndex("data6"));
            } else if (mimeType.equals("vnd.android.cursor.item/phone_v2")) {
                String phoneNumber = cursor.getString(cursor.getColumnIndex("data1"));
                int type = cursor.getInt(cursor.getColumnIndex("data2"));
                if (!TextUtils.isEmpty(phoneNumber)) {
                    switch (type) {
                        case 1:
                            label2 = "home";
                            break;
                        case 2:
                            label2 = "mobile";
                            break;
                        case 3:
                            label2 = "work";
                            break;
                        default:
                            label2 = "other";
                            break;
                    }
                    contact.phones.add(new Item(label2, phoneNumber));
                }
            } else if (mimeType.equals("vnd.android.cursor.item/email_v2")) {
                String email = cursor.getString(cursor.getColumnIndex("data1"));
                int type2 = cursor.getInt(cursor.getColumnIndex("data2"));
                if (!TextUtils.isEmpty(email)) {
                    switch (type2) {
                        case 0:
                            if (cursor.getString(cursor.getColumnIndex("data3")) == null) {
                                label = "";
                                break;
                            } else {
                                label = cursor.getString(cursor.getColumnIndex("data3")).toLowerCase();
                                break;
                            }
                        case 1:
                            label = "home";
                            break;
                        case 2:
                            label = "work";
                            break;
                        case 4:
                            label = "mobile";
                            break;
                        default:
                            label = "other";
                            break;
                    }
                    contact.emails.add(new Item(label, email));
                }
            } else if (mimeType.equals("vnd.android.cursor.item/organization")) {
                contact.company = cursor.getString(cursor.getColumnIndex("data1"));
                contact.jobTitle = cursor.getString(cursor.getColumnIndex("data4"));
                contact.department = cursor.getString(cursor.getColumnIndex("data5"));
            } else if (mimeType.equals("vnd.android.cursor.item/postal-address_v2")) {
                contact.postalAddresses.add(new PostalAddressItem(cursor));
            }
        }
        return map;
    }

    /* JADX INFO: finally extract failed */
    public String getPhotoUriFromContactId(String contactId) {
        Cursor cursor = this.contentResolver.query(Data.CONTENT_URI, (String[]) PHOTO_PROJECTION.toArray(new String[PHOTO_PROJECTION.size()]), "contact_id = ?", new String[]{contactId}, null);
        if (cursor != null) {
            try {
                if (cursor.moveToNext()) {
                    String rawPhotoURI = cursor.getString(cursor.getColumnIndex("photo_uri"));
                    if (!TextUtils.isEmpty(rawPhotoURI)) {
                        if (cursor == null) {
                            return rawPhotoURI;
                        }
                        cursor.close();
                        return rawPhotoURI;
                    }
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return null;
    }
}
